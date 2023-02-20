package com.lixin.campusforum.dao;

import com.lixin.campusforum.model.bo.TopicBo;
import com.lixin.campusforum.model.bo.TopicListItem;
import com.lixin.campusforum.model.bo.TopicModifyBo;
import org.apache.ibatis.annotations.Mapper;
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
    int insert(TopicBo topic);

    /**
     * ...
     *
     * @param topicId ...
     * @return ...
     */
    TopicBo one(String topicId);

    /**
     * ...
     *
     * @return ...
     */
    int count();

    /**
     * ...
     *
     * @param offset 偏移位
     * @param rows   行数
     * @return list
     */
    List<TopicListItem> list(int offset, int rows);


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
