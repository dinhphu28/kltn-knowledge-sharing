package com.ndp.knowsharing.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ndp.knowsharing.Entities.File;

@Repository
public interface FileRepo extends JpaRepository<File, String> {
    
}
