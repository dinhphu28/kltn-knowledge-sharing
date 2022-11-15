package com.ndp.knowsharing.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "app_fd_interaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Interaction {
    
    @Id
    @Column(name = "c_article_id")
    private String articleId;

    @Column(name = "c_comment_score")
    private Integer commentScore;

    @Column(name = "c_vote_score")
    private Integer voteScore;
}
