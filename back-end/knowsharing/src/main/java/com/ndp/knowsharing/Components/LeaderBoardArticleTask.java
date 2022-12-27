package com.ndp.knowsharing.Components;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ndp.knowsharing.Entities.ArticleForHome;
import com.ndp.knowsharing.Entities.ArticleVoteScore;
import com.ndp.knowsharing.Services.ArticleForHomeService;
import com.ndp.knowsharing.Services.ArticleVoteScoreService;

@Component
public class LeaderBoardArticleTask {
    private final String TYPE = "leader-board";

    @Autowired
    private ArticleVoteScoreService articleVoteScoreService;

    @Autowired
    private ArticleForHomeService articleForHomeService;

    public Boolean updateLeaderBoard() {
        Boolean isSuccess = false;

        List<ArticleVoteScore> articleVoteScores = articleVoteScoreService.retrieveTop3ArticleHighestVoteScore();

        int index = 1;
        for (ArticleVoteScore articleVoteScore : articleVoteScores) {
            LocalDateTime now = LocalDateTime.now();

            ArticleForHome articleForHome = new ArticleForHome(TYPE + "-" + index++, articleVoteScore.getArticleId(), now, now, TYPE);

            ArticleForHome tmpSaved = articleForHomeService.saveOne(articleForHome);

            if(tmpSaved != null) {
                isSuccess = true;
            }
        }
        
        return isSuccess;
    }
}
