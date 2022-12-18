package com.ndp.knowsharing.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ndp.knowsharing.Entities.Article;
import com.ndp.knowsharing.Services.ArticleService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/tests")
public class TestController {
    @Autowired
    private ArticleService articleService;

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> retrieveAllArticlesWithTagIds(@RequestParam(value = "page", required = true) Integer pageNumber, @RequestParam(value = "tagids", required = true) List<String> tagIds) {
        ResponseEntity<Object> entity;

        // List<String> tagIds = new ArrayList<String>();
        // tagIds.add("7f7e3bc7-6c50-4b5c-87da-b602d964e12d");
        // tagIds.add("204c375e-eb40-49de-a556-b751899723d4");

        String categoryId = "05f15fb9-737b-4a44-a424-168f04d474c8";

        List<Article> articles = articleService.retrieveOnePageByCategoryAndHiddenWithTagIds(tagIds, pageNumber, categoryId, 0);
        // Integer articles = articleService.retrieveOneCommonPageWithTags(tagIds, pageNumber);


        entity = new ResponseEntity<>(articles, HttpStatus.OK);

        return entity;
    }
}
