package com.lixin.campusforum.user.controller;

import com.lixin.campusforum.common.result.Result;
import com.lixin.campusforum.common.utils.ResultUtils;
import com.lixin.campusforum.user.model.form.LoginForm;
import com.lixin.campusforum.user.model.form.RegisterForm;
import com.lixin.campusforum.user.model.vo.UserVo;
import com.lixin.campusforum.user.service.TokenService;
import com.lixin.campusforum.user.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lixin
 */
@RestController
public class SystemController {

    private final UserService userService;
    private final TokenService<UserVo> tokenService;

    public SystemController(UserService userService,
                            @Qualifier("inMemoryTokenService") TokenService<UserVo> tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Result<UserVo> register(@Validated @RequestBody RegisterForm form) {
        UserVo user = userService.register(form);
        return ResultUtils.ok("user", user);
    }

    @PostMapping("/login")
    public Result<Object> login(@Validated @RequestBody LoginForm form) {
        UserVo user = userService.login(form);
        String token = tokenService.getToken(user);
        Result<Object> result = ResultUtils.ok();
        result.put("token", token);
        result.put("user", user);
        return result;
    }

}
