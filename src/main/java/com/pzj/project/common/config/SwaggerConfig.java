package com.pzj.project.common.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName SwaggerConfig
 * @Description
 * // 应用在控制层
 * @Api(value="controler介绍",tags={"controler介绍"})
 * @ApiOperation(value = "方法介绍", notes = "方法介绍")
 * @ApiImplicitParams @ApiImplicitParam 结合使用 方法参数介绍
 * @ApiParam类似于上面两个注解(推荐使用)
 *
 * //应用于实体类
 * @ApiModel(description = "实体类介绍")
 * @ApiModelProperty(value = "属性介绍")
 *
 *  * @ApiOperation("叶勇的接口")
 *  * @PostMapping("/yeyong")
 *  * @ResponseBody
 *  * public String hello(@ApiParam("这个名字会被返回")String username){
 *  *    return username;
 *  * }
 *
 * @Author yaoqi
 * @Date 2022/9/19 9:50
 * @Version 1.0
 *
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // swagger显示标识符在不同环境配置不同参数(测试环境开放,正式环境关闭保证安全)
    @Value("${spring.swagger.enableSwagger}")
    private boolean enableSwagger;

    /**
     * 接口黑名单，swagger 生成接口的时候，不生成黑名单中的接口
     */
    private static final List<String> excludedPathPrefix = Arrays.asList(
            "/originFileAppend",
            "/pipelineFileConfig",
            "/projectServiceVersionApproving",
            "/projectServiceVersion",
            "/rCharWordVectorOriginData",
            "/rOriginDataSync",
            "/rTrainDataLabelTask",
            "/rTrainModelVersionService",
            "/syncDataConfig",
            "/test",
            "/trainDataVersion",
            "/trainModelVersion"
    );

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(enableSwagger)
                .select()
                //controller接口项目地址
                .apis(RequestHandlerSelectors.basePackage("com.pzj.project.controller"))
                .paths((s) -> {
                    for(String pathPrefix : excludedPathPrefix) {
                        if(StringUtils.startsWith(s, pathPrefix)) {
                            return false;
                        }
                    }
                    return true;
                })
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("机器学习平台后端服务接口文档")
                .description("描述信息")
                .version("1.0.0")
                .build();
    }
}
