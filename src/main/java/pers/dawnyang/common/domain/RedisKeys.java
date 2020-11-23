package pers.dawnyang.common.domain;

public class RedisKeys {

	/**
	 * 会话缓存时间，单位毫秒
	 */
	public static final Long sessionTimeOut = (long) (30 * 60 * 1000);

	/**
	 * 用户登录标志
	 */
	public static final String userLoginKey = "user:login:";

	/**
	 * shiro会话key
	 */
	public static final String shiroSessionKey = "shiro:session:";

	/**
	 * 验证码cookieId
	 */
	public static final String captchaCookieId = "CaptchaImgCookieId";

}
