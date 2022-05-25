package com.lixin.campusforum.common.result;

import com.lixin.campusforum.common.exception.SystemException;

import java.util.Map;

/**
 * @author lixin
 */
public class NoDataResult extends Result<String> {

    @Override
    public void setData(Map<String, String> data) {
        throw new SystemException("NoDataResult has not data.");
    }

    @Override
    public Map<String, String> getData() {
        return null;
    }

    @Override
    public void put(String key, String val) {
        throw new SystemException("NoDataResult has not data.");
    }
}
