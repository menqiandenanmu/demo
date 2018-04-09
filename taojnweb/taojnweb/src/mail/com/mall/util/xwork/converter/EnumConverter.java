package com.mall.util.xwork.converter;

import com.mall.util.common.GenericUtil;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.enums.AbstractEnumSupport;

public class EnumConverter extends CustomizeConverter {
	@SuppressWarnings("unchecked")
	protected Object fromString(String value, Class clazz) {
		if (TxtUtil.isEmpty(value))
			return null;
		if (AbstractEnumSupport.class.isAssignableFrom(clazz)) {
			return GenericUtil.valueOf(clazz
					.asSubclass(AbstractEnumSupport.class), Integer
					.parseInt(value));
		}
		return Enum.valueOf(clazz, value);
	}
}