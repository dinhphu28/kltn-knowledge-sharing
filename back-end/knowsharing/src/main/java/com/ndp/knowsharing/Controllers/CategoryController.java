package com.ndp.knowsharing.Controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ndp.knowsharing.Entities.Category;
import com.ndp.knowsharing.Models.Category.CategoryCreateModel;
import com.ndp.knowsharing.Services.CategoryService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> retrieveAll() {
        ResponseEntity<Object> entity;

        List<Category> categories = categoryService.retrieveAll();

        entity = new ResponseEntity<>(categories, HttpStatus.OK);

        return entity;
    }

    @GetMapping(
        value = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> retrieveById(@PathVariable("id") String id) {
        ResponseEntity<Object> entity;

        Category category = categoryService.retrieveById(id);

        entity = new ResponseEntity<>(category, HttpStatus.OK);

        return entity;
    }

    @PostMapping(
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> createOne(@RequestBody CategoryCreateModel categoryCreateModel) {
        ResponseEntity<Object> entity;

        if(categoryCreateModel.getName() == null) {
            entity = new ResponseEntity<>("{ \"Notice\": \"Not allow empty\" }", HttpStatus.BAD_REQUEST);
        } else if(categoryCreateModel.getName().isBlank()) {
            entity = new ResponseEntity<>("{ \"Notice\": \"Not allow empty\" }", HttpStatus.BAD_REQUEST);
        } else {
            LocalDateTime now = LocalDateTime.now();

            Category tmpToSave = new Category(null, now, now, null, null, null, null, categoryCreateModel.getName());

            Category tmpSaved = categoryService.createOne(tmpToSave);

            if(tmpSaved == null) {
                entity = new ResponseEntity<>("{ \"Notice\": \"Failed!\" }", HttpStatus.BAD_REQUEST);
            } else {
                entity = new ResponseEntity<>(tmpSaved, HttpStatus.CREATED);
            }
        }

        return entity;
    }
}
