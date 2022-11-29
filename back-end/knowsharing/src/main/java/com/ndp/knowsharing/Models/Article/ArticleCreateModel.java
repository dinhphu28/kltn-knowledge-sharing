package com.ndp.knowsharing.Models.Article;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCreateModel {

    private String createdBy;

    private String createdByName;

    private String title;

    private String description;

    private String content;

    private String audioContent;

    private String author;

    // private String url;

    private String category;

    private String thumbnailUrl;

    // private Integer hidden;

    private List<String> tags;
}
