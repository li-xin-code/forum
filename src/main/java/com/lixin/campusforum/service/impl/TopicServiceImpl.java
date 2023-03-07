package com.lixin.campusforum.service.impl;

import com.github.pagehelper.PageHelper;
import com.lixin.campusforum.common.exception.ForumSystemException;
import com.lixin.campusforum.common.exception.NotExpectedException;
import com.lixin.campusforum.common.result.DataResult;
import com.lixin.campusforum.common.result.NoDataResult;
import com.lixin.campusforum.common.utils.ForumSystemUtils;
import com.lixin.campusforum.common.utils.ResultUtils;
import com.lixin.campusforum.config.PageConfig;
import com.lixin.campusforum.dao.CommentDao;
import com.lixin.campusforum.dao.TopicDao;
import com.lixin.campusforum.model.bo.topic.RelatedMeListItemBo;
import com.lixin.campusforum.model.bo.topic.TopicBo;
import com.lixin.campusforum.model.bo.topic.TopicListItemBo;
import com.lixin.campusforum.model.bo.topic.TopicModifyBo;
import com.lixin.campusforum.model.entity.TopicDo;
import com.lixin.campusforum.model.form.TopicForm;
import com.lixin.campusforum.model.form.TopicModifyForm;
import com.lixin.campusforum.model.query.RelatedMeQuery;
import com.lixin.campusforum.model.vo.topic.*;
import com.lixin.campusforum.service.TopicService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.lixin.campusforum.common.utils.ForumSystemUtils.dateFormat;

/**
 * @author lixin
 */
@Service("topicService")
public class TopicServiceImpl implements TopicService {

    private final TopicDao topicDao;
    private final PageConfig pageConfig;
    private final CommentDao commentDao;

    public TopicServiceImpl(TopicDao topicDao, PageConfig pageConfig, CommentDao commentDao) {
        this.topicDao = topicDao;
        this.pageConfig = pageConfig;
        this.commentDao = commentDao;
    }

    @Override
    public DataResult<AddTopicVo> add(TopicForm form, String authorId) {
        TopicDo topic = new TopicDo();
        String topicId = ForumSystemUtils.uuid();
        topic.setTopicId(topicId);
        topic.setAuthorId(authorId);
        topic.setTitle(form.getTitle());
        topic.setContent(form.getContent());
        LocalDateTime now = LocalDateTime.now();
        topic.setCreateTime(now);
        topic.setModifyTime(now);
        int rows = topicDao.insert(topic);
        if (rows != 1) {
            throw new NotExpectedException("affected rows should 1 but " + rows);
        }
        AddTopicVo vo = new AddTopicVo();
        vo.setTopicId(topicId);
        return ResultUtils.ok(vo);
    }

    @Override
    public DataResult<TopicListVo> list(int page) {
        PageHelper.startPage(page, pageConfig.getTopicPageSize());
        List<TopicListItemBo> bos = topicDao.list();
        TopicListVo vo = new TopicListVo();
        ForumSystemUtils.configPageInfo(vo, bos);
        List<TopicListVoItem> list = ForumSystemUtils.easyCopy(bos, TopicListVoItem.class);
        vo.setList(list);
        return ResultUtils.ok(vo);
    }

    @Override
    public DataResult<TopicVo> get(String topicId) {
        TopicBo bo = Optional.ofNullable(topicDao.one(topicId))
                .orElseThrow(() -> new NotExpectedException("topic not found."));
        return ResultUtils.ok(b2v(bo));
    }

    @Override
    public NoDataResult modify(TopicModifyForm form, String userId) {
        TopicBo bo = Optional.ofNullable(topicDao.one(form.getTopicId()))
                .orElseThrow(() -> new NotExpectedException("topic not found."));
        if (!bo.getAuthorId().equals(userId)) {
            throw new NotExpectedException("Only the author can modify the topic.");
        }
        TopicModifyBo modify = new TopicModifyBo();
        modify.setTopicId(form.getTopicId());
        modify.setTitle(form.getTitle());
        modify.setContent(form.getContent());
        modify.setModifyTime(LocalDateTime.now());
        int rows = topicDao.update(modify);
        if (rows != 1) {
            throw new NotExpectedException("update result is not 1.");
        }
        return ResultUtils.ok();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public NoDataResult remove(String topicId, String userId) {
        TopicBo bo = Optional.ofNullable(topicDao.one(topicId))
                .orElseThrow(() -> new NotExpectedException("topic not found."));
        if (bo.getAuthorId().equals(userId)) {
            int rows = topicDao.delete(topicId);
            if (rows != 1) {
                throw new NotExpectedException("affected rows should 1 but " + rows);
            }
            commentDao.deleteByTopicId(topicId);
        } else {
            throw new ForumSystemException("remove topic fail: Actions do not come from the author.");
        }
        return ResultUtils.ok();
    }

    @Override
    public DataResult<RelatedMeVo> relatedMe(RelatedMeQuery query, String userId) {
        RelatedMeVo vo = new RelatedMeVo();
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<RelatedMeListItemBo> boList = topicDao.selectRelatedMe(query, userId);
        ForumSystemUtils.configPageInfo(vo, boList);
        List<RelatedMeListItemVo> list = ForumSystemUtils.easyCopy(boList, RelatedMeListItemVo.class);
        vo.setList(list);
        return ResultUtils.ok(vo);
    }

    private TopicVo b2v(TopicBo bo) {
        if (bo == null) {
            return null;
        }
        TopicVo vo = new TopicVo();
        BeanUtils.copyProperties(bo, vo);
        vo.setCreateTime(dateFormat(bo.getCreateTime()));
        vo.setModifyTime(dateFormat(bo.getModifyTime()));
        return vo;
    }
}
