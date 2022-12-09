package com.ndp.knowsharing.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ndp.knowsharing.Entities.User;
import com.ndp.knowsharing.Entities.UserRole;
import com.ndp.knowsharing.Models.Auth.CredentialReturn;
import com.ndp.knowsharing.Models.Auth.ForgetPasswordUpdateModel;
import com.ndp.knowsharing.Models.Auth.PasswordChangeModel;
import com.ndp.knowsharing.Models.Auth.UserModel;
import com.ndp.knowsharing.Services.UserRoleService;
import com.ndp.knowsharing.Services.UserService;
import com.ndp.knowsharing.Utils.Auth.PasswordAuthUtil;
import com.ndp.knowsharing.Utils.Auth.JWT.jwtSecurity;
import com.ndp.knowsharing.Utils.Auth.JWT.myJWT;
import com.ndp.knowsharing.Utils.Mail.SendMailUtil;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private SendMailUtil sendMailUtil;

    // Login
    @PutMapping(
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> login(@RequestBody UserModel user) {
        ResponseEntity<Object> entity;

        if(user.getUsername() == null || user.getPassword() == null) {
            entity = new ResponseEntity<>("{ \"Notice\": \"Username and password not be empty\" }", HttpStatus.BAD_REQUEST);
        } else {
            User tmpUser = userService.retrieveById(user.getUsername());

            if(tmpUser == null) {
                entity = new ResponseEntity<>("{ \"Notice\": \"Invalid username or password\" }", HttpStatus.BAD_REQUEST);  // invalid username
            } else {
                if(tmpUser.getActive() == 1) {
                    PasswordAuthUtil passwordAuthUtil = new PasswordAuthUtil();

                    if(passwordAuthUtil.verifyPassword(user.getPassword(), tmpUser.getPassword())) {
                        // create token here

                        myJWT jwt = new jwtSecurity();

                        String token = jwt.GenerateToken(user.getUsername());

                        if(token == "") {
                            entity = new ResponseEntity<>("{ \"Notice\": \"Invalid username or password\" }", HttpStatus.BAD_REQUEST);  // failed to create token
                        } else {
                            UserRole userRole = userRoleService.retrieveByUserId(tmpUser.getId());

                            String roleStr = "";

                            // if(userRole.getRoleId() == "admin") {
                            //     roleStr = "admin";
                            // } else if(userRole.getRoleId() == "mod") {
                            //     roleStr = "mod";
                            // } else if(userRole.getRoleId() == "norm") {
                            //     roleStr = "norm";
                            // }

                            roleStr = userRole.getRoleId();

                            CredentialReturn credentialReturn = new CredentialReturn(user.getUsername(), roleStr, token);

                            entity = new ResponseEntity<>(credentialReturn, HttpStatus.OK);
                        }
                    } else {
                        entity = new ResponseEntity<>("{ \"Notice\": \"Invalid username or password\" }", HttpStatus.BAD_REQUEST);  // invalid password
                    }
                } else {
                    entity = new ResponseEntity<>("{ \"Notice\": \"User was blocked\" }", HttpStatus.LOCKED);
                }
            }
        }

        return entity;
    }

    // Update password

    @PatchMapping(
        value = "/{username}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> updatePassword(@PathVariable("username") String username, @RequestBody PasswordChangeModel passwordChangeModel) {
        ResponseEntity<Object> entity;

        if(passwordChangeModel.getOldValue() == null || passwordChangeModel.getNewValue() == null) {
            entity = new ResponseEntity<>("{ \"Notice\": \"Not be empty\" }", HttpStatus.BAD_REQUEST);
        } else {
            User tmpUser = userService.retrieveById(username);

            if(tmpUser == null) {
                entity = new ResponseEntity<>("{ \"Notice\": \"Invalid username or password\" }", HttpStatus.BAD_REQUEST);  // invalid username
            } else {
                PasswordAuthUtil passwordAuthUtil = new PasswordAuthUtil();

                if(passwordAuthUtil.verifyPassword(passwordChangeModel.getOldValue(), tmpUser.getPassword())) {

                    String encryptedPassword = passwordAuthUtil.storePassword(passwordChangeModel.getNewValue());
                    User tmpToUpdate = tmpUser;
                    tmpToUpdate.setPassword(encryptedPassword);

                    User tmpUserChangedPwd = userService.updateOne(tmpToUpdate);

                    if(tmpUserChangedPwd == null) {
                        entity = new ResponseEntity<>("{ \"Notice\": \"Invalid username or password\" }", HttpStatus.BAD_REQUEST);  // failed to update
                    } else {
                        entity = new ResponseEntity<>("{ \"username\": \"" + username + "\", \"password\": \"" + passwordChangeModel.getNewValue() + "\" }", HttpStatus.OK);
                    }
                } else {
                    entity = new ResponseEntity<>("{ \"Notice\": \"Invalid username or password\" }", HttpStatus.BAD_REQUEST);  // invalid password
                }
            }
        }

        return entity;
    }

    // Forget password
    @PatchMapping(
        value = "/forget-password/{username}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> forgetPassword(@PathVariable("username") String username, @RequestBody ForgetPasswordUpdateModel forgetPasswordUpdateModel) {
        ResponseEntity<Object> entity;

        myJWT jwt = new jwtSecurity();

        User tmpUserCk = userService.retrieveById(username);

        if(tmpUserCk == null) {
            entity = new ResponseEntity<>("{ \"Notice\": \"User not found\" }", HttpStatus.NOT_FOUND);
        } else {
            if(!jwt.VerifyToken(forgetPasswordUpdateModel.getToken(), username)) {
                entity = new ResponseEntity<>("{ \"Notice\": \"Token is not verified!\" }", HttpStatus.CONFLICT);
            } else {
                PasswordAuthUtil passwordAuthUtil = new PasswordAuthUtil();

                String newPasswordEncrypted = passwordAuthUtil.storePassword(forgetPasswordUpdateModel.getPassword());

                User tmpToUpdate = tmpUserCk;

                tmpToUpdate.setPassword(newPasswordEncrypted);

                User tmpSaved = userService.updateOne(tmpToUpdate);

                if(tmpSaved == null) {
                    entity = new ResponseEntity<>("{ \"Notice\": \"Can't change password!\" }", HttpStatus.INTERNAL_SERVER_ERROR);
                } else {
                    entity = new ResponseEntity<>("{ \"Notice\": \"Password changed!\" }", HttpStatus.OK);
                }
            }
        }
        
        return entity;
    }

    /*
     * Send request: forgot password and send email to user (click to email's content to reset password)
     */
    @GetMapping(
        value = "/forget-password/{username}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> getTokenForgetPassword(@PathVariable("username") String username) {
        ResponseEntity<Object> entity;

        myJWT jwt = new jwtSecurity();

        User user = userService.retrieveById(username);

        if(user == null) {
            entity = new ResponseEntity<>("{ \"Notice\": \"Email of user does not exist or can't receive inbox!\" }", HttpStatus.BAD_REQUEST);
        } else {
            String token = jwt.GenerateForgetPasswordToken(username);

            if(token.equals("")) {
                entity = new ResponseEntity<>("{ \"Notice\": \"Failed to generate token\" }", HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                try {
                    String htmlMsgContent = "<h3>Reset your password</h3>" +
                        "<p>Your token here:</p>" +
                        "<h5>" + token + "</h5>" +
                        "<p>This token will expired after 5 minutes</p>";
    
                    sendMailUtil.sendHtmlEmail(htmlMsgContent, user.getEmail());
    
                    entity = new ResponseEntity<>("{ \"Notice\": \"Check your email!\" }", HttpStatus.OK);
                } catch (Exception e) {
                    // TODO: handle exception
                    entity = new ResponseEntity<>("{ \"Notice\": \"Email of user does not exist or can't receive inbox!\" }", HttpStatus.BAD_REQUEST);
                }
            }
        }

        return entity;
    }

    // Register
    @PostMapping(
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> register(@RequestBody UserModel user) {
        ResponseEntity<Object> entity;

        if(user.getUsername() == null || user.getPassword() == null) {
            entity = new ResponseEntity<>("{ \"Notice\": \"Username and password not be empty\" }", HttpStatus.BAD_REQUEST);
        } else {
            PasswordAuthUtil passwordAuthUtil = new PasswordAuthUtil();

            String encryptedPassword = passwordAuthUtil.storePassword(user.getPassword());
            User tmpToCreate = new User(user.getUsername(), user.getUsername(), encryptedPassword, "", "", "", "", 0, 1);
            User tmpSaved = userService.createOne(tmpToCreate);

            if(tmpSaved == null) {
                entity = new ResponseEntity<>("{ \"Notice\": \"Username is existed\" }", HttpStatus.BAD_REQUEST);
            } else {
                UserRole tmpUserRoleToSave = new UserRole("norm", user.getUsername());
                UserRole tmpUserRoleSaved = userRoleService.createOne(tmpUserRoleToSave);

                if(tmpUserRoleSaved == null) {
                    entity = new ResponseEntity<>("{ \"Notice\": \"Failed to create user\" }", HttpStatus.BAD_REQUEST);
                } else {
                    entity = new ResponseEntity<>("{ \"username\": \"" + user.getUsername() + "\", \"password\": \"" + user.getPassword() + "\" }", HttpStatus.OK);
                }
            }
        }

        return entity;
    }
}
