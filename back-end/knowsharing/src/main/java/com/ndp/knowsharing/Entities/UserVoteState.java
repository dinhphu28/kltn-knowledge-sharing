package com.ndp.knowsharing.Entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "app_fd_user_vote_state")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserVoteState {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private String id;

    @Column(name = "datecreated")
    private LocalDateTime dateCreated;

    @Column(name = "datemodified")
    private LocalDateTime dateModified;

    @Column(name = "createdby")
    private String createdBy;

    @Column(name = "createdbyname")
    private String createdByName;

    @Column(name = "modifiedby")
    private String modifiedBy;

    @Column(name = "modifiedbyname")
    private String modifiedByName;

    @Column(name = "c_article_id")
    private String articleId;

    @Column(name = "c_author")
    private String author;

    @Column(name = "c_vote_state")
    private Integer voteState;
}
