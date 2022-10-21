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
@Table(name = "app_fd_category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private String id;

    @Column(name = "datecreated")
    private LocalDateTime dateCreated;

    @Column(name = "datemodifed")
    private LocalDateTime dateModified;

    @Column(name = "createdby")
    private String createdBy;

    @Column(name = "createdbyname")
    private String createdByName;

    @Column(name = "modifiedby")
    private String modifiedBy;

    @Column(name = "modifiedbyname")
    private String modifiedByName;

    @Column(name = "c_title")
    private String title;

    @Column(name = "c_description")
    private String description;

    @Column(name = "c_content")
    private String content;

    @Column(name = "c_audio_content")
    private String audioContent;

    @Column(name = "c_author")
    private String author;

    @Column(name = "c_url")
    private String url;

    @Column(name = "c_category")
    private String category;

    @Column(name = "c_thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "c_hidden")
    private Integer hidden;
}
