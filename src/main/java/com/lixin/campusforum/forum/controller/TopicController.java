package com.lixin.campusforum.forum.controller;

import com.lixin.campusforum.common.annotation.LoginRequired;
import com.lixin.campusforum.common.result.NoDataResult;
import com.lixin.campusforum.common.result.Result;
import com.lixin.campusforum.common.utils.ResultUtils;
import com.lixin.campusforum.forum.model.bo.TopicListItem;
import com.lixin.campusforum.forum.model.form.TopicForm;
import com.lixin.campusforum.forum.model.form.TopicModifyForm;
import com.lixin.campusforum.forum.model.vo.TopicVo;
import com.lixin.campusforum.forum.service.TopicService;
import com.lixin.campusforum.user.model.vo.UserVo;
import com.lixin.campusforum.user.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lixin
 */
@RestController
@RequestMapping("/topic")
public class TopicController {

    private final TopicService topicService;
    private final TokenService<UserVo> tokenService;

    public TopicController(TopicService topicService, TokenService<UserVo> tokenService) {
        this.topicService = topicService;
        this.tokenService = tokenService;
    }

    @LoginRequired
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Result<String> add(
            @RequestBody TopicForm form,
            @RequestHeader("token") String token) {
        UserVo user = tokenService.getData(token);
        String topicId = topicService.add(form, user.getUuid());
        return ResultUtils.ok("topicId", topicId);
    }

    @GetMapping("/list/{page}")
    public Result<List<TopicListItem>> list(@PathVariable Integer page) {
        List<TopicListItem> list = topicService.list(page);
        return ResultUtils.ok("list", list);
    }

    @GetMapping("/{topicId}")
    public Result<TopicVo> one(@PathVariable String topicId) {
        TopicVo topic = topicService.get(topicId);
        return ResultUtils.ok("topic", topic);
    }

    @LoginRequired
    @DeleteMapping("/{topicId}")
    public NoDataResult del(@PathVariable String topicId,
                            @RequestHeader("token") String token) {
        UserVo user = tokenService.getData(token);
        topicService.remove(topicId, user.getUuid());
        return ResultUtils.okNoData();
    }

    @PutMapping
    public NoDataResult modify(@RequestBody TopicModifyForm form,
                               @RequestHeader String token) {
        UserVo user = tokenService.getData(token);
        topicService.modify(form, user.getUuid());
        return ResultUtils.okNoData();
    }

    @GetMapping("/total")
    public Result<Integer> total() {
        Integer total = topicService.total();
        return ResultUtils.ok("total", total);
    }

}
