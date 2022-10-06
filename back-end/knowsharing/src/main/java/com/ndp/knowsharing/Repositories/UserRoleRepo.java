package com.ndp.knowsharing.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndp.knowsharing.Entities.UserRole;
import com.ndp.knowsharing.Entities.IdClasses.UserRoleId;

public interface UserRoleRepo extends JpaRepository<UserRole, UserRoleId> {
    
}
