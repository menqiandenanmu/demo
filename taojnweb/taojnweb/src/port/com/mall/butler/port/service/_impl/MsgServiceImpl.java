package com.mall.butler.port.service._impl;

import java.net.MalformedURLException;
import java.util.List;

import org.codehaus.xfire.XFire;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;

import webService.SendmsgPortType;

import com.mall.butler.ManageContext;
import com.mall.butler.RequestContext;
import com.mall.butler.port.service.MsgPortService;
import com.mall.butler.service.ApplicationLogService;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.butler.sys.m.service.MSysParamService;
import com.mall.butler.sys.model.SysParam;

public class MsgServiceImpl extends BaseServiceImpl implements MsgPortService {
	@Autowired
	private ApplicationLogService applicationLogService;
	@Autowired
	private MSysParamService mSysParamService;
	@Override
	public String sendMsg(String mobile, String msg) {
		System.out.println("短信发送号码"+mobile+"内容"+msg+"ip"+RequestContext.getRemotIp());
		Service serviceModel = new ObjectServiceFactory()
				.create(SendmsgPortType.class);
		String url = "http://111.1.31.120/webservice/services/sendmsg";
		XFire xfire = XFireFactory.newInstance().getXFire();
		XFireProxyFactory factory = new XFireProxyFactory(xfire);
		String serviceUrl = url;
//		String corporation = "rwwzjr_jx063553";
		//获取系统参数
		SysParam destTeleNum=new SysParam();
		destTeleNum.setParamCode("destTeleNum");
		List<SysParam> destTeleNumList=mSysParamService.queryAll(destTeleNum);
		String destTele=destTeleNumList.get(0).getParamValue();
		SysParam sysParam=new SysParam();
		sysParam.setParamCode("corporation");
		List<SysParam> sysList=mSysParamService.queryAll(sysParam);
		String corporation=sysList.get(0).getParamValue();
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
		sb.append("<src_tele_num>"+destTele+"</src_tele_num>");
//		sb.append("<src_tele_num>106573063553</src_tele_num>");
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
		if(null!=str){
			if(str.contains("<state><![CDATA[0]]></state>"))
				return "0";
		}
		System.out.println("短信发送失败："+mobile);
		applicationLogService.generic("短信发送失败：手机号【" + mobile
				+ " 短信发送失败】", "短信发送失败", ApplicationLogService.GENERIC,
				ManageContext.LOG_SYS_TYPE, ManageContext.LOG_OPT_TYPE_ACC,
				null);
		return "1";
		
	}

}
