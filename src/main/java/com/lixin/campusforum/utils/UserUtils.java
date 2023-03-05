package com.lixin.campusforum.utils;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author lixin
 */
public class UserUtils {
    
    private UserUtils() {
    }

    public static String passwordEncode(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
    }

}
