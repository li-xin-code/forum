package com.lixin.campusforum.forum.model.bo;

import lombok.Data;

/**
 * @author lixin
 */
@Data
public class CommentBo {
    private String commentId;
    private String authorId;
    private String author;
    private String content;
    private String topicId;
    private String replyId;
    private String replyAuthor;
    private String createTime;
}
