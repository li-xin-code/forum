package com.lixin.campusforum.common.constant.enums;

import java.util.Arrays;

/**
 * @author lixin
 * @date 2023/3/5 23:44
 */
public enum RelatedTypeEnums {

    /**
     * 发布
     */
    PUBLISHED(1, "发布者"),

    /**
     * 评论
     */
    COMMENTED(2, "参与评论");

    /**
     * code
     */
    private final Integer code;
    /**
     * 描述
     */
    private final String description;

    RelatedTypeEnums(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    
    public RelatedTypeEnums get(Integer code) {
        return Arrays.stream(values())
                .filter(relatedTypeEnums -> relatedTypeEnums.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("related type code not match"));
    }
}
