package com.ndp.knowsharing.Models.ArticleTag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleTagReturnModel {
    private String id;

    private String category;

    private String categoryName;

    private String tagName;

    private Integer isActive;
}
