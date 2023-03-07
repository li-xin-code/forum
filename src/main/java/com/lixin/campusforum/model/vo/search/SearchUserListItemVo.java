package com.lixin.campusforum.model.vo.search;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lixin
 * @date 2023/3/7 21:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SearchUserListItemVo extends SearchResultListItemVo {
    private static final long serialVersionUID = 5986968402044947228L;
    private String userId;
    private String userName;
    private String gender;
    private String face;
    private String userSign;
    private String createTime;
}
