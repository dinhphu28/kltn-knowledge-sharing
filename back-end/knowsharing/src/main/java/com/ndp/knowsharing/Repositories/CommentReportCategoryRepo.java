package com.ndp.knowsharing.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndp.knowsharing.Entities.CommentReportCategory;

public interface CommentReportCategoryRepo extends JpaRepository<CommentReportCategory, String> {
    
}
