package com.ndp.knowsharing.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ndp.knowsharing.Entities.ArticleTag;

@Repository
public interface ArticleTagRepo extends JpaRepository<ArticleTag, String> {
    
}
