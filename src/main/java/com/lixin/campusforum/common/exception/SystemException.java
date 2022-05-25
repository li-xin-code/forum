package com.lixin.campusforum.common.exception;

/**
 * @author lixin
 */
public class SystemException extends RuntimeException {
    private static final long serialVersionUID = 1751856386916991599L;

    public SystemException() {
    }

    public SystemException(String message) {
        super(message);
    }
}
