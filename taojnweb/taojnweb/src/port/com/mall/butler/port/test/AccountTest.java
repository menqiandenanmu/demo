package com.mall.butler.port.test;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import sun.misc.BASE64Decoder;

import com.mall.butler.account.model.AccountLogin;
import com.mall.util.common.TxtUtil;

import crmWebService.IHsCRMWebSrv;
import crmWebService.IHsCRMWebSrvserviceLocator;
import crmWebService.TReturnInfo;


public class AccountTest {
	
	private static final String clientCode="9009_0004";
	public static void main(String[] args) {
		AccountLogin accountLogin=new AccountLogin();
		accountLogin.setId(5240L);
		
		String  workKey="EE263D02-8E3E-4B20-BE40-0C6C9C666FD4";
		
		
		IHsCRMWebSrvserviceLocator lo = new IHsCRMWebSrvserviceLocator();
		IHsCRMWebSrv da;
		try {
			  //String Random="00"+DateUtil.format(new Date(), "yyyyMMddHHmmss");
				da = lo.getIHsCRMWebSrvPort();
				StringBuffer sb=new StringBuffer();
				sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				sb.append("<InputParameter>");
				//0-卡内号	1-卡面号	2-会员卡号	3-手机号（2012-07-10增加）必填
				sb.append("<CardNoType>3</CardNoType>");
				sb.append("<CardNo>"+accountLogin.getId()+"</CardNo>");
				sb.append("<WorkKey>{" + workKey + "}</WorkKey>");
				sb.append("</InputParameter>");
				String body=sb.toString();
				String tag=TxtUtil.digest(body+clientCode);
				
				StringBuilder request = new StringBuilder();
				request.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				request.append("<InputParameter>");
				request.append("<Head>");
				request.append("<Tag>"+tag+"</Tag>");
				request.append("<ClientCode>"+clientCode+"</ClientCode>");
				request.append("</Head>");
				request.append("<Body>");
				request.append(body);
				request.append("</Body>");
				request.append("</InputParameter>");
				System.out.println(request.toString());
				TReturnInfo rst;
			try {
				rst = da.IWsPosCommOperate(907, sb.toString());
				System.out.println(getFromBASE64(rst.getRtnMsg())); ;
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
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
