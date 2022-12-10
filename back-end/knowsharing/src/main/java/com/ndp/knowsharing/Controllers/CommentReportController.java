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
import com.ndp.knowsharing.Entities.Comment;
import com.ndp.knowsharing.Entities.CommentReport;
import com.ndp.knowsharing.Models.CommentReport.CommentReportCreateModel;
import com.ndp.knowsharing.Models.CommentReport.CommentReportItemModel;
import com.ndp.knowsharing.Models.CommentReport.CommentReportUpdateModel;
import com.ndp.knowsharing.Services.ArticleService;
import com.ndp.knowsharing.Services.CommentReportService;
import com.ndp.knowsharing.Services.CommentService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/comment-reports")
public class CommentReportController {
    @Autowired
    private CommentReportService commentReportService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> retrieveAll(@RequestParam(value = "solved", required = true) Boolean solved) {
        ResponseEntity<Object> entity;

        List<CommentReportItemModel> commentReportItemModels = new ArrayList<CommentReportItemModel>();

        List<CommentReport> commentReports = commentReportService.retrieveBySolved(solved ? 1 : 0);

        for (CommentReport commentReport : commentReports) {
            
            Comment comment = commentService.retrieveById(commentReport.getCommentId());

            Article article = articleService.retrieveOne(comment.getArticleId());
            
            CommentReportItemModel tmpCRIModel = new CommentReportItemModel(commentReport.getId(), commentReport.getCommentId(), commentReport.getAuthor(), commentReport.getDateCreated(), commentReport.getContent(), comment.getAuthor(), comment.getContent(), article.getTitle(), article.getUrl(), commentReport.getIsSolved());

            commentReportItemModels.add(tmpCRIModel);
        }

        entity = new ResponseEntity<>(commentReportItemModels, HttpStatus.OK);

        return entity;
    }

    @GetMapping(
        value = "/{commentId}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> retrieveAllByCommentId(@PathVariable("commentId") String commentId) {
        ResponseEntity<Object> entity;

        List<CommentReport> commentReports = commentReportService.retrieveAllByCommentId(commentId);

        entity = new ResponseEntity<>(commentReports, HttpStatus.OK);

        return entity;
    }

    @PostMapping(
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> createNewReport(@RequestBody CommentReportCreateModel commentReport) {
        ResponseEntity<Object> entity;

        if(commentReport.getCommentId() == null || commentReport.getAuthor() == null || commentReport.getContent() == null) {

            entity = new ResponseEntity<>("{ \"Notice\": \"Failed\" }", HttpStatus.BAD_REQUEST);

        } else {
            // CommentReport tmpCR = commentReport.toCommentReport(myDateTimeUtils.getCurrentDate(), myDateTimeUtils.getCurrentTime());

            LocalDateTime now = LocalDateTime.now();

            CommentReport tmpToSave = new CommentReport(null, now, now, "", "", "", "", commentReport.getCommentId(), commentReport.getAuthor(), commentReport.getContent(), 0);

            CommentReport tmpSaved = commentReportService.createOne(tmpToSave);

            if(tmpSaved == null) {
                entity = new ResponseEntity<>("{ \"Notice\": \"Failed\" }", HttpStatus.BAD_REQUEST);
            } else {
                entity = new ResponseEntity<>(tmpSaved, HttpStatus.CREATED);
            }
        }

        return entity;
    }

    @PutMapping(
        value = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> markSolvedOrUnsolved(@PathVariable("id") String id, @RequestBody CommentReportUpdateModel commentReportUpdateModel) {
        ResponseEntity<Object> entity;

        CommentReport tmpToSave = commentReportService.retrieveById(id);

        if(tmpToSave == null) {
            entity = new ResponseEntity<>("{ \"Notice\": \"Not found\" }", HttpStatus.NOT_FOUND);
        } else {
            tmpToSave.setIsSolved(commentReportUpdateModel.getSolved() ? 1 : 0);

            CommentReport tmpSaved = commentReportService.updateOne(tmpToSave);

            if(tmpSaved == null) {
                entity = new ResponseEntity<>("{ \"Notice\": \"Failed\" }", HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                entity = new ResponseEntity<>(tmpSaved, HttpStatus.OK);
            }
        }

        return entity;
    }
}
