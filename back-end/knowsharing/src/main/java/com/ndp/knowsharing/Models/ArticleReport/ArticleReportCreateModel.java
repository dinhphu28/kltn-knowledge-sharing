package com.ndp.knowsharing.Models.ArticleReport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleReportCreateModel {
    private String articleId;

    private String author;

    private String content;
}
