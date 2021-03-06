package com.mall.util.ibatis.util;

import java.util.Map;

/**
 * @author caedmon
 */
@SuppressWarnings("all")
public class MapUtils {

	public static void putIfNull(Map map, Object key, Object defaultValue) {
		if (key == null)
			throw new IllegalArgumentException("key must be not null");
		if (map == null)
			throw new IllegalArgumentException("map must be not null");
		if (map.get(key) == null) {
			map.put(key, defaultValue);
		}
	}

}
