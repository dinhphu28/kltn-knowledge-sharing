package com.ndp.knowsharing.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndp.knowsharing.Entities.Category;

public interface CategoryRepo extends JpaRepository<Category, String> {
    
}
