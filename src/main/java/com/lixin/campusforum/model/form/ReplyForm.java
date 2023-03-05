package com.lixin.campusforum.model.form;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lixin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ReplyForm extends CommentForm {
    /**
     * 被回复的评论id
     */
    private String commentId;
}
