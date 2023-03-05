package com.lixin.campusforum.model.bo.user;

import com.lixin.campusforum.model.base.BaseBo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lixin
 * @date 2023/3/4 19:16
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserInfoBo extends BaseBo {
    private static final long serialVersionUID = 8700755988032360331L;
    private String userId;
    private String userName;
    private Integer gender;
    private String face;
    private String userSign;
    private String createTime;
}
