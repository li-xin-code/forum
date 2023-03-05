package com.lixin.campusforum.model.vo;

import com.lixin.campusforum.model.vo.user.UserVo;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lixin
 */
@Data
public class LoginVo implements Serializable {

    private static final long serialVersionUID = 9172162479467868251L;

    private String token;

    private UserVo user;

}
