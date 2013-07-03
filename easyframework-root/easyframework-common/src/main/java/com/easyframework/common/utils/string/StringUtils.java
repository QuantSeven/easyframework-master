package com.easyframework.common.utils.string;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

	/**
	 * 判断是否为空或null
	 * 
	 * @param obj
	 *            对象
	 * @return boolean
	 */
	public static boolean isNullOrEmpty(Object obj) {
		return obj == null || "".equals(obj.toString());
	}
}
