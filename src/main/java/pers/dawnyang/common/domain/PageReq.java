package pers.dawnyang.common.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 接收json格式的分页查询请求类
 * 
 * @author yangyh create 2020年7月15日下午22:24:43
 * 
 */
@Getter
@Setter
@ApiModel(description = "分页请求对象")
public class PageReq<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "页码", required = true, example = "1")
	// 翻译前端参数
	@JsonProperty(value = "current")
	private long current = 1;

	@ApiModelProperty(value = "页大小", required = true, example = "10")
	@JsonProperty(value = "size")
	private long size = 10;

	@ApiModelProperty(value = "参数实体")
	private T query;

}
