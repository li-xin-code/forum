package com.lixin.campusforum.user.service.impl;

import com.lixin.campusforum.common.exception.AuthenticationException;
import com.lixin.campusforum.common.exception.SystemException;
import com.lixin.campusforum.common.utils.SystemUtils;
import com.lixin.campusforum.user.common.utils.UserUtils;
import com.lixin.campusforum.user.dao.UserDao;
import com.lixin.campusforum.user.model.bo.UserBo;
import com.lixin.campusforum.user.model.form.LoginForm;
import com.lixin.campusforum.user.model.form.RegisterForm;
import com.lixin.campusforum.user.model.vo.UserVo;
import com.lixin.campusforum.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * @author lixin
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserVo register(final RegisterForm form) {
        UserBo userBo = new UserBo();
        final String username = form.getUsername();
        final String password = form.getPassword();
        if (checkNameFormat(username)) {
            throw new SystemException("username format wrong.");
        }
        if (checkPwdFormat(password)) {
            throw new SystemException("password format wrong.");
        }
        if (nameExist(username)) {
            throw new SystemException("username already exists.");
        }
        if (!password.equals(form.getRepeatPassword())) {
            throw new SystemException("password and the repeat password do not match.");
        }
        String uuid = SystemUtils.uuid();
        userBo.setUsername(username);
        userBo.setUuid(uuid);
        userBo.setPassword(UserUtils.passwordEncode(password));
        int insert = userDao.insert(userBo);
        if (insert != 1) {
            throw new SystemException("注册失败");
        }
        UserVo userVo = new UserVo();
        userVo.setUsername(username);
        userVo.setUuid(uuid);
        return userVo;
    }

    @Override
    public UserVo login(LoginForm form) {
        String username = form.getUsername();
        String password = UserUtils.passwordEncode(form.getPassword());
        UserBo userBo = userDao.find(username);
        if (userBo == null || !userBo.getPassword().equals(password)) {
            throw new AuthenticationException("username or password wrong.");
        }
        UserVo user = new UserVo();
        user.setUuid(userBo.getUuid());
        user.setUsername(userBo.getUsername());
        return user;
    }

    @Override
    public Boolean nameExist(String username) {
        return userDao.exit(username) == 1;
    }

    @Override
    public UserVo rename(String name, String uuid) {
        if (checkNameFormat(name)) {
            throw new SystemException("username format wrong.");
        }
        if (nameExist(name)) {
            throw new SystemException("username already exists.");
        }
        if (userDao.updateName(name, uuid) != 1) {
            throw new SystemException("sql execute inconsistent with expectations.");
        }
        UserBo userBo = userDao.findByUuid(uuid);
        UserVo user = new UserVo();
        user.setUuid(userBo.getUuid());
        user.setUsername(userBo.getUsername());
        return user;
    }

    @Override
    public void resetPassword(String password, String uuid) {
        if (checkPwdFormat(password)) {
            throw new SystemException("password format wrong.");
        }
        password = UserUtils.passwordEncode(password);
        if (userDao.updatePassword(password, uuid) != 1) {
            throw new SystemException("sql execute inconsistent with expectations.");
        }
    }

    private boolean checkNameFormat(String username) {
        return !Pattern.matches(UserUtils.USERNAME_FORMAT, username);
    }

    private boolean checkPwdFormat(String pwd) {
        return !Pattern.matches(UserUtils.PASSWORD_FORMAT, pwd);
    }

}
