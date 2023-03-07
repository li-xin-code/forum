package com.lixin.campusforum.model.vo.topic;

import com.lixin.campusforum.model.base.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lixin
 * @date 2023/3/5 23:35
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RelatedMeListItemVo extends BaseVo {
    private static final long serialVersionUID = -4131152265976898985L;
    private String topicId;
    private String author;
    private String face;
    private String title;
    private String createTime;
    private String modifyTime;

    /**
     * 评论总数
     */
    private Integer commentTotal;

    /**
     * 关系类型
     * <p>
     * {@link com.lixin.campusforum.common.constant.enums.RelatedTypeEnums}
     */
    private Integer relatedTypeCode;
}
