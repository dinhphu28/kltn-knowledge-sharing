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
import com.ndp.knowsharing.Entities.ArticleVoteScore;
import com.ndp.knowsharing.Services.ArticleService;
import com.ndp.knowsharing.Services.ArticleVoteScoreService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/tests")
public class TestController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleVoteScoreService articleVoteScoreService;

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> retrieveTop3ArticleHighestVoteScore() {
        ResponseEntity<Object> entity;

        List<ArticleVoteScore> articleVoteScores = articleVoteScoreService.retrieveTop3ArticleHighestVoteScore();
        

        entity = new ResponseEntity<>(articleVoteScores, HttpStatus.OK);

        return entity;
    }
}
