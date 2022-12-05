package com.ndp.knowsharing.Models.CommentReport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentReportCreateModel {
    private String commentId;

    private String author;

    private String content;
}
