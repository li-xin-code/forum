package com.lixin.campusforum.model.vo.search;

import com.lixin.campusforum.model.base.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author lixin
 * @date 2023/3/5 23:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SearchResultVo extends BaseVo {
    private static final long serialVersionUID = 3990445247649857166L;
    private Integer searchType;
    private List<? extends SearchResultListItemVo> list;
}
