package com.lixin.campusforum.service;

import com.lixin.campusforum.common.result.DataResult;
import com.lixin.campusforum.common.result.NoDataResult;
import com.lixin.campusforum.model.form.CommentForm;
import com.lixin.campusforum.model.vo.comment.CommentListVo;

/**
 * @author lixin
 */
public interface CommentService {

    /**
     * 评论列表
     *
     * @param topicId ...
     * @param page    ...
     * @return ...
     */
    DataResult<CommentListVo> list(String topicId, int page);

    /**
     * 评论
     *
     * @param form     ...
     * @param authorId ...
     * @return result
     */
    NoDataResult comment(CommentForm form, String authorId);

    /**
     * ...
     *
     * @param commentId ...
     * @param userId    ...
     * @return result
     */
    NoDataResult remove(String commentId, String userId);
}
