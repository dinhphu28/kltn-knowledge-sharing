package com.ndp.knowsharing.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleVoteScore {
    @Id
    @Column(name = "c_article_id")
    private String articleId;

    @Column(name = "sum_vote_state")
    private Integer sumVoteState;
}
