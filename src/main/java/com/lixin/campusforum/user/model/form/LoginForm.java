package com.lixin.campusforum.user.model.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author lixin
 */
@Data
public class LoginForm {

    @NotBlank(message = "用户名不可为空")
    private String username;

    @NotBlank(message = "密码不可为空")
    private String password;

}
