package com.pzj.project.common.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.pzj.project.common.Result;
import com.pzj.project.common.ResultCode;
import com.pzj.project.model.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private final static Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String userId = request.getHeader("userId");
        String userName = request.getHeader("x-user");

        if (checkWhiteUrl(request.getRequestURI())) {
            return true;
        }

        if(StringUtils.isAnyBlank(userId,userName)){
            Result result = Result.error(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage());
//            printJson(response, result);
//            return false;
        }

//        User user = new User();
//        user.setUserId(userId);user.setUserName(userName);

//        stringRedisTemplate.opsForValue().set(userId, JSONObject.toJSONString(user));
        return true;
    }

    private static boolean checkWhiteUrl(String requestURI) {

        if (
                requestURI.contains("swagger-ui") ||
                requestURI.contains("html") ||
                requestURI.contains("callback") ||
                requestURI.contains("test") ||
                requestURI.contains("/api/requestToModelService") ||
                requestURI.contains("/api") ||
                requestURI.contains("/download") ||
                requestURI.contains("error")
        ) {
            return true;
        }
        return false;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, X-Requested-With, token");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, OPTIONS, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
    }

    private static void printJson(HttpServletResponse response, Result result) {
        String content = JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
        printContent(response, content);
    }


    private static void printContent(HttpServletResponse response, String content) {
        try {
            response.reset();
            response.setContentType("application/json");
            response.setHeader("Cache-Control", "no-store");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
            pw.write(content);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
