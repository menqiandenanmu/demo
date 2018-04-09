package com.mall.butler.helper.json;

import java.util.Collection;

public interface JSONHelper{
	String serialize(Object object);

	<T> T deserialize(String json, Class<T> clazz);

	<T> String serialize(Collection<T> object, Class<Collection<T>> clazz);

	<T> Collection<T> deserialize(String json, Class<Collection<T>> clazz);
}
