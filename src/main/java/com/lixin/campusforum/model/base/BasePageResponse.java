package com.lixin.campusforum.model.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lixin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BasePageResponse extends BaseVo {

    private static final long serialVersionUID = 6873149358886629322L;

    /**
     * 当前页码
     */
    private Integer pageNum;

    /**
     * 页容量
     */
    private Integer pageSize;

    /**
     * 总页数
     * total page
     */
    private Integer pages;

    /**
     * 总量
     */
    private Long total;
}
