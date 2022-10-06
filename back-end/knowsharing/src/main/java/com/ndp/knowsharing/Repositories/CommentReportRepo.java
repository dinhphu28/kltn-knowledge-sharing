package com.ndp.knowsharing.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndp.knowsharing.Entities.CommentReport;

public interface CommentReportRepo extends JpaRepository<CommentReport, String> {
    
}
