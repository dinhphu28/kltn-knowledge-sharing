package com.ndp.knowsharing.Services;

import java.util.ArrayList;
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

    public List<Article> retrieveByUrl(String url) {
        return repo.findByUrl(url);
    }

    public List<Article> retrieveTop5ByHiddenAndNewest(Integer hidden) {
        return repo.findTop5ByHiddenOrderByDateCreatedDesc(hidden);
    }

    public List<Article> retrieveWithFullTextSearchByTitleAndDescriptionAndContent(String searchStr, Integer pageNumber) {
        List<Article> articles = new ArrayList<Article>();
        
        try {
            Page<Article> page = repo.findWithFullTextSearchByTitleAndDescriptionAndContent(searchStr, PageRequest.of(pageNumber, 10));

            articles = page.getContent();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return articles;
    }

    public List<Article> retrieveWithFullTextSearchByTitleAndDescriptionAndContentAndByCategory(String searchStr, String categoryId, Integer pageNumber) {
        List<Article> articles = new ArrayList<Article>();

        try {
            Page<Article> page = repo.findWithFullTextSearchByTitleAndDescriptionAndContentAndByCategory(searchStr, categoryId, PageRequest.of(pageNumber, 10));

            articles = page.getContent();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return articles;
    }

    public List<Article> retrieveWithFullTextSearchByTitleAndDescriptionAndContentAndByDateCreatedBetween(String searchStr, String from, String to, Integer pageNumber) {
        List<Article> articles = new ArrayList<Article>();

        try {
            Page<Article> page = repo.findWithFullTextSearchByTitleAndDescriptionAndContentAndByDateCreatedBetween(searchStr, from, to, PageRequest.of(pageNumber, 10));

            articles = page.getContent();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return articles;
    }

    public List<Article> retrieveWithFullTextSearchByTitleAndDescriptionAndContentAndByDateCreatedBetweenAndCategory(String searchStr, String from, String to, String categoryId, Integer pageNumber) {
        List<Article> articles = new ArrayList<Article>();

        try {
            Page<Article> page = repo.findWithFullTextSearchByTitleAndDescriptionAndContentAndByDateCreatedBetweenAndCategory(searchStr, from, to, categoryId, PageRequest.of(pageNumber, 10));

            articles = page.getContent();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return articles;
    }

    public List<Article> retrieveOneCommonPage(Integer pageNumber) {
        Page<Article> page = repo.findAll(PageRequest.of(pageNumber, 10, Sort.by("dateCreated").descending()));

        List<Article> articles = page.getContent();

        return articles;
    }

    public List<Article> retrieveOneCommonPageWithTags(List<String> tagIds, Integer pageNumber) {
        
        List<Article> articles = new ArrayList<Article>();
        
        try {
            Page<Article> page = repo.findAllWithTagIds(tagIds, PageRequest.of(pageNumber, 10, Sort.by("dateCreated").descending()));

            articles = page.getContent();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return articles;
    }

    public Long retrieveNumOfPagesAllWithTagIds(List<String> tagIds) {
        Long numOfPage = Long.valueOf(0);
        
        try {
            Page<Article> page = repo.findAllWithTagIds(tagIds, PageRequest.of(0, 10, Sort.by("dateCreated").descending()));

            numOfPage = Long.valueOf(page.getTotalPages());
        } catch (Exception e) {
            // TODO: handle exception
        }

        return numOfPage;
    }

    public List<Article> retrieveOneCommonPageAndHidden(Integer pageNumber, Integer hidden) {
        List<Article> articles = repo.findByHidden(hidden, PageRequest.of(pageNumber, 10, Sort.by("dateCreated").descending()));

        return articles;
    }

    public List<Article> retrieveOneCommonPageAndHiddenWithTagIds(List<String> tagIds, Integer pageNumber, Integer hidden) {
        List<Article> articles = new ArrayList<Article>();
        
        try {
            Page<Article> page = repo.findByHiddenWithTagIds(tagIds, hidden, PageRequest.of(pageNumber, 10, Sort.by("dateCreated").descending()));

            articles = page.getContent();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return articles;
    }

    public Long retrieveNumOfPagesByHiddenWithTagIds(List<String> tagIds, Integer hidden) {
        Long numOfPage = Long.valueOf(0);
        
        try {
            Page<Article> page = repo.findByHiddenWithTagIds(tagIds, hidden, PageRequest.of(0, 10, Sort.by("dateCreated").descending()));

            numOfPage = Long.valueOf(page.getTotalPages());
        } catch (Exception e) {
            // TODO: handle exception
        }

        return numOfPage;
    }

    public List<Article> retrieveOnePageByCategory(Integer pageNumber, String categoryId) {
        List<Article> articles = repo.findByCategory(categoryId, PageRequest.of(pageNumber, 10, Sort.by("dateCreated").descending()));

        return articles;
    }

    public List<Article> retrieveOnePageByCategoryWithTagIds(List<String> tagIds, Integer pageNumber, String categoryId) {
        List<Article> articles = new ArrayList<Article>();
        
        try {
            Page<Article> page = repo.findByCategoryWithTagIds(tagIds, categoryId, PageRequest.of(pageNumber, 10, Sort.by("dateCreated").descending()));

            articles = page.getContent();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return articles;
    }

    public Long retrieveNumOfPagesByCategoryWithTagIds(List<String> tagIds, String categoryId) {
        Long numOfPage = Long.valueOf(0);
        
        try {
            Page<Article> page = repo.findByCategoryWithTagIds(tagIds, categoryId, PageRequest.of(0, 10, Sort.by("dateCreated").descending()));

            numOfPage = Long.valueOf(page.getTotalPages());
        } catch (Exception e) {
            // TODO: handle exception
        }

        return numOfPage;
    }

    public List<Article> retrieveOnePageByCategoryAndHidden(Integer pageNumber, String categoryId, Integer hidden) {
        List<Article> articles = repo.findByCategoryAndHidden(categoryId, hidden, PageRequest.of(pageNumber, 10, Sort.by("dateCreated").descending()));

        return articles;
    }

    public List<Article> retrieveOnePageByCategoryAndHiddenWithTagIds(List<String> tagIds, Integer pageNumber, String categoryId, Integer hidden) {
        List<Article> articles = new ArrayList<Article>();
        
        try {
            Page<Article> page = repo.findByCategoryAndHiddenWithTagIds(tagIds, categoryId, hidden, PageRequest.of(pageNumber, 10, Sort.by("dateCreated").descending()));

            articles = page.getContent();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return articles;
    }

    public Long retrieveNumOfPagesByCategoryAndHiddenWithTagIds(List<String> tagIds, String categoryId, Integer hidden) {
        Long numOfPage = Long.valueOf(0);
        
        try {
            Page<Article> page = repo.findByCategoryAndHiddenWithTagIds(tagIds, categoryId, hidden, PageRequest.of(0, 10, Sort.by("dateCreated").descending()));

            numOfPage = Long.valueOf(page.getTotalPages());
        } catch (Exception e) {
            // TODO: handle exception
        }

        return numOfPage;
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

    public Long retrieveNumOfPagesWithFullTextSearchByTitleAndDescriptionAndContent(String searchStr) {
        Long numOfPage = Long.valueOf(0);
        
        try {
            Page<Article> page = repo.findWithFullTextSearchByTitleAndDescriptionAndContent(searchStr, PageRequest.of(0, 10));

            numOfPage = Long.valueOf(page.getTotalPages());
        } catch (Exception e) {
            // TODO: handle exception
        }

        return numOfPage;
    }

    public Long retrieveNumOfPagesWithFullTextSearchByTitleAndDescriptionAndContentAndByCategory(String searchStr, String categoryId) {
        Long numOfPage = Long.valueOf(0);

        try {
            Page<Article> page = repo.findWithFullTextSearchByTitleAndDescriptionAndContentAndByCategory(searchStr, categoryId, PageRequest.of(0, 10));

            numOfPage = Long.valueOf(page.getTotalPages());
        } catch (Exception e) {
            // TODO: handle exception
        }

        return numOfPage;
    }

    public Long retrieveNumOfPagesWithFullTextSearchByTitleAndDescriptionAndContentAndByDateCreatedBetween(String searchStr, String from, String to) {
        Long numOfPage = Long.valueOf(0);

        try {
            Page<Article> page = repo.findWithFullTextSearchByTitleAndDescriptionAndContentAndByDateCreatedBetween(searchStr, from, to, PageRequest.of(0, 10));

            numOfPage = Long.valueOf(page.getTotalPages());
        } catch (Exception e) {
            // TODO: handle exception
        }

        return numOfPage;
    }

    public Long retrieveNumOfPagesWithFullTextSearchByTitleAndDescriptionAndContentAndByDateCreatedBetweenAndCategory(String searchStr, String from, String to, String categoryId) {
        Long numOfPage = Long.valueOf(0);

        try {
            Page<Article> page = repo.findWithFullTextSearchByTitleAndDescriptionAndContentAndByDateCreatedBetweenAndCategory(searchStr, from, to, categoryId, PageRequest.of(0, 10));

            numOfPage = Long.valueOf(page.getTotalPages());
        } catch (Exception e) {
            // TODO: handle exception
        }

        return numOfPage;
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

