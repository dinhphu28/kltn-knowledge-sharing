package com.ndp.knowsharing.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndp.knowsharing.Repositories.ReportCategoryRepo;

@Service
public class ReportCategoryService {
    @Autowired
    private ReportCategoryRepo repo;

    
}
