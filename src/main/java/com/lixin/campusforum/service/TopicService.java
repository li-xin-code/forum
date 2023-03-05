package com.lixin.campusforum.service;

import com.lixin.campusforum.common.result.DataResult;
import com.lixin.campusforum.common.result.NoDataResult;
import com.lixin.campusforum.model.form.TopicForm;
import com.lixin.campusforum.model.form.TopicModifyForm;
import com.lixin.campusforum.model.vo.topic.AddTopicVo;
import com.lixin.campusforum.model.vo.topic.TopicListVo;
import com.lixin.campusforum.model.vo.topic.TopicVo;

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
    DataResult<AddTopicVo> add(TopicForm form, String authorId);

    /**
     * ...
     *
     * @param page page number
     * @return ...
     */
    DataResult<TopicListVo> list(int page);

    /**
     * ...
     *
     * @param topicId ...
     * @return ...
     */
    DataResult<TopicVo> get(String topicId);

    /**
     * ...
     *
     * @param form   ...
     * @param userId ...
     * @return ...
     */
    NoDataResult modify(TopicModifyForm form, String userId);

    /**
     * ...
     *
     * @param topicId ...
     * @param userId  ...
     * @return ...
     */
    NoDataResult remove(String topicId, String userId);


}
