package com.yf.zx.core.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * ApplicationContextUtil [spring应用上下文操作类]
 * 在action之外或者control类之外获取webapplication
 *  
 * @author zhang.yifeng
 * @CreateDate 2017年11月23日
 * @version 1.0.0
 * @since  1.0.0 
 * @see com.yf.zx.core.util.spring 
 *
 */
public class ApplicationContextUtil implements ApplicationContextAware {

	
	private static ApplicationContext applicationContext;     //Spring应用上下文环境  
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextUtil.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 判断ApplicationContext是否注入
	 */
	private static void checkApplicationContext() {
		if (applicationContext == null) {
			throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义ApplicationContextUtil");
		}
	}
	
	/**
	 * 根据Bean的名称查找Bean
	 * 
	 * @param beanName bean名称
	 */
	@SuppressWarnings("unchecked")
	public static <T>T getBean(String beanName){
		checkApplicationContext();
		return (T)applicationContext.getBean(beanName);
	}
}
