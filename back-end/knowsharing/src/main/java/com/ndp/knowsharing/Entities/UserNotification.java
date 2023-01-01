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
@Table(name = "app_fd_user_notification")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserNotification {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private String id;

    @Column(name = "datecreated")
    private LocalDateTime dateCreated;

    @Column(name = "createdby")
    private String createdBy;

    @Column(name = "createdbyname")
    private String createdByName;

    @Column(name = "c_for_user")
    private String forUser;

    @Column(name = "c_title")
    private String title;

    @Column(name = "c_content")
    private String content;

    @Column(name = "c_hidden")
    private Integer hidden;
}
