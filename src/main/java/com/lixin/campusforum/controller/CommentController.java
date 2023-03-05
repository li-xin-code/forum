package com.lixin.campusforum.controller;

import com.lixin.campusforum.common.annotation.LoginRequired;
import com.lixin.campusforum.common.result.DataResult;
import com.lixin.campusforum.common.result.NoDataResult;
import com.lixin.campusforum.model.form.CommentForm;
import com.lixin.campusforum.model.vo.comment.CommentListVo;
import com.lixin.campusforum.model.vo.user.UserVo;
import com.lixin.campusforum.service.CommentService;
import com.lixin.campusforum.service.TokenService;
import org.springframework.web.bind.annotation.*;

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
        return commentService.comment(form, user.getUserId());
    }

    @GetMapping("/{topicId}/{page}")
    public DataResult<CommentListVo> list(@PathVariable String topicId, @PathVariable Integer page) {
        return commentService.list(topicId, page);
    }

    @LoginRequired
    @DeleteMapping("/{commentId}")
    public NoDataResult remove(@PathVariable String commentId,
                               @RequestHeader("token") String token) {
        UserVo user = tokenService.getData(token);
        return commentService.remove(commentId, user.getUserId());
    }

}
