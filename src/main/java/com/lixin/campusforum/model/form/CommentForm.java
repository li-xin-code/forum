package com.lixin.campusforum.model.form;

import lombok.Data;

/**
 * @author lixin
 */
@Data
public class CommentForm {
    /**
     * 话题编号
     */
    private String topicId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 被回复的评论编号
     */
    private String replyCommentId;
}
