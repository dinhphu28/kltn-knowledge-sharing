package com.ndp.knowsharing.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ndp.knowsharing.Entities.UserVoteState;

@Repository
public interface UserVoteStateRepo extends JpaRepository<UserVoteState, String> {
    List<UserVoteState> findByArticleId(String articleId);

    UserVoteState findByArticleIdAndAuthor(String articleId, String userId);
    
    Long countByArticleId(String articleId);

    Long deleteByArticleId(String articleId);
}
