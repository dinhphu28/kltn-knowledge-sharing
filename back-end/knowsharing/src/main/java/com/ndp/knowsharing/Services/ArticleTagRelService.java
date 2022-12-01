package com.ndp.knowsharing.Services;

import java.util.ArrayList;
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

    public List<ArticleTagRel> createMulti(List<ArticleTagRel> articleTagRels) {
        List<ArticleTagRel> tmps = new ArrayList<ArticleTagRel>();

        for (ArticleTagRel articleTagRel : articleTagRels) {

            articleTagRel.setId(null);

            ArticleTagRel tmpItem = articleTagRel;

            tmps.add(tmpItem);
        }

        List<ArticleTagRel> tmpSaved = new ArrayList<ArticleTagRel>();

        try {
            tmpSaved = repo.saveAll(tmps);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return tmpSaved;
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

    public Long deleteAllByArticleId(String articleId) {
        // Boolean isSuccess = false;
        Long deletedRecords = Long.valueOf(0);

        try {
            deletedRecords = repo.deleteByArticleId(articleId);

            // if(ii > 0) {
            //     isSuccess = true;
            // }
        } catch (Exception e) {
            // TODO: handle exception
        }

        // Long ii = repo.deleteByArticleId(articleId);

        // if(ii > 0) {
        //     isSuccess = true;
        // }

        return deletedRecords;
    }
}
