package com.ndp.knowsharing.Models.ArticleReport;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleReportItemModel {
    private String id;

    private LocalDateTime dateCreated;

    private String articleId;

    private String author;

    private String content;

    private String articleTitle;

    private String articleUrl;

    private Integer isSolved;
}
