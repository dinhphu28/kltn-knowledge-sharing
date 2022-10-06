package com.ndp.knowsharing.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndp.knowsharing.Repositories.UserReportRepo;

@Service
public class UserReportService {
    @Autowired
    private UserReportRepo repo;

    
}
