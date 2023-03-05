package com.lixin.campusforum.model.vo.comment;

import com.lixin.campusforum.model.base.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lixin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CommentVo extends BaseVo {

    private static final long serialVersionUID = 718427273700854260L;

    private String commentId;

    /**
     * username
     */
    private String author;
    private String authorId;
    private String content;
    private String createTime;
    private String modifyTime;
}
