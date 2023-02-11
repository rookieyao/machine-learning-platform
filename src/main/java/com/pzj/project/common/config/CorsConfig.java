package com.pzj.project.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName CorsConfig
 * @Description
 * @Author yaoqi
 * @Date 2022/6/20 17:50
 * @Version 1.0
 **/
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("Content-Type","X-Requested-With","X-Roles","X-User","accept,Origin","Access-Control-Request-Method","Access-Control-Request-Headers","Authorization")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowCredentials(true);
    }
}
