package com.pzj.project.common.util;


import com.pzj.project.common.Result;
import com.pzj.project.common.enums.ErrorCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * @Author yaoqi
 * @Description 校验工具类
 * @Date 2022/5/24 14:40
 **/
public class ValidatorUtils {

    private final static Logger log = LoggerFactory.getLogger(ValidatorUtils.class);

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * bean整体校验，有不合规范，抛出第1个违规异常
     */
    public static void validate(Object obj, Class<?>... groups) {
        Set<ConstraintViolation<Object>> resultSet = validator.validate(obj, groups);
        if (resultSet.size() > 0) {
            //如果存在错误结果，则将其解析并进行拼凑后异常抛出
            List<String> errorMessageList = resultSet.stream().map(o -> o.getMessage()).collect(Collectors.toList());
            StringBuilder errorMessage = new StringBuilder();
            errorMessageList.stream().forEach(o -> errorMessage.append(o + ";"));
            throw new IllegalArgumentException(errorMessage.toString());
        }
    }


    public static Result<?> getResult(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = "";
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                message = message + fieldError.getDefaultMessage() + ",";
            }
            log.info("参数错误:" + message.substring(0, message.length() - 1));
            return Result.error(ErrorCodeEnum.PARAM_ERROR.getCode(), message.substring(0, message.length() - 1));
        }
        return null;
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        //判断是否有小数点
        if (str.indexOf(".") > 0) {
            //判断是否只有一个小数点
            if (str.indexOf(".") == str.lastIndexOf(".") && str.split("\\.").length == 2) {
                return pattern.matcher(str.replace(".", "")).matches();
            } else {
                return false;
            }
        } else {
            return pattern.matcher(str).matches();
        }
    }
}

