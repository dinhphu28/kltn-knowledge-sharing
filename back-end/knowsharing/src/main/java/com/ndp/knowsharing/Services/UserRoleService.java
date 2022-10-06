package com.ndp.knowsharing.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndp.knowsharing.Repositories.UserRoleRepo;

@Service
public class UserRoleService {
    @Autowired
    private UserRoleRepo repo;

    
}
