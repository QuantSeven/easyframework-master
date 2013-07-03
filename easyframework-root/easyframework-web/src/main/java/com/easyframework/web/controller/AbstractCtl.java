package com.easyframework.web.controller;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.easyframework.common.utils.properties.PropertiesUtil;

/**
 * 所有控制器的基类，提供了国际化的消息处理(如有需要可以在此基础上扩展所有公共的方法)
 * 
 * @author QuantSeven 2013-6-21 下午1:23:44
 */
@Controller
public class AbstractCtl {

	// 首先注入Spring容器中的ReloadableResourceBundleMessageSource资源文件读取类
	protected ReloadableResourceBundleMessageSource messageSource;

	protected SessionLocaleResolver localeResolver;

	@Resource
	public void setMessageSource(ReloadableResourceBundleMessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Resource
	public void setLocaleResolver(SessionLocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}

	protected Locale getLocale() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
	}

	@RequestMapping(value = "common/getI18NMessage", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getI18NMessage(@RequestParam("prefix") String prefix) {
		return PropertiesUtil.getPropMapStartingWith(prefix, "i18n/easyframework_" + getLocale() + ".properties");
	}

	/**
	 * 获取国际化文件的内容
	 * 
	 * @param code
	 *            国际化文件的代码
	 * @return 国际化的内容
	 */
	public String getMessage(String code) {
		return messageSource.getMessage(code, null, getLocale());
	}

	/**
	 * 获取国际化文件的内容
	 * 
	 * @param code
	 *            国际化文件的代码
	 * @param args
	 *            参数
	 * @return 国际化的内容
	 */
	public String getMessage(String code, Object[] args) {
		return messageSource.getMessage(code, args, getLocale());
	}

	/**
	 * 获取国际化文件的内容
	 * 
	 * @param code
	 *            国际化文件的代码
	 * @param args
	 *            参数
	 * @param defaultMessage
	 *            默认的信息
	 * @return 国际化的内容
	 */
	public String getMessage(String code, Object[] args, String defaultMessage) {
		return messageSource.getMessage(code, args, defaultMessage, getLocale());
	}

}
