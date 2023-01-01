package com.ndp.knowsharing.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndp.knowsharing.Entities.ArticleVoteScore;
import com.ndp.knowsharing.Repositories.ArticleVoteScoreRepo;

@Service
public class ArticleVoteScoreService {
    @Autowired
    private ArticleVoteScoreRepo repo;

    public List<ArticleVoteScore> retrieveTop3ArticleHighestVoteScore() {
        List<ArticleVoteScore> sth = new ArrayList<ArticleVoteScore>();

        try {
            sth = repo.findTop3ArticleHighestVoteScore();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return sth;
    }
}
