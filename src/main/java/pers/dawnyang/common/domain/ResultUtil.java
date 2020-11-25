package pers.dawnyang.common.domain;

public class ResultUtil<T> {

	public static <T> Result<T> success(T object) {
		Result<T> result = new Result<>();
		result.setSuccess(true);
		result.setCode(ResultEnum.SUCCESS.getCode());
		result.setMsg(ResultEnum.SUCCESS.getMsg());
		result.setData(object);
		return result;
	}

	public static <T> Result<T> success() {
		return success(null);
	}

	public static <T> Result<T> error() {
		Result<T> result = new Result<>();
		result.setSuccess(false);
		result.setCode(ResultEnum.ERROR.getCode());
		result.setMsg(ResultEnum.ERROR.getMsg());
		return result;
	}

	public static <T> Result<T> error(ResultEnum re, T object) {
		Result<T> result = new Result<>();
		result.setSuccess(false);
		result.setCode(re.getCode());
		result.setMsg(re.getMsg());
		return result;
	}

	public static <T> Result<T> error(Integer code, String msg) {
		Result<T> result = new Result<>();
		result.setSuccess(false);
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}

	public static <T> Result<T> error(ResultEnum re) {
		Result<T> result = new Result<>();
		result.setSuccess(false);
		result.setCode(re.getCode());
		result.setMsg(re.getMsg());
		return result;
	}

	public static <T> Result<T> globalError() {
		Result<T> result = new Result<>();
		result.setSuccess(false);
		result.setCode(ResultEnum.UNKNOWN_ERROR.getCode());
		result.setMsg(ResultEnum.UNKNOWN_ERROR.getMsg());
		return result;
	}
}