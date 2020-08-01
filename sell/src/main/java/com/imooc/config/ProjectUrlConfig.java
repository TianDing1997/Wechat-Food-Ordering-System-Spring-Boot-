package com.imooc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-31 20:27
 **/

@Data
@ConfigurationProperties(prefix = "projectUrl")
@Component
public class ProjectUrlConfig {

    /**
     * Wechat Official Account PlatForm Authorized Url
     */
    private String wechatMpAuthorize;

    /**
     * Wechat Open PlatForm Authorized Url
     */
    private String wechatOpenAuthorize;

    /**
     * System
     */
    private String sell;
}
