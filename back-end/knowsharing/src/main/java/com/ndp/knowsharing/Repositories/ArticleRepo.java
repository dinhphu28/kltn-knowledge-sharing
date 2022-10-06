package com.ndp.knowsharing.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndp.knowsharing.Entities.Article;

public interface ArticleRepo extends JpaRepository<Article, String> {
    
}
