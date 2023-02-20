package com.lixin.campusforum.model.bo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lixin
 */
@Data
public class AddCommentBo {
    private String commentId;
    private String authorId;
    private String content;
    private String topicId;
    private String replyId;
    private String replyAuthor;
    private LocalDateTime createTime;
}
