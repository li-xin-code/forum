package com.lixin.campusforum.model.bo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lixin
 */
@Data
public class TopicBo {
    private String topicId;
    private String authorId;
    private String author;
    private String title;
    private String content;
    private LocalDateTime createTime;
}
