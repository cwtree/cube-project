package com.cube.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cube.pojo.MyResp;
import com.cube.pojo.Resp;

import cn.hutool.core.exceptions.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常处理 尽量不要把异常处理依赖于此全局异常处理器 能在业务里明确处理的，全部提前处理掉
 * 
 * @author phoenix
 * @date 2020年2月5日
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(Throwable.class)
	@ResponseBody
	public MyResp handleException(HttpServletRequest request, Throwable e) {
		log.error("进入全局异常Throwable处理", e);
		Map<String, Object> map = new HashMap<>(2);
		map.put("url", request.getRequestURL().toString());
		map.put("exception", ExceptionUtil.getMessage(e));
		return MyResp.builder().code(Resp.ERROR.getCode()).msg(Resp.ERROR.getMsg()).errMsg(map).build();
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public MyResp paramException(HttpServletRequest request, Exception e) {
		log.error("进入异常Exception处理", e);
		StringBuffer errorMsg = new StringBuffer();
		if (e instanceof ConstraintViolationException) {
			Set<ConstraintViolation<?>> cves = ((ConstraintViolationException) e).getConstraintViolations();
			cves.forEach(ex -> errorMsg.append(ex.getMessage()));
			return MyResp.builder().code(Resp.PARAM_ERROR.getCode()).msg(e.getMessage()).build();
		} else if (e instanceof MissingPathVariableException) {
			errorMsg.append("请检查参数 " + ((MissingPathVariableException) e).getVariableName());
			return MyResp.builder().code(Resp.PARAM_ERROR.getCode()).msg(Resp.PARAM_ERROR.getMsg())
					.errMsg("URL参数有误," + errorMsg).build();
		} else if (e instanceof MethodArgumentNotValidException) {
			errorMsg.append(
					((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().get(0).getDefaultMessage());
			return MyResp.builder().code(Resp.PARAM_ERROR.getCode()).msg(Resp.PARAM_ERROR.getMsg())
					.errMsg("方法参数有误," + errorMsg).build();
		} else {
			log.error("请求异常", e);
			errorMsg.append("参数异常").append("#").append(ExceptionUtil.getMessage(e));
			return MyResp.builder().code(Resp.PARAM_ERROR.getCode()).msg(Resp.PARAM_ERROR.getMsg())
					.errMsg(errorMsg.toString()).build();
		}
	}

}
