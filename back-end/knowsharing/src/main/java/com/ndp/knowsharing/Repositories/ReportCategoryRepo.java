package com.ndp.knowsharing.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndp.knowsharing.Entities.ReportCategory;

public interface ReportCategoryRepo extends JpaRepository<ReportCategory, String> {
    
}
