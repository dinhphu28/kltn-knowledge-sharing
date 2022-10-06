package com.ndp.knowsharing.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndp.knowsharing.Entities.ArticleReportCategory;

public interface ArticleReportCategoryRepo extends JpaRepository<ArticleReportCategory, String> {
    
}
