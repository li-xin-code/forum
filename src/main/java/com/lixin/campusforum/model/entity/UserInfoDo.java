package com.lixin.campusforum.model.entity;

import com.lixin.campusforum.model.entity.base.BaseDo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户信息表
 *
 * @author lixin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserInfoDo extends BaseDo {

    private static final long serialVersionUID = -1393677190241760126L;
    
    /**
     * 用户编号
     */
    private String userId;

    /**
     * 用户性别:0-默认；1：男；2：女；
     */
    private Integer gender;

    /**
     * 头像
     */
    private String face;

    /**
     * 用户签名
     */
    private String userSign;
}