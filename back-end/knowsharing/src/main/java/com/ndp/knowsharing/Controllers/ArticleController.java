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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ndp.knowsharing.Entities.Article;
import com.ndp.knowsharing.Models.Article.ArticleCreateModel;
import com.ndp.knowsharing.Models.Article.ArticleUpdateModel;
import com.ndp.knowsharing.Models.Article.PageOfArticleModel;
import com.ndp.knowsharing.Services.ArticleService;
import com.ndp.knowsharing.Utils.UriParser.MyUriParserUtils;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private MyUriParserUtils myUriParserUtils;

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    // public ResponseEntity<Object> retrieveAll(@RequestParam(value = "page", required = true) Integer pageNum, @RequestParam(value = "category", required = false) String categoryName, @RequestParam(value = "hidden", required = false) Boolean hidden) {
    //     ResponseEntity<Object> entity;

    //     PageOfArticleModel pageOfArticleModel = new PageOfArticleModel();

    //     if(categoryName == null) {
    //         if(hidden == null) {

    //         } else {
    //             List<Article> articles = articleService.re
    //         }
    //     } else {

    //     }

    //     return entity;
    // }
    public ResponseEntity<Object> retrieveAll() {
        ResponseEntity<Object> entity;

        List<Article> articles = articleService.retrieveAll();

        entity = new ResponseEntity<>(articles, HttpStatus.OK);

        return entity;
    }

    @GetMapping(
        value = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> retrieveById(@PathVariable("id") String id) {
        ResponseEntity<Object> entity;

        Article article = articleService.retrieveOne(id);

        entity = new ResponseEntity<>(article, HttpStatus.OK);

        return entity;
    }

    @PostMapping(
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> createOneArticle(@RequestBody ArticleCreateModel articleCreateModel) {
        ResponseEntity<Object> entity;

        if(articleCreateModel.getCreatedBy() == null ||
            articleCreateModel.getCreatedByName() == null ||
            articleCreateModel.getTitle() == null ||
            articleCreateModel.getDescription() == null ||
            articleCreateModel.getContent() == null ||
            articleCreateModel.getAudioContent() == null ||
            articleCreateModel.getAuthor() == null ||
            articleCreateModel.getCategory() == null ||
            articleCreateModel.getThumbnailUrl() == null
        ) {
            entity = new ResponseEntity<>("{ \"Notice\": \"Failed\" }", HttpStatus.BAD_REQUEST);
        } else {

            LocalDateTime now = LocalDateTime.now();

            Article articleEntity = new Article(null, now, now, "", "", "", "", articleCreateModel.getTitle(), articleCreateModel.getDescription(), articleCreateModel.getContent(), articleCreateModel.getAudioContent(), articleCreateModel.getAuthor(), myUriParserUtils.getFinalArticleUrl(articleCreateModel.getTitle()), articleCreateModel.getCategory(), articleCreateModel.getThumbnailUrl(), 0);

            Article tmpSaved = articleService.createOne(articleEntity);

            if(tmpSaved == null) {
                entity = new ResponseEntity<>("{ \"Notice\": \"Failed\" }", HttpStatus.BAD_REQUEST);
            } else {
                entity = new ResponseEntity<>(tmpSaved, HttpStatus.CREATED);
            }
        }

        return entity;
    }

    @PutMapping(
        value = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> updateOneArticle(@PathVariable("id") String id, @RequestBody ArticleUpdateModel articleUpdateModel) {
        ResponseEntity<Object> entity;

        if(articleUpdateModel.getModifiedBy() == null ||
            articleUpdateModel.getModifiedByName() == null ||
            articleUpdateModel.getTitle() == null ||
            articleUpdateModel.getDescription() == null ||
            articleUpdateModel.getContent() == null ||
            articleUpdateModel.getAudioContent() == null ||
            articleUpdateModel.getCategory() == null ||
            articleUpdateModel.getThumbnailUrl() == null
        ) {
            entity = new ResponseEntity<>("{ \"Notice\": \"Failed\" }", HttpStatus.BAD_REQUEST);
        } else {
            Article tmpArticle = articleService.retrieveOne(id);

            if(tmpArticle == null) {
                entity = new ResponseEntity<>("{ \"Notice\": \"Failed\" }", HttpStatus.BAD_REQUEST);
            } else {
                LocalDateTime now = LocalDateTime.now();

                Article articleEntity = new Article(id, tmpArticle.getDateCreated(), now, tmpArticle.getCreatedBy(), tmpArticle.getCreatedByName(), "", "", articleUpdateModel.getTitle(), articleUpdateModel.getDescription(), articleUpdateModel.getContent(), articleUpdateModel.getAudioContent(), tmpArticle.getAuthor(), tmpArticle.getUrl(), articleUpdateModel.getCategory(), articleUpdateModel.getThumbnailUrl(), 0);

                Article tmpSaved = articleService.updateOne(articleEntity);

                if(tmpSaved == null) {
                    entity = new ResponseEntity<>("{ \"Notice\": \"Failed\" }", HttpStatus.BAD_REQUEST);
                } else {
                    entity = new ResponseEntity<>(tmpSaved, HttpStatus.CREATED);
                }
            }
        }

        return entity;
    }
}
