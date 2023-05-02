package com.lixin.campusforum.model.vo.topic;

import com.lixin.campusforum.model.base.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lixin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TopicListVoItemVo extends BaseVo {

    private static final long serialVersionUID = -2769823972056666317L;

    private String topicId;
    private String author;
    private String authorId;
    private String face;
    private String title;
    private String createTime;
    private String modifyTime;

    /**
     * 评论总数
     */
    private Integer commentTotal;
}