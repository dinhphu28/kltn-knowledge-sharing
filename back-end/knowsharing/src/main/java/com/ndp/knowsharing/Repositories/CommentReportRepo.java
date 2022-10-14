package com.ndp.knowsharing.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ndp.knowsharing.Entities.CommentReport;

@Repository
public interface CommentReportRepo extends JpaRepository<CommentReport, String> {
    List<CommentReport> findByCommentId(String commentId);

    List<CommentReport> findByCommentIdOrderByDateCreatedDesc(String commentId);

    List<CommentReport> findByOrderByDateCreatedDesc();

    Long deleteByCommentId(String commentId);

    List<CommentReport> findByIsSolved(Integer isSolved);
}
