package com.lixin.campusforum.common.result;

/**
 * @author lixin
 */
public class NoDataResult extends BaseHttpResult<Object> {

    @Override
    public void setData(Object data) {
        throw new UnsupportedOperationException();
    }
}
