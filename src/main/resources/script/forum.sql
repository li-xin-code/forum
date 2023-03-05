drop database if exists forum;
create database forum;

use forum;

drop table if exists user_registration;
create table user_registration
(
    id          bigint auto_increment primary key,
    user_id     char(8) collate utf8_bin     not null comment '用户编号',
    username    varchar(12) collate utf8_bin not null comment '用户名',
    password    varchar(100)                 not null comment '密码',
    create_time datetime                     not null comment '注册时间',
    unique index index_user_id (user_id),
    unique index index_username (username)
) comment '用户登记表';

drop table if exists topic;
create table topic
(
    id          bigint auto_increment primary key,
    topic_id    char(8) collate utf8_bin not null comment '话题编号',
    author_id   char(8) collate utf8_bin not null comment '作者用户编号',
    title       varchar(20)              not null comment '标题',
    content     varchar(1000)            not null comment '话题内容',
    create_time datetime                 not null comment '创建时间',
    modify_time datetime                 not null comment '修改时间',
    unique index index_topic_id (topic_id)
) comment '话题表';

drop table if exists comment;
create table comment
(
    id                   bigint auto_increment primary key,
    comment_id           char(8) collate utf8_bin     not null comment '评论编号',
    author_id            char(8) collate utf8_bin     not null comment '评论作者用户编号',
    content              varchar(1000)                not null comment '评论内容',
    topic_id             char(8) collate utf8_bin     not null comment '评论所属话题编号',
    reply_comment_id     char(8) collate utf8_bin     not null comment '被回复评论编号',
    reply_comment_author varchar(12) collate utf8_bin not null comment '被回复评论作者用户编号',
    create_time          datetime                     not null comment '创建时间',
    modify_time          datetime                     not null comment '修改时间',
    unique index index_comment_id (comment_id)
) comment '评论表';

drop table if exists user_info;
create table user_info
(
    id          bigint auto_increment primary key,
    user_id     char(8) collate utf8_bin not null comment '用户编号',
    face        varchar(100)             not null comment '头像',
    gender      int                      not null comment '用户性别:0-默认；1：男；2：女；',
    user_sign   varchar(30)              not null comment '用户签名',
    create_time datetime                 not null comment '创建时间',
    unique index index_user_id (user_id)
) comment '用户信息表';

