package com.easyframework.common.utils.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * Properties配置文件读取工具类
 * 
 * @author QuantSeven 2013-6-24 下午2:59:29
 */
public class PropertiesUtil {

	private static ResourceLoader resourceLoader = new DefaultResourceLoader();

	/**
	 * 取出String类型的Property
	 */
	public static String getProperty(String resourcesPath, String key) {
		Properties properties = loadProperties(resourcesPath);
		if (properties == null) {
			return null;
		}
		String systemProperty = System.getProperty(key);
		if (systemProperty != null) {
			return systemProperty;
		}
		return properties.getProperty(key);
	}

	/**
	 * 取出String类型的Property.如果都為Null则返回Default值.
	 */
	public static String getProperty(String resourcesPath, String key, String defaultValue) {
		String value = getProperty(resourcesPath, key);
		return value != null ? value : defaultValue;
	}

	/**
	 * 取出Integer类型的Property.如果都為Null或内容错误则抛出异常.
	 */
	public static Integer getInteger(String resourcesPath, String key) {
		String value = getProperty(resourcesPath, key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Integer.valueOf(value);
	}

	/**
	 * 取出Integer类型的Property.如果都為Null則返回Default值，如果内容错误则抛出异常
	 */
	public Integer getInteger(String resourcesPath, String key, Integer defaultValue) {
		String value = getProperty(resourcesPath, key);
		return value != null ? Integer.valueOf(value) : defaultValue;
	}

	/**
	 * 文件路径使用Spring Resource格式.
	 */
	private static Properties loadProperties(String... resourcesPaths) {
		Properties props = new Properties();

		for (String location : resourcesPaths) {
			InputStream is = null;
			try {
				Resource resource = resourceLoader.getResource(location);
				is = resource.getInputStream();
				props.load(is);
			} catch (IOException ex) {
			} finally {
				IOUtils.closeQuietly(is);
			}
		}
		return props;
	}

	/**
	 * 获取Properties配置文件的所有配置Map
	 * 
	 * @param resourcesPaths
	 *            文件名
	 * @return 配置Map
	 */
	public static Map<String, Object> getPropMap(String... resourcesPaths) {
		Map<String, Object> popMap = new HashMap<String, Object>();
		Properties properties = loadProperties(resourcesPaths);
		for (Object o : properties.keySet()) {
			String key = o.toString();
			String value = properties.getProperty(key);
			popMap.put(key, value);
		}
		return popMap;
	}

	/**
	 * 获取资源文件以指定前缀开始的值
	 * 
	 * @param resourcesPaths文件名
	 * @return 配置Map
	 */
	public static Map<String, Object> getPropMapStartingWith(String prefix, String... resourcesPaths) {
		Map<String, Object> popMap = new HashMap<String, Object>();
		Properties properties = loadProperties(resourcesPaths);
		for (Object o : properties.keySet()) {
			String key = o.toString();
			if (key.contains(prefix) && key.startsWith(prefix)) {
				String value = properties.getProperty(key);
				popMap.put(key, value);
			}
		}
		return popMap;
	}

}
