package com.lixin.campusforum.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author lixin
 */
@Data
@Configuration
@ConfigurationProperties("forum.page")
public class PageConfig {
    private Integer topicPageSize;
    private Integer commentPageSize;
}
