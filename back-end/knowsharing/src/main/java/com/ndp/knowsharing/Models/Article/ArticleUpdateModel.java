package com.ndp.knowsharing.Models.Article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleUpdateModel {
    private String modifiedBy;

    private String modifiedByName;

    private String title;

    private String description;

    private String content;

    private String audioContent;

    private String category;

    private String thumbnailUrl;
}
