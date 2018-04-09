package com.mall.util.common.lang;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 处理异常的工具类。
 * 
 * @author caedmon
 * @version $Id: ArrayUtil.java 2010-03-15
 */
public class ExceptionUtil {
	/**
	 * 取得异常的stacktrace字符串。
	 * 
	 * @param throwable
	 *            异常
	 * 
	 * @return stacktrace字符串
	 */
	public static String getStackTrace(Throwable throwable) {
		StringWriter buffer = new StringWriter();
		PrintWriter out = new PrintWriter(buffer);

		throwable.printStackTrace(out);
		out.flush();

		return buffer.toString();
	}
}
