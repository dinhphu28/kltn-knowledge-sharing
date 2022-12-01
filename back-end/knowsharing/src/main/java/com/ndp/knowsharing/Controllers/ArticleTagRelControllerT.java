package com.ndp.knowsharing.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ndp.knowsharing.Entities.ArticleTagRel;
import com.ndp.knowsharing.Services.ArticleTagRelService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/article-tag-rel")
public class ArticleTagRelControllerT {
    @Autowired
    private ArticleTagRelService articleTagRelService;

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> retrieveAll() {
        ResponseEntity<Object> entity;

        List<ArticleTagRel> articleTagRels = articleTagRelService.retrieveAll();

        entity = new ResponseEntity<>(articleTagRels, HttpStatus.OK);

        return entity;
    }

    @DeleteMapping(
        value = "/{articleId}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> deleteByArticleId(@PathVariable("articleId") String articleId) {
        ResponseEntity<Object> entity;

        Long kk = articleTagRelService.deleteAllByArticleId("64f1abc6-5fdd-4264-93e1-08e025ffef10");
        // Boolean kk = true;

        entity = new ResponseEntity<>(kk, HttpStatus.OK);

        return entity;
    }
}
