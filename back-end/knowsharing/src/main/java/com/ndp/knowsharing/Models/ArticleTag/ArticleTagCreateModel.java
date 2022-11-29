package com.ndp.knowsharing.Models.ArticleTag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleTagCreateModel {
    private String category;

    private String tagName;
}
