package com.lixin.campusforum.model.vo.topic;

import lombok.Data;

/**
 * @author lixin
 */
@Data
public class TopicVo {
    private String topicId;
    private String author;
    private String authorId;
    private String title;
    private String content;
    private String createTime;
    private String modifyTime;
    /**
     * 评论总数
     */
    private Integer commentTotal;
}
