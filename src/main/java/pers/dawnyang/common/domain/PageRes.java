package pers.dawnyang.common.domain;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 
 * @author yangyh create 2020年7月15日下午22:24:43
 * @see
 */
@ApiModel(description = "分页返回对象")
@Getter
@Setter
public class PageRes<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "总数")
	private Long total;

	@ApiModelProperty(value = "结果数据")
	private List<T> records;

}
