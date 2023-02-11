package com.pzj.project.common.handler;

import com.pzj.project.common.Result;
import com.pzj.project.common.enums.ErrorCodeEnum;
import com.pzj.project.common.exception.CheckException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestControllerAdvice

public class GobalExceptionHandler {
	@Value("${spring.application.name}")
	private String applicationName;

	@Value("${spring.profiles.active}")
	private String profile;



	
	private static final Logger log = LoggerFactory.getLogger(GobalExceptionHandler.class);
	
	@ExceptionHandler(CheckException.class)
	@ResponseBody
	public Result<?> error(CheckException e) {
		log.info( "MIAutoPlatformBizException:",  e.getMessage());

		return Result.error(e.getErrorCode().getStatus(),e.getErrorCode().getMessage(),e);
	}

	/**
	 * 所有的校验失败情况都会由这里处理
	 */
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Result exceptionHandler(MethodArgumentNotValidException e) {

		log.error("req params error", e);
		return Result.error(e.getMessage(), e);
	}


	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Result<?> error(Exception e) {
		e.printStackTrace();
		log.info("Excetion:{}", e.getMessage());

		return Result.error(ErrorCodeEnum.SYSTEM_ERROR.getCode(), e.getMessage());
	}


	@ExceptionHandler(BadSqlGrammarException.class)
	@ResponseBody
	public Result<?> error(BadSqlGrammarException e) {
		return Result.error(ErrorCodeEnum.SYSTEM_ERROR);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseBody
	public Result<?> error(JsonParseException e) {
		String errorMsg = getChinese(e.getMessage());
		return Result.error(StringUtils.isNotBlank(errorMsg.trim())?errorMsg:e.getMessage(),e);
	}

	private static String getChinese(String paramValue) {
		String regex = "([\u4e00-\u9fa5]+)";
		String str = "";
		Matcher matcher = Pattern.compile(regex).matcher(paramValue);
		while (matcher.find()) {
			str += matcher.group(0);
		}
		return str;
	}

   private static StringWriter getExceptionStackInfo(Exception e) {
	   StringWriter me = new StringWriter();
	   PrintWriter pw = new PrintWriter(me);
	   e.printStackTrace(pw);
	   pw.flush();
	   return me;
   }
}
