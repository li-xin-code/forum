package com.lixin.campusforum.dao;

import com.lixin.campusforum.model.bo.AddCommentBo;
import com.lixin.campusforum.model.bo.CommentBo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lixin
 */
@Mapper
@Repository
public interface CommentDao {

    /**
     * ...
     *
     * @param commentId ...
     * @return rows
     */
    int exists(String commentId);

    /**
     * ...
     *
     * @param comment ...
     * @return ...
     */
    int insert(AddCommentBo comment);

    /**
     * ...
     *
     * @param commentId ...
     * @return ...
     */
    int delete(String commentId);

    /**
     * ...
     *
     * @param topicId ...
     * @param offset  ...
     * @param rows    ...
     * @return ...
     */
    List<CommentBo> list(String topicId, int offset, int rows);

    /**
     * ...
     *
     * @param commentId ...
     * @return ...
     */
    CommentBo one(String commentId);

    /**
     * ...
     *
     * @param topicId ...
     * @return 话题评论数
     */
    int count(String topicId);
}
