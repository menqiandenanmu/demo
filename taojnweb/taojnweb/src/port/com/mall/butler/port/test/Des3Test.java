package com.mall.butler.port.test;



import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Karas
 * 3DES 加解密算法，此算法用于与MSC系统算法对接，并非标准算法，请谨慎修改
 * 算法在正常算法基础上进行了接口适应的特定修改，请不要在其它地方使用此类
 */

public class Des3Test {
	private final static String Algorithm = "DESede";
	private final static char[] base = { '1', '2', '3', '4', '5', '6', '7',
			'8', '9', '0', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z' };

	/**
	 * 加密
	 * @param message 明文
	 * @param strKey  密钥
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String message, String strKey)
			throws Exception {
		message = message + "        ";
		byte[] bMsg = message.getBytes("gbk");
		int l = (bMsg.length / 16 + 1) * 16;
		byte[] btMsg = Arrays.copyOf(bMsg, l);

		byte[] digestOfPassword = strKey.getBytes();
		byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
		for (int j = 0, k = 16; j < 8;) {
			keyBytes[k++] = keyBytes[j++];
		}
		// 定义 加密算法,可用 DES,DESede,Blowfish
		SecretKey key = new SecretKeySpec(keyBytes, Algorithm);
		// 加密
		Cipher c1 = Cipher.getInstance(Algorithm);
		c1.init(Cipher.ENCRYPT_MODE, key);
		String rtn = byte2hex(c1.doFinal(btMsg));
		return rtn.substring(0, (bMsg.length / 8 + 1) * 16);
	}

	/**
	 * 校正算法。。。
	 * @param length
	 * @param strKey
	 * @return
	 * @throws Exception
	 */
	public static String getAdd(int length, String strKey) throws Exception {
		byte[] btMsg = new byte[length / 2];

		byte[] digestOfPassword = strKey.getBytes("utf-8");
		byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
		for (int j = 0, k = 16; j < 8;) {
			keyBytes[k++] = keyBytes[j++];
		}
		SecretKey key = new SecretKeySpec(keyBytes, Algorithm);
		Cipher c1 = Cipher.getInstance(Algorithm);
		c1.init(Cipher.ENCRYPT_MODE, key);

		String rtn = byte2hex(c1.doFinal(btMsg));

		return rtn.substring(length);
	}

	/**
	 * 解密算法
	 * @param message 密文
	 * @param strKey  密钥
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String message, String strKey)
			throws Exception {
		message = message + getAdd(message.length(), strKey);
		byte[] digestOfPassword = strKey.getBytes();
		byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
		for (int j = 0, k = 16; j < 8;) {
			keyBytes[k++] = keyBytes[j++];
		}

		SecretKey key = new SecretKeySpec(keyBytes, Algorithm);
		Cipher decipher = Cipher.getInstance(Algorithm);
		decipher.init(Cipher.DECRYPT_MODE, key);

		byte[] plainText = decipher.doFinal(hex2byte(message));

		return new String(plainText, "gbk");
	}

	/**
	 * 产生一个新密钥
	 * @param len  密钥长度
	 * @return
	 */
	public static String newKey(int len) {
		java.util.Random rd = new java.util.Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			sb.append(base[Math.abs(rd.nextInt()) % 62]);
		}
		return sb.toString();
	}

	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	public static byte[] hex2byte(String s) {
		int m = 0, n = 0;
		int l = s.length() / 2;
		byte[] b = new byte[l];
		for (int i = 0; i < l; i++) {
			m = i * 2 + 1;
			n = m + 1;
			b[i] = uniteBytes(s.substring(i * 2, m), s.substring(m, n));
		}
		return b;
	}

	private static byte uniteBytes(String src0, String src1) {
		byte b0 = Byte.decode("0x" + src0).byteValue();
		b0 = (byte) (b0 << 4);
		byte b1 = Byte.decode("0x" + src1).byteValue();
		byte ret = (byte) (b0 | b1);
		return ret;
	}

	public static void main(String[] args) {

		String src = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><InputParameter><TranType>1</TranType><SaleNo>P34424242</SaleNo><UserCode>6688</UserCode><CshName></CshName><CshCode></CshCode><Password>123456</Password><RandomNo>-1812444856</RandomNo><SMSCode></SMSCode><WorkGuid>{4EBC6281-8455-4CCB-8F97-767CFF18B414}</WorkGuid><CardNo>2010091500000002</CardNo><AccType>1</AccType><XsDate>2010-09-19 09:39:47</XsDate><ZfTotal>1.1</ZfTotal><OrgCode>3173</OrgCode><CardNoType>1</CardNoType><OldSaleNo></OldSaleNo><SerialNo></SerialNo><POSNo></POSNo><Remark>测试</Remark></InputParameter>";
		String key = "6553750820372580";
		String rst = "7FF0971BAE82FF81E8E1502AB06FAC9AC46BFE4F83B6C4123D4C9202E38B7FC6E789457DAFE0D86C90554DBC54F3BA7A607AB270DA92A729DD4148608EBAADE68426E564CB8154559139AFA87149472942AA85139E2792107089212458EB3D90841BDF566A74B14C4D91360D890FCDA1AC5D12482DED92EE9445F52B1C969AA660B3B1F363388A90DFBF03E1790C49EBC6318B60B20C22DCFD360E6DF7A88D2A9AD9A408C40C4F863F19DDF4D16F886A59509A07BFB4D1D03F19DDF4D16F886A97BD2220F75773B5DC70A660BA642B9A15E63B12E981D2F6";

		try {
			// rst = DES3.encrypt(src, key);
			src = Des3Test.decrypt(rst, key);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println(src);
		System.out.println(rst);
	}
}