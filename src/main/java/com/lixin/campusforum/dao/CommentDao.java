package com.lixin.campusforum.dao;

import com.lixin.campusforum.model.bo.CommentBo;
import com.lixin.campusforum.model.entity.CommentDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
    int insert(CommentDo comment);

    /**
     * ...
     *
     * @param commentId ...
     * @param userId    ...
     * @return ...
     */
    int delete(@Param("commentId") String commentId, @Param("userId") String userId);

    /**
     * ...
     *
     * @param topicId ...
     * @return ...
     */
    List<CommentBo> list(String topicId);

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

    /**
     * deleteByTopicId
     *
     * @param topicId ...
     * @date 2023/3/3 23:04
     **/
    void deleteByTopicId(@Param("topicId") String topicId);
}
