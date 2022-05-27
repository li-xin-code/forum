package com.lixin.campusforum.forum.service.impl;

import com.lixin.campusforum.common.config.PageConfig;
import com.lixin.campusforum.common.exception.NotExpectedException;
import com.lixin.campusforum.common.exception.SystemException;
import com.lixin.campusforum.common.utils.SystemUtils;
import com.lixin.campusforum.forum.dao.CommentDao;
import com.lixin.campusforum.forum.model.bo.AddCommentBo;
import com.lixin.campusforum.forum.model.bo.CommentBo;
import com.lixin.campusforum.forum.model.form.CommentForm;
import com.lixin.campusforum.forum.model.form.ReplyForm;
import com.lixin.campusforum.forum.model.vo.CommentVo;
import com.lixin.campusforum.forum.model.vo.ReplyVo;
import com.lixin.campusforum.forum.service.CommentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lixin
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {

    private final PageConfig pageConfig;
    private final CommentDao commentDao;

    public CommentServiceImpl(PageConfig pageConfig, CommentDao commentDao) {
        this.pageConfig = pageConfig;
        this.commentDao = commentDao;
    }

    @Override
    public List<CommentVo> list(String topicId, int page) {
        Integer rows = pageConfig.getComment();
        int offset = rows * (page - 1);
        List<CommentBo> list = commentDao.list(topicId, offset, rows);
        List<CommentVo> commentVos = new ArrayList<>(rows);
        list.forEach(c -> commentVos.add(b2v(c)));
        return commentVos;
    }

    @Override
    public void comment(CommentForm form, String authorId) {
        AddCommentBo commentBo = new AddCommentBo();
        commentBo.setContent(form.getContent());
        commentBo.setTopicId(form.getTopicId());
        commentBo.setAuthorId(authorId);
        commentBo.setReplyId("");
        commentBo.setReplyAuthor("");
        String commentId = SystemUtils.uuid();
        commentBo.setCommentId(commentId);
        commentBo.setCreateTime(LocalDateTime.now());
        int rows = commentDao.insert(commentBo);
        if (rows != 1) {
            throw new NotExpectedException("affected rows should 1 but " + rows);
        }
    }

    @Override
    public void reply(ReplyForm form, String authorId) {
        CommentBo bo = commentDao.one(form.getCommentId());
        if (bo == null) {
            throw new NotExpectedException("reply target is not exists.");
        }
        AddCommentBo commentBo = new AddCommentBo();
        commentBo.setContent(form.getContent());
        commentBo.setTopicId(form.getTopicId());
        commentBo.setReplyId(form.getCommentId());
        commentBo.setReplyAuthor(bo.getAuthor());
        commentBo.setAuthorId(authorId);
        String commentId = SystemUtils.uuid();
        commentBo.setCommentId(commentId);
        commentBo.setCreateTime(LocalDateTime.now());
        int rows = commentDao.insert(commentBo);
        if (rows != 1) {
            throw new NotExpectedException("affected rows should 1 but " + rows);
        }
    }

    @Override
    public void remove(String commentId, String userId) {
        CommentBo bo = commentDao.one(commentId);
        if (bo == null) {
            throw new NotExpectedException("comment not found.");
        }
        if (bo.getAuthorId().equals(userId)) {
            int rows = commentDao.delete(commentId);
            if (rows < 1) {
                throw new NotExpectedException("affected rows should more than the 1 but " + rows);
            }
        } else {
            throw new SystemException("remove comment fail: Actions do not come from the author.");
        }

    }

    @Override
    public Integer total(String topicId) {
        return commentDao.count(topicId);
    }

    private CommentVo b2v(CommentBo bo) {
        CommentVo vo = bo.getReplyId().isEmpty() ? new CommentVo() :
                new ReplyVo().setReplyId(bo.getReplyId()).
                        setReplyAuthor(bo.getReplyAuthor());
        vo.setCommentId(bo.getCommentId());
        vo.setContent(bo.getContent());
        vo.setAuthorId(bo.getAuthorId());
        vo.setAuthor(bo.getAuthor());
        vo.setCreateTime(bo.getCreateTime());
        return vo;
    }
}
