package com.mall.butler.port.test;



/**
 * Utility class for chapter 4 examples
 */
public class Utils {
	private static String digits = "0123456789abcdef";

	/**
	 * Convert a byte array of 8 bit characters into a String.
	 * 
	 * @param bytes
	 *            the array containing the characters
	 * @param length
	 *            the number of bytes to process
	 * @return a String representation of bytes
	 */
	public static String toString(byte[] bytes, int length) {
		char[] chars = new char[length];

		for (int i = 0; i != chars.length; i++) {
			chars[i] = (char) (bytes[i] & 0xff);
		}

		return new String(chars);
	}

	public static String toHex(byte[] data) {
		return toHex(data, data.length);
	}

	/**
	 * Convert a byte array of 8 bit characters into a String.
	 * 
	 * @param bytes
	 *            the array containing the characters
	 * @return a String representation of bytes
	 */
	public static String toString(byte[] bytes) {
		return toString(bytes, bytes.length);
	}

	/**
	 * Convert the passed in String to a byte array by taking the bottom 8 bits
	 * of each character it contains.
	 * 
	 * @param string
	 *            the string to be converted
	 * @return a byte array representation
	 */
	public static byte[] toByteArray(String string) {
		byte[] bytes = new byte[string.length()];
		char[] chars = string.toCharArray();

		for (int i = 0; i != chars.length; i++) {
			bytes[i] = (byte) chars[i];
		}

		return bytes;
	}

	public static String toHex(byte[] data, int length) {
		StringBuffer buf = new StringBuffer();

		for (int i = 0; i != length; i++) {
			int v = data[i] & 0xff;

			buf.append(digits.charAt(v >> 4));
			buf.append(digits.charAt(v & 0xf));
		}

		return buf.toString();
	}

	/**
	 * bytes转换成十六进制字符串
	 */
	public static String byte2HexStr(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			// if (n<b.length-1) hs=hs+":";
		}
		return hs.toUpperCase();
	}

	private static byte uniteBytes(String src0, String src1) {
		byte b0 = Byte.decode("0x" + src0).byteValue();
		b0 = (byte) (b0 << 4);
		byte b1 = Byte.decode("0x" + src1).byteValue();
		byte ret = (byte) (b0 | b1);
		return ret;
	}

	/**
	 * bytes转换成十六进制字符串
	 */
	public static byte[] hexStr2Bytes(String src) {
		int m = 0, n = 0;
		int l = src.length() / 2;
		byte[] ret = new byte[l];
		for (int i = 0; i < l; i++) {
			m = i * 2 + 1;
			n = m + 1;
			ret[i] = uniteBytes(src.substring(i * 2, m), src.substring(m, n));
		}
		return ret;
	}
}

