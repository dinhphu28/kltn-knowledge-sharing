package com.ndp.knowsharing.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ndp.knowsharing.Entities.ArticleTagRel;

@Repository
public interface ArticleTagRelRepo extends JpaRepository<ArticleTagRel, String> {
    
}
