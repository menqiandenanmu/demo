/**
 * @author caedmon
 */
package com.mall.util.ibatis.spring.mutildatesource;

@SuppressWarnings("all")
public class DbContextHolder {

	private static final ThreadLocal contexthodler = new ThreadLocal();

	public static void setDbType(String dbType) {
		contexthodler.set(dbType);
	}

	public static String getDbType() {
		return (String) contexthodler.get();
	}

	public static void clearDbType() {
		contexthodler.remove();
	}
}
