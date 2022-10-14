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
}
