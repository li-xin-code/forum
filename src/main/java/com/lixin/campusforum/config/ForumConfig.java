package com.lixin.campusforum.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author lixin
 */
@Data
@Configuration
@ConfigurationProperties("forum")
public class ForumConfig {
    private PageConfig page;
    private UploadFileConfig upload;

    /**
     * 默认头像
     */
    private String defaultFace;
}
