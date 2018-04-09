package com.mall.util.common.lang;

import java.util.regex.Pattern;

/**
 * 数字处理类
 * 
 * @author caedmon Version 2010-6-10
 */
public class NumberUtil {
	/**
	 * 字符串转换成数字
	 * 
	 * @param param
	 * @return
	 */
	public static Integer getInt(String param) {
		Object object = (param == null) ? 0 : param;
		if (isNumber(object.toString())) {
			return Integer.valueOf(object.toString());
		} else
			throw new NumberFormatException();
	}

	/**
	 * 判断是否为数字
	 * 
	 * @param param
	 * @return
	 */
	public static boolean isNumber(String param) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(param).matches();
	}
}
