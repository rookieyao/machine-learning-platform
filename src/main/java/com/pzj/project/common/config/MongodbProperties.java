package com.pzj.project.common.config;

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
@ConfigurationProperties(prefix = "mongodb")
public class MongodbProperties {

}
