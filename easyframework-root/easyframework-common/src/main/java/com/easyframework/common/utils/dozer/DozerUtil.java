package com.easyframework.common.utils.dozer;

import java.util.Collection;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.MappingException;

import com.google.common.collect.Lists;

/**
 * 封装Dozer, 实现深度转换Bean<->Bean的Util.实现:
 * 
 * 1. 持有Mapper的单例. 2. 返回值类型转换. 3. 批量转换Collection中的所有对象. 4. 区分创建新的B对象与将对象A值复制到已存在的B对象两种函数.
 * 
 * @author QuantSeven 2013-6-19 下午12:46:28
 */
public class DozerUtil {

	/**
	 * 持有Dozer单例, 避免重复创建DozerMapper消耗资源.
	 */
	private static DozerBeanMapper dozer = new DozerBeanMapper();

	/**
	 * 基于Dozer转换对象的类型. source-->destinationClass
	 * 
	 * @param source
	 *            源对象
	 * @param destinationClass
	 *            目标类
	 * @return T
	 * @since 1.0
	 */
	public static <T> T map(Object source, Class<T> destinationClass) {
		return dozer.map(source, destinationClass);
	}

	public static <T> T map(Object source, Class<T> destinationClass, String mapId) throws MappingException {
		return dozer.map(source, destinationClass, mapId);
	}

	/**
	 * 基于Dozer转换Collection中对象的类型.sourceList--->destinationClass
	 * 
	 * @param sourceList
	 *            源 Collection所有类型
	 * @param destinationClass
	 *            目标类
	 * @return List<T>
	 * @since 1.0
	 */
	public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
		List<T> destinationList = Lists.newArrayList();
		for (Object sourceObject : sourceList) {
			T destinationObject = dozer.map(sourceObject, destinationClass);
			destinationList.add(destinationObject);
		}
		return destinationList;
	}

	/**
	 * 基于Dozer将对象A的值拷贝到对象B中.source--->destinationObject
	 * 
	 * @param source
	 *            源对象
	 * @param destinationObject
	 *            目标对象
	 * @since 1.0
	 */
	public static void copy(Object source, Object destinationObject) {
		dozer.map(source, destinationObject);
	}

	public static void copy(Object source, Object destination, String mapId) throws MappingException {
		dozer.map(source, destination, mapId);
	}

}
