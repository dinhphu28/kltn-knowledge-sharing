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
@Table(name = "app_fd_user_report_category_rel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserReportCategory {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private String id;

    @Column(name = "c_user_report_id")
    private String userReportId;

    @Column(name = "c_report_category_id")
    private String reportCategoryId;
}
