package com.lixin.campusforum.common.exception;

/**
 * 令牌无效异常
 *
 * @author lixin
 */
public class TokenInvalidException extends RuntimeException {

    private static final long serialVersionUID = 4547520726255886222L;

    public TokenInvalidException() {
        super("Token invalid.");
    }
}
