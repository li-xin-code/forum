package com.lixin.campusforum.common.utils;

import com.lixin.campusforum.common.enums.ResultCode;
import com.lixin.campusforum.common.result.NoDataResult;
import com.lixin.campusforum.common.result.Result;

import java.util.HashMap;

/**
 * @author lixin
 */
public class ResultUtils {
    private ResultUtils() {
    }

    public static NoDataResult okNoData() {
        NoDataResult result = new NoDataResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg("success");
        return result;
    }

    public static NoDataResult ok(String msg) {
        NoDataResult result = new NoDataResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> ok() {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg("success");
        HashMap<String, T> map = new HashMap<>(16);
        result.setData(map);
        return result;
    }

    public static <T> Result<T> ok(String key, T val) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg("success");
        HashMap<String, T> map = new HashMap<>(16);
        map.put(key, val);
        result.setData(map);
        return result;
    }

    public static NoDataResult fail(String msg) {
        NoDataResult result = new NoDataResult();
        result.setCode(ResultCode.FAIL.getCode());
        result.setMsg(msg);
        return result;
    }

    public static NoDataResult fail(ResultCode code, String msg) {
        NoDataResult result = new NoDataResult();
        result.setCode(code.getCode());
        result.setMsg(msg);
        return result;
    }
}
