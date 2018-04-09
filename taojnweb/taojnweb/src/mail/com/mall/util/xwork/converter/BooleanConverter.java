package com.mall.util.xwork.converter;

import com.sun.org.apache.commons.beanutils.ConversionException;

public class BooleanConverter extends CustomizeConverter {
	private String[] trueStrings = { "true", "yes", "y", "on", "1" };
	private String[] falseStrings = { "false", "no", "n", "off", "0" };
	private String[] nullStrings = { "null", "" };

	@SuppressWarnings("unchecked")
	protected Object fromString(String value, Class clazz) {
		String stringValue = value.toString().toLowerCase();
		for (int i = 0; i < this.trueStrings.length; i++) {
			if (this.trueStrings[i].equals(stringValue))
				return Boolean.TRUE;
		}
		for (int i = 0; i < this.falseStrings.length; i++) {
			if (this.falseStrings[i].equals(stringValue))
				return Boolean.FALSE;
		}
		for (int i = 0; i < this.nullStrings.length; i++) {
			if (this.nullStrings[i].equals(stringValue))
				return null;
		}
		throw new ConversionException("Can't convert value '" + value
				+ "' to a Boolean");
	}
}