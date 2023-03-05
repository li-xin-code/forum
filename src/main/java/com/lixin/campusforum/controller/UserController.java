package com.lixin.campusforum.controller;

import com.lixin.campusforum.common.annotation.LoginRequired;
import com.lixin.campusforum.common.exception.ForumSystemException;
import com.lixin.campusforum.common.result.DataResult;
import com.lixin.campusforum.common.result.NoDataResult;
import com.lixin.campusforum.model.form.RenameForm;
import com.lixin.campusforum.model.form.ResetPasswordForm;
import com.lixin.campusforum.model.vo.user.UserInfoVo;
import com.lixin.campusforum.model.vo.user.UserVo;
import com.lixin.campusforum.service.TokenService;
import com.lixin.campusforum.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author lixin
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final TokenService<UserVo> tokenService;

    public UserController(UserService userService,
                          @Qualifier("inMemoryTokenService") TokenService<UserVo> tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @LoginRequired
    @GetMapping("/info")
    public DataResult<UserInfoVo> userInfo(@RequestHeader("token") String token) {
        UserVo user = tokenService.getData(token);
        return userService.userInfo(user.getUserId());
    }

    @GetMapping("/name_available")
    public NoDataResult available(@RequestParam String name) {
        return userService.nameAvailable(name);
    }

    @LoginRequired
    @PutMapping("/rename")
    public DataResult<UserVo> rename(@Validated @RequestBody RenameForm rename,
                                     @RequestHeader("token") String token) {
        return userService.rename(rename.getName(), token);
    }

    @LoginRequired
    @PutMapping("/reset-password")
    public NoDataResult resetPassword(@Validated @RequestBody ResetPasswordForm form,
                                      @RequestHeader("token") String token) {
        UserVo user = tokenService.getData(token);
        String password = form.getPassword();
        if (!password.equals(form.getRepeatPassword())) {
            throw new ForumSystemException("password and the repeat password do not match.");
        }
        return userService.resetPassword(password, user.getUserId());
    }

}
