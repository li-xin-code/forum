package com.lixin.campusforum.common.constant.enums;

/**
 * @author lixin
 * @date 2023/3/4 18:25
 */
public enum UserGenderEnums {
    /**
     * 未知 默认
     */
    UNKNOWN(0, "未知"),
    /**
     * 男性
     */
    MALE(1, "男性"),
    /**
     * 女性
     */
    FEMALE(2, "女性");

    /**
     * gender code
     */
    private final int code;

    /**
     * 说明
     */
    private final String description;

    UserGenderEnums(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
