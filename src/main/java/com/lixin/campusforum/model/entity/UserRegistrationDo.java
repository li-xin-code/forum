package com.lixin.campusforum.model.entity;

import com.lixin.campusforum.model.entity.base.BaseDo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lixin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserRegistrationDo extends BaseDo {

    private static final long serialVersionUID = -5404019560757514530L;

    private String userId;
    private String username;
    private String password;
}
