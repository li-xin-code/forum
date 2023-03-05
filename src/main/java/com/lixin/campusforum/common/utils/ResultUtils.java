package com.lixin.campusforum.common.utils;

import com.lixin.campusforum.common.constant.enums.ResultCodeEnums;
import com.lixin.campusforum.common.result.DataResult;
import com.lixin.campusforum.common.result.NoDataResult;

/**
 * @author lixin
 */
public final class ResultUtils {

    private ResultUtils() {
    }

    private static final int DEFAULT_SUCCESS_CODE = ResultCodeEnums.SUCCESS.getCode();
    private static final int DEFAULT_FAIL_CODE = ResultCodeEnums.FAIL.getCode();
    private static final String DEFAULT_SUCCESS_MSG = "ok";

    public static NoDataResult ok() {
        return buildNoDataResult(DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MSG);
    }

    public static NoDataResult fail(String msg) {
        return buildNoDataResult(DEFAULT_FAIL_CODE, msg);
    }

    public static <T> DataResult<T> ok(T data) {
        return build(DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MSG, data);
    }

    public static <T> DataResult<T> ok(String msg, T data) {
        return build(DEFAULT_SUCCESS_CODE, msg, data);

    }

    public static NoDataResult fail(ResultCodeEnums code, String msg) {
        return buildNoDataResult(code.getCode(), msg);
    }

    public static <T> DataResult<T> fail(ResultCodeEnums code, String msg, T data) {
        return build(code.getCode(), msg, data);
    }

    private static <T> DataResult<T> build(int code, String msg, T data) {
        DataResult<T> result = new DataResult<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    private static NoDataResult buildNoDataResult(int code, String msg) {
        NoDataResult result = new NoDataResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
