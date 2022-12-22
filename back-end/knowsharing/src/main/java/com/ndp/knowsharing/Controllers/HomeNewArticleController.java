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
import org.springframework.web.bind.annotation.RestController;

import com.ndp.knowsharing.Entities.Article;
import com.ndp.knowsharing.Entities.Category;
import com.ndp.knowsharing.Models.ArticleForHome.NewlyArticleForHomeReturnModel;
import com.ndp.knowsharing.Services.ArticleService;
import com.ndp.knowsharing.Services.CategoryService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/new-articles")
public class HomeNewArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> retrieveAll() {
        ResponseEntity<Object> entity;

        List<NewlyArticleForHomeReturnModel> newlyArticleForHomeReturnModels = new ArrayList<NewlyArticleForHomeReturnModel>();

        List<Article> newestArticles = articleService.retrieveTop5ByHiddenAndNewest(0);

        for (Article article : newestArticles) {
            Category category = categoryService.retrieveById(article.getCategory());

            NewlyArticleForHomeReturnModel tmp = new NewlyArticleForHomeReturnModel(article, category.getName());

            newlyArticleForHomeReturnModels.add(tmp);
        }

        entity = new ResponseEntity<>(newlyArticleForHomeReturnModels, HttpStatus.OK);

        return entity;
    }
}
