package com.mall.butler.port.test;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.mall.util.common.lang.DateUtil;

import crmWebService.IHsCRMWebSrv;
import crmWebService.IHsCRMWebSrvserviceLocator;
import crmWebService.TReturnInfo;
public class CrmPublicKeyTest {
	public static final String KEY_ALGORITHM = "RSA";
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
	private static final String PUBLIC_KEY = "RSAPublicKey";
	private static final String PRIVATE_KEY = "RSAPrivateKey";


	public static String getPublicKey(Map<String, Object> keyMap)
			throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		byte[] publicKey = key.getEncoded();
		return encryptBASE64(key.getEncoded());
	}

	public static String getPrivateKey(Map<String, Object> keyMap)
			throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		byte[] privateKey = key.getEncoded();
		return encryptBASE64(key.getEncoded());
	}

	public static byte[] decryptBASE64(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}

	public static String encryptBASE64(byte[] key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key);
	}

	public static Map<String, Object> initKey() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator
				.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}

	public static void main(String[] args) {
		try {

			// 获取公钥
			IHsCRMWebSrvserviceLocator lo = new IHsCRMWebSrvserviceLocator();
			IHsCRMWebSrv da = lo.getIHsCRMWebSrvPort();
			StringBuilder sb = new StringBuilder();
			sb.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
			sb.append("<InputParameter>");
			sb.append("<Random>45</Random>");
			sb.append("<ClientCode>9009_0007</ClientCode>");
			sb.append("</InputParameter>");
			TReturnInfo rst = da.IWsPosCommOperate(8, sb.toString());
			System.out.println(rst.getReturnCode());
			System.out
					.println("20963770985082037258267013644832680819194444170865674109439404719885226241714481879640783348297997948427404211373894962551236372533411432647622255530836353218272251916811821629043426707565400351131213303148172115928391315808665241847484993407632783520603053639514468062034674617971413297279493510927957440275568732809417989268857024667945756452767120447211373511007441264575103012675179769758922918966654874533285783639668932301859385955501408660891072760643889895703821362288397513136466372572877510678561083413134328083977102081885958523282887072436899591195972865308007615085235131433633410539409896249248231311437");
			System.out.print("==============================="
					+ getFromBASE64(rst.getOutputPara()));

		}
	 catch(Exception e){
	 	e.printStackTrace();
	}
	   
	}
	 public static byte[] encryptMode(byte[] keybyte,byte[] src){  
         try {  
            //生成密钥  
            SecretKey deskey = new SecretKeySpec(keybyte, "DESede");  
            //加密  
            Cipher c1 = Cipher.getInstance("DESede");  
            c1.init(Cipher.ENCRYPT_MODE, deskey);  
            return c1.doFinal(src);//在单一方面的加密或解密  
        } catch (java.security.NoSuchAlgorithmException e1) {  
            // TODO: handle exception  
             e1.printStackTrace();  
        }catch(javax.crypto.NoSuchPaddingException e2){  
            e2.printStackTrace();  
        }catch(java.lang.Exception e3){  
            e3.printStackTrace();  
        }  
        return null;  
    }  
      
	// 将 BASE64 编码的字符串 s 进行解码
	public static String getFromBASE64(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b,"gbk");
		} catch (Exception e) {
			return null;
		}
	}
}
