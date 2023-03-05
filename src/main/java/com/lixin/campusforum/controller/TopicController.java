package com.lixin.campusforum.controller;

import com.lixin.campusforum.common.annotation.LoginRequired;
import com.lixin.campusforum.common.result.DataResult;
import com.lixin.campusforum.common.result.NoDataResult;
import com.lixin.campusforum.model.form.TopicForm;
import com.lixin.campusforum.model.form.TopicModifyForm;
import com.lixin.campusforum.model.vo.topic.AddTopicVo;
import com.lixin.campusforum.model.vo.topic.TopicListVo;
import com.lixin.campusforum.model.vo.topic.TopicVo;
import com.lixin.campusforum.model.vo.user.UserVo;
import com.lixin.campusforum.service.TokenService;
import com.lixin.campusforum.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public DataResult<AddTopicVo> add(@RequestBody TopicForm form,
                                      @RequestHeader String token) {
        UserVo user = tokenService.getData(token);
        return topicService.add(form, user.getUserId());
    }

    @GetMapping("/list/{page}")
    public DataResult<TopicListVo> list(@PathVariable Integer page) {
        return topicService.list(page);
    }

    @GetMapping("/{topicId}")
    public DataResult<TopicVo> one(@PathVariable String topicId) {
        return topicService.get(topicId);
    }

    @LoginRequired
    @DeleteMapping("/{topicId}")
    public NoDataResult del(@PathVariable String topicId,
                            @RequestHeader("token") String token) {
        UserVo user = tokenService.getData(token);
        return topicService.remove(topicId, user.getUserId());
    }

    @PutMapping
    public NoDataResult modify(@RequestBody TopicModifyForm form,
                               @RequestHeader String token) {
        UserVo user = tokenService.getData(token);
        return topicService.modify(form, user.getUserId());
    }

}
