package com.lixin.campusforum.forum.service.impl;

import com.lixin.campusforum.common.config.PageConfig;
import com.lixin.campusforum.common.exception.NotExpectedException;
import com.lixin.campusforum.common.exception.SystemException;
import com.lixin.campusforum.common.utils.SystemUtils;
import com.lixin.campusforum.forum.dao.TopicDao;
import com.lixin.campusforum.forum.model.bo.TopicBo;
import com.lixin.campusforum.forum.model.bo.TopicListItem;
import com.lixin.campusforum.forum.model.bo.TopicModifyBo;
import com.lixin.campusforum.forum.model.form.TopicForm;
import com.lixin.campusforum.forum.model.form.TopicModifyForm;
import com.lixin.campusforum.forum.model.vo.TopicVo;
import com.lixin.campusforum.forum.service.TopicService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lixin
 */
@Service("topicService")
public class TopicServiceImpl implements TopicService {

    private final TopicDao topicDao;
    private final PageConfig pageConfig;

    public TopicServiceImpl(TopicDao topicDao, PageConfig pageConfig) {
        this.topicDao = topicDao;
        this.pageConfig = pageConfig;
    }

    @Override
    public String add(TopicForm form, String authorId) {
        TopicBo topic = new TopicBo();
        String topicId = SystemUtils.uuid();
        topic.setTopicId(topicId);
        topic.setAuthorId(authorId);
        topic.setTitle(form.getTitle());
        topic.setContent(form.getContent());
        LocalDateTime now = LocalDateTime.now();
        topic.setCreateTime(now);
        int rows = topicDao.insert(topic);
        if (rows != 1) {
            throw new NotExpectedException("affected rows should 1 but " + rows);
        }
        return topicId;
    }

    @Override
    public List<TopicListItem> list(int page) {
        Integer rows = pageConfig.getTopic();
        int offset = page > 0 ? rows * (page - 1) : 0;
        return topicDao.list(offset, rows);
    }

    @Override
    public TopicVo get(String topicId) {
        TopicBo bo = topicDao.one(topicId);
        if (bo == null) {
            throw new NotExpectedException("topic not found.");
        }
        return b2v(bo);
    }

    @Override
    public void modify(TopicModifyForm form, String userId) {
        TopicVo topic = get(form.getTopicId());
        if (!topic.getAuthorId().equals(userId)) {
            throw new NotExpectedException("Only the author can modify the topic.");
        }
        TopicModifyBo modify = new TopicModifyBo();
        modify.setTopicId(form.getTopicId());
        modify.setTitle(form.getTitle());
        modify.setContent(form.getContent());
        int rows = topicDao.update(modify);
        if (rows != 1) {
            throw new NotExpectedException("update result is not 1.");
        }
    }

    @Override
    public void remove(String topicId, String userId) {
        TopicVo topic = get(topicId);
        if (topic.getAuthorId().equals(userId)) {
            int rows = topicDao.delete(topicId);
            if (rows != 1) {
                throw new NotExpectedException("affected rows should 1 but " + rows);
            }
        } else {
            throw new SystemException("remove topic fail: Actions do not come from the author.");
        }
    }

    @Override
    public Integer total() {
        return topicDao.count();
    }

    private TopicVo b2v(TopicBo bo) {
        if (bo == null) {
            return null;
        }
        TopicVo vo = new TopicVo();
        vo.setTopicId(bo.getTopicId());
        vo.setTitle(bo.getTitle());
        vo.setContent(bo.getContent());
        vo.setCreateTime(SystemUtils.dateFormat(bo.getCreateTime()));
        vo.setAuthor(bo.getAuthor());
        vo.setAuthorId(bo.getAuthorId());
        return vo;
    }
}
