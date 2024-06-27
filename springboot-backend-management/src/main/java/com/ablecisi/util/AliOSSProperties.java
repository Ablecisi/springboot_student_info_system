package com.ablecisi.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * itheima_web_project
 * com.ablecisi.util
 *
 * @author Ablecisi
 * @version 1.0
 * 2024/5/1
 * 星期三
 * 下午5:46
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliOSSProperties {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String fileHost;
    private String ossUrl;
}
