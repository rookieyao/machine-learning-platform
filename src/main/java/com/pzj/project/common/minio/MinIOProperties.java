package com.pzj.project.common.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MinIOProperties
 * @Description
 * @Author yaoqi
 * @Date 2022/6/9 19:31
 * @Version 1.0
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinIOProperties {

    private String endpoint;

    private Integer port;

    private String accessKey;

    private String secretKey;

    private String url;

    /**
     *     //"如果是true，则用的是https而不是http,默认值是true"
     */
    private Boolean secure;

    /**
     *     //"默认存储桶"
     */
    private String bucketName;

    /**
     * 图片的最大大小
     */
    private long imageSize;

    /**
     * 其他文件的最大大小
     */
    private long fileSize;

}
