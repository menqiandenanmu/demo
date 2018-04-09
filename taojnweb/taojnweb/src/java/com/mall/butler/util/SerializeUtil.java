package com.mall.butler.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 序列化工具类
 * 
 * @author huangLh
 * 
 */
public class SerializeUtil {

	/**
	 * 序列化
	 * 
	 * @param string
	 * @return
	 */
	public static String serialize(Object string) {
		ObjectOutputStream out = null;
		ByteArrayOutputStream byteStream = null;
		try {
			byteStream = new ByteArrayOutputStream();
			out = new ObjectOutputStream(byteStream);
			out.writeObject(string);
			byte[] buff = byteStream.toByteArray();

			BASE64Encoder encoder = new BASE64Encoder();
			String objstr = URLEncoder.encode(encoder.encode(buff), "UTF-8");

			return objstr;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (null != out) {
					out.close();
				}
			} catch (IOException e) {
			}
			try {
				if (null != byteStream) {
					byteStream.close();
				}
			} catch (IOException e) {
			}
		}
	}

	/**
	 * 反序列化
	 * 
	 * @param string
	 * @return
	 */
	public static Object deSerialize(String string) {
		ByteArrayInputStream bins = null;
		ObjectInputStream ins = null;

		try {
			string = URLDecoder.decode(string, "UTF-8");
			byte[] buff = new BASE64Decoder().decodeBuffer(string);
			bins = new ByteArrayInputStream(buff);
			ins = new ObjectInputStream(bins);

			ins.close();

			return ins.readObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (null != bins) {
					bins.close();
				}
			} catch (IOException ex) {
			}
			try {
				if (null != ins) {
					ins.close();
				}
			} catch (IOException ex) {
			}
		}
	}
}
