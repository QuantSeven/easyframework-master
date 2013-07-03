package com.easyframework.core.utils;

import org.springframework.context.ApplicationContextAware;

public interface ContextUtils extends ApplicationContextAware {

	public <T> T getBean(Class<T> clazz, String beanName);

	public <T> T getDao(Class<T> daoClass);

	public <T> T getService(Class<T> serviceClass);
}
