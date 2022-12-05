package com.ndp.knowsharing.Controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ndp.knowsharing.Entities.Article;
import com.ndp.knowsharing.Entities.ArticleReport;
import com.ndp.knowsharing.Models.ArticleReport.ArticleReportCreateModel;
import com.ndp.knowsharing.Models.ArticleReport.ArticleReportItemModel;
import com.ndp.knowsharing.Models.ArticleReport.ArticleReportUpdateModel;
import com.ndp.knowsharing.Services.ArticleReportService;
import com.ndp.knowsharing.Services.ArticleService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/article-reports")
public class ArticleReportController {
    @Autowired
    private ArticleReportService articleReportService;

    @Autowired
    private ArticleService articleService;

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> retrieveAll(@RequestParam(value = "solved", required = true) Boolean solved) {
        ResponseEntity<Object> entity;

        List<ArticleReportItemModel> articleReportItemModels = new ArrayList<ArticleReportItemModel>();

        List<ArticleReport> articleReports = articleReportService.retrieveBySolved(solved ? 1 : 0);

        for (ArticleReport articleReport : articleReports) {
            
            Article article = articleService.retrieveOne(articleReport.getArticleId());

            ArticleReportItemModel tmpARIModel = new ArticleReportItemModel(articleReport.getId(), articleReport.getDateCreated(), articleReport.getArticleId(), article.getAuthor(), articleReport.getContent(), article.getTitle(), article.getUrl(), articleReport.getIsSolved());

            articleReportItemModels.add(tmpARIModel);
        }

        entity = new ResponseEntity<>(articleReportItemModels, HttpStatus.OK);

        return entity;
    }

    @GetMapping(
        value = "/{articleId}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )

    public ResponseEntity<Object> retrieveAllByArticleId(@PathVariable("articleId") String articleId) {
        ResponseEntity<Object> entity;

        List<ArticleReport> articleReports = articleReportService.retrieveAllByArticleId(articleId);

        entity = new ResponseEntity<>(articleReports, HttpStatus.OK);

        return entity;
    }

    @PostMapping(
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> createNewReport(@RequestBody ArticleReportCreateModel articleReportCreateModel) {
        ResponseEntity<Object> entity;

        if(articleReportCreateModel.getArticleId() == null || articleReportCreateModel.getAuthor() == null || articleReportCreateModel.getContent() == null) {
            entity = new ResponseEntity<>("{ \"Notice\": \"Failed\" }", HttpStatus.BAD_REQUEST);
        } else {

            LocalDateTime now = LocalDateTime.now();

            ArticleReport tmpToSave = new ArticleReport(null, now, now, "", "", "", "", articleReportCreateModel.getArticleId(), articleReportCreateModel.getAuthor(), articleReportCreateModel.getContent(), 0);

            ArticleReport tmpSaved = articleReportService.createOne(tmpToSave);

            if(tmpSaved == null) {
                entity = new ResponseEntity<>("{ \"Notice\": \"Failed\" }", HttpStatus.BAD_REQUEST);
            } else {
                entity = new ResponseEntity<>(tmpToSave, HttpStatus.CREATED);
            }
        }

        return entity;
    }

    @PutMapping(
        value = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> markSolvedOrUnSolve(@PathVariable("id") String id, @RequestBody ArticleReportUpdateModel articleReportUpdateModel) {
        ResponseEntity<Object> entity;

        ArticleReport tmpToSave = articleReportService.retrieveById(id);

        if(tmpToSave == null) {
            entity = new ResponseEntity<>("{ \"Notice\": \"Not found\" }", HttpStatus.NOT_FOUND);
        } else {
            tmpToSave.setIsSolved(articleReportUpdateModel.getIsSolved() ? 1 : 0);

            ArticleReport tmpSaved = articleReportService.updateOne(tmpToSave);

            if(tmpSaved == null) {
                entity = new ResponseEntity<>("{ \"Notice\": \"Failed\" }", HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                entity = new ResponseEntity<>(tmpSaved, HttpStatus.OK);
            }
        }

        return entity;
    }
}
