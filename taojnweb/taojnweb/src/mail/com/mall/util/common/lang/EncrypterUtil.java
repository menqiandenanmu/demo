package com.mall.util.common.lang;

import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 加密工具类 Version 2010-6-11
 */
public class EncrypterUtil {

	public static String MD5Hex(String data) {
		return DigestUtils.md5Hex(data);
	}

	public static String Base64Encode(String data) {
		return new BASE64Encoder().encode(data.getBytes());
	}

	public static String Base64Decode(String data) {
		byte[] s = null;
		try {
			s = new BASE64Decoder().decodeBuffer(data);
		} catch (IOException e) {
			s = null;
		}
		return new String(s);
	}
}
