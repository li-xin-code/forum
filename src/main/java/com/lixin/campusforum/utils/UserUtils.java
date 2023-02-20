package com.lixin.campusforum.utils;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author lixin
 */
public class UserUtils {

    public final static String USERNAME_FORMAT = "^[\\w_\u4e00-\u9fa5\\-]{2,12}$";
    public final static String PASSWORD_FORMAT = "^[\\w~`!@#$%^&*\\-+=]{6,16}$";

    private UserUtils() {
    }

    public static String passwordEncode(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
    }

}
