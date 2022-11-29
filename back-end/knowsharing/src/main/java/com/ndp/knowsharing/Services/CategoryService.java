package com.ndp.knowsharing.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndp.knowsharing.Entities.Category;
import com.ndp.knowsharing.Repositories.CategoryRepo;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo repo;

    public List<Category> retrieveAll() {
        return repo.findAll();
    }

    public Category retrieveById(String id) {
        Category sth = null;

        try {
            sth = repo.findById(id).get();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return sth;
    }

    public Category createOne(Category category) {
        Category tmpCat = null;

        category.setId(null);

        try {
            tmpCat = repo.save(category);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return tmpCat;
    }
}
