package com.lixin.campusforum.dao;

import com.lixin.campusforum.model.bo.UserBo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author lixin
 */
@Mapper
@Repository
public interface UserDao {

    /**
     * insert user into database.
     *
     * @param user ..
     * @return the number of affected rows.
     */
    int insert(UserBo user);

    /**
     * query if username is existing.
     *
     * @param username ...
     * @return the number of rows.
     */
    int exit(String username);

    /**
     * find by username
     *
     * @param username ...
     * @return user
     */
    UserBo find(String username);

    /**
     * find by uuid.
     *
     * @param uuid ...
     * @return user
     */
    UserBo findByUuid(String uuid);

    /**
     * update username by uuid.
     *
     * @param name new name
     * @param uuid uuid
     * @return the number of rows.
     */
    int updateName(String name, String uuid);

    /**
     * update password bu uuid.
     *
     * @param password new password
     * @param uuid     uuid
     * @return the number of rows.
     */
    int updatePassword(String password, String uuid);
}
