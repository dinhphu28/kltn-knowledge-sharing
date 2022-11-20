package com.ndp.knowsharing.Entities;

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
@Table(name = "app_fd_article_tag")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleTag {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private String id;

    @Column(name = "c_category")
    private String category;

    @Column(name = "c_tag_name")
    private String tagName;

    @Column(name = "c_active")
    private Integer isActive;
}
