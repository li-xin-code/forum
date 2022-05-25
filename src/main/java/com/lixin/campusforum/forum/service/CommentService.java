package com.lixin.campusforum.forum.service;

import com.lixin.campusforum.forum.model.form.CommentForm;
import com.lixin.campusforum.forum.model.form.ReplyForm;
import com.lixin.campusforum.forum.model.vo.CommentVo;

import java.util.List;

/**
 * @author lixin
 */
public interface CommentService {

    /**
     * 评论列表
     *
     * @param page    ...
     * @param topicId ...
     * @return ...
     */
    List<CommentVo> list(String topicId, int page);

    /**
     * 评论
     *
     * @param form     ...
     * @param authorId ...
     */
    void comment(CommentForm form, String authorId);

    /**
     * 回复
     *
     * @param form     ...
     * @param authorId ...
     */
    void reply(ReplyForm form, String authorId);

    /**
     * ...
     *
     * @param commentId ...
     * @param userId    ...
     */
    void remove(String commentId, String userId);

    /**
     * ...
     *
     * @param topicId ...
     * @return ...
     */
    Integer total(String topicId);
}
