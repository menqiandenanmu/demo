package com.mall.butler.weixin.content;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 微信基本参数
 * 
 * @author wangxy 2014-12-8
 */
public class WxApiContext {
	private static Logger log = LoggerFactory.getLogger(WxApiContext.class);
	/** 第三方用户唯一凭证 ***/
	public static final String AppID;
	/** 第三方用户唯一凭证密钥 ***/
	public static final String AppSecret;
	/** 第三方用户验证TOKEN ***/
	public static final String Token;
	/** 调用接口的URL ***/
	public static final String ServiceUrl;
	/** 默认项目URL 跳转页面 ***/
	public static final String WapUrl;
	
	/** 扫描登录权限 0不支持 1支持***/
	public static final String ScanLoginFlag;

	
	static {
		try {
			String fileName = "/conf/wap.properties";
			log.info("读取配置文件" + fileName + ".");
			Properties props = new Properties();
			InputStream ips = WxApiContext.class.getClassLoader()
			.getResourceAsStream(fileName);

			if (ips == null) {
				throw new RuntimeException("配置文件" + fileName + "不存在");
			}
			props.load(ips);
			AppID = props.getProperty("weixin.appid");
			AppSecret = props.getProperty("weixin.appsecret");
			Token = props.getProperty("weixin.Token");
			ServiceUrl = props.getProperty("weixin.ServiceUrl");
			WapUrl = props.getProperty("weixin.WapUrl");
			ScanLoginFlag = props.getProperty("weixin.ScanLoginFlag");
			ips.close();
			log.info("读取配置文件" + fileName + "完成.");
		} catch (Exception e) {
			log.error("读取配置文件出错!");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
