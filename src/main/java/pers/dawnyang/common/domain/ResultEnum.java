package pers.dawnyang.common.domain;

/**
 * 返回枚举值
 * 
 * @author dawn
 *
 */
public enum ResultEnum {
	SUCCESS(true, 200, "操作成功"), ERROR(false, 500, "操作失败！"), UNKNOWN_ERROR(false, -1, "系统错误！"),
	DATA_IS_NULL(true, 3, "数据为空"), NOT_PARENT_NODE(false, -1, "不能添加到叶子节点！"), WRONG_PHONE(false, -1, "手机号错误"),
	UNAUTH(false, 500, "功能未授权"), VALIDE_ERROR(false, 500, ""), NOT_LEAF_NODE(false, -1, "请选择叶子节点！"),
	ERROR_VALIDCODE(false, 500, "验证码错误"), ERROR_USERPWD(false, 500, "用户名或密码错误"),
	ERROR_EXCEEDROLE(false, 500, "角色等级太低，无权操作！"), ERROR_EXCEEDROLE_USER(false, 500, "角色等级太低，无权修改用户！"),
	ERROR_EXCEEDROLE_ROLE(false, 500, "角色等级太低，无权赋予角色！"), ERROR_SESSION_EXPIRES(false, 500, "会话过期，请重新登录！"),
	ERROR_SMS_SEND(false, 500, "短信发送失败！"), SUCCESS_SMS_SEND(true, 200, "短信发送成功"), WRONG_SQL(false, 500, "sql结构错误！"),
	WRONG_AUTOCODE_CODEKIND(false, 500, "代码位置错误！"), WRONG_AUTOCODE_ADDCODE(false, 500, "追加代码应在叶子目录！"),
	WRONG_AUTOCODE_CREATECODE(false, 500, "新建功能应在父级目录！"), WRONG_AUTOCODE_HASVIEW(false, 500, "如需要界面则必须为新建功能！"),
	WRONG_AUTOCODE_NOFIELDINFO(false, 500, "无字段配置信息！");

	private boolean success;
	private Integer code;
	private String msg;

	ResultEnum(boolean success, Integer code, String msg) {
		this.success = success;
		this.code = code;
		this.msg = msg;
	}

	public Boolean getSuccess() {
		return success;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}