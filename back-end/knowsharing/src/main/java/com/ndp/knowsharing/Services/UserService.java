package com.ndp.knowsharing.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndp.knowsharing.Repositories.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;

    
}