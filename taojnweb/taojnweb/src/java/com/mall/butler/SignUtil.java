package com.mall.butler;


import com.mall.util.common.TxtUtil;


public class SignUtil {
	/**
	 * 对所有的参数进行字符串合并
	 * @param partner 
	 * 
	 * @param params
	 * @param pass
	 * @return
	 */
	public static String makeSign(String dateInfo,String transTime, String partner) {
		String signData = new StringBuilder(partner).append(
				transTime).append(dateInfo).append(CommonContext.KEY)
				.toString();
		return TxtUtil.digest(signData);
	}
}
