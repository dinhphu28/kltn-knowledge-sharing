package com.ndp.knowsharing.Controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ndp.knowsharing.Entities.UserNotification;
import com.ndp.knowsharing.Models.UserNotification.UserNotificationCreateModel;
import com.ndp.knowsharing.Services.UserNotificationService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/user-notification")
public class UserNotificationController {
    @Autowired
    private UserNotificationService userNotificationService;

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> retrieveAll(@RequestParam(value = "username", required = true) String username) {
        ResponseEntity<Object> entity;

        List<UserNotification> userNotifications = userNotificationService.retrieveByForUserAndHidden(username, 0);

        entity = new ResponseEntity<>(userNotifications, HttpStatus.OK);

        return entity;
    }

    @PostMapping(
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> createOne(@RequestBody UserNotificationCreateModel userNotificationCreateModel) {
        ResponseEntity<Object> entity;

        LocalDateTime now = LocalDateTime.now();

        UserNotification tmpToSave = new UserNotification(null, now, "", "", userNotificationCreateModel.getForUser(), userNotificationCreateModel.getTitle(), userNotificationCreateModel.getContent(), 0);

        UserNotification tmpSaved = userNotificationService.createOne(tmpToSave);

        if(tmpSaved != null) {
            entity = new ResponseEntity<>(tmpSaved, HttpStatus.CREATED);
        } else {
            entity = new ResponseEntity<>("{ \"Notice\": \"Failed\" }", HttpStatus.BAD_REQUEST);
        }

        return entity;
    }
}
