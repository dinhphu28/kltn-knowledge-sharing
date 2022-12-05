package com.ndp.knowsharing.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ndp.knowsharing.Entities.ArticleTagRel;

@Repository
@Transactional
public interface ArticleTagRelRepo extends JpaRepository<ArticleTagRel, String> {
    
    Long deleteByArticleId(String articleId);
}
