package com.ndp.knowsharing.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ndp.knowsharing.Entities.Article;
import com.ndp.knowsharing.Repositories.ArticleRepo;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepo repo;

    public List<Article> retrieveAll() {
        return repo.findAll();
    }

    public List<Article> retrieveOneCommonPage(Integer pageNumber) {
        Page<Article> page = repo.findAll(PageRequest.of(pageNumber, 10, Sort.by("dateCreated").descending()));

        List<Article> articles = page.getContent();

        return articles;
    }

    public List<Article> retrieveOneCommonPageAndHidden(Integer pageNumber, Integer hidden) {
        List<Article> articles = repo.findByHidden(hidden, PageRequest.of(pageNumber, 10, Sort.by("dateCreated").descending()));

        return articles;
    }

    public List<Article> retrieveOnePageByCategory(Integer pageNumber, String categoryId) {
        List<Article> articles = repo.findByCategory(categoryId, PageRequest.of(pageNumber, 10, Sort.by("dateCreated").descending()));

        return articles;
    }

    public List<Article> retrieveOnePageByCategoryAndHidden(Integer pageNumber, String categoryId, Integer hidden) {
        List<Article> articles = repo.findByCategoryAndHidden(categoryId, hidden, PageRequest.of(pageNumber, 10, Sort.by("dateCreated").descending()));

        return articles;
    }

    public Long retrieveNumOfPages(String categoryId) {
        if(categoryId == null) {
            return repo.count();
        } else {
            return repo.countByCategory(categoryId);
        }
    }

    public Long retrieveNumOfPagesAndHidden(String categoryId, Integer hidden) {
        if(categoryId == null) {
            return repo.countByHidden(hidden);
        } else {
            return repo.countByCategoryAndHidden(categoryId, hidden);
        }
    }

    public Article retrieveOne(String id) {
        Article sth = null;

        try {
            sth = repo.findById(id).get();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return sth;
    }

    public Article createOne(Article article) {
        Article tmpArticle = null;

        article.setId(null);

        try {
            tmpArticle = repo.save(article);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return tmpArticle;
    }

    public Article updateOne(Article article) {
        Article tmpArticle = null;

        try {
            repo.findById(article.getId()).get();

            tmpArticle = repo.save(article);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return tmpArticle;
    }
}

