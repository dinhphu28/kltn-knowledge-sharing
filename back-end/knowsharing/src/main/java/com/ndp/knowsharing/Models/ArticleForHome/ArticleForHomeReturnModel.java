package com.ndp.knowsharing.Models.ArticleForHome;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleForHomeReturnModel {

    private String id;

    private LocalDateTime dateCreated;

    private LocalDateTime  dateModified;

    private String type;

    private String articleId;

    private LocalDateTime dateCreatedArticle;

    private String title;

    private String content;

    private String description;

    private String author;

    private String url;

    private String category;

    private String thumbnailUrl;

    private Integer hidden;
}
