package com.mall.butler.util;

import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

/**
 * 唯一标识符发生器(20位)
 * @author hlh
 *
 */
public class OidUtils {

	/**
	 * 生成包含0-9数字、A-Z字母、a-z字母的20位随机数
	 * @param:
	 * @return: <String>
	 */
	public static String newId() {
		
		/* 随机种子 */
		char x36s[] = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

		/* 随机数长度 */
		short len = 20;
		
		/* 结果随机数{保存在字符串中} */
		char[] chs = new char[len];

		/**
		 * 生成前8位随机字符(以系统时间为随机池, 以36位数字+英文字母为随机种子)
		 */
		long v = (System.currentTimeMillis() - 936748800000L) >> 1; // 1999-9-9
		//要横跨102年
		for (int i=7; i>0; i--) {
			chs[i] = x36s[(int)(v % 36)];
			v = v / 36;
		}
		chs[0] = x36s[(int)(v % 26) + 10]; //确保第一个随机字符是"字母", 以符合一般编程的标识符定义

		/**
		 * 生成后12位随机字符(以UUID为随机池, 以36位数字+英文字母为随机种子)
		 */
		UUID u = UUID.randomUUID();
		v = u.getLeastSignificantBits() ^ u.getMostSignificantBits();
		if (v < 0) {
			v = -v;
		}

		for (int i=8; i<len; i++) {
			chs[i] = x36s[(int)(v % 36)];
			v = v / 36;
		}

		return new String(chs);
	}


	/**
	 * 生成仅包含0-9数字的10位随机数
	 * @param:
	 * @return: <String>
	 */
	public static long newLongId() {
		
		/* 随机种子 */
		char x10s[] = "0123456789".toCharArray();

		/* 随机数长度 */
		short shortLength = 10;

		/* 结果随机数{保存在字符串中} */
		char[] chs = new char[shortLength];
		
		/**
		 * 生成前8位随机字符(以系统时间为随机池, 以10位数字为随机种子)
		 */
		long v = (System.currentTimeMillis() - 936748800000L) >> 1; // 1999-9-9
		//要横跨102年
		for (int i=2; i >= 0; i--) {
			chs[i] = x10s[(int)(v % 10)];
			v = v / 10;
		}

		/**
		 * 生成后10位随机字符(以UUID为随机池, 以10位数字为随机种子)
		 */
		UUID u = UUID.randomUUID();
		v = u.getLeastSignificantBits() ^ u.getMostSignificantBits();
		if (v < 0) {
			v = -v;
		}

		for (int i=3; i<shortLength; i++) {
			chs[i] = x10s[(int)(v % 10)];
			v = v / 10;
		}

		return Long.parseLong(new String(chs));
	}
	
	public static String newId(String startWith){
		
		if(StringUtils.isBlank(startWith)){
			startWith="";
		}
		
		StringBuilder stingBu=new StringBuilder();
		stingBu.append(startWith);
		
		char x36s[] = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		short len = (short)(20-startWith.length());
		char[] chs = new char[len];

		long v = (System.currentTimeMillis() - 936748800000L) >> 1;
		for (int i=7; i>0; i--) {
			chs[i] = x36s[(int)(v % 36)];
			v = v / 36;
		}
		chs[0] = x36s[(int)(v % 26) + 10];
		
		chs[len-1] = x36s[(int)(v % 26) + 10];

		UUID u = UUID.randomUUID();
		v = u.getLeastSignificantBits() ^ u.getMostSignificantBits();
		if (v < 0) {
			v = -v;
		}

		for (int i=8; i<len-1; i++) {
			chs[i] = x36s[(int)(v % 36)];
			v = v / 36;
		}
		stingBu.append(new String(chs));

		return stingBu.toString();
	}
	
	public static String macAddress(char split){
		
		/* 随机种子 */
		char x62s[] = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

		/* 随机数长度 */
		short len = 17;
		
		int splitLen = 0;
		
		/* 结果随机数{保存在字符串中} */
		char[] chs = new char[len];

		/**
		 * 生成前8位随机字符(以系统时间为随机池, 以36位数字+英文字母为随机种子)
		 */
		long v = (System.currentTimeMillis() - 936748800000L) >> 1; // 1999-9-9
		//要横跨102年
		for (int i = 0; i < 9; i++) {
			if (splitLen > 0 && splitLen % 2 == 0) {
				chs[i] = split;
				splitLen = 0;
			} else {
				chs[i] = x62s[(int)(v % 62)];
				splitLen = splitLen + 1;
			}
			
			v = v / 36;
		}

		/**
		 * 生成后12位随机字符(以UUID为随机池, 以36位数字+英文字母为随机种子)
		 */
		UUID u = UUID.randomUUID();
		v = u.getLeastSignificantBits() ^ u.getMostSignificantBits();
		if (v < 0) {
			v = -v;
		}

		for (int i = 9; i < len; i++) {
			if(splitLen > 0 && splitLen % 2 == 0){
				chs[i] = split;
				splitLen = 0;
			} else {
				chs[i] = x62s[(int)(v % 62)];
				splitLen = splitLen + 1;
			}
			
			v = v / 36;
		}

		return new String(chs);
	}
	
	public static String macAddress() {
		
		String mac = "";
		
		Random random = new Random();
		
		int[] sn = {
				0x00, 0x16, 0x3e,
				random.nextInt(0x7f + 1),
				random.nextInt(0xff + 1),
				random.nextInt(0xff + 1)
		};
		
		for(int i = 0; i < sn.length; i++) {
			mac += String.format("%02x", sn[i]) + ":";
		}
		
		return mac.substring(0, mac.lastIndexOf(":"));
	}
}

