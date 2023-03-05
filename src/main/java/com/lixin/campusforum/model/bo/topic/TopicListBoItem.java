package com.lixin.campusforum.model.bo.topic;

import com.lixin.campusforum.model.base.BaseBo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lixin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TopicListBoItem extends BaseBo {
    private static final long serialVersionUID = 3033044780266749320L;
    private String topicId;
    private String author;
    private String title;
    private String createTime;
    private String modifyTime;
    /**
     * 评论总数
     */
    private Integer commentTotal;
}
