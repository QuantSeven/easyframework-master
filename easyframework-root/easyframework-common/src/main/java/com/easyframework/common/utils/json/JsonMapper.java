package com.easyframework.common.utils.json;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * 封装Jackson，实现JSON String<->Java Object的Mapper.
 * 
 * 封装不同的输出风格, 使用不同的builder函数创建实例.
 * 
 * <p>
 * 项目名称：framework-generic
 * </p>
 * <p>
 * 版权：2012-广州扬基信息科技有限公司
 * </p>
 * 
 * @see framework.generic.utilities.JsonMapper
 * @version 1.0, 2012-11-30 上午10:41:49
 * @author quanyongan
 */
public class JsonMapper {

	private static Logger logger = LoggerFactory.getLogger(JsonMapper.class);
	private static ObjectMapper mapper = new ObjectMapper();

	public JsonMapper() {
		// 设置默认日期格式
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.registerModule(new Hibernate4Module());
		mapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	}

	/**
	 * 对象转换成Json字符串。如果对象为Null, 返回"null". 如果集合为空集合, 返回"[]".
	 * 
	 * @param object
	 *            Object可以是POJO，也可以是Collection或数组。
	 * @return String 转换后的Json字符串
	 * @since 1.0
	 */
	public static String toJson(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (IOException e) {
			logger.warn("write to json string error:" + object, e);
			return null;
		}
	}

	/**
	 * 反序列化POJO或简单Collection如List<String>. 如果JSON字符串为Null或"null"字符串, 返回Null.
	 * 如果JSON字符串为"[]", 返回空集合.
	 * 
	 * 如需反序列化复杂Collection如List<MyBean>, 请使用fromJson(String,JavaType)
	 * 
	 * @param jsonString
	 *            需要转换的JSON字符串
	 * @param clazz
	 *            反序列化的POJO
	 * @see #fromJson(String, JavaType)
	 * @return T 一个指定的对象
	 * @since 1.0
	 */
	public static <T> T fromJson(String jsonString, Class<T> clazz) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return mapper.readValue(jsonString, clazz);
		} catch (IOException e) {
			logger.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}

	/**
	 * 反序列化复杂Collection如List<Bean>, 先使用函數createCollectionType构造类型,然后调用本函数.
	 * 
	 * @see #createCollectionType(Class, Class...)
	 */
	/**
	 * 反序列化复杂Collection如List<Bean>, 先使用函數createCollectionType构造类型,然后调用本函数.
	 * 
	 * @see #createCollectionType(Class, Class...)
	 * @param jsonString
	 *            需要转换的JSON字符串
	 * @param javaType
	 *            反序列化的对象Java类型
	 * @return 反序列化的对象
	 * @since 1.0
	 */
	public static <T> T fromJson(String jsonString, JavaType javaType) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return (T) mapper.readValue(jsonString, javaType);
		} catch (IOException e) {
			logger.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}

	/**
	 * 读取json字符串的某个节点
	 * 
	 * @param jsonString
	 *            json字符串
	 * @return
	 * @since 1.0
	 */
	public static JsonNode readNode(String jsonString) {
		JsonNode node = null;
		try {
			node = mapper.readTree(jsonString);
		} catch (JsonProcessingException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		return node;
	}

	/**
	 * 构造泛型的Collection Type如： ArrayList<MyBean>,
	 * 则调用constructCollectionType(ArrayList.class,MyBean.class)
	 * HashMap<String,MyBean>, 则调用(HashMap.class,String.class, MyBean.class)
	 * 
	 * @param collectionClass
	 *            泛型的Collection
	 * @param elementClasses
	 *            元素类
	 * @return JavaType Java类型
	 * @since 1.0
	 */
	public static JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}

	/**
	 * 当JSON里只含有Bean的部分属性时，更新一个已存在的Bean,只覆盖部分属性
	 * 
	 * @param jsonString
	 *            需要转换的JSON字符串
	 * @param object
	 *            反序列化的对象
	 * @return T 反序列化的对象
	 * @since 1.0
	 */
	public static <T> T update(String jsonString, T object) {
		try {
			return (T) mapper.readerForUpdating(object).readValue(jsonString);
		} catch (JsonProcessingException e) {
			logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
		} catch (IOException e) {
			logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
		}
		return null;
	}

	/**
	 * 输出JSONP格式数据.
	 * 
	 * @param functionName
	 *            功能名称
	 * @param object
	 *            序列化的POJO
	 * @return String 字符串
	 * @since 1.0
	 */
	public static String toJsonP(String functionName, Object object) {
		return toJson(new JSONPObject(functionName, object));
	}

	/**
	 * 设定是否使用Enum的toString函数來读写Enum, 为False时使用Enum的name()函数来读写Enum, 默认为False.
	 * 注意本函數一定要在Mapper创建後, 所有的读写動作之前调用.
	 * 
	 * @since 1.0
	 */
	public void enableEnumUseToString() {
		mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
		mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
	}

	/**
	 * 支持使用Jaxb的Annotation，使得POJO上的annotation不用与Jackson耦合。
	 * 默认会先查找jaxb的annotation，如果找不到再找jackson的。
	 * 
	 * @since 1.0
	 *//*
	public void enableJaxbAnnotation() {
		JaxbAnnotationModule module = new JaxbAnnotationModule();
		mapper.registerModule(module);
	}*/

}
