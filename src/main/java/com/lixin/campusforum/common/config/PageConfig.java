package com.lixin.campusforum.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lixin
 */
@Data
@Component
@ConfigurationProperties("forum.page")
public class PageConfig {
    private Integer topic;
    private Integer comment;
}
