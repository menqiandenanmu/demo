package com.mall.util.common;

import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

public abstract class TxtUtil {
	private static final String HEXES = "0123456789ABCDEF";

	/**
	 * 字符转成字节
	 * 
	 * @param c
	 * @return
	 */
	public static byte toByte(char c) {
		byte b = (byte) HEXES.indexOf(c);
		return b;
	}

	/**
	 * 合并数组
	 * 
	 * @param c
	 * @return
	 */
	public static List<Object> unionArray(Boolean repeat, Object[]... arrays) {
		List<Object> result = new ArrayList<Object>();
		try {
			for (Object[] arr : arrays) {
				for (Object ar : arr) {
					if (repeat) {
						if (!result.contains(ar)) {
							result.add(ar);
						}
					} else {
						result.add(ar);
					}
				}
			}
		} catch (Exception e) {
			return null;
		}
		return result;
	}

	/**
	 * 字节数组转成HEX字符串（大写）
	 * 
	 * @param raw
	 * @return
	 */
	public static String toHex(byte[] raw) {
		if (raw == null) {
			return null;
		}
		final StringBuilder hex = new StringBuilder(2 * raw.length);
		for (final byte b : raw) {
			hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(
					HEXES.charAt((b & 0x0F)));
		}
		return hex.toString();
	}
	
	/**
	 * 转换字符串到整数
	 * 
	 * @author caedmon
	 * @date 2010-2-28 下午01:56:27
	 * @param raw
	 * @return
	 */
	public static int toInt(String value) {
		int result = 0;
		try {
			result = Integer.valueOf(value);
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * HEX字符串转成字节数组
	 * 
	 * @param hex
	 * @return
	 */
	public static byte[] toBytes(String hex) {
		int len = hex.length() / 2;
		hex = hex.toUpperCase();
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}

	/**
	 * 转成Unicode。
	 * 
	 * @param value
	 * @return
	 */
	public static String toUnicode(String value) {
		String result = "";
		String temp = "";
		try {
			for (char c : value.toCharArray()) {
				temp = "0000" + Integer.toHexString(c);
				temp = "\\u" + temp.substring(temp.length() - 4);
				result = result + temp;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static final boolean isEmpty(String s) {
		return null == s || s.trim().isEmpty();
	}

	/**
	 * 获取IP地址
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public static final String getIp(HttpServletRequest request,
			HttpServletResponse response) {

		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	private static final String IP_PARTNER = "(((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))[.](((2[0-4]\\d)|(25[0-5]))|(1\\d{2})|([1-9]\\d)|(\\d))";

	public static final boolean isIPAddress(String s) {
		if (isEmpty(s))
			return false;
		Pattern p = Pattern.compile(IP_PARTNER);
		Matcher m = p.matcher(s);
		return m.matches();
	}

	public static String getMd5(String data) {
		return DigestUtils.md5Hex(data);
	}

	/**
	 * MD5加密
	 * 
	 * @param src
	 * @return
	 */
	public static final String digest(String src) {
		try {
			MessageDigest digest = java.security.MessageDigest
					.getInstance("MD5");
			digest.update(src.getBytes("UTF-8"));
			return toHex(digest.digest());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 与。net接口的md5加密 转小写
	 * 
	 * @param s
	 * @return
	 */
	public final static String MD5(String s) {
		String hexDigits = "0123456789abcdef";
		try {
			byte[] strTemp = s.getBytes("gb2312");
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] raw = mdTemp.digest();
			final StringBuilder hex = new StringBuilder(2 * raw.length);
			for (final byte b : raw) {
				hex.append(hexDigits.charAt((b & 0xF0) >> 4)).append(
						hexDigits.charAt((b & 0x0F)));
			}
			return hex.toString();
		} catch (Exception e) {
			return null;
		}
	}

	public static final String toHex(Serializable s) throws IOException {
		return toHex(RawUtil.serialize(s));
	}

	public static final Object fromHex(String hex) throws IOException,
			ClassNotFoundException {
		return RawUtil.deserialize(toBytes(hex));
	}

	/**
	 * 截取定长字符串。不足用字符代替
	 * 
	 * @author caedmon
	 * @date 2008-12-31 下午04:47:56
	 * @param value
	 * @param len
	 * @param c
	 * @return
	 */
	public static String align(String value, int len, char c, boolean leftAlign) {
		// TODO: to optimize.
		String result = "";
		if (leftAlign)
			for (int i = 0; i < len; i++) {
				if (i < value.length())
					result = result + value.charAt(i);
				else
					result = result + c;
			}
		else
			for (int i = 0; i < len; i++) {
				if (i < value.length())
					result = result + value.charAt(i);
				else
					result = c + result;
			}
		return result;
	}

	/**
	 * 按指定长度截取字符串（中英文都把一个字符长度处理）
	 * 
	 * @author:cshy Email:cshy486@126.com
	 * @Date:2009-8-12 下午01:34:49
	 * @param src
	 *            要截取的字符串
	 * @param len
	 *            要截取的长度（字节）
	 * @param end
	 *            结束字符串（如果截取后的字符串长度小于截取前字符串长在末尾添加的字符串）
	 * @return String 截取后的字符串
	 */
	public static String trim(String src, int len, String triming) {
		if (src == null || len <= 0)
			return "";
		byte[] bStr = src.getBytes();
		if (len >= bStr.length)
			return src;
		String cStr = new String(bStr, len - 1, 2);
		if (cStr.length() == 1 && src.contains(cStr))
			len--;
		return new String(bStr, 0, len) + triming;
	}

	public static String replace(String src, int start, int len,
			String replacement) {
		if (src.length() < start + len - 1) {
			return src;
		}
		return new StringBuilder().append(src.substring(0, start - 1)).append(
				replacement).append(src.substring(start + len - 1)).toString();
	}

	/**
	 * 
	 * @Function:将字符集合按给定分隔符拼接成字符串
	 * @author:caoshiyan Email:cshy486@126.com
	 * @date:2009-3-3 下午03:48:45
	 * @param src
	 * @param c
	 * @return String
	 * 
	 */
	public static String connect(String[] src, String c) {
		if (src == null || src.length == 0)
			return "";
		StringBuffer sb = new StringBuffer("");
		sb.append(src[0]);
		for (int i = 1; i < src.length; i++)
			sb.append(c).append(src[i]);
		return sb.toString();
	}

	/**
	 * 字符串是否为有效邮件地址
	 * 
	 * @author:cshy Email:cshy486@126.com
	 * @Date:2009-10-30 下午05:46:20
	 * @param emailStr
	 * @return
	 */
	public static boolean isEmail(String emailStr) {
		String regEmail = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		return TxtUtil.match(emailStr, regEmail, true);
	}

	/**
	 * 字符串是否为有效手机号码
	 * 
	 * @author:cshy Email:cshy486@126.com
	 * @Date:2009-10-30 下午05:46:18
	 * @param mobileStr
	 * @return
	 */
	public static boolean isMobile(String mobileStr) {
		// String regMobile = "(13[0-9]|15[0|3|6|7|8|9]|18[8|9])\\d{8}";
		String regMobile = "(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])\\d{8}";
		return TxtUtil.match(mobileStr, regMobile, false);
	}

	/**
	 * 得到手机号码的运营商
	 * 
	 * @param mobileStr
	 * @return 中国联通(unicom),中国移动(mobile),中国电信(telecom)
	 */
	public static String getMobileCorp(String mobileStr) {
		if (mobileStr.contains("+")) {
			mobileStr = mobileStr.replace("+86", "");
		}

		String telephone = mobileStr.substring(0, 3);
		int telArea = Integer.valueOf(telephone).intValue();
		// 移动号码段
		int[] TELS_YD = { 134, 135, 136, 137, 138, 139, 147, 150, 151, 152,
				154, 157, 158, 159, 187, 188 };
		for (int i = 0; i < TELS_YD.length; i++) {
			if (telArea == TELS_YD[i]) {
				return "mobile";
			}
		}
		// 联通号码段
		int[] TELS_LT = { 130, 131, 132, 133, 155, 156, 185, 186 };
		for (int i = 0; i < TELS_LT.length; i++) {
			if (telArea == TELS_LT[i]) {
				return "unicom";
			}
		}
		// 电信号码段
		int[] TELS_DX = { 189, 180 };
		for (int i = 0; i < TELS_DX.length; i++) {
			if (telArea == TELS_LT[i]) {
				return "telecom";
			}
		}
		return null;
	}

	/**
	 * 身份证号码
	 * 
	 * @author caedmon
	 * @date 2010-6-17 下午06:42:29
	 * @param IDNumber
	 * @return
	 */
	public static boolean isIDNumber(String IDNumber) {
		boolean result = IDNumber.matches("[0-9]{15}|[0-9]{17}[0-9X]");
		if (result) {
			int year, month, date;
			if (IDNumber.length() == 15) {
				year = Integer.parseInt(IDNumber.substring(6, 8));
				month = Integer.parseInt(IDNumber.substring(8, 10));
				date = Integer.parseInt(IDNumber.substring(10, 12));
			} else {
				year = Integer.parseInt(IDNumber.substring(6, 10));
				month = Integer.parseInt(IDNumber.substring(10, 12));
				date = Integer.parseInt(IDNumber.substring(12, 14));
			}
			switch (month) {
			case 2:
				result = (date >= 1)
						&& (year % 4 == 0 ? date <= 29 : date <= 28);
				break;
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				result = (date >= 1) && (date <= 31);
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				result = (date >= 1) && (date <= 31);
				break;
			default:
				result = false;
				break;
			}
		}
		return result;
	}

	/**
	 * 正则匹配字符串判别
	 * 
	 * @author:cshy Email:cshy486@126.com
	 * @Date:2009-10-30 下午05:42:42
	 * @param reg
	 *            正则字符串
	 * @param src
	 *            要匹配目标字符串
	 * @param caseIgnore
	 *            是否忽略大小写
	 * @return boolean
	 */
	public static boolean match(String src, String reg, boolean caseIgnore) {
		Pattern pattern = caseIgnore ? Pattern.compile(reg,
				Pattern.CASE_INSENSITIVE) : Pattern.compile(reg);
		Matcher matcher = pattern.matcher(src);
		return matcher.matches();
	}

	/**
	 * 限制字符串长度，超出长度用str代替
	 * 
	 * @author:cshy Email:cshy486@126.com
	 * @Date:2009-10-30 下午05:42:42
	 * @param leng
	 *            限制长度
	 * @param src
	 *            目标字符串
	 * @param str
	 *            追加字符串
	 * @return String
	 */
	public static String preview(String src, int len, String str) {
		if (src != null && src.length() > len) {
			src = src.substring(0, len - 1) + str;
		}
		return src;
	}

	/**
	 * 产生6位随机手机验证码
	 * 
	 * @return
	 */
	public final static String get6Radom() {
		String newString = null;

		// 得到0.0到1.0之间的数字,并扩大1000000倍
		double doubleP = Math.random() * 1000000;

		// 如果数据等于1000000,则减少1
		if (doubleP >= 1000000) {
			doubleP = 999999;
		}

		// 然后把这个数字转化为不包含小数点的整数
		int tempString = (int) Math.ceil(doubleP);

		// 转化为字符串
		newString = "" + tempString;

		// 把得到的数增加为固定长度,为6位
		while (newString.length() < 6) {
			newString = "0" + newString;
		}

		return newString;
	}

	/**
	 * 此方法用于检查密码是否合法，用户名密码只能使用英文字母、数字以及-和_，并且首字符必须为字母或数字 密码首字符必须为字母或数字
	 * 
	 * @param passwordStr
	 *            String
	 * @return boolean
	 */
	public static boolean checkUserName(String username) {
		for (int nIndex = 0; nIndex < username.length(); nIndex++) {
			char cCheck = username.charAt(nIndex);
			if (nIndex == 0 && (cCheck == '-' || cCheck == '_')) {
				return false;
			}

			if (!(isDigit(cCheck) || isAlpha(cCheck) || isChinese(cCheck)
					|| cCheck == '-' || cCheck == '_')) {
				return false;
			}

		}
		return true;
	}

	/**
	 * 此方法判断输入字符是否为数字0-9 是返回true不是返回false
	 * 
	 * @param c
	 *            char
	 * @return boolean
	 */
	public static boolean isDigit(char c) {
		return (('0' <= c) && (c <= '9'));
	}

	/**
	 * 此方法判断输入字符是否为数字a-z或A-Z 是返回true不是返回false
	 * 
	 * @param c
	 *            char
	 * @return boolean
	 */
	public static boolean isAlpha(char c) {
		return ((('a' <= c) && (c <= 'z')) || (('A' <= c) && (c <= 'Z')));
	}

	/**
	 * 此方法判断输入字符是否为数字中文 是返回true不是返回false
	 * 
	 * @param c
	 *            char
	 * @return boolean
	 */
	public static boolean isChinese(char c) {

		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);

		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS

		|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS

		|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A

		|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION

		|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION

		|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {

			return true;

		}

		return false;

	}

	/**
	 * 判断是否为汉字，是返回true
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isC(String str) {
		boolean strflag = false;

		for (int i = 0; i < str.length(); i++) {
			strflag = (str.charAt(i) + "").matches("[\u4e00-\u9faf]");
		}
		return strflag;
	}

}