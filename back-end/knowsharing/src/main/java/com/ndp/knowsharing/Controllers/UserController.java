package com.ndp.knowsharing.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ndp.knowsharing.Entities.User;
import com.ndp.knowsharing.Entities.UserRole;
import com.ndp.knowsharing.Models.UsersMan.UsersManModelReturn;
import com.ndp.knowsharing.Services.UserRoleService;
import com.ndp.knowsharing.Services.UserService;
// import com.ndp.knowsharing.Utils.Auth.JWT.jwtSecurity;
// import com.ndp.knowsharing.Utils.Auth.JWT.myJWT;
// import com.ndp.knowsharing.Utils.Auth.TokenProcessing.AuthHeaderProcessing;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    // @Autowired
    // private AuthHeaderProcessing authHeaderProcessing;

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
}
