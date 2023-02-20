package com.lixin.campusforum.model.vo;

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
}
