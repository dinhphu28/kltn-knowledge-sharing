package com.ndp.knowsharing.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndp.knowsharing.Entities.CommentReport;
import com.ndp.knowsharing.Repositories.CommentReportRepo;

@Service
public class CommentReportService {
    @Autowired
    private CommentReportRepo repo;

    public List<CommentReport> retrieveAll() {
        return repo.findByOrderByDateCreatedDesc();
    }

    public List<CommentReport> retrieveAllByCommentId(String commentId) {
        return repo.findByCommentIdOrderByDateCreatedDesc(commentId);
    }

    public List<CommentReport> retrieveBySolved(Integer solved) {
        return repo.findByIsSolved(solved);
    }

    public CommentReport retrieveById(String id) {
        CommentReport tmp = null;

        try {
            tmp = repo.findById(id).get();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return tmp;
    }

    public CommentReport createOne(CommentReport commentReport) {
        CommentReport tmp = null;

        commentReport.setId(null);

        try {
            tmp = repo.save(commentReport);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return tmp;
    }

    public CommentReport updateOne(CommentReport commentReport) {
        CommentReport tmp = null;

        try {
            repo.findById(commentReport.getId()).get();

            tmp = repo.save(commentReport);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return tmp;
    }

    public Boolean deleteOne(String id) {
        Boolean kk = false;

        try {
            repo.findById(id).get();
            
            repo.deleteById(id);

            kk = true;
        } catch (Exception e) {
            //TODO: handle exception
        }

        return kk;
    }

    public Boolean deleteAllByArticleId(String articleId) {
        Boolean kk = false;

        try {
            Long ii = repo.deleteByCommentId(articleId);

            if(ii > 0) {
                kk = true;
            }
        } catch (Exception e) {
            //TODO: handle exception
        }

        return kk;
    }
}
