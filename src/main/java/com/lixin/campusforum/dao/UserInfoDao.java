package com.lixin.campusforum.dao;

import com.lixin.campusforum.model.entity.UserInfoDo;
import org.springframework.stereotype.Repository;

/**
 * @author lixin
 * @date 2023-03-02 22:29:04
 * @description 针对表【user_info(用户信息表)】的数据库操作Mapper
 * @Entity com.lixin.campusforum.model.entity.UserInfoDo
 */
@Repository
public interface UserInfoDao {

    /**
     * deleteByPrimaryKey
     *
     * @param id ...
     * @return int
     * @date 2023/3/2 23:10
     **/
    int deleteByPrimaryKey(Long id);

    /**
     * insert
     *
     * @param record ...
     * @return int
     * @date 2023/3/2 23:13
     **/
    int insert(UserInfoDo record);

    /**
     * insertSelective
     *
     * @param record ...
     * @return int
     * @date 2023/3/2 23:14
     **/
    int insertSelective(UserInfoDo record);

    /**
     * selectByPrimaryKey
     *
     * @param id ...
     * @return com.lixin.campusforum.model.entity.UserInfoDo
     * @date 2023/3/2 23:18
     **/
    UserInfoDo selectByPrimaryKey(Long id);


    /**
     * updateByPrimaryKeySelective
     *
     * @param record ...
     * @return int
     * @date 2023/3/2 23:20
     **/
    int updateByPrimaryKeySelective(UserInfoDo record);

    /**
     * updateByPrimaryKey
     *
     * @param record ...
     * @return int
     * @date 2023/3/2 23:23
     **/
    int updateByPrimaryKey(UserInfoDo record);

}
