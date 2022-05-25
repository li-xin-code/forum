package com.lixin.campusforum.forum.model.form;

import lombok.Data;

/**
 * @author lixin
 */
@Data
public class ReplyForm {
    private String topicId;
    private String content;
    /**
     * 被回复的评论id
     */
    private String commentId;
}
