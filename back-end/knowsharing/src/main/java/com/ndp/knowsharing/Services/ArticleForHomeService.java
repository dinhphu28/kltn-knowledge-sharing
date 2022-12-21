package com.ndp.knowsharing.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndp.knowsharing.Entities.ArticleForHome;
import com.ndp.knowsharing.Repositories.ArticleForHomeRepo;

@Service
public class ArticleForHomeService {
    @Autowired
    private ArticleForHomeRepo repo;

    public List<ArticleForHome> retrieveAll() {
        return repo.findAll();
    }

    public ArticleForHome retrieveById(String id) {
        ArticleForHome sth = null;

        try {
            sth = repo.findById(id).get();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return sth;
    }

    public List<ArticleForHome> retrieveByType(String type) {
        return repo.findByType(type);
    }

    public ArticleForHome saveOne(ArticleForHome articleForHome) {
        ArticleForHome tmp = null;

        try {
            tmp = repo.save(articleForHome);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return tmp;
    }

    public Boolean deleteById(String id) {
        Boolean isSuccess = false;

        try {
            repo.deleteById(id);

            isSuccess = true;
        } catch (Exception e) {
            // TODO: handle exception
        }

        return isSuccess;
    }
}
