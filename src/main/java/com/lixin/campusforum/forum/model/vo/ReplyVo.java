package com.lixin.campusforum.forum.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author lixin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ReplyVo extends CommentVo {
    private String replyId;
    private String replyAuthor;
}
