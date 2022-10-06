package com.ndp.knowsharing.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndp.knowsharing.Repositories.CategoryRepo;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo repo;

    
}
