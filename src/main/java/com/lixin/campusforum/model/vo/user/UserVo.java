package com.lixin.campusforum.model.vo.user;

import com.lixin.campusforum.model.base.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lixin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserVo extends BaseVo {
    private static final long serialVersionUID = -6849594912127680432L;
    private String username;
    private String userId;
}
