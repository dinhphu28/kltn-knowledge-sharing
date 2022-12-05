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

create table app_fd_article_tag (
    id varchar(255) not null primary key,
    c_category varchar(255) not null,
    c_tag_name varchar(255) not null,

    constraint FK722918EBDE9E4AE1AAF72B53F5EA4644
                            foreign key (c_category) references app_fd_category(id)
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
    c_author varchar(255) not null,
    c_url varchar(255) not null,
    c_category varchar(255) not null,
    c_thumbnail_url longtext not null,
    c_hidden int default 0 not null,

    constraint FKE0D32E80FAA44DA393F0447F25798257
                            foreign key (c_author) references dir_user(id),
    constraint FK95DD882CC0BF4EDDA2CE04A26A5D220E
                            foreign key (c_category) references app_fd_category(id)
);

create table app_fd_article_tag_rel (
    id varchar(255) not null primary key,
    c_tag_id varchar(255) not null,
    c_article_id varchar(255) not null,

    constraint FK77376AC6FDF04BAFB24036B41F8C5CCB
                            foreign key (c_article_id) references app_fd_article(id),
    constraint FK3F984925FC614A029BC695F5780C70C0
                            foreign key (c_tag_id) references app_fd_article_tag(id)
);

create table app_fd_comment (
    id varchar(255) not null primary key,
    dateCreated datetime,
    dateModified datetime,
    createdBy varchar(255),
    createdByName varchar(255),
    modifiedBy varchar(255),
    modifiedByName varchar(255),
    c_author varchar(255) not null,
    c_article_id varchar(255) not null,
    c_content longtext not null,
    c_hidden int default 0 not null,

    constraint FK5B6688F29DF84B6AB5B6E22F360E8276
                            foreign key (c_author) references dir_user(id),
    constraint FKF156FDE5ADC647B68D97D2C126190B7B
                            foreign key (c_article_id) references app_fd_article(id)
);

create table app_fd_user_vote_state (
    id varchar(255) not null primary key,
    dateCreated datetime,
    dateModified datetime,
    createdBy varchar(255),
    createdByName varchar(255),
    modifiedBy varchar(255),
    modifiedByName varchar(255),
    c_article_id varchar(255) not null,
    c_author varchar(255) not null,
    c_vote_state int default 0,

    unique (c_article_id, c_author),

    constraint FKDEEBFD1F79F340D9812AAC109E992636
                            foreign key (c_article_id) references app_fd_article(id),
    constraint FKB82D06AE979D440C8D482CA15FF8145C
                            foreign key (c_author) references dir_user(id)
);

create table app_fd_interaction (
    c_article_id varchar(255) not null,
    c_comment_score int not null,
    c_vote_score int not null,

    primary key (c_article_id),

    constraint FK349797B931CC4FF492088A1B8F943C68
                            foreign key (c_article_id) references app_fd_article(id)
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
    c_article_id varchar(255) not null,
    c_author varchar(255) not null,
    c_content longtext not null,
    c_is_solved int default 0 not null,

    constraint FK539CA7D17EDF43EA94D4BC8AE2E69562
                            foreign key (c_article_id) references app_fd_article(id),
    constraint FKC704090104394E7295A87670CAA0396B
                            foreign key (c_author) references dir_user(id)
);

create table app_fd_article_report_category_rel (
    id varchar(255) not null primary key,
    c_article_report_id varchar(255) not null,
    c_report_category_id varchar(255) not null,

    unique (c_article_report_id, c_report_category_id),

    constraint FK73B8F2BBF4EC4EC09C1D9591C14BA006
                            foreign key (c_article_report_id) references app_fd_article_report(id),
    constraint FK7736332C414F444894D9C65EEAFE7A9D
                            foreign key (c_report_category_id) references app_fd_report_category(id)
);

create table app_fd_comment_report (
    id varchar(255) not null primary key,
    dateCreated datetime,
    dateModified datetime,
    createdBy varchar(255),
    createdByName varchar(255),
    modifiedBy varchar(255),
    modifiedByName varchar(255),
    c_comment_id varchar(255) not null,
    c_author varchar(255) not null,
    c_content longtext not null,
    c_is_solved int default 0 not null,

    constraint FKE2397A934C85476780152529E2027134
                            foreign key (c_comment_id) references app_fd_comment(id),
    constraint FK65AD022C83AD4B0490CB9159DCDB5211
                            foreign key (c_author) references dir_user(id)
);

create table app_fd_comment_report_category_rel (
    id varchar(255) not null primary key,
    c_comment_report_id varchar(255) not null,
    c_report_category_id varchar(255) not null,

    unique (c_comment_report_id, c_report_category_id),

    constraint FKE15888246653419FA2987FE4B0971FAC
                            foreign key (c_comment_report_id) references app_fd_comment_report(id),
    constraint FK7424DB5A34214FF5817C185D7C512BB1
                            foreign key (c_report_category_id) references app_fd_report_category(id)
);

create table app_fd_user_report (
    id varchar(255) not null primary key,
    dateCreated datetime,
    dateModified datetime,
    createdBy varchar(255),
    createdByName varchar(255),
    modifiedBy varchar(255),
    modifiedByName varchar(255),
    c_user_id varchar(255) not null,
    c_author varchar(255) not null,
    c_content longtext not null,
    c_is_solved int default 0 not null,

    constraint FK1996E205C2C34F7196B27B82F527ECA8
                            foreign key (c_user_id) references dir_user(id),
    constraint FK04D01B8B643F42BA8412E312B6A6EB75
                            foreign key (c_author) references dir_user(id)
);

create table app_fd_user_report_category_rel (
    id varchar(255) not null primary key,
    c_user_report_id varchar(255) not null,
    c_report_category_id varchar(255) not null,

    unique (c_user_report_id, c_report_category_id),

    constraint FK7A3728A2C0CF41ED97311F9BCC205AAD
                            foreign key (c_user_report_id) references app_fd_user_report(id),
    constraint FKF378EAF0A5AF4D4789AE8272934F0754
                            foreign key (c_report_category_id) references app_fd_report_category(id)
);

insert into dir_user
(id, username, password, firstName, lastName, email, avatar, verified, active) VALUES
('admin', 'admin', '$2a$10$jLTZ9.FMCcnwVqXQDQhTou3GBN3LZo47Wef1pjiWCNyU18vvN//R2', 'Admin', 'Admin', null, null, 0, 1);

insert into dir_role
(id, name, description) VALUES
('admin', 'admin', 'admin');
insert into dir_role
(id, name, description) VALUES
('mod', 'mod', 'mod');
insert into dir_role
(id, name, description) VALUES
('norm', 'norm', 'norm');

insert into dir_user_role
(roleId, userId) VALUES
('admin', 'admin');