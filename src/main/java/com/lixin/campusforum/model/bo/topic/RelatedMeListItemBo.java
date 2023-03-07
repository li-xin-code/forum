package com.lixin.campusforum.model.bo.topic;

import com.lixin.campusforum.model.base.BaseBo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lixin
 * @date 2023/3/7 16:11
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RelatedMeListItemBo extends BaseBo {
    private static final long serialVersionUID = 9035377542729982666L;

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
