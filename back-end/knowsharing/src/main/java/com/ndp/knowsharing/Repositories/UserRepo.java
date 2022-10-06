package com.ndp.knowsharing.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndp.knowsharing.Entities.User;

public interface UserRepo extends JpaRepository<User, String> {
    
}
