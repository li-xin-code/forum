package com.lixin.campusforum.common.enums;

/**
 * @author lixin
 */
public enum ResultCode {

    /**
     * success
     */
    SUCCESS(200),
    /**
     * AuthenticationException 认证失败
     */
    AUTHENTICATION(401),
    /**
     * MethodArgumentNotValidException 参数校验失败
     */
    ARGUMENT_NOT_VALID(501),
    /**
     * TokenInvalidException 无效token
     */
    TOKEN_INVALID(502),
    /**
     * Exception
     */
    ERROR(500),
    /**
     * fail
     */
    FAIL(400);

    int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
