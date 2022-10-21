package com.ndp.knowsharing.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndp.knowsharing.Entities.Comment;
import com.ndp.knowsharing.Repositories.CommentRepo;

@Service
public class CommentService {
    @Autowired
    private CommentRepo repo;

    public List<Comment> retrieveAll() {
        return repo.findAll();
    }

    public List<Comment> retrieveAllByArticleId(String articleId) {
        return repo.findByArticleId(articleId);
    }

    public List<Comment> retrieveAllByArticleIdAndHidden(String articleId, Integer hidden) {
        return repo.findByArticleIdAndHidden(articleId, hidden);
    }

    public Comment retrieveById(String id) {
        Comment sth = null;

        try {
            sth = repo.findById(id).get();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return sth;
    }

    public Comment createOne(Comment comment) {
        Comment tmp = null;

        comment.setId(null);

        try {
            tmp = repo.save(comment);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return tmp;
    }

    public Boolean deleteById(String id) {
        Boolean isSuccess = false;

        try {
            repo.findById(id).get();

            repo.deleteById(id);

            isSuccess = true;
        } catch (Exception e) {
            // TODO: handle exception
        }

        return isSuccess;
    }

    public Boolean deleteAllByArticleId(String articleId) {
        Boolean isSuccess = false;

        try {
            Long ii = repo.deleteByArticleId(articleId);

            if(ii > 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return isSuccess;
    }
}
