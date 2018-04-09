package com.mall.butler.port.test;

import java.util.Date;

import com.mall.util.common.lang.DateUtil;

import crmWebService.IHsCRMWebSrv;
import crmWebService.IHsCRMWebSrvserviceLocator;
import crmWebService.TReturnInfo;

public class DateTest {
public static void main(String[] args) {
	try{
		   IHsCRMWebSrvserviceLocator lo = new IHsCRMWebSrvserviceLocator();
		   IHsCRMWebSrv da = lo.getIHsCRMWebSrvPort();
		    String password="00"+DateUtil.format(new Date(), "yyyyMMddHHmmss");
		   StringBuilder sb = new StringBuilder();
    		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    		sb.append("<InputParameter>");
    		sb.append("<Random>34</Random>");
    		sb.append("<ClientCode>江南大厦 9009_0007</ClientCode>");
    		sb.append("<WorkKey>"+password+"</WorkKey>");
    		sb.append("<UserCode>211</UserCode>");
    		sb.append("<Passwd>0</Passwd>");
    		sb.append("<VerifyInfo>AABC</VerifyInfo>");
    		sb.append("<Computer></Computer>");
    		sb.append("<TerminalNo></TerminalNo>");
    		sb.append("</InputParameter>");
    		System.out.println(sb.toString());
		   TReturnInfo rst = da.IWsPosCommOperate(12,sb.toString());   
		   System.out.print(rst.getRtnMsg());
		   
		 }
		 catch(Exception e){
		 	e.printStackTrace();
		}
		   
}


}
