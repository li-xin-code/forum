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
public class TopicBo extends BaseBo {
    private static final long serialVersionUID = -5994556039527313253L;
    private String topicId;
    private String author;
    private String authorId;
    private String title;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private Integer commentTotal;
}
