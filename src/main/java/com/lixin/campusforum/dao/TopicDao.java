package com.lixin.campusforum.dao;

import com.lixin.campusforum.model.bo.topic.RelatedMeListItemBo;
import com.lixin.campusforum.model.bo.topic.TopicBo;
import com.lixin.campusforum.model.bo.topic.TopicListItemBo;
import com.lixin.campusforum.model.bo.topic.TopicModifyBo;
import com.lixin.campusforum.model.entity.TopicDo;
import com.lixin.campusforum.model.query.RelatedMeQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lixin
 */
@Mapper
@Repository
public interface TopicDao {

    /**
     * ...
     *
     * @param topic ..
     * @return rows
     */
    int insert(TopicDo topic);

    /**
     * ...
     *
     * @param topicId ...
     * @return ...
     */
    TopicBo one(@Param("topicId") String topicId);

    /**
     * ...
     *
     * @return ...
     */
    int count();

    /**
     * ...
     *
     * @return list
     */
    List<TopicListItemBo> list();

    /**
     * ...
     *
     * @param modifyBo ...
     * @return ...
     */
    int update(TopicModifyBo modifyBo);

    /**
     * ...
     *
     * @param topicId ...
     * @return ...
     */
    int delete(String topicId);

    /**
     * selectRelatedMe
     *
     * @param query  ...
     * @param userId ...
     * @return java.util.List<com.lixin.campusforum.model.bo.topic.RelatedMeListItemBo>
     * @date 2023/3/7 16:15
     **/
    List<RelatedMeListItemBo> selectRelatedMe(@Param("query") RelatedMeQuery query, @Param("userId") String userId);

}
