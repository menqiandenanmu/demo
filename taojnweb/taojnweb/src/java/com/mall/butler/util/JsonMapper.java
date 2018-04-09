package com.mall.butler.util;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;

public class JsonMapper {

	private static final ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
	}

	/**
	 * 将对象转换为JSON字符串。
	 * 
	 * @param object
	 *            待转换对象
	 * @return
	 */
	public static String toJson(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (IOException e) {
			throw new RuntimeException("write to json string error:" + object, e);
		}
	}

	/**
	 * 輸出JSONP格式數據.
	 */
	public static String toJsonP(String functionName, Object object) {
		return toJson(new JSONPObject(functionName, object));
	}

	/**
	 * 将JSON字符串转换为Object。
	 * 
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	public static <T> T fromJson(String jsonString, Class<T> clazz) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}
		try {
			return objectMapper.readValue(jsonString, clazz);
		} catch (IOException e) {
			throw new RuntimeException("parse json string error:" + jsonString, e);
		}
	}
}
