package com.lixin.campusforum.model.vo.topic;

import com.lixin.campusforum.model.base.BasePageResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author lixin
 * @date 2023/3/5 23:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RelatedMeVo extends BasePageResponse {
    private static final long serialVersionUID = -5874235666545562412L;
    private List<RelatedMeListItemVo> list;
}
