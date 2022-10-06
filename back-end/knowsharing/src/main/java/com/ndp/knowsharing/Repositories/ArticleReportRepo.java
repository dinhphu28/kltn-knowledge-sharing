package com.ndp.knowsharing.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndp.knowsharing.Entities.ArticleReport;

public interface ArticleReportRepo extends JpaRepository<ArticleReport, String> {
    
}
