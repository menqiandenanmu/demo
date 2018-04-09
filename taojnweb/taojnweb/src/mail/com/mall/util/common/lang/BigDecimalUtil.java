package com.mall.util.common.lang;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

public class BigDecimalUtil {

	private static DecimalFormat df = new DecimalFormat("###0.00");
	private static DecimalFormat dfZ = new DecimalFormat("###");

	public static String format(Number b) {
		if (b == null)
			return "";
		return df.format(b);

	}

	public static Number parse(String source) {
		try {
			return df.parse(source);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String formatInteger(BigDecimal b) {
		if (b == null)
			return "";
		return dfZ.format(b);

	}

	/**
	 * @author ww
	 * @Description: double类型四舍五入函数
	 * @param money
	 * @param scale
	 *            小数点位数
	 * @return double
	 */
	public static double round(double money, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(money));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static void main(String[] args) {
		System.out.println(format(new BigDecimal(3)));
		System.out.println(round(0.156D, 2));
	}
}
