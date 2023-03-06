package com.lixin.campusforum.model.query;

import com.lixin.campusforum.model.base.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lixin
 * @date 2023/3/6 00:16
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SearchQuery extends BaseQuery {

    private static final long serialVersionUID = 505508724972078414L;

    /**
     * 搜索类型
     */
    private Integer searchType;

    /**
     * 搜索关键字
     */
    private String keyword;
}
