package com.mall.butler.port.test;

import java.net.MalformedURLException;

import org.codehaus.xfire.XFire;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;

import webService.SendmsgPortType;

public class SendMsg {
public static void main(String[] args) {
	String mobile="13606528316";
	String msg="您好 我就就问候下您";
		Service serviceModel = new ObjectServiceFactory()
				.create(SendmsgPortType.class);
		String url = "http://111.1.31.120/webservice/services/sendmsg";
		XFire xfire = XFireFactory.newInstance().getXFire();
		XFireProxyFactory factory = new XFireProxyFactory(xfire);
		String serviceUrl = url;
		String corporation = "rwwzjr_jx063553";
		// 对应的验证码是7326，即2249乘以3加上579 得到验证码7326
		// String mobile="13606528316";
		int password = (Integer.parseInt(mobile.substring(7, 11)) * 3 + 1314);
		// String msg="尊敬的用户您好啊，我获取到了健康健康";
		/**
		 *组合infos内容 xml格式
		 */
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<infos>");
		sb.append("<info>");
		sb.append("<msg_id>-1</msg_id>");
		sb.append("<password>" + password + "</password>");
		sb.append("<src_tele_num>106573063553</src_tele_num>");
		sb.append("<dest_tele_num>" + mobile + "</dest_tele_num>");
		sb.append("<msg>" + msg + "</msg>");
		sb.append("</info>");
		sb.append("</infos>");
		System.out.println(sb.toString());
		SendmsgPortType client = null;

		try {
			client = (SendmsgPortType) factory.create(serviceModel, serviceUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		String str = client.sendmsg(corporation, sb.toString());
		System.out.println("返回了了===================="+str);
		if(null!=str){
			if(str.contains("<state><![CDATA[0]]></state>"))
				System.out.println("成功了");
		}
		System.out.println("失败了");
	}
}
