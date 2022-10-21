package com.ndp.knowsharing.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ndp.knowsharing.Entities.UserRole;
import com.ndp.knowsharing.Entities.IdClasses.UserRoleId;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, UserRoleId> {
    List<UserRole> findByUserId(String userId);

    List<UserRole> findByRoleId(String roleId);
}
