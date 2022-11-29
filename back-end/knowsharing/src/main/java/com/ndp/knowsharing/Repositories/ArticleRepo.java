package com.ndp.knowsharing.Repositories;

import java.util.List;
  
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ndp.knowsharing.Entities.Article;

@Repository
public interface ArticleRepo extends JpaRepository<Article, String> {
    List<Article> findByCategory(String category, Pageable pageable);

    List<Article> findByCategoryAndHidden(String category, Integer hidden, Pageable pageable);

    List<Article> findByHidden(Integer hidden, Pageable pageable);

    Long countByCategory(String category);

    Long countByCategoryAndHidden(String category, Integer hidden);

    Long countByHidden(Integer hidden);
}
