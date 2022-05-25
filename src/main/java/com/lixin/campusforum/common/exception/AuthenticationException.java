package com.lixin.campusforum.common.exception;

/**
 * @author lixin
 */
public class AuthenticationException extends SystemException {

    private static final long serialVersionUID = 4123328263222830642L;

    public AuthenticationException() {
    }

    public AuthenticationException(String message) {
        super(message);
    }
}
