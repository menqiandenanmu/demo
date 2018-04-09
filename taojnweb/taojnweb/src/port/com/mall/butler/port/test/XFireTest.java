package com.mall.butler.port.test;
import java.net.MalformedURLException;
import java.security.Security;
import java.util.Date;

import javax.crypto.Cipher;  
import javax.crypto.SecretKey;  
import javax.crypto.spec.SecretKeySpec;  
  
import org.apache.commons.codec.digest.DigestUtils;  
import org.codehaus.xfire.XFire;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;

import webService.SendmsgPortType;

import com.mall.util.common.lang.DateUtil;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import crmWebService.IHsCRMWebSrv;
import crmWebService.IHsCRMWebSrvserviceLocator;
import crmWebService.TReturnInfo;
public class XFireTest {
	 public static void main(String[] args) {  
	        //添加新安全算法,如果用JCE就要把它添加进去  
	        byte[] enk = hex("zeromike");//用户名  
	        Security.addProvider(new com.sun.crypto.provider.SunJCE());  
	        String password = "1234567";//密码  
	        System.out.println("加密前的字符串:" + password);  
	        byte[] encoded = encryptMode(enk,password.getBytes());  
	        String pword = Base64.encode(encoded);   
	        System.out.println("加密后的字符串:" + pword);  
	          
	        byte[] reqPassword = Base64.decode(pword);  
	        byte[] srcBytes = decryptMode(enk,reqPassword);  
	        System.out.println("解密后的字符串:" + (new String(srcBytes)));  
	    }  
	    
	    //转换成十六进制字符串  
	    public static String byte2Hex(byte[] b){  
	        String hs="";  
	        String stmp="";  
	        for(int n=0; n<b.length; n++){  
	            stmp = (java.lang.Integer.toHexString(b[n]& 0XFF));  
	            if(stmp.length()==1){  
	                hs = hs + "0" + stmp;                 
	            }else{  
	                hs = hs + stmp;  
	            }  
	            if(n<b.length-1)hs=hs+":";  
	        }  
	        return hs.toUpperCase();          
	    }  
	    //keybyte为加密密钥，长度为24字节      
	    //src为加密后的缓冲区  
	    public static byte[] decryptMode(byte[] keybyte,byte[] src){  
	        try {  
	            //生成密钥  
	            SecretKey deskey = new SecretKeySpec(keybyte, "DESede");  
	            //解密  
	            Cipher c1 = Cipher.getInstance("DESede");  
	            c1.init(Cipher.DECRYPT_MODE, deskey);  
	            return c1.doFinal(src);  
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
	 public static byte[] hex(String username){  
	        String key = "test";//关键字  
	        String f = DigestUtils.md5Hex(username+key);  
	        byte[] bkeys = new String(f).getBytes();  
	        byte[] enk = new byte[24];  
	        for (int i=0;i<24;i++){  
	            enk[i] = bkeys[i];  
	        }  
	        return enk;  
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
}
