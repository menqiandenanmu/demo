package com.mall.util.xwork.converter;

import java.util.Map;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

public abstract class CustomizeConverter extends DefaultTypeConverter {
	protected abstract Object fromString(String paramString, Class<?> paramClass);

	@SuppressWarnings("unchecked")
	public Object convertValue(Map context, Object value, Class clazz) {
		if ((value instanceof String[])) {
			String[] params = (String[]) value;
			Object[] r = new Object[params.length];
			for (int i = 0; i < r.length; i++)
				r[i] = fromString(params[i], clazz);
			return r.length == 1 ? r[0] : r;
		}
		if ((value instanceof String))
			return fromString((String) value, clazz);
		throw new RuntimeException("Can not convert non-string value.");
	}
}