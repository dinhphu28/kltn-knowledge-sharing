package com.ndp.knowsharing.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndp.knowsharing.Entities.ArticleTag;
import com.ndp.knowsharing.Repositories.ArticleTagRepo;

@Service
public class ArticleTagService {
    @Autowired
    private ArticleTagRepo repo;

    public List<ArticleTag> retrieveAll() {
        return repo.findAll();
    }

    public ArticleTag retrieveById(String id) {
        ArticleTag sth = null;

        try {
            sth = repo.findById(id).get();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return sth;
    }

    public List<ArticleTag> retrieveByCategory(String categoryId) {
        return repo.findByCategory(categoryId);
    }

    public ArticleTag createOne(ArticleTag articleTag) {
        ArticleTag tmp = null;

        articleTag.setId(null);

        try {
            tmp = repo.save(articleTag);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return tmp;
    }

    public ArticleTag updateOne(ArticleTag articleTag) {
        ArticleTag tmp = null;

        try {
            repo.findById(articleTag.getId()).get();

            tmp = repo.save(articleTag);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return tmp;
    }
}
