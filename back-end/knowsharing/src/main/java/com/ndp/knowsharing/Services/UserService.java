package com.ndp.knowsharing.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndp.knowsharing.Entities.User;
import com.ndp.knowsharing.Repositories.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;

    public User retrieveById(String userId) {
        User sth = null;

        try {
            sth = repo.findById(userId).get();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return sth;
    }

    public List<User> retrieveAll() {
        return repo.findAll();
    }

    public User createOne(User user) {
        User tmp = null;

        try {
            repo.findById(user.getId()).get();
        } catch (Exception e) {
            // TODO: handle exception

            tmp = repo.save(user);
        }

        return tmp;
    }

    public User updateOne(User user) {
        User tmp = null;

        try {
            repo.findById(user.getId()).get();

            tmp = repo.save(user);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return tmp;
    }
}
