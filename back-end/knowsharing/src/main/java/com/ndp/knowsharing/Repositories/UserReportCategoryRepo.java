package com.ndp.knowsharing.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndp.knowsharing.Entities.UserReportCategory;

public interface UserReportCategoryRepo extends JpaRepository<UserReportCategory, String> {
    
}
