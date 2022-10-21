package com.ndp.knowsharing.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndp.knowsharing.Entities.Role;
import com.ndp.knowsharing.Repositories.RoleRepo;

@Service
public class RoleService {
    @Autowired
    private RoleRepo repo;

    public List<Role> retrieveAll() {
        return repo.findAll();
    }

    public Role retrieveById(String id) {
        Role sth = null;

        try {
            sth = repo.findById(id).get();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return sth;
    }

    public Role createOne(Role role) {
        Role tmp = null;

        role.setId(null);

        try {
            tmp = repo.save(role);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return tmp;
    }

    public Role updateOne(Role role) {
        Role tmp = null;

        try {
            repo.findById(role.getId()).get();

            tmp = repo.save(role);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return tmp;
    }

    public Boolean deleteOne(String id) {
        Boolean isSuccess = false;

        try {
            repo.findById(id).get();

            repo.deleteById(id);

            isSuccess = true;
        } catch (Exception e) {
            // TODO: handle exception
        }

        return isSuccess;
    }
}
