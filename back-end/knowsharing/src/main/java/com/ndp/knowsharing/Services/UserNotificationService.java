package com.ndp.knowsharing.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndp.knowsharing.Entities.UserNotification;
import com.ndp.knowsharing.Repositories.UserNotificationRepo;

@Service
public class UserNotificationService {
    @Autowired
    private UserNotificationRepo repo;

    public List<UserNotification> retrieveAll() {
        return repo.findAll();
    }

    public UserNotification retrieveById(String id) {
        UserNotification sth = null;

        try {
            sth = repo.findById(id).get();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return sth;
    }

    public List<UserNotification> retrieveByForUserAndHidden(String username, Integer hidden) {
        List<UserNotification> sth = new ArrayList<UserNotification>();

        try {
            sth = repo.findByForUserAndHidden(username, hidden);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return sth;
    }

    public UserNotification createOne(UserNotification userNotification) {
        UserNotification tmp = null;

        userNotification.setId(null);

        try {
            tmp = repo.save(userNotification);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return tmp;
    }

    public UserNotification updateOne(UserNotification userNotification) {
        UserNotification tmpComment = null;

        try {
            repo.findById(userNotification.getId()).get();

            tmpComment = repo.save(userNotification);
        } catch (Exception e) {
            //TODO: handle exception
        }

        return tmpComment;
    }
}
