package com.ndp.knowsharing.Controllers;

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

import com.ndp.knowsharing.Entities.ArticleTag;
import com.ndp.knowsharing.Models.ArticleTag.ArticleTagCreateModel;
import com.ndp.knowsharing.Services.ArticleTagService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/article-tags")
public class ArticleTagController {
    @Autowired
    private ArticleTagService articleTagService;

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> retrieveAll() {
        ResponseEntity<Object> entity;

        List<ArticleTag> articleTags = articleTagService.retrieveAll();

        entity = new ResponseEntity<>(articleTags, HttpStatus.OK);

        return entity;
    }

    @GetMapping(
        value = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> retrieveById(@PathVariable("id") String id) {
        ResponseEntity<Object> entity;

        ArticleTag articleTag = articleTagService.retrieveById(id);

        entity = new ResponseEntity<>(articleTag, HttpStatus.OK);

        return entity;
    }

    @PostMapping(
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> createOne(@RequestBody ArticleTagCreateModel articleTagCreateModel) {
        ResponseEntity<Object> entity;

        ArticleTag tmpToSave = new ArticleTag(null, articleTagCreateModel.getCategory(), articleTagCreateModel.getTagName(), 1);

        ArticleTag tmpSaved = articleTagService.createOne(tmpToSave);

        if(tmpSaved == null) {
            entity = new ResponseEntity<>("{ \"Notice\": \"Failed!\" }", HttpStatus.BAD_REQUEST);
        } else {
            entity = new ResponseEntity<>(tmpSaved, HttpStatus.CREATED);
        }

        return entity;
    }
}
