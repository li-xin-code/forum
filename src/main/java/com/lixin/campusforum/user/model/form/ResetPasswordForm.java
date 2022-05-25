package com.lixin.campusforum.user.model.form;

import com.lixin.campusforum.user.common.utils.UserUtils;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author lixin
 */
@Data
public class ResetPasswordForm {

    @NotBlank(message = "密码不可为空")
    @Pattern(regexp = UserUtils.PASSWORD_FORMAT, message = "密码格式错误")
    private String password;

    @NotBlank(message = "重复密码不可为空")
    @Pattern(regexp = UserUtils.PASSWORD_FORMAT, message = "重复密码格式错误")
    private String repeatPassword;

}
