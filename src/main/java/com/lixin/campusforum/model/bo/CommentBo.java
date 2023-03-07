package com.lixin.campusforum.model.bo;

import com.lixin.campusforum.model.base.BaseBo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lixin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CommentBo extends BaseBo {
    private static final long serialVersionUID = -8427534442108288288L;
    private String commentId;
    private String authorId;
    private String author;
    private String face;
    private String content;
    private String topicId;
    private String replyCommentId;
    private String replyCommentAuthor;
    private String createTime;
    private String modifyTime;
}
