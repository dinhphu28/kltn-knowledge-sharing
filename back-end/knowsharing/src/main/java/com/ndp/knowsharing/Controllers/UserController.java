package com.ndp.knowsharing.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ndp.knowsharing.Entities.User;
import com.ndp.knowsharing.Entities.UserRole;
import com.ndp.knowsharing.Models.Auth.UserModel;
import com.ndp.knowsharing.Models.UsersMan.ActiveStateChangeModel;
import com.ndp.knowsharing.Models.UsersMan.PasswordForceChangeModel;
import com.ndp.knowsharing.Models.UsersMan.UsersManModelReturn;
import com.ndp.knowsharing.Services.UserRoleService;
import com.ndp.knowsharing.Services.UserService;
import com.ndp.knowsharing.Utils.Auth.PasswordAuthUtil;
import com.ndp.knowsharing.Utils.Auth.JWT.jwtSecurity;
import com.ndp.knowsharing.Utils.Auth.JWT.myJWT;
import com.ndp.knowsharing.Utils.Auth.TokenProcessing.AuthHeaderProcessing;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private AuthHeaderProcessing authHeaderProcessing;

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> retrieveByRoleId(@RequestParam(value = "role", required = true) String role, @RequestParam(value = "active", required = true) Boolean active, @RequestHeader(value = "Authorization", required = false) String authorization) {
        ResponseEntity<Object> entity;

        // String token = authHeaderProcessing.getTokenFromAuthHeader(authorization);

        // myJWT jwt = new jwtSecurity();

        // Boolean authorized = jwt.VerifyToken(token, "admin");
        Boolean authorized = true;

        if(authorized) {
            List<UsersManModelReturn> usersManModelReturns = new ArrayList<UsersManModelReturn>();

            List<UserRole> userRoles = userRoleService.retrieveByRoleId(role);

            for (UserRole item : userRoles) {
                String tmpUsername = item.getUserId();

                User tmpUser = userService.retrieveById(tmpUsername);

                Integer tmpActive = tmpUser.getActive();

                if(tmpActive == (active ? 1 : 0)) {
                    User tmpUserInfo = userService.retrieveById(tmpUsername);
    
                    String tmpAvatar = "";
    
                    if(tmpUserInfo != null) {
                        tmpAvatar = tmpUserInfo.getAvatar();
                    }
                    UsersManModelReturn tmpUsersManModelReturn = new UsersManModelReturn(tmpUsername, tmpActive == 1, tmpAvatar);
    
                    usersManModelReturns.add(tmpUsersManModelReturn);
                }
            }

            entity = new ResponseEntity<>(usersManModelReturns, HttpStatus.OK);

        } else {
            entity = new ResponseEntity<>("{ \"Notice\": \"Unauthorized\" }", HttpStatus.UNAUTHORIZED);
        }

        return entity;
    }

    // Change password (by admin)
    @PutMapping(
        value = "/password",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> forceChangePassword(@RequestBody PasswordForceChangeModel passwordForceChangeModel, @RequestHeader(value = "Authorization", required = false) String authorization) {
        ResponseEntity<Object> entity;

        // String token = authHeaderProcessing.getTokenFromAuthHeader(authorization);

        // myJWT jwt = new jwtSecurity();

        // Boolean authorized = jwt.VerifyToken(token, "admin");
        
        Boolean authorized = true;

        if(authorized) {
            if(passwordForceChangeModel.getUsername() == null || passwordForceChangeModel.getNewPassword() == null) {
                entity = new ResponseEntity<>("{ \"Notice\": \"Username and password not be empty\" }", HttpStatus.BAD_REQUEST);
            } else {
                User tmpUser = userService.retrieveById(passwordForceChangeModel.getUsername());
    
                PasswordAuthUtil passwordAuthUtil = new PasswordAuthUtil();
    
                String encryptedPassword = passwordAuthUtil.storePassword(passwordForceChangeModel.getNewPassword());
    
                tmpUser.setPassword(encryptedPassword);
                
                User tmpToSave = userService.updateOne(tmpUser);
    
                if(tmpToSave == null) {
                    entity = new ResponseEntity<>("{ \"Notice\": \"Failed to change password\" }", HttpStatus.BAD_REQUEST);
                } else {
                    entity = new ResponseEntity<>("{ \"username\": \"" + passwordForceChangeModel.getUsername() + "\", \"password\": \"" + passwordForceChangeModel.getNewPassword() + "\" }", HttpStatus.OK);
                }
            }
        } else {
            entity = new ResponseEntity<>("{ \"Notice\": \"Unauthorized\" }", HttpStatus.UNAUTHORIZED);
        }

        return entity;
    }

    /*
     * Change Active state: Active/Inactive
     */
    @PutMapping(
        value = "/active-state",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> changeActiveState(@RequestBody ActiveStateChangeModel activeStateChangeModel, @RequestHeader(value = "Authorization", required = false) String authorization) {
        ResponseEntity<Object> entity;

        // String token = authHeaderProcessing.getTokenFromAuthHeader(authorization);

        // myJWT jwt = new jwtSecurity();

        // Boolean authorized = jwt.VerifyToken(token, "admin");
        Boolean authorized = true;

        if(authorized) {
            if(activeStateChangeModel.getUsername() == null || activeStateChangeModel.getActive() == null) {
                entity = new ResponseEntity<>("{ \"Notice\": \"Not be empty\" }", HttpStatus.BAD_REQUEST);
            } else {
                User tmpUser = userService.retrieveById(activeStateChangeModel.getUsername());
    
                tmpUser.setActive(activeStateChangeModel.getActive() ? 1 : 0);
    
                User tmpToSave = userService.updateOne(tmpUser);
    
                if(tmpToSave == null) {
                    entity = new ResponseEntity<>("{ \"Notice\": \"Failed to change password\" }", HttpStatus.BAD_REQUEST);
                } else {
                    entity = new ResponseEntity<>("{ \"username\": \"" + activeStateChangeModel.getUsername() + "\", \"active\": \"" + activeStateChangeModel.getActive() + "\" }", HttpStatus.OK);
                }
            }
        } else {
            entity = new ResponseEntity<>("{ \"Notice\": \"Unauthorized\" }", HttpStatus.UNAUTHORIZED);
        }

        return entity;
    }

    /*
     * Create new mod user
     */
    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> register(@RequestBody UserModel user,@RequestHeader(value = "Authorization", required = false) String authorization) {
        ResponseEntity<Object> entity;

        // String token = authHeaderProcessing.getTokenFromAuthHeader(authorization);

        // myJWT jwt = new jwtSecurity();

        // Boolean authorized = jwt.VerifyToken(token, "admin");
        Boolean authorized = true;
        
        if(authorized) {
            if(user.getUsername() == null || user.getPassword() == null) {
                entity = new ResponseEntity<>("{ \"Notice\": \"Username and password not be empty\" }", HttpStatus.BAD_REQUEST);
            } else {
                PasswordAuthUtil passwordAuthUtil = new PasswordAuthUtil();
    
                String encryptedPassword = passwordAuthUtil.storePassword(user.getPassword());
                // User tmpToCreate = new User(user.getUsername(), encryptedPassword, true);
                User tmpToCreate = new User(user.getUsername(), user.getUsername(), encryptedPassword, "", "", "", "", 0, 1);
                User tmpSaved = userService.createOne(tmpToCreate);
    
                if(tmpSaved == null) {
                    entity = new ResponseEntity<>("{ \"Notice\": \"Username is existed\" }", HttpStatus.BAD_REQUEST);
                } else {
                    // UserRole tmpUserRoleToSave = new UserRole(0, user.getUsername(), 2);
                    UserRole tmpUserRoleToSave = new UserRole("mod", user.getUsername());
                    UserRole tmpUserRoleSaved = userRoleService.createOne(tmpUserRoleToSave);
    
                    if(tmpUserRoleSaved == null) {
                        entity = new ResponseEntity<>("{ \"Notice\": \"Failed to create user\" }", HttpStatus.BAD_REQUEST);
                    } else {
                        entity = new ResponseEntity<>("{ \"username\": \"" + user.getUsername() + "\", \"password\": \"" + user.getPassword() + "\" }", HttpStatus.OK);
                    }
                }
            }
        } else {
            entity = new ResponseEntity<>("{ \"Notice\": \"Unauthorized\" }", HttpStatus.UNAUTHORIZED);
        }

        return entity;
    }
}
