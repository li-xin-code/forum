package com.lixin.campusforum.common.constant.consist;

/**
 * @author lixin
 */
public final class UserConstant {
    private UserConstant() {
    }

    public final static String USERNAME_FORMAT = "^[\\w_\u4e00-\u9fa5\\-]{2,12}$";
    public final static String PASSWORD_FORMAT = "^[\\w~`!@#$%^&*\\-+=]{6,16}$";
    public final static String DEFAULT_USER_SIGN = "这个人很神秘，什么也没留下。";
}
