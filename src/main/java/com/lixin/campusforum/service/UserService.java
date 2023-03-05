package com.lixin.campusforum.service;

import com.lixin.campusforum.common.result.DataResult;
import com.lixin.campusforum.common.result.NoDataResult;
import com.lixin.campusforum.model.form.LoginForm;
import com.lixin.campusforum.model.form.RegisterForm;
import com.lixin.campusforum.model.vo.LoginVo;
import com.lixin.campusforum.model.vo.user.UserInfoVo;
import com.lixin.campusforum.model.vo.user.UserVo;

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
    DataResult<UserVo> register(RegisterForm form);

    /**
     * login
     *
     * @param form ...
     * @return com.lixin.campusforum.common.result.DataResult<com.lixin.campusforum.model.vo.LoginVo>
     * @date 2023/3/2 23:29
     **/
    DataResult<LoginVo> login(LoginForm form);

    /**
     * reset username
     *
     * @param name  new name
     * @param token token
     * @return new user info
     */
    DataResult<UserVo> rename(String name, String token);

    /**
     * resetPassword
     *
     * @param password ...
     * @param userId   ...
     * @return com.lixin.campusforum.common.result.NoDataResult
     * @date 2023/3/3 23:37
     **/
    NoDataResult resetPassword(String password, String userId);

    /**
     * check if username is available.
     *
     * @param username username
     * @return result
     */
    NoDataResult nameAvailable(String username);

    /**
     * userInfo
     *
     * @param userId ...
     * @return com.lixin.campusforum.common.result.DataResult<com.lixin.campusforum.model.vo.user.UserInfoVo>
     * @date 2023/3/4 19:21
     **/
    DataResult<UserInfoVo> userInfo(String userId);
}
