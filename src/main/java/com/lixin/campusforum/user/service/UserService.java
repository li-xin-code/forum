package com.lixin.campusforum.user.service;

import com.lixin.campusforum.user.model.form.LoginForm;
import com.lixin.campusforum.user.model.form.RegisterForm;
import com.lixin.campusforum.user.model.vo.UserVo;

/**
 * @author lixin
 */
public interface UserService {

    /**
     * register account
     *
     * @param form {username,password,repeatPassword}
     * @return ...
     */
    UserVo register(RegisterForm form);


    /**
     * login
     *
     * @param form {username,password}
     * @return ...
     */
    UserVo login(LoginForm form);

    /**
     * check if username is existing.
     *
     * @param username username
     * @return if username exists return true
     */
    Boolean nameExist(String username);

    /**
     * reset username
     *
     * @param name new name
     * @param uuid uuid
     * @return new user info
     */
    UserVo rename(String name, String uuid);

    /**
     * reset password
     *
     * @param password new password
     * @param uuid     uuid
     */
    void resetPassword(String password, String uuid);

}
