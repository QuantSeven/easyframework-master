package com.easyframework.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.easyframework.common.utils.string.StringUtils;

@Component("contextUtils")
public class ContextUtilsImpl implements ContextUtils {

	private ApplicationContext applicationContext;

	@Override
	public <T> T getBean(Class<T> clazz, String beanName) {
		return (T) applicationContext.getBean(beanName, clazz);
	}

	@Override
	public <T> T getDao(Class<T> daoClass) {
		return (T) applicationContext.getBean(StringUtils.uncapitalize(daoClass.getSimpleName()));
	}

	@Override
	public <T> T getService(Class<T> serviceClass) {
		return (T) applicationContext.getBean(StringUtils.uncapitalize(serviceClass.getSimpleName()));
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
