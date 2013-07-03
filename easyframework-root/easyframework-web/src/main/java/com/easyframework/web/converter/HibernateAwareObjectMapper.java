package com.easyframework.web.converter;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

public class HibernateAwareObjectMapper extends ObjectMapper {
	private static final long serialVersionUID = 5900053419240857155L;

	public HibernateAwareObjectMapper() {
		registerModule(new Hibernate4Module());
		// 设置默认日期格式
		setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		registerModule(new Hibernate4Module());
		setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
		configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	}
}
