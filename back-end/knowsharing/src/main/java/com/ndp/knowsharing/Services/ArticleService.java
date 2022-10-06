package com.ndp.knowsharing.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndp.knowsharing.Repositories.ArticleRepo;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepo repo;

    
}
