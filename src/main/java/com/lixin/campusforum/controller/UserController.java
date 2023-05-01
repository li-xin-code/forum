package com.lixin.campusforum.controller;

import com.lixin.campusforum.common.annotation.LoginRequired;
import com.lixin.campusforum.common.exception.ForumSystemException;
import com.lixin.campusforum.common.result.DataResult;
import com.lixin.campusforum.common.result.NoDataResult;
import com.lixin.campusforum.model.form.ModifyUserInfoForm;
import com.lixin.campusforum.model.form.RenameForm;
import com.lixin.campusforum.model.form.ResetPasswordForm;
import com.lixin.campusforum.model.vo.user.UserInfoVo;
import com.lixin.campusforum.model.vo.user.UserVo;
import com.lixin.campusforum.service.TokenService;
import com.lixin.campusforum.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author lixin
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TokenService<String> tokenService;

    @LoginRequired
    @GetMapping("/info")
    public DataResult<UserInfoVo> userInfo(@RequestParam(required = false) String userId, @RequestHeader String token) {
        return userService.userInfo(Objects.isNull(userId) ? tokenService.getData(token) : userId);
    }

    @LoginRequired
    @PutMapping("/info")
    public NoDataResult modifyUserInfo(@RequestBody ModifyUserInfoForm form, @RequestHeader String token) {
        String userId = tokenService.getData(token);
        return userService.modifyUserInfo(form, userId);
    }

    @GetMapping("/name-available")
    public NoDataResult available(@RequestParam String name) {
        return userService.nameAvailable(name);
    }

    @LoginRequired
    @PutMapping("/rename")
    public DataResult<UserVo> rename(@Validated @RequestBody RenameForm rename,
                                     @RequestHeader("token") String token) {
        String userId = tokenService.getData(token);
        return userService.rename(rename.getName(), userId);
    }

    @LoginRequired
    @PutMapping("/reset-password")
    public NoDataResult resetPassword(@Validated @RequestBody ResetPasswordForm form,
                                      @RequestHeader String token) {
        String userId = tokenService.getData(token);
        String password = form.getPassword();
        if (!password.equals(form.getRepeatPassword())) {
            throw new ForumSystemException("password and the repeat password do not match.");
        }
        return userService.resetPassword(password, userId);
    }

}
