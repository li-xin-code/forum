package com.lixin.campusforum.controller;

import com.lixin.campusforum.common.annotation.LoginRequired;
import com.lixin.campusforum.common.result.DataResult;
import com.lixin.campusforum.common.result.NoDataResult;
import com.lixin.campusforum.model.form.TopicForm;
import com.lixin.campusforum.model.form.TopicModifyForm;
import com.lixin.campusforum.model.query.RelatedMeQuery;
import com.lixin.campusforum.model.vo.topic.AddTopicVo;
import com.lixin.campusforum.model.vo.topic.RelatedMeVo;
import com.lixin.campusforum.model.vo.topic.TopicListVo;
import com.lixin.campusforum.model.vo.topic.TopicVo;
import com.lixin.campusforum.service.TokenService;
import com.lixin.campusforum.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author lixin
 */
@RestController
@RequestMapping("/topic")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;
    private final TokenService<String> tokenService;

    @LoginRequired
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DataResult<AddTopicVo> add(@RequestBody TopicForm form,
                                      @RequestHeader String token) {
        String userId = tokenService.getData(token);
        return topicService.add(form, userId);
    }

    @GetMapping("/list/{page}")
    public DataResult<TopicListVo> list(@PathVariable Integer page) {
        return topicService.list(page);
    }

    @GetMapping("/detail/{topicId}")
    public DataResult<TopicVo> detail(@PathVariable String topicId) {
        return topicService.get(topicId);
    }

    @LoginRequired
    @DeleteMapping("/{topicId}")
    public NoDataResult del(@PathVariable String topicId,
                            @RequestHeader("token") String token) {
        String userId = tokenService.getData(token);
        return topicService.remove(topicId, userId);
    }

    @PutMapping
    public NoDataResult modify(@RequestBody TopicModifyForm form,
                               @RequestHeader String token) {
        String userId = tokenService.getData(token);
        return topicService.modify(form, userId);
    }

    @GetMapping("/related_me")
    public DataResult<RelatedMeVo> relatedMe(RelatedMeQuery query, @RequestHeader String token) {
        String userId = tokenService.getData(token);
        return topicService.relatedMe(query, userId);
    }

}
