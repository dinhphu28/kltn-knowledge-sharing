package com.ndp.knowsharing.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndp.knowsharing.Entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, String> {
    
}
