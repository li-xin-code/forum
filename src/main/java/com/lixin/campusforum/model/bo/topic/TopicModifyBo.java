package com.lixin.campusforum.model.bo.topic;

import com.lixin.campusforum.model.base.BaseBo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author lixin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TopicModifyBo extends BaseBo {
    private static final long serialVersionUID = -79338113732161523L;
    private String topicId;
    private String title;
    private String content;
    private LocalDateTime modifyTime;
}
