package com.lixin.campusforum.model.entity;

import com.lixin.campusforum.model.entity.base.BaseDo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 评论表
 *
 * @author lxin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CommentDo extends BaseDo {

    private static final long serialVersionUID = -9084212008325797139L;
    /**
     * 评论编号
     */
    private String commentId;

    /**
     * 评论作者用户编号
     */
    private String authorId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论所属话题编号
     */
    private String topicId;

    /**
     * 被回复评论编号
     */
    private String replyCommentId;

    /**
     * 被回复评论作者用户编号
     */
    private String replyCommentAuthor;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;
}