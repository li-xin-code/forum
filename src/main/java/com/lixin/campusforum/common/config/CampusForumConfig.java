package com.lixin.campusforum.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lixin
 */
@Data
@Component
@ConfigurationProperties("campusforum")
public class CampusForumConfig {
    private PageConfig pageConfig;
}
