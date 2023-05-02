package com.lixin.campusforum.model.vo.topic;

import com.lixin.campusforum.model.base.BasePageResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author lixin
 * @date 2023/3/3 22:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TopicListVo extends BasePageResponse {

    private static final long serialVersionUID = -7009460156174330205L;

    private List<TopicListVoItemVo> list;

}
