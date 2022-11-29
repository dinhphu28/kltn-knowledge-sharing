package com.ndp.knowsharing.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndp.knowsharing.Repositories.ArticleReportRepo;

@Service
public class ArticleReportCategoryService {
    @Autowired
    private ArticleReportRepo repo;

    
}
