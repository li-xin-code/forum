package com.lixin.campusforum.forum.model.bo;

import lombok.Data;

/**
 * @author lixin
 */
@Data
public class TopicListItem {
    private String topicId;
    private String author;
    private String title;
    private String createTime;
}
