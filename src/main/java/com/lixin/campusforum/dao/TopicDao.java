package com.lixin.campusforum.dao;

import com.lixin.campusforum.model.bo.topic.TopicBo;
import com.lixin.campusforum.model.bo.topic.TopicListBoItem;
import com.lixin.campusforum.model.bo.topic.TopicModifyBo;
import com.lixin.campusforum.model.entity.TopicDo;
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
    List<TopicListBoItem> list();

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

}
