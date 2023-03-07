package com.lixin.campusforum.controller;

import com.lixin.campusforum.common.annotation.LoginRequired;
import com.lixin.campusforum.common.exception.AuthenticationException;
import com.lixin.campusforum.common.exception.ForumSystemException;
import com.lixin.campusforum.common.result.DataResult;
import com.lixin.campusforum.common.result.NoDataResult;
import com.lixin.campusforum.model.form.RenameForm;
import com.lixin.campusforum.model.form.ResetPasswordForm;
import com.lixin.campusforum.model.form.UserInfoModifyForm;
import com.lixin.campusforum.model.vo.user.UserInfoVo;
import com.lixin.campusforum.model.vo.user.UserVo;
import com.lixin.campusforum.service.TokenService;
import com.lixin.campusforum.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author lixin
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TokenService<UserVo> tokenService;

    @LoginRequired
    @GetMapping("/info")
    public DataResult<UserInfoVo> userInfo(@RequestParam(required = false) String userId, @RequestHeader String token) {
        String id = Optional.ofNullable(userId)
                .orElseGet(() ->
                        Optional.ofNullable(tokenService.getData(token))
                                .map(UserVo::getUserId)
                                .orElseThrow(() -> new AuthenticationException("token expired.")));
        return userService.userInfo(id);
    }

    @LoginRequired
    @PutMapping("/info")
    public NoDataResult modifyUserInfo(@RequestBody UserInfoModifyForm form, @RequestHeader String token) {
        UserVo userVo = tokenService.getData(token);
        return userService.modifyUserInfo(form, userVo.getUserId());
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
                                      @RequestHeader String token) {
        UserVo user = tokenService.getData(token);
        String password = form.getPassword();
        if (!password.equals(form.getRepeatPassword())) {
            throw new ForumSystemException("password and the repeat password do not match.");
        }
        return userService.resetPassword(password, user.getUserId());
    }

}
