package com.ndp.knowsharing.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndp.knowsharing.Entities.ArticleReport;
import com.ndp.knowsharing.Repositories.ArticleReportRepo;

@Service
public class ArticleReportService {
    @Autowired
    private ArticleReportRepo repo;

    public List<ArticleReport> retrieveAll() {
        // return repo.findAll();
        return repo.findByOrderByDateCreatedDesc();
    }

    public List<ArticleReport> retrieveAllByArticleId(String articleId) {
        // return repo.findByArticleId(articleId);
        return repo.findByArticleIdOrderByDateCreatedDesc(articleId);
    }

    public List<ArticleReport> retrieveBySolved(Integer solved) {
        return repo.findByIsSolved(solved);
    }

    public ArticleReport retrieveById(String id) {
        ArticleReport tmp = null;

        try {
            tmp = repo.findById(id).get();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return tmp;
    }

    public ArticleReport createOne(ArticleReport articleReport) {
        ArticleReport tmp = null;

        articleReport.setId(null);

        try {
            tmp = repo.save(articleReport);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return tmp;
    }

    public ArticleReport updateOne(ArticleReport articleReport) {
        ArticleReport tmpArticleReport = null;

        try {
            repo.findById(articleReport.getId()).get();

            tmpArticleReport = repo.save(articleReport);
        } catch (Exception e) {
            //TODO: handle exception
        }

        return tmpArticleReport;
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
            Long ii = repo.deleteByArticleId(articleId);

            if(ii > 0) {
                kk = true;
            }
        } catch (Exception e) {
            //TODO: handle exception
        }

        return kk;
    }
}
