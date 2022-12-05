package com.ndp.knowsharing.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ndp.knowsharing.Entities.ArticleReport;

@Repository
@Transactional
public interface ArticleReportRepo extends JpaRepository<ArticleReport, String> {
    List<ArticleReport> findByArticleId(String articleId);

    List<ArticleReport> findByArticleIdOrderByDateCreatedDesc(String articleId);

    List<ArticleReport> findByOrderByDateCreatedDesc();

    Long deleteByArticleId(String articleId);

    List<ArticleReport> findByIsSolved(Integer isSolved);
}
