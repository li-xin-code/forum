package com.lixin.campusforum.dao;

import com.lixin.campusforum.model.bo.user.UserInfoBo;
import com.lixin.campusforum.model.entity.UserRegistrationDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author lixin
 */
@Mapper
@Repository
public interface UserRegistrationDao {

    /**
     * insert user into database.
     *
     * @param user ...
     * @return the number of affected rows.
     */
    int insert(UserRegistrationDo user);

    /**
     * count by username.
     *
     * @param username ...
     * @return the number of rows.
     */
    int selectCountByUsername(@Param("username") String username);

    /**
     * find by username
     *
     * @param username ...
     * @return user
     */
    UserRegistrationDo find(@Param("username") String username);

    /**
     * find by userId.
     *
     * @param userId userId
     * @return user
     */
    UserRegistrationDo findByUserId(@Param("userId") String userId);

    /**
     * update username by uuid.
     *
     * @param name   new name
     * @param userId userId
     * @return the number of rows.
     */
    int updateName(@Param("name") String name, @Param("userId") String userId);

    /**
     * update password bu uuid.
     *
     * @param password new password
     * @param userId   userId
     * @return the number of rows.
     */
    int updatePassword(@Param("password") String password, @Param("userId") String userId);

    /**
     * selectUserInfo
     *
     * @param userId ...
     * @return com.lixin.campusforum.model.bo.user.UserInfoBo
     * @date 2023/3/4 19:30
     **/
    UserInfoBo selectUserInfo(@Param("userId") String userId);
}
