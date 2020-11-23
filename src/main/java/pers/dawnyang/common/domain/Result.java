package pers.dawnyang.common.domain;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 基础业务返回类
 * 
 * @author dawn
 *
 */
@Setter
@Getter
@ApiModel(description = "请求响应结果")
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "调用是否成功")
	private boolean success;
	@ApiModelProperty(value = "错误编码")
	private Integer code;
	@ApiModelProperty(value = "错误信息")
	private String msg;
	@ApiModelProperty(value = "业务数据")
	private T data;

	public Result() {
	}

	public Result(boolean success, Integer code, String msg) {
		this.success = success;
		this.code = code;
		this.msg = msg;
	}

	public Result(boolean success, Integer code, String msg, T data) {
		this.success = success;
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

}