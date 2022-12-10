package com.ndp.knowsharing.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ndp.knowsharing.Entities.User;
import com.ndp.knowsharing.Models.Profile.ProfileReturnModel;
import com.ndp.knowsharing.Models.Profile.ProfileUpdateModel;
import com.ndp.knowsharing.Services.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/profiles")
public class UserInfoController {
    @Autowired
    private UserService userService;

    /*
     * Retrieve all profiles
     */
    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> retrieveAll() {
        ResponseEntity<Object> entity;

        List<ProfileReturnModel> profiles = new ArrayList<ProfileReturnModel>();

        List<User> users = userService.retrieveAll();

        for (User user : users) {
            ProfileReturnModel profileReturnModel = new ProfileReturnModel(user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAvatar(), user.getVerified());

            profiles.add(profileReturnModel);
        }

        entity = new ResponseEntity<>(profiles, HttpStatus.OK);

        return entity;
    }

    /*
     * Retrieve Profile by Username
     */
    @GetMapping(
        value = "/{username}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> retrieveById(@PathVariable("username") String username) {
        ResponseEntity<Object> entity;

        User user = userService.retrieveById(username);
        
        if(user != null) {
            ProfileReturnModel profile = new ProfileReturnModel(username, user.getFirstName(), user.getLastName(), user.getEmail(), user.getAvatar(), user.getVerified());

            entity = new ResponseEntity<>(profile, HttpStatus.OK);
        } else {
            entity = new ResponseEntity<>("{ \"Notice\": \"User not found\" }", HttpStatus.NOT_FOUND);
        }
        return entity;
    }

    /*
     * Update Profiles
     */
    @PutMapping(
        value = "/{username}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> updateProfile(@PathVariable("username") String username, @RequestBody ProfileUpdateModel profileUpdateModel) {
        ResponseEntity<Object> entity;

        if(profileUpdateModel.getAvatar() == null) {
            entity = new ResponseEntity<>("{ \"Notice\": \"Not null\" }", HttpStatus.BAD_REQUEST);
        } else {

            // User tmpToSave = profileUpdateModel.toUserInfoEntity(username, true);

            User tmpFinded = userService.retrieveById(username);

            if(tmpFinded != null) {

                User tmpToSave = new User(tmpFinded.getId(), tmpFinded.getUsername(), tmpFinded.getPassword(), tmpFinded.getFirstName(), tmpFinded.getLastName(), profileUpdateModel.getEmail(), profileUpdateModel.getAvatar(), tmpFinded.getVerified(), tmpFinded.getActive());

                if(tmpFinded.getEmail() != null && profileUpdateModel != null) {
                    if(!tmpFinded.getEmail().equalsIgnoreCase(profileUpdateModel.getEmail())) {
                        tmpToSave.setVerified(0);
                    }
                }
                
                User tmpSaved = userService.updateOne(tmpToSave);

                if(tmpSaved != null) {
                    entity = new ResponseEntity<>(profileUpdateModel, HttpStatus.OK);
                } else {
                    entity = new ResponseEntity<>("{ \"Notice\": \"Failed\" }", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                entity = new ResponseEntity<>("{ \"Notice\": \"Failed\" }", HttpStatus.BAD_REQUEST);
            }
        }

        return entity;
    }
}
