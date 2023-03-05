package com.lixin.campusforum.model.vo.comment;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author lixin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ReplyVo extends CommentVo {
    private static final long serialVersionUID = 744508564898802578L;
    private String replyCommentId;
    private String replyCommentAuthor;

    public ReplyVo(String replyCommentId, String replyCommentAuthor) {
        this.replyCommentId = replyCommentId;
        this.replyCommentAuthor = replyCommentAuthor;
    }
}
