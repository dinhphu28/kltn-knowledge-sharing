package com.ndp.knowsharing.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndp.knowsharing.Entities.ArticleTagRel;
import com.ndp.knowsharing.Repositories.ArticleTagRelRepo;

@Service
public class ArticleTagRelService {
    @Autowired
    private ArticleTagRelRepo repo;

    public List<ArticleTagRel> retrieveAll() {
        return repo.findAll();
    }

    public ArticleTagRel retrieveById(String id) {
        ArticleTagRel sth = null;

        try {
            sth = repo.findById(id).get();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return sth;
    }

    public ArticleTagRel createOne(ArticleTagRel articleTagRel) {
        ArticleTagRel tmp = null;

        articleTagRel.setId(null);

        try {
            tmp = repo.save(articleTagRel);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return tmp;
    }

    public Boolean deleteOne(String id) {
        Boolean isSuccess = false;

        try {
            repo.deleteById(id);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return isSuccess;
    }
}
