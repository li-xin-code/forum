package com.lixin.campusforum.controller;

import com.lixin.campusforum.common.annotation.LoginRequired;
import com.lixin.campusforum.common.result.DataResult;
import com.lixin.campusforum.model.form.LoginForm;
import com.lixin.campusforum.model.form.RegisterForm;
import com.lixin.campusforum.model.query.SearchQuery;
import com.lixin.campusforum.model.vo.FileUploadVo;
import com.lixin.campusforum.model.vo.LoginVo;
import com.lixin.campusforum.model.vo.search.SearchResultVo;
import com.lixin.campusforum.model.vo.user.UserVo;
import com.lixin.campusforum.service.FileService;
import com.lixin.campusforum.service.SearchService;
import com.lixin.campusforum.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author lixin
 */
@RestController
@RequiredArgsConstructor
public class ForumSystemController {

    private final UserService userService;
    private final FileService fileService;
    private final SearchService searchService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public DataResult<UserVo> register(@Validated @RequestBody RegisterForm form) {
        return userService.register(form);
    }

    @PostMapping("/login")
    public DataResult<LoginVo> login(@Validated @RequestBody LoginForm form) {
        return userService.login(form);
    }

    @LoginRequired
    @PostMapping("/upload")
    public DataResult<FileUploadVo> upload(@RequestParam MultipartFile file) {
        return fileService.save(file);
    }

    @GetMapping("/search")
    public DataResult<SearchResultVo> search(SearchQuery query) {
        return searchService.search(query);
    }

}
