package com.ndp.knowsharing.Models.Article;

import java.time.LocalDateTime;

import com.ndp.knowsharing.Entities.Article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleItemReturnModel {
    private String id;

    private LocalDateTime dateCreated;

    private LocalDateTime dateModified;

    private String createdBy;

    private String createdByName;

    private String modifiedBy;

    private String modifiedByName;

    private String title;

    private String description;

    private String content;

    private String audioContent;

    private String author;

    private String url;

    private String category;

    private String thumbnailUrl;

    private Integer hidden;

    private Integer voteScore;

    public ArticleItemReturnModel(Article article, Integer voteScore) {
        this.id = article.getId();

        this.dateCreated = article.getDateCreated();

        this.dateModified = article.getDateModified();

        this.createdBy = article.getCreatedBy();

        this.createdByName = article.getCreatedByName();

        this.modifiedBy = article.getModifiedBy();

        this.modifiedByName = article.getModifiedByName();

        this.title = article.getTitle();

        this.description = article.getDescription();

        this.content = article.getContent();

        this.audioContent = article.getAudioContent();

        this.author = article.getAuthor();

        this.url = article.getUrl();

        this.category = article.getCategory();

        this.thumbnailUrl = article.getThumbnailUrl();

        this.hidden = article.getHidden();
    }
}
