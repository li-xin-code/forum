package com.lixin.campusforum.common.exception;

/**
 * 于预期不符异常
 *
 * @author lixin
 */
public class NotExpectedException extends ForumSystemException {
    private static final long serialVersionUID = 2286315431277788316L;

    public NotExpectedException(String message) {
        super(message);
    }
}
