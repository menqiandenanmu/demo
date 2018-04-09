package com.mall.butler.port.test;



import java.math.BigInteger;

import java.security.KeyFactory;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;

/**
 * Karas
 * RSA 加解密算法，此算法用于与MSC系统算法对接，并非标准算法，请谨慎修改
 * 算法在基本的RSA算法上增加了数据分组处理
 */
public class RsaUtilTest {
	static {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	}

	/**
	 * 
	 * @param input 明文
	 * @param eKey  加密密钥e，字符串表达的10进制长整型
	 * @param nKey  加密密钥n，字符串表达的10进制长整型
	 * @return	    密文
	 * @throws Exception
	 */
	public static String encrypt(String input, String eKey, String nKey)
			throws Exception {
		byte[] bInput = input.getBytes("UTF8");

		Cipher cipher = Cipher.getInstance("RSA/NONE/PKCS1Padding", "BC");
		KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");

		RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(
				new BigInteger(nKey), new BigInteger(eKey));

		RSAPublicKey pubKey = (RSAPublicKey) keyFactory
				.generatePublic(pubKeySpec);

		cipher.init(Cipher.ENCRYPT_MODE, pubKey);

		int CellLength = new BigInteger(nKey).bitLength() / 8;
		int MaxLength = CellLength - 11;
		int GroupLength = (int) Math.ceil(bInput.length * 1.00 / MaxLength);

		byte[] cipherText = new byte[GroupLength * CellLength];

		for (int i = 0; i < GroupLength; i++) {
			int len = MaxLength < bInput.length - MaxLength * i ? MaxLength
					: bInput.length - MaxLength * i;
			byte[] inByte = new byte[len];
			System.arraycopy(bInput, MaxLength * i, inByte, 0, len);

			byte[] temp = cipher.doFinal(inByte);
			System.arraycopy(temp, 0, cipherText, i * CellLength, CellLength);
		}

		return Utils.toHex(cipherText);
	}

	/**
	 * 
	 * @param input  密文
	 * @param dKey   解密密钥d，字符串表达的10进制长整型
	 * @param nKey   解密密钥n，字符串表达的10进制长整型
	 * @return		  明文
	 * @throws Exception
	 */
	public static String decrypt(String input, String dKey, String nKey)
			throws Exception {

		byte[] bInput = Utils.hexStr2Bytes(input);
		Cipher cipher = Cipher.getInstance("RSA/NONE/PKCS1Padding", "BC");
		KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");

		RSAPrivateKeySpec privKeySpec = new RSAPrivateKeySpec(new BigInteger(
				nKey), new BigInteger(dKey));

		RSAPrivateKey privKey = (RSAPrivateKey) keyFactory
				.generatePrivate(privKeySpec);

		cipher.init(Cipher.DECRYPT_MODE, privKey);

		int CellLength = new BigInteger(nKey).bitLength() / 8;
		int MaxLength = CellLength - 11;
		int GroupLength = (int) Math.ceil(bInput.length * 1.00 / CellLength);

		List<byte[]> plainList = new ArrayList<byte[]>();
		int len = 0;
		for (int i = 0; i < GroupLength; i++) {
			byte[] inByte = new byte[CellLength];
			System.arraycopy(bInput, CellLength * i, inByte, 0, CellLength);

			byte[] temp = cipher.doFinal(inByte);
			plainList.add(temp);
			len += temp.length;
		}

		byte[] plainText = new byte[len];

		for (int i = 0; i < GroupLength; i++) {
			System.arraycopy(plainList.get(i), 0, plainText, i * MaxLength,
					plainList.get(i).length);
		}

		return Utils.toString(plainText);
	}

	public static void main(String[] args) throws Exception {
		String input = "<?xml version=\"1.0\" encoding=\"GBK\"?><InputParameter><WorkKey><?xml version=\"1.0\" encoding=\"GBK\"?><InputParameter><WorkKey><?xml version=\"1.0\" encoding=\"GBK\"?><InputParameter><WorkKey><?xml version=\"1.0\" encoding=\"GBK\"?><InputParameter><WorkKey>FqvePIXDjeJDZB0c</WorkKey><ClientCode>001000001</ClientCode><UserCode>888888</UserCode><Tag>B7E721198E55AB879DC942C17538CA93</Tag>";
		String n = "21029918861962600196134958515087315576925534045020103499325002037133921330428279277137792157401895427225270426309352406951017508288647265881800625130979823346619650688102578313072511322699904183522545654063308482689916741594182188417126053339202398359187954032715454966216975088143745305920182142156001059172556355476432799515541345666976405419153845750058370359832520643499950972387920201010531580140400097176236424591929506142412823856376003915558540789145685463944477306072885549455607062060948634208619497225258874154182786079465962293288354144608630707546953523711362188889399870691298428459514421699544178800851";
		String e = "17";
		String d = "41235135023456078815950899049190814856716733421608046077107847131635139863584861327721161092944892994559353777077161582256897075075778952709412990452901614405136569976671722182495120240588047418671658145222173495470424983518004291013972653606279212468995988299442068561209755074791657462588592435600002076808365041373721459827889650552452751424808562094981892579271325203122187462691864998962387839905867258232398918824324604644119131009295412342404903686862125268756080308339532957613435033863245746623508448495343700291017269572375126261103269578427992952043186102946975324766804527075990434941993393983376986353";

		String enc = RsaUtilTest.encrypt(input, e, n);

		System.out.println("enc : " + enc);
		System.out.println("dec : " + RsaUtilTest.decrypt(enc, d, n));

	}
}