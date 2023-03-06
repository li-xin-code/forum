package com.lixin.campusforum.service.impl;

import com.lixin.campusforum.common.constant.consist.UserConstant;
import com.lixin.campusforum.common.constant.enums.UserGenderEnums;
import com.lixin.campusforum.common.exception.AuthenticationException;
import com.lixin.campusforum.common.exception.ForumSystemException;
import com.lixin.campusforum.common.exception.NotExpectedException;
import com.lixin.campusforum.common.result.DataResult;
import com.lixin.campusforum.common.result.NoDataResult;
import com.lixin.campusforum.common.utils.ForumSystemUtils;
import com.lixin.campusforum.common.utils.ResultUtils;
import com.lixin.campusforum.config.ForumConfig;
import com.lixin.campusforum.dao.UserInfoDao;
import com.lixin.campusforum.dao.UserRegistrationDao;
import com.lixin.campusforum.model.bo.user.UserInfoBo;
import com.lixin.campusforum.model.entity.UserInfoDo;
import com.lixin.campusforum.model.entity.UserRegistrationDo;
import com.lixin.campusforum.model.form.LoginForm;
import com.lixin.campusforum.model.form.RegisterForm;
import com.lixin.campusforum.model.form.UserInfoModifyForm;
import com.lixin.campusforum.model.vo.LoginVo;
import com.lixin.campusforum.model.vo.user.UserInfoVo;
import com.lixin.campusforum.model.vo.user.UserVo;
import com.lixin.campusforum.service.TokenService;
import com.lixin.campusforum.service.UserService;
import com.lixin.campusforum.utils.UserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import static com.lixin.campusforum.common.constant.consist.UserConstant.DEFAULT_USER_SIGN;

/**
 * @author lixin
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRegistrationDao userDao;
    private final TokenService<UserVo> tokenService;
    private final UserInfoDao userInfoDao;
    private final ForumConfig forumConfig;

    public UserServiceImpl(UserRegistrationDao userDao, TokenService<UserVo> tokenService,
                           UserInfoDao userInfoDao, ForumConfig forumConfig) {
        this.userDao = userDao;
        this.tokenService = tokenService;
        this.userInfoDao = userInfoDao;
        this.forumConfig = forumConfig;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DataResult<UserVo> register(final RegisterForm form) {
        final String username = form.getUsername();
        final String password = form.getPassword();
        if (nameIsAvailable(username)) {
            throw new ForumSystemException("Username Unavailable.");
        }
        if (pwdFormatIsNotMatch(password)) {
            throw new ForumSystemException("password format wrong.");
        }
        if (!password.equals(form.getRepeatPassword())) {
            throw new ForumSystemException("password and the repeat password do not match.");
        }
        UserRegistrationDo registrationDo = new UserRegistrationDo();
        String uuid = ForumSystemUtils.uuid();
        registrationDo.setUsername(username);
        registrationDo.setUserId(uuid);
        registrationDo.setPassword(UserUtils.passwordEncode(password));
        registrationDo.setCreateTime(LocalDateTime.now());
        int insert = userDao.insert(registrationDo);
        if (insert != 1) {
            throw new NotExpectedException("注册失败");
        }
        if (userInfoDao.insert(initUserInfo(uuid)) != 1) {
            throw new NotExpectedException("初始化用户信息失败");
        }
        UserVo userVo = new UserVo();
        userVo.setUsername(username);
        userVo.setUserId(uuid);
        return ResultUtils.ok(userVo);
    }

    @Override
    public DataResult<LoginVo> login(LoginForm form) {
        String username = form.getUsername();
        UserRegistrationDo registrationDo = Optional.ofNullable(userDao.find(username))
                .orElseThrow(() -> new AuthenticationException("username or password wrong."));
        if (UserUtils.passwordEncode(form.getPassword()).equals(registrationDo.getPassword())) {
            UserVo user = new UserVo();
            BeanUtils.copyProperties(registrationDo, user);
            String token = tokenService.getToken(user);
            LoginVo response = new LoginVo();
            response.setToken(token);
            response.setUser(user);
            return ResultUtils.ok(response);
        } else {
            throw new AuthenticationException("username or password wrong.");
        }
    }

    @Override
    public DataResult<UserVo> rename(String name, String token) {
        UserVo user = tokenService.getData(token);
        if (!nameIsAvailable(name)) {
            throw new ForumSystemException("Username Unavailable.");
        }
        if (userDao.updateName(name, user.getUserId()) != 1) {
            throw new NotExpectedException("sql execute inconsistent with expectations.");
        }
        user.setUsername(name);
        tokenService.update(token, user);
        return ResultUtils.ok(user);
    }

    @Override
    public NoDataResult resetPassword(String password, String userId) {
        if (pwdFormatIsNotMatch(password)) {
            throw new ForumSystemException("password format wrong.");
        }
        password = UserUtils.passwordEncode(password);
        if (userDao.updatePassword(password, userId) != 1) {
            throw new NotExpectedException("sql execute inconsistent with expectations.");
        }
        return ResultUtils.ok();
    }

    @Override
    public NoDataResult nameAvailable(String username) {
        return nameIsAvailable(username) ? ResultUtils.ok() : ResultUtils.fail("Name Unavailable.");
    }

    @Override
    public DataResult<UserInfoVo> userInfo(String userId) {
        UserInfoBo infoBo = Optional.ofNullable(userDao.selectUserInfo(userId))
                .orElseThrow(() -> new NotExpectedException("用户信息不存在"));
        UserInfoVo infoVo = new UserInfoVo();
        BeanUtils.copyProperties(infoBo, infoVo);
        EnumSet<UserGenderEnums> genderEnums = EnumSet.allOf(UserGenderEnums.class);
        UserGenderEnums gender = genderEnums.stream()
                .filter(item -> infoBo.getGender().equals(item.getCode()))
                .findFirst()
                .orElse(UserGenderEnums.UNKNOWN);
        infoVo.setGender(gender.getDescription());
        return ResultUtils.ok(infoVo);
    }

    @Override
    public NoDataResult modifyUserInfo(UserInfoModifyForm form, String userId) {
        if (Objects.isNull(userId)) {
            throw new NotExpectedException("userId can not be null.");
        }
        if (ForumSystemUtils.isEmpty(form)) {
            throw new ForumSystemException("all property is null");
        }
        UserInfoDo userInfoDo = new UserInfoDo();
        BeanUtils.copyProperties(form, userInfoDo);
        userInfoDo.setUserId(userId);
        int row = userInfoDao.updateByUserId(userInfoDo);
        return row == 1 ? ResultUtils.ok() : ResultUtils.fail();
    }

    private boolean nameIsAvailable(String username) {
        return nameFormatIsMatch(username) && (userDao.selectCountByUsername(username) == 0);
    }

    private boolean nameFormatIsMatch(String username) {
        return Pattern.matches(UserConstant.USERNAME_FORMAT, username);
    }

    private boolean pwdFormatIsNotMatch(String pwd) {
        return !Pattern.matches(UserConstant.PASSWORD_FORMAT, pwd);
    }

    private UserInfoDo initUserInfo(String userId) {
        UserInfoDo infoDo = new UserInfoDo();
        infoDo.setUserId(userId);
        infoDo.setCreateTime(LocalDateTime.now());
        infoDo.setGender(UserGenderEnums.UNKNOWN.getCode());
        infoDo.setFace(forumConfig.getDefaultFace());
        infoDo.setUserSign(DEFAULT_USER_SIGN);
        return infoDo;
    }

}
