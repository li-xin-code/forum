package com.lixin.campusforum.controller;

import com.lixin.campusforum.common.annotation.LoginRequired;
import com.lixin.campusforum.common.exception.SystemException;
import com.lixin.campusforum.common.result.NoDataResult;
import com.lixin.campusforum.common.result.Result;
import com.lixin.campusforum.common.utils.ResultUtils;
import com.lixin.campusforum.model.form.RenameForm;
import com.lixin.campusforum.model.form.ResetPasswordForm;
import com.lixin.campusforum.model.vo.UserVo;
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
    public Result<UserVo> userInfo(@RequestHeader("token") String token) {
        UserVo user = tokenService.getData(token);
        return ResultUtils.ok("user", user);
    }

    @GetMapping("/exist/{name}")
    public Result<Boolean> username(@PathVariable String name) {
        Boolean exist = userService.nameExist(name);
        return ResultUtils.ok("exist", exist);
    }

    @LoginRequired
    @PutMapping("/rename")
    public NoDataResult rename(@Validated @RequestBody RenameForm rename,
                               @RequestHeader("token") String token) {
        UserVo user = tokenService.getData(token);
        String newName = rename.getName();
        user = userService.rename(newName, user.getUuid());
        tokenService.update(token, user);
        return ResultUtils.okNoData();
    }

    @LoginRequired
    @PutMapping("/reset-password")
    public NoDataResult resetPassword(@Validated @RequestBody ResetPasswordForm form,
                                      @RequestHeader("token") String token) {
        UserVo user = tokenService.getData(token);
        String password = form.getPassword();
        if (!password.equals(form.getRepeatPassword())) {
            throw new SystemException("password and the repeat password do not match.");
        }
        userService.resetPassword(password, user.getUuid());
        return ResultUtils.okNoData();
    }

}
