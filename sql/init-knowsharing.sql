create database knowsharing;

use knowsharing;

create table dir_user (
    id varchar(255) not null primary key,
    username varchar(255),
    password varchar(255),
    firstName varchar(255),
    lastName varchar(255),
    email varchar(255),
    avatar varchar(255),
    verified int,
    active int
);

create table dir_role (
    id varchar(255) not null primary key,
    name varchar(255),
    description varchar(255)
);

create table dir_user_role (
    roleId varchar(255) not null,
    userId varchar(255) not null,
    primary key (userId, roleId),
    constraint FK4E2710B9B7154794BE1CD801E3B33CF0
                           foreign key (roleId) references dir_role(id),
    constraint FKD9D4CFF4C50C41218FA0906B0F50A507
                           foreign key (userId) references dir_user(id)
);

-- ---

create table app_fd_category (
    id varchar(255) not null primary key,
    dateCreated datetime,
    dateModified datetime,
    createdBy varchar(255),
    createdByName varchar(255),
    modifiedBy varchar(255),
    modifiedByName varchar(255),
    c_name varchar(255)
);

create table app_fd_article (
    id varchar(255) not null primary key,
    dateCreated datetime,
    dateModified datetime,
    createdBy varchar(255),
    createdByName varchar(255),
    modifiedBy varchar(255),
    modifiedByName varchar(255),
    c_title varchar(255),
    c_description longtext,
    c_content longtext,
    c_audio_content longtext,
    c_author varchar(255) not null references dir_user(id),
    c_url varchar(255) not null,
    c_category varchar(255) not null references app_fd_category(id),
    c_thumbnail_url longtext not null,
    c_hidden int default 0 not null
);

create table app_fd_comment (
    id varchar(255) not null primary key,
    dateCreated datetime,
    dateModified datetime,
    createdBy varchar(255),
    createdByName varchar(255),
    modifiedBy varchar(255),
    modifiedByName varchar(255),
    c_author varchar(255) not null references dir_user(id),
    c_article_id varchar(255) not null references app_fd_article(id),
    c_content longtext not null,
    c_hidden int default 0 not null
);

create table app_fd_user_vote_state (
    id varchar(255) not null primary key,
    dateCreated datetime,
    dateModified datetime,
    createdBy varchar(255),
    createdByName varchar(255),
    modifiedBy varchar(255),
    modifiedByName varchar(255),
    c_article_id varchar(255) not null references app_fd_article(id),
    c_author varchar(255) not null references dir_user(id),
    c_vote_state int default 0,

    unique (c_article_id, c_author)
);

create table app_fd_interaction (
    c_article_id varchar(255) not null references app_fd_article(id),
    c_comment_score int not null,
    c_vote_score int not null,

    primary key (c_article_id)
);

create table app_fd_report_category (
    id varchar(255) not null primary key,
    dateCreated datetime,
    dateModified datetime,
    createdBy varchar(255),
    createdByName varchar(255),
    modifiedBy varchar(255),
    modifiedByName varchar(255),
    c_name varchar(255),
    c_type varchar(255)
);

create table app_fd_article_report (
    id varchar(255) not null primary key,
    dateCreated datetime,
    dateModified datetime,
    createdBy varchar(255),
    createdByName varchar(255),
    modifiedBy varchar(255),
    modifiedByName varchar(255),
    c_article_id varchar(255) not null references app_fd_article(id),
    c_author varchar(255) not null references dir_user(id),
    c_content longtext not null,
    c_is_solved int default 0 not null
);

create table app_fd_article_report_category_rel (
    id varchar(255) not null primary key,
    c_article_report_id varchar(255) not null references app_fd_article_report(id),
    c_report_category_id varchar(255) not null references app_fd_report_category(id),

    unique (c_article_report_id, c_report_category_id)
);

create table app_fd_comment_report (
    id varchar(255) not null primary key,
    dateCreated datetime,
    dateModified datetime,
    createdBy varchar(255),
    createdByName varchar(255),
    modifiedBy varchar(255),
    modifiedByName varchar(255),
    c_comment_id varchar(255) not null references app_fd_comment(id),
    c_author varchar(255) not null references dir_user(id),
    c_content longtext not null,
    c_is_solved int default 0 not null
);

create table app_fd_comment_report_category_rel (
    id varchar(255) not null primary key,
    c_comment_report_id varchar(255) not null references app_fd_comment_report(id),
    c_report_category_id varchar(255) not null references app_fd_report_category(id),

    unique (c_comment_report_id, c_report_category_id)
);

create table app_fd_user_report (
    id varchar(255) not null primary key,
    dateCreated datetime,
    dateModified datetime,
    createdBy varchar(255),
    createdByName varchar(255),
    modifiedBy varchar(255),
    modifiedByName varchar(255),
    c_user_id varchar(255) not null references dir_user(id),
    c_author varchar(255) not null references dir_user(id),
    c_content longtext not null,
    c_is_solved int default 0 not null
);

create table app_fd_user_report_category_rel (
    id varchar(255) not null primary key,
    c_user_report_id varchar(255) not null references app_fd_user_report(id),
    c_report_category_id varchar(255) not null references app_fd_report_category(id),

    unique (c_user_report_id, c_report_category_id)
);
