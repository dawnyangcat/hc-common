/**
 * Copyright 2008-2009. Chongqing Communications Industry Services Co.,Ltd Information Technology Branch. All rights reserved. <a>http://www.cqcis.com</a>
 */
package pers.dawnyang.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author 田平 create 2014-5-8下午12:42:01
 */
@Component
public class SpringUtil implements ApplicationContextAware {
	private static ApplicationContext appCtx; // Spring应用上下文环境

	/*
	 * 实现了ApplicationContextAware 接口，必须实现该方法；通过传递applicationContext参数初始化成员变量applicationContext
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringUtil.appCtx = applicationContext;
	}

	public static ApplicationContext getAppCtx() {
		return appCtx;
	}

	public static Object getBean(String name) throws BeansException {
		return appCtx.getBean(name);
	}

	public static <T> T getBean(Class<T> requiredType) throws BeansException {
		return appCtx.getBean(requiredType);
	}

}
