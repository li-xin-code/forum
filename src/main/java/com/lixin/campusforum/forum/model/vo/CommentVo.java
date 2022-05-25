package com.lixin.campusforum.forum.model.vo;

import lombok.Data;

/**
 * @author lixin
 */
@Data
public class CommentVo {
    private String commentId;

    /**
     * username
     */
    private String author;
    private String authorId;

    private String content;
    private String createTime;
}
