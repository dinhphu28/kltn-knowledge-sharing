package com.ndp.knowsharing.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndp.knowsharing.Repositories.UserReportCategoryRepo;

@Service
public class UserReportCategoryService {
    @Autowired
    private UserReportCategoryRepo repo;

    
}
