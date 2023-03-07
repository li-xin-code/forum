package com.lixin.campusforum.common.constant.enums;

import java.util.EnumSet;

/**
 * @author lixin
 * @date 2023/3/7 20:10
 */
public enum SearchTypeEnums {

    /**
     * 不支持搜索
     */
    NOT_SUPPORTED(0),

    /**
     * 搜索用户
     */
    USER(1),
    /**
     * 搜索话题
     */
    TOPIC(2);

    /**
     * 类型代码
     */
    private final Integer typeCode;

    SearchTypeEnums(Integer typeCode) {
        this.typeCode = typeCode;
    }

    public Integer getTypeCode() {
        return typeCode;
    }

    public static SearchTypeEnums get(int code) {
        return EnumSet.allOf(SearchTypeEnums.class).stream()
                .filter(type -> type.getTypeCode().equals(code))
                .findFirst()
                .orElse(SearchTypeEnums.NOT_SUPPORTED);
    }
}
