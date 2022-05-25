package com.lixin.campusforum.common.result;

import lombok.Data;

import java.util.Map;

/**
 * @author lixin
 */
@Data
public class Result<T> {
    private Map<String, T> data;
    private Integer code;
    private String msg;

    public void put(String key, T val) {
        data.put(key, val);
    }

}
