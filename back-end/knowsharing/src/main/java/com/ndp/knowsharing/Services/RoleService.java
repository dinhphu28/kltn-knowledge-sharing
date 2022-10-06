package com.ndp.knowsharing.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndp.knowsharing.Repositories.RoleRepo;

@Service
public class RoleService {
    @Autowired
    private RoleRepo repo;

    
}
