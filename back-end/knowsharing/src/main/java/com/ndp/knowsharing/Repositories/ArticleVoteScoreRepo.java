package com.ndp.knowsharing.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ndp.knowsharing.Entities.ArticleVoteScore;

@Repository
public interface ArticleVoteScoreRepo extends JpaRepository<ArticleVoteScore, String> {
    
    String query1 = "select sum(c_vote_state) as sum_vote_state, c_article_id from app_fd_user_vote_state group by c_article_id order by sum_vote_state desc limit 3";
    @Query(
        value = query1,
        nativeQuery = true
    )
    List<ArticleVoteScore> findTop3ArticleHighestVoteScore();
}
