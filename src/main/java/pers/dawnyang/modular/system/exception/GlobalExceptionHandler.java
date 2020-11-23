package pers.dawnyang.modular.system.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import pers.dawnyang.common.domain.Result;
import pers.dawnyang.common.domain.ResultEnum;
import pers.dawnyang.common.domain.ResultUtil;

/**
 * 
 * @author dawn
 *
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	// 全局统一异常
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Result<String> defultExcepitonHandler(HttpServletRequest request, Exception e) {
		log.error("defultExcepiton:", e);
		return ResultUtil.globalError();
	}

	// 自定义业务异常，业务逻辑里面throw new BusinessException("错误信息！");
	@ExceptionHandler(BussinessException.class)
	@ResponseBody
	public Result<String> BusinessExcepitonHandler(HttpServletRequest request, HttpServletResponse response,
			BussinessException e) {
		log.error("BusinessException:", e);
		return ResultUtil.error(e.getCode(), e.getMsg());
	}

	// 参数校验异常
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public Result<String> MethodArgumentNotValidExceptionHandler(HttpServletRequest request,
			HttpServletResponse response, MethodArgumentNotValidException e) {
		log.error("MethodArgumentNotValidException:", e);
		BindingResult bindingResult = e.getBindingResult();
		StringBuilder errMsg = new StringBuilder(bindingResult.getFieldErrors().size() * 16);
		for (int i = 0; i < bindingResult.getFieldErrors().size(); i++) {
			if (i > 0) {
				errMsg.append(",");
			}
			FieldError error = bindingResult.getFieldErrors().get(i);
			errMsg.append(error.getField() + ":" + error.getDefaultMessage());
		}
		return ResultUtil.error(ResultEnum.VALIDE_ERROR.getCode(), errMsg.toString());
	}

	@ExceptionHandler(ValidationException.class)
	@ResponseBody
	public Result<String> ValidationExceptionHandler(HttpServletRequest request, HttpServletResponse response,
			ValidationException e) {
		log.error("ValidationException:", e);
		String message = e.getMessage();
		String str1 = message.substring(0, message.indexOf(":"));
		String str2 = message.substring(str1.length() + 1, message.length());
		return ResultUtil.error(ResultEnum.VALIDE_ERROR.getCode(), str2);
	}

}
