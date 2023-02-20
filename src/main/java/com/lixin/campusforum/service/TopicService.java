package com.lixin.campusforum.service;

import com.lixin.campusforum.model.bo.TopicListItem;
import com.lixin.campusforum.model.form.TopicForm;
import com.lixin.campusforum.model.form.TopicModifyForm;
import com.lixin.campusforum.model.vo.TopicVo;

import java.util.List;

/**
 * @author lixin
 */
public interface TopicService {

    /**
     * ...
     *
     * @param form     ...
     * @param authorId ...
     * @return topic id.
     */
    String add(TopicForm form, String authorId);

    /**
     * ...
     *
     * @param page page number
     * @return ...
     */
    List<TopicListItem> list(int page);

    /**
     * ...
     *
     * @param topicId ...
     * @return ...
     */
    TopicVo get(String topicId);

    /**
     * ...
     *
     * @param form   ...
     * @param userId ...
     */
    void modify(TopicModifyForm form, String userId);

    /**
     * ...
     *
     * @param topicId ...
     * @param userId  ...
     */
    void remove(String topicId, String userId);

    /**
     * ...
     *
     * @return ...
     */
    Integer total();

}
