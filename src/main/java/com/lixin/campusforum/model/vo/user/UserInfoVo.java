package com.lixin.campusforum.model.vo.user;

import com.lixin.campusforum.model.base.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lixin
 * @date 2023/3/4 19:08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserInfoVo extends BaseVo {
    private static final long serialVersionUID = -887769987248063878L;
    private String userId;
    private String username;
    private String gender;
    private String face;
    private String userSign;
    private String createTime;
}
