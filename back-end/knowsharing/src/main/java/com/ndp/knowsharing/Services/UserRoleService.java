package com.ndp.knowsharing.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndp.knowsharing.Entities.UserRole;
import com.ndp.knowsharing.Entities.IdClasses.UserRoleId;
import com.ndp.knowsharing.Repositories.UserRoleRepo;

@Service
public class UserRoleService {
    @Autowired
    private UserRoleRepo repo;

    public List<UserRole> retrieveAll() {
        return repo.findAll();
    }

    public UserRole retrieveById(UserRoleId userRoleId) {
        UserRole sth = null;

        try {
            sth = repo.findById(userRoleId).get();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return sth;
    }

    public UserRole retrieveByUserId(String userId) {
        UserRole sth = null;

        try {
            sth = repo.findByUserId(userId);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return sth;
    }

    public List<UserRole> retrieveByRoleId(String roleId) {
        return repo.findByRoleId(roleId);
    }

    public UserRole createOne(UserRole userRole) {
        UserRole tmp = null;

        try {
            repo.findById(new UserRoleId(userRole.getUserId(), userRole.getRoleId())).get();

        } catch (Exception e) {
            // TODO: handle exception
            tmp = repo.save(userRole);
        }

        return tmp;
    }

    public Boolean deleteOne(UserRoleId userRoleId) {
        Boolean isSuccess = false;

        try {
            repo.deleteById(userRoleId);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return isSuccess;
    }
}
