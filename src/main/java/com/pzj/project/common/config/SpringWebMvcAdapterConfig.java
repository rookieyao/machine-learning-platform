package com.pzj.project.common.config;

import com.google.common.collect.Lists;
import com.pzj.project.common.annotation.CurrentUserMethodArgumentResolver;
import com.pzj.project.common.filter.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Author yaoqi
 * @Description 注册拦截器
 * @Date 2022/5/26 14:57
 * @Param
 * @return
 **/
@Configuration
public class SpringWebMvcAdapterConfig implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor loginInterceptor;
    @Autowired
    CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> list = Lists.newArrayList();
//        list.add("/project/trainModelDataDownload");
//        list.add("/project/getProjectCount");
//        list.add("/project/totalTaskNum");
//        list.add("/fileManage/fileDownload");
        registry.addInterceptor(loginInterceptor).excludePathPatterns(list).addPathPatterns("/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentUserMethodArgumentResolver);
    }
}
