package com.imooc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-23 21:55
 **/
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    //app id and app secret for payment test (official account platform)
    private String mpAppId;

    private String mpAppSecret;

    //app id and app secret for login test (open platform)
    private String openAppId;

    private String openAppSecret;

    //Business Id
    private String mchId;

    //Business Key
    private String mchKey;

    //Business certification path
    private String keyPath;

    //WeChat Pay asynchronous notify url
    private String notifyUrl;

    //open id
    private  String openId;
}
