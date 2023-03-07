package com.lixin.campusforum.model.query;

import com.lixin.campusforum.model.base.BasePageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lixin
 * @date 2023/3/7 16:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RelatedMeQuery extends BasePageRequest {
    private static final long serialVersionUID = -2293050421439946682L;
}
