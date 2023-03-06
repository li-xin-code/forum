package com.lixin.campusforum.model.form;

import com.lixin.campusforum.model.base.BaseForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lixin
 * @date 2023/3/5 23:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserInfoModifyForm extends BaseForm {
    private static final long serialVersionUID = 5585872519577128914L;
    private String gender;
    private String face;
    private String userSign;
}
