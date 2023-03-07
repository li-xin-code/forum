package com.lixin.campusforum.model.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author lixin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BasePageRequest extends BaseQuery implements Serializable {

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
