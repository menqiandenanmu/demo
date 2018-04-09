package com.mall.util.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mall.util.common.enums.AbstractEnumSupport;

public class GenericUtil {
	/**
	 * 判断对象是否相等（或均为空）
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static <T> boolean equal(T s1, T s2) {
		return null == s1 ? null == s2 : s1.equals(s2);
	}

	@SuppressWarnings("unchecked")
	public static <T extends AbstractEnumSupport> T valueOf(Class<T> clazz,
			int value) {
		try {
			return (T) clazz.getMethod("valueOf", new Class[] { Integer.TYPE })
					.invoke(clazz, new Object[] { value });
		} catch (Exception e) {
			throw new RuntimeException(
					"The enum does not support valueOf() method correctly.", e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T extends AbstractEnumSupport> T[] values(Class<T> clazz) {
		try {
			return (T[]) clazz.getMethod("values", new Class[0]).invoke(clazz,
					new Object[0]);
		} catch (Exception e) {
			throw new RuntimeException(
					"The enum does not support values() method correctly.", e);
		}
	}

	public static <T> T[] remanent(T[] src, T[] remove) {
		List<T> r = new ArrayList<T>(Arrays.asList(src));
		r.removeAll(Arrays.asList(remove));
		return r.toArray(Arrays.copyOf(src, 0));
	}
}
