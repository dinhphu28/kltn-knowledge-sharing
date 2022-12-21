package com.ndp.knowsharing.Repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ndp.knowsharing.Entities.Article;

@Repository
public interface ArticleRepo extends JpaRepository<Article, String> {

    List<Article> findByUrl(String url);

    List<Article> findByCategory(String category, Pageable pageable);

    List<Article> findByCategoryAndHidden(String category, Integer hidden, Pageable pageable);

    List<Article> findByHidden(Integer hidden, Pageable pageable);

    Long countByCategory(String category);

    Long countByCategoryAndHidden(String category, Integer hidden);

    Long countByHidden(Integer hidden);

    // String query = "select distinct a.id, a.dateCreated, a.dateModified, a.createdBy, a.createdByName, a.modifiedBy, a.modifiedByName, a.c_title, a.c_description, a.c_content, a.c_audio_content, a.c_author, a.c_url, a.c_category, a.c_thumbnail_url, a.c_hidden from app_fd_article a join app_fd_article_tag_rel b on a.id = b.c_article_id where b.c_tag_id = '7f7e3bc7-6c50-4b5c-87da-b602d964e12d' or b.c_tag_id = '204c375e-eb40-49de-a556-b751899723d4'";
    String query = "select distinct a.id, a.dateCreated, a.dateModified, a.createdBy, a.createdByName, a.modifiedBy, a.modifiedByName, a.c_title, a.c_description, a.c_content, a.c_audio_content, a.c_author, a.c_url, a.c_category, a.c_thumbnail_url, a.c_hidden from app_fd_article a join app_fd_article_tag_rel b on a.id = b.c_article_id where b.c_tag_id in (:tagids)";
    @Query(
        value = query,
        nativeQuery = true
    )
    Page<Article> findAllWithTagIds(@Param("tagids") List<String> tagIds, Pageable pageable);

    String query2 = "select distinct a.id, a.dateCreated, a.dateModified, a.createdBy, a.createdByName, a.modifiedBy, a.modifiedByName, a.c_title, a.c_description, a.c_content, a.c_audio_content, a.c_author, a.c_url, a.c_category, a.c_thumbnail_url, a.c_hidden from app_fd_article a join app_fd_article_tag_rel b on a.id = b.c_article_id where a.c_hidden = :hidden and b.c_tag_id in (:tagids)";
    @Query(
        value = query2,
        nativeQuery = true
    )
    Page<Article> findByHiddenWithTagIds(@Param("tagids") List<String> tagIds, @Param("hidden") Integer hidden, Pageable pageable);

    String query3 = "select distinct a.id, a.dateCreated, a.dateModified, a.createdBy, a.createdByName, a.modifiedBy, a.modifiedByName, a.c_title, a.c_description, a.c_content, a.c_audio_content, a.c_author, a.c_url, a.c_category, a.c_thumbnail_url, a.c_hidden from app_fd_article a join app_fd_article_tag_rel b on a.id = b.c_article_id where a.c_category = :category and b.c_tag_id in (:tagids)";
    @Query(
        value = query3,
        nativeQuery = true
    )
    Page<Article> findByCategoryWithTagIds(@Param("tagids") List<String> tagIds, @Param("category") String category, Pageable pageable);

    String query4 = "select distinct a.id, a.dateCreated, a.dateModified, a.createdBy, a.createdByName, a.modifiedBy, a.modifiedByName, a.c_title, a.c_description, a.c_content, a.c_audio_content, a.c_author, a.c_url, a.c_category, a.c_thumbnail_url, a.c_hidden from app_fd_article a join app_fd_article_tag_rel b on a.id = b.c_article_id where a.c_category = :category and a.c_hidden = :hidden and b.c_tag_id in (:tagids)";
    @Query(
        value = query4,
        nativeQuery = true
    )
    Page<Article> findByCategoryAndHiddenWithTagIds(@Param("tagids") List<String> tagIds, @Param("category") String category, @Param("hidden") Integer hidden, Pageable pageable);
}
