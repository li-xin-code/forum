package com.lixin.campusforum.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author lixin
 */
@Data
@Configuration
@ConfigurationProperties("forum.upload")
public class UploadFileConfig {

    /**
     * 图片上传保存路径
     */
    private String imagePath;

}
