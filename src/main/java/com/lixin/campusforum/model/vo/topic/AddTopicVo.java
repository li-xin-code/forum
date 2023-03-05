package com.lixin.campusforum.model.vo.topic;

import com.lixin.campusforum.model.base.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lixin
 * @date 2023/3/3 22:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AddTopicVo extends BaseVo {
    private static final long serialVersionUID = 3737039343704630582L;
    private String topicId;
}
