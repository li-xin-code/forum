package com.lixin.campusforum.model.bo.user;

import com.lixin.campusforum.model.base.BaseBo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lixin
 * @date 2023/3/7 21:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserInfoListItemBo extends BaseBo {
    private static final long serialVersionUID = 5335174980309749268L;
    private String userId;
    private String userName;
    private Integer gender;
    private String face;
    private String userSign;
    private String createTime;
}
