package com.lixin.campusforum.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lixin
 */
@Data
@Component
@ConfigurationProperties("forum.upload")
public class UploadFileConfig {

    /**
     * 图片上传保存路径
     */
    private String imagePath;
    
}
