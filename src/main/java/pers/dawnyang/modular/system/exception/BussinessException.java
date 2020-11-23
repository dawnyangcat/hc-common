package pers.dawnyang.modular.system.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.dawnyang.common.domain.ResultEnum;

@Data
@EqualsAndHashCode(callSuper = true)
public class BussinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String msg;
    private int code = -1;

    public BussinessException() {

    }

    public BussinessException(String msg) {
	this.msg = msg;
    }

    public BussinessException(String msg, int code) {
	this.msg = msg;
	this.code = code;
    }

    public BussinessException(ResultEnum re) {
	this.msg = re.getMsg();
	this.code = re.getCode();
    }

}
