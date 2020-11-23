package pers.dawnyang.common.domain;

public class ResultUtil<T> {

	/**
	 * 查询成功且返回数据
	 * <P/>
	 * success: true
	 * <P/>
	 * code: 200
	 * <P/>
	 * msg: 成功
	 * <P/>
	 * data: 业务数据
	 * <P/>
	 * 
	 * @param object
	 * @return
	 */
	public static <T> Result<T> success(T object) {
		Result<T> result = new Result<>();
		result.setSuccess(true);
		result.setCode(ResultEnum.SUCCESS.getCode());
		result.setMsg(ResultEnum.SUCCESS.getMsg());
		result.setData(object);
		return result;
	}

	/** 操作成功不带数据 **/
	public static <T> Result<T> success() {
		return success(null);
	}

	/**
	 * 默认操作失败
	 * <P/>
	 * success: false
	 * <P/>
	 * code: -1
	 * <P/>
	 * msg: 未知错误
	 * <P/>
	 * 
	 * @param <T>
	 * @return
	 */
	public static <T> Result<T> error() {
		Result<T> result = new Result<>();
		result.setSuccess(false);
		result.setCode(ResultEnum.ERROR.getCode());
		result.setMsg(ResultEnum.ERROR.getMsg());
		return result;
	}

	/**
	 * 返回失败，带数据
	 * 
	 * @param object
	 * @return
	 */
	public static <T> Result<T> error(ResultEnum re, T object) {
		Result<T> result = new Result<>();
		result.setSuccess(false);
		result.setCode(re.getCode());
		result.setMsg(re.getMsg());
		return result;
	}

	/**
	 * 自定义错误返回
	 * 
	 * @param      <T>
	 * @param code
	 * @param msg
	 * @return
	 */
	public static <T> Result<T> error(Integer code, String msg) {
		Result<T> result = new Result<>();
		result.setSuccess(false);
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}

	/**
	 * 返回自定义枚举值错误
	 * 
	 * @param    <T>
	 * @param re
	 * @return
	 */
	public static <T> Result<T> error(ResultEnum re) {
		Result<T> result = new Result<>();
		result.setSuccess(false);
		result.setCode(re.getCode());
		result.setMsg(re.getMsg());
		return result;
	}

	/*	*//**
			 * 返回自定义枚举值成功结果
			 * 
			 * @param    <T>
			 * @param re
			 * @return
			 *//*
				 * public static <T> Result<T> success(ResultEnum re) { Result<T> result = new
				 * Result<>(); result.setSuccess(false); result.setCode(re.getCode());
				 * result.setMsg(re.getMsg()); return result; }
				 */

	/**
	 * 统一默认异常返回
	 * <P/>
	 * success: false
	 * <P/>
	 * code: -1
	 * <P/>
	 * msg: 未知错误
	 * <P/>
	 */
	public static <T> Result<T> globalError() {
		Result<T> result = new Result<>();
		result.setSuccess(false);
		result.setCode(ResultEnum.UNKNOWN_ERROR.getCode());
		result.setMsg(ResultEnum.UNKNOWN_ERROR.getMsg());
		return result;
	}
}