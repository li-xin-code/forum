package com.lixin.campusforum.common.exception;

/**
 * 身份认证异常
 *
 * @author lixin
 */
public class AuthenticationException extends ForumSystemException {

    private static final long serialVersionUID = 4123328263222830642L;

    public AuthenticationException() {
    }

    public AuthenticationException(String message) {
        super(message);
    }
}
