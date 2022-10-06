package com.ndp.knowsharing.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndp.knowsharing.Repositories.UserVoteStateRepo;

@Service
public class UserVoteStateService {
    @Autowired
    private UserVoteStateRepo repo;

    
}
