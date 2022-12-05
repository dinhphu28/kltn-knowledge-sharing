package com.ndp.knowsharing.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ndp.knowsharing.Entities.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, String> {
    List<Comment> findByArticleId(String articleId);

    List<Comment> findByArticleIdAndHidden(String articleId, Integer hidden);

    Long deleteByArticleId(String articleId);
}
