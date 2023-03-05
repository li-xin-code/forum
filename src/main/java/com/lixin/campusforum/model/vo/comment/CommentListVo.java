package com.lixin.campusforum.model.vo.comment;

import com.lixin.campusforum.model.base.BasePageResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author lixin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CommentListVo extends BasePageResponse {
    private List<CommentVo> list;
}
