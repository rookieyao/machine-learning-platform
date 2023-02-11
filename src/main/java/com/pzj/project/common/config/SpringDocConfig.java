package com.pzj.project.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName SpringDocConfig
 * @Description
 * @Author yaoqi
 * @Date 2022/6/2 17:34
 * @Version 1.0
 **/
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "自定义1",
                description = "自定义2",
                version = "1.0"
        )
)
@SecurityScheme(
        name = "token",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SpringDocConfig {
}
