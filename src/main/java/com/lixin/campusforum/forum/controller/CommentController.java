package com.lixin.campusforum.forum.controller;

import com.lixin.campusforum.common.annotation.LoginRequired;
import com.lixin.campusforum.common.result.NoDataResult;
import com.lixin.campusforum.common.result.Result;
import com.lixin.campusforum.common.utils.ResultUtils;
import com.lixin.campusforum.forum.model.form.CommentForm;
import com.lixin.campusforum.forum.model.form.ReplyForm;
import com.lixin.campusforum.forum.model.vo.CommentVo;
import com.lixin.campusforum.forum.service.CommentService;
import com.lixin.campusforum.user.model.vo.UserVo;
import com.lixin.campusforum.user.service.TokenService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lixin
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final TokenService<UserVo> tokenService;

    public CommentController(CommentService commentService, TokenService<UserVo> tokenService) {
        this.commentService = commentService;
        this.tokenService = tokenService;
    }

    @LoginRequired
    @PostMapping
    public NoDataResult comment(@RequestBody CommentForm form, @RequestHeader String token) {
        UserVo user = tokenService.getData(token);
        commentService.comment(form, user.getUuid());
        return ResultUtils.okNoData();
    }

    @LoginRequired
    @PostMapping("/reply")
    public NoDataResult reply(@RequestBody ReplyForm form, @RequestHeader String token) {
        UserVo user = tokenService.getData(token);
        commentService.reply(form, user.getUuid());
        return ResultUtils.okNoData();
    }

    @GetMapping("/{topicId}/{page}")
    public Result<List<CommentVo>> list(@PathVariable String topicId, @PathVariable Integer page) {
        List<CommentVo> list = commentService.list(topicId, page);
        return ResultUtils.ok("list", list);
    }

    @LoginRequired
    @DeleteMapping("/{commentId}")
    public NoDataResult remove(@PathVariable String commentId,
                               @RequestHeader("token") String token) {
        UserVo user = tokenService.getData(token);
        commentService.remove(commentId, user.getUuid());
        return ResultUtils.okNoData();
    }

    @GetMapping("/total/{commentId}")
    public Result<Integer> total(@PathVariable String commentId) {
        Integer total = commentService.total(commentId);
        return ResultUtils.ok("total", total);
    }

}
