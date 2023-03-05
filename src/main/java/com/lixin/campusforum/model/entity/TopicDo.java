package com.lixin.campusforum.model.entity;

import com.lixin.campusforum.model.entity.base.BaseDo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 话题表
 *
 * @author lixin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TopicDo extends BaseDo implements Serializable {

    private static final long serialVersionUID = -6409428384355503627L;

    /**
     * 话题编号
     */
    private String topicId;

    /**
     * 作者用户编号
     */
    private String authorId;

    /**
     * 标题
     */
    private String title;

    /**
     * 话题内容
     */
    private String content;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;
}