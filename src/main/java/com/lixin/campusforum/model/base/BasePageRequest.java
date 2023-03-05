package com.lixin.campusforum.model.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lixin
 */
@Data
public abstract class BasePageRequest implements Serializable {

    private static final long serialVersionUID = -5471394222391378282L;

    /**
     * 页码
     */
    private Integer pageNum = 1;
    /**
     * 页大小
     */
    private Integer pageSize = 10;
}
