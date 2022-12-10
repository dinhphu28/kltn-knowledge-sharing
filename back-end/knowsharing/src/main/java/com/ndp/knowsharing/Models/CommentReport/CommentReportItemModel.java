package com.ndp.knowsharing.Models.CommentReport;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentReportItemModel {
    private String id;

    private String commentId;

    // private Integer articleId;

    private String author;

    private LocalDateTime dateCreated;

    private String content;

    private String commentAuthor;

    private String commentContent;

    private String articleTitle;

    private String articleUrl;

    private Integer solved;
}
