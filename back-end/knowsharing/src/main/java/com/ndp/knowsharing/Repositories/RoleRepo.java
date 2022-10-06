package com.ndp.knowsharing.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndp.knowsharing.Entities.Role;

public interface RoleRepo extends JpaRepository<Role, String> {
    
}
