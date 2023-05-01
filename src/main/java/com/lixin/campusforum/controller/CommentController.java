package com.lixin.campusforum.controller;

import com.lixin.campusforum.common.annotation.LoginRequired;
import com.lixin.campusforum.common.result.DataResult;
import com.lixin.campusforum.common.result.NoDataResult;
import com.lixin.campusforum.model.form.CommentForm;
import com.lixin.campusforum.model.vo.comment.CommentListVo;
import com.lixin.campusforum.service.CommentService;
import com.lixin.campusforum.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author lixin
 */
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final TokenService<String> tokenService;

    @LoginRequired
    @PostMapping
    public NoDataResult comment(@RequestBody CommentForm form, @RequestHeader String token) {
        String userId = tokenService.getData(token);
        return commentService.comment(form, userId);
    }

    @GetMapping("/{topicId}/{page}")
    public DataResult<CommentListVo> list(@PathVariable String topicId, @PathVariable Integer page) {
        return commentService.list(topicId, page);
    }

    @LoginRequired
    @DeleteMapping("/{commentId}")
    public NoDataResult remove(@PathVariable String commentId,
                               @RequestHeader("token") String token) {
        String userId = tokenService.getData(token);
        return commentService.remove(commentId, userId);
    }

}
