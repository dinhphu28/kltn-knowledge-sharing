package com.ndp.knowsharing.Models.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateModel {

    private String author;

    private String content;

    // private String audioContent;
}
