package com.ndp.knowsharing.Scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ndp.knowsharing.Components.LeaderBoardArticleTask;

@Component
public class LeaderBoardArticleTaskScheduler {
    @Autowired
    private LeaderBoardArticleTask leaderBoardArticleTask;

    @Scheduled(cron = "${ndp.properties.schedule.cron-expression}")
    public void updateArticlesOnLeaderBoard () {
        leaderBoardArticleTask.updateLeaderBoard();
    }
}
