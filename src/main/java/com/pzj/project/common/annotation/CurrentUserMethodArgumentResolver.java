package com.pzj.project.common.annotation;

import com.alibaba.fastjson.JSONObject;

import com.pzj.project.common.util.ThreadLocalUtil;
import com.pzj.project.model.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

/**
 * @Author yaoqi
 * @Description //TODO
 * @Date 2022/5/25 10:11 
 * @Param 
 * @return 
 **/
@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private final static Logger log = LoggerFactory.getLogger(CurrentUserMethodArgumentResolver.class);

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest webRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {

        String userId = webRequest.getHeader("userId");


        if (StringUtils.isBlank(userId)) {
            throw new MissingServletRequestPartException("userId in Header can't be null");
        } else {

//            String key = Md5Utils.md5Hex(userId);
            String info = stringRedisTemplate.opsForValue().get(userId);

            User user = JSONObject.parseObject(info, User.class);
            ThreadLocalUtil.set("userId",user.getUserName());
            return user;
        }
    }
}