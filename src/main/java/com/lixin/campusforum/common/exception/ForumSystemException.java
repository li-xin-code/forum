package com.lixin.campusforum.common.exception;

/**
 * 论坛系统异常
 *
 * @author lixin
 */
public class ForumSystemException extends RuntimeException {

    private static final long serialVersionUID = 1751856386916991599L;

    public ForumSystemException() {
    }

    public ForumSystemException(String message) {
        super(message);
    }
}
