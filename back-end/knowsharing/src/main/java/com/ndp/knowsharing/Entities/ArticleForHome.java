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
@Table(name = "app_fd_articles_for_home")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleForHome {
    @Id
    // @GeneratedValue(generator = "UUID")
    // @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private String id;

    @Column(name = "c_article_id")
    private String articleId;

    @Column(name = "datecreated")
    private LocalDateTime dateCreated;

    @Column(name = "datemodified")
    private LocalDateTime dateModified;

    @Column(name = "c_type")
    private String type;
}
