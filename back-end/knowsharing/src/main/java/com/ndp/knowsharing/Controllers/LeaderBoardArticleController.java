package com.ndp.knowsharing.Controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ndp.knowsharing.Entities.Article;
import com.ndp.knowsharing.Entities.ArticleForHome;
import com.ndp.knowsharing.Models.ArticleForHome.ArticleForHomeReturnModel;
import com.ndp.knowsharing.Models.ArticleForHome.ArticleForHomeUpdateModel;
import com.ndp.knowsharing.Services.ArticleForHomeService;
import com.ndp.knowsharing.Services.ArticleService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/leader-board-articles")
public class LeaderBoardArticleController {
    private final String TYPE = "leader-board";

    @Autowired
    private ArticleForHomeService articleForHomeService;

    @Autowired
    private ArticleService articleService;

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> retrieveAll() {
        ResponseEntity<Object> entity;

        List<ArticleForHomeReturnModel> articleForHomeReturnModels = new ArrayList<ArticleForHomeReturnModel>();

        List<ArticleForHome> nominatedArticles = articleForHomeService.retrieveByType(TYPE);

        for (ArticleForHome articleForHome : nominatedArticles) {
            Article tmpArticle = articleService.retrieveOne(articleForHome.getArticleId());

            if(tmpArticle != null) {
                if(tmpArticle.getHidden() == 0) {
                    ArticleForHomeReturnModel articleForHomeReturnModel = new ArticleForHomeReturnModel(articleForHome.getId(), articleForHome.getDateCreated(), articleForHome.getDateModified(), articleForHome.getType(), tmpArticle.getId(), tmpArticle.getDateCreated(), tmpArticle.getTitle(), tmpArticle.getContent(), tmpArticle.getDescription(), tmpArticle.getAuthor(), tmpArticle.getUrl(), tmpArticle.getCategory(), tmpArticle.getThumbnailUrl(), tmpArticle.getHidden());

                    articleForHomeReturnModels.add(articleForHomeReturnModel);
                }
            }
        }

        entity = new ResponseEntity<>(articleForHomeReturnModels, HttpStatus.OK);

        return entity;
    }

    @PutMapping(
        value = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> updateById(@PathVariable("id") String id, @RequestBody ArticleForHomeUpdateModel articleForHomeUpdateModel) {
        ResponseEntity<Object> entity;

        if(articleForHomeUpdateModel.getArticleId().isBlank()) {
            entity = new ResponseEntity<>("{ \"Notice\": \"Failed\" }", HttpStatus.BAD_REQUEST);
        } else {
            LocalDateTime now = LocalDateTime.now();

            ArticleForHome articleForHome = new ArticleForHome(id, articleForHomeUpdateModel.getArticleId(), now, now, TYPE);

            ArticleForHome tmpSaved = articleForHomeService.saveOne(articleForHome);

            entity = new ResponseEntity<>(tmpSaved, HttpStatus.OK);
        }

        return entity;
    }

    @DeleteMapping(
        value = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> deleteById(@PathVariable("id") String id) {
        ResponseEntity<Object> entity;

        Boolean isSuccess = articleForHomeService.deleteById(id);

        entity = new ResponseEntity<>("{ \"isSuccess\": " + isSuccess + " }", HttpStatus.OK);

        return entity;
    }
}
