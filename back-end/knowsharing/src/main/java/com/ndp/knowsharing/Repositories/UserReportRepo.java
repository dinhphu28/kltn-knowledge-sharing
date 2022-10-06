package com.ndp.knowsharing.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndp.knowsharing.Entities.UserReport;

public interface UserReportRepo extends JpaRepository<UserReport, String> {
    
}
