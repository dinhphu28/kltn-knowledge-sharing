package com.ndp.knowsharing.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ndp.knowsharing.Entities.ArticleForHome;

@Repository
public interface ArticleForHomeRepo extends JpaRepository<ArticleForHome, String> {
    
    List<ArticleForHome> findByType(String type);
}
