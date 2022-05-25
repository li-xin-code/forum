package com.lixin.campusforum;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lixin
 */
@SpringBootApplication
@MapperScan(value = {"com.lixin.campusforum.user.dao", "com.lixin.campusforum.forum.dao"})
public class CampusForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusForumApplication.class, args);
    }

}
