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
import com.lixin.campusforum.model.bo.CommentBo;
import com.lixin.campusforum.model.entity.CommentDo;
import com.lixin.campusforum.model.form.CommentForm;
import com.lixin.campusforum.model.vo.comment.CommentListVo;
import com.lixin.campusforum.model.vo.comment.CommentVo;
import com.lixin.campusforum.model.vo.comment.ReplyVo;
import com.lixin.campusforum.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author lixin
 */
@Service("commentService")
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final PageConfig pageConfig;
    private final CommentDao commentDao;

    @Override
    public DataResult<CommentListVo> list(String topicId, int page) {
        PageHelper.startPage(page, pageConfig.getCommentPageSize());
        List<CommentBo> commentBos = Optional.ofNullable(commentDao.list(topicId))
                .orElse(Collections.emptyList());
        List<CommentVo> list = commentBos
                .stream().map(CommentServiceImpl::b2v).collect(Collectors.toList());
        CommentListVo result = new CommentListVo();
        ForumSystemUtils.configPageInfo(result, commentBos);
        result.setList(list);
        return ResultUtils.ok(result);
    }

    @Override
    public NoDataResult comment(CommentForm form, String authorId) {
        String replyCommentId = Optional.ofNullable(form.getReplyCommentId()).orElse("");
        CommentDo commentDo = build(form, authorId);
        if (StringUtils.hasLength(replyCommentId)) {
            String author = Optional.ofNullable(commentDao.one(replyCommentId))
                    .map(CommentBo::getAuthor)
                    .orElseThrow(() -> new NotExpectedException("reply target is not exists."));
            commentDo.setReplyCommentId(replyCommentId);
            commentDo.setReplyCommentAuthor(author);
        } else {
            commentDo.setReplyCommentId("");
            commentDo.setReplyCommentAuthor("");
        }
        LocalDateTime time = LocalDateTime.now();
        commentDo.setCreateTime(time);
        commentDo.setModifyTime(time);
        int rows = commentDao.insert(commentDo);
        if (rows != 1) {
            throw new NotExpectedException("affected rows should 1 but " + rows);
        }
        return ResultUtils.ok();
    }

    @Override
    public NoDataResult remove(String commentId, String userId) {
        CommentBo bo = commentDao.one(commentId);
        if (bo == null) {
            throw new NotExpectedException("comment not found.");
        }
        if (bo.getAuthorId().equals(userId)) {
            int rows = commentDao.delete(commentId, userId);
            if (rows < 1) {
                throw new NotExpectedException("affected rows should more than the 1 but " + rows);
            }
        } else {
            throw new ForumSystemException("remove comment fail: Actions do not come from the author.");
        }
        return ResultUtils.ok();
    }

    private CommentDo build(CommentForm form, String authorId) {
        CommentDo commentDo = new CommentDo();
        commentDo.setContent(form.getContent());
        commentDo.setTopicId(form.getTopicId());
        commentDo.setAuthorId(authorId);
        String commentId = ForumSystemUtils.uuid();
        commentDo.setCommentId(commentId);
        commentDo.setCreateTime(LocalDateTime.now());
        return commentDo;
    }

    private static CommentVo b2v(CommentBo bo) {
        String replyId = Optional.ofNullable(bo.getReplyCommentId()).orElse("");
        CommentVo vo = replyId.isEmpty() ? new CommentVo() :
                new ReplyVo(bo.getReplyCommentId(), bo.getReplyCommentAuthor());
        BeanUtils.copyProperties(bo, vo);
        return vo;
    }
}
