package com.mall.butler.port.test;
import java.rmi.RemoteException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.xml.rpc.ServiceException;

import sun.misc.BASE64Decoder;

import com.mall.butler.ManageContext;
import com.mall.butler.port.xml.OutputParameter;
import com.mall.butler.util.XmlHelper;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.lang.DateUtil;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;

import crmWebService.IHsCRMWebSrv;
import crmWebService.IHsCRMWebSrvserviceLocator;
import crmWebService.TReturnInfo;
public class CrmTest {

      
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
	
	public static void getkey(){
		
		//获取公钥
		IHsCRMWebSrvserviceLocator lo = new IHsCRMWebSrvserviceLocator();
		   IHsCRMWebSrv da;
		try {
			da = lo.getIHsCRMWebSrvPort();
		
		   StringBuilder sb = new StringBuilder();
			sb.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
			sb.append("<InputParameter>");
			sb.append("<Random>11</Random>");
			sb.append("<ClientCode>001_0001</ClientCode>");
			sb.append("</InputParameter>");
		   TReturnInfo rst = null;
		try {
			rst = da.IWsPosCommOperate(8,sb.toString());
		} catch (RemoteException e) {
			e.printStackTrace();
		}   
		OutputParameter outputParameter=XmlHelper.toObj(OutputParameter.class, getFromBASE64(rst.getOutputPara()));
		   System.out.print("==============================="+outputParameter.getKEYE());
		   System.out.print("==============================="+outputParameter.getKEYN());
		   } catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	public static void checkIn() {
		try{
			
			   String e="65537";
			   //String n="24930620721153266542903617992812718264154233348691443108752558824720419944247854398340005720481674005890690548905340582343700383649854547052329626019742240337657127910087281813728898822150530538380194127143040813803619573430528945983064164905782870175357318198333072199786133181238236262167926032559493507081051216107443495460182169645464583979578433229415087484090932712921243615707346899225222836076894292626185447690190908281680622080734468562147659977372250840255512470406008239137460660597402717971130348343083273354512073080920535270830236138872215352903922719756831144482135593800551516653737327615046595998579";
			   String n="17318276793991807675556183133789500524193181854648710604966148268276002612823031292300621436706815684252201871930428416188446361453756194054883078048468984781351511357979363705809621103380420387312807817564466803346450120361410452614971655522003809462251898718545215440231906681099076135799809503292681364882536028090018310491957436002121177026541995033749709095304779032082872548468737765084292930905055292548406483594778316459125968651457729371008163633070802799374892814651270477802858391151230237635457743229962118758967509623699524419929705926310655530289668302856014304685001209887883118674483309163856586784451";
			   IHsCRMWebSrvserviceLocator lo = new IHsCRMWebSrvserviceLocator();
			   IHsCRMWebSrv da = lo.getIHsCRMWebSrvPort();
			   String random="0020160421163123";
			   String workkey="0020160421163888";
			  // String workkey="6523292832821234";
			   String sb = new String();
				sb=sb+"<InputParameter>";
				sb=sb+"<Random>"+random+"</Random>";
				sb=sb+"<ClientCode>001_0001</ClientCode>";
				sb=sb+"<WorkKey>"+workkey+"</WorkKey>";
				sb=sb+"<UserCode>9998</UserCode>";
				sb=sb+"<Passwd>hisense1</Passwd>";
				sb=sb+"<VerifyInfo>AABC</VerifyInfo>";
				sb=sb+"</InputParameter>";
			   TReturnInfo rst = da.IWsPosCommOperate(12,RsaUtilTest.encrypt(sb, e, n)); 
			   if(rst.getReturnCode()==0){
				   System.out.print("==============================="+rst.getReturnCode());
				   System.out.print("==============================="+Des3Test.decrypt(rst.getOutputPara(),workkey));}
			   else
				   System.out.print("==============================="+getFromBASE64(rst.getRtnMsg()));
			   
			 }
			 catch(Exception e){
			 	e.printStackTrace();
			}
	}
	public static void queryAccount() {
		IHsCRMWebSrvserviceLocator lo = new IHsCRMWebSrvserviceLocator();
		IHsCRMWebSrv da;
		try {
			  String workkey="0020160421163888";
			// 注册完成后保存会员卡号用生日做字段
			da = lo.getIHsCRMWebSrvPort();
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<InputParameter>");
			sb.append("<CardNoType>2</CardNoType>");
			//sb.append("<CardNo>140122199012080833</CardNo>");
			//sb.append("<CardNo>13606528316</CardNo>");
			sb.append("<CardNo>685609</CardNo>");
			sb.append("<WorkGuid>{0269F5CE-EDBE-45F7-9D91-AF6C78E19B1F}</WorkGuid>");
			sb.append("</InputParameter>");
			String body =Des3Test.encrypt(sb.toString(), workkey) ;
			String tag = TxtUtil.digest(body+"AABC").toUpperCase();

			StringBuilder request = new StringBuilder();
			request.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			request.append("<InputParameter>");
			request.append("<Head>");
			request.append("<Tag>" + tag + "</Tag>");
			request.append("<ClientCode>001_0001</ClientCode>");
			request.append("</Head>");
			request.append("<Body>");
			request.append(body);
			request.append("</Body>");
			request.append("</InputParameter>");
			System.out.println("body内容为："+sb.toString());
			System.out.println("请求内容为："+request.toString());
			TReturnInfo rst;
			
				rst = da.IWsPosCommOperate(907, request.toString());
			System.out.println("返回编号："+rst.getReturnCode());
			System.out.println("错误说明："+getFromBASE64(rst.getRtnMsg()));
			
		} catch (Exception es) {
			es.printStackTrace();
		}
	}
	public static void main(String[] args){
		//String msgString="PD94bWwgdmVyc2lvbj0iMS4wIj8+DQo8T3V0cHV0UGFyYW1ldGVyPjxSdG5Db2RlPjU8L1J0bkNvZGU+PEVyck1zZz694s72WE1Myqew3KO6RXh0ZXJuYWwgZXhjZXB0aW9uIEUwNkQ3MzYzIDwvRXJyTXNnPjwvT3V0cHV0UGFyYW1ldGVyPg0K";
		//getFromBASE64(msgString);
		//getkey();//获取key
		//checkIn();//签到
//		String ssString="<VipCardNo>151365</VipCardNo>".toUpperCase();
//		String sd="<OutputParameter>"+ssString+"</OutputParameter>";
//		OutputParameter outputParameter=XmlHelper.toObj(OutputParameter.class,sd.toString());
//		System.out.println(outputParameter.getVIPCARDNO());
		//doRegAccount();//注册
		queryAccount();//查询用户
		//doAddPoint();//添加积分
		//queryPoint();//查询积分
		//checkOut();//签退
	}
	
	public static void queryPoint() {
		IHsCRMWebSrvserviceLocator lo = new IHsCRMWebSrvserviceLocator();
		IHsCRMWebSrv da;
		try {
			 String workkey="0020160503221810";
			// 注册完成后保存会员卡号用生日做字段
			da = lo.getIHsCRMWebSrvPort();
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<InputParameter>");
			sb.append("<WorkGuid>{00A05933-870A-4B53-B1FF-26FA42B7032B}</WorkGuid>");
			sb.append("<CardNoType>2</CardNoType>");
			sb.append("<CardNo>151346</CardNo>");
			sb.append("<Year>" + DateUtil.format(new Date(), "yyyy") + "</Year>");
			sb.append("<Month>" + DateUtil.format(new Date(), "MM") + "</Month>");
			sb.append("<BgnRowNum>0</BgnRowNum>");
			sb.append("<EndRowNum>100</EndRowNum>");
			sb.append("</InputParameter>");
			String body =Des3Test.encrypt(sb.toString(), workkey) ;
			String tag = TxtUtil.digest(body+"AABC").toUpperCase();

			StringBuilder request = new StringBuilder();
			request.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			request.append("<InputParameter>");
			request.append("<Head>");
			request.append("<Tag>" + tag + "</Tag>");
			request.append("<ClientCode>9009_0007</ClientCode>");
			request.append("</Head>");
			request.append("<Body>");
			request.append(body);
			request.append("</Body>");
			request.append("</InputParameter>");
			System.out.println(request.toString());
			TReturnInfo rst;
				rst = da.IWsPosCommOperate(903, request.toString());
				System.out.println(rst.getReturnCode());
				//System.out.println(rst.getRtnMsg());
				//System.out.println(getFromBASE64(rst.getRtnMsg()));
				System.out.println(Des3Test.decrypt(getFromBASE64(rst.getOutputPara()),workkey));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void doAddPoint() {
		IHsCRMWebSrvserviceLocator lo = new IHsCRMWebSrvserviceLocator();
		IHsCRMWebSrv da;
		try {
			String workkey="6553750820373888";
			// 注册完成后保存会员卡号用生日做字段
			String Random = "00"
				+ DateUtil.format(new Date(), "yyyyMMddHHmmss");
			da = lo.getIHsCRMWebSrvPort();
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<InputParameter>");
			sb.append("<RandomNo>" + Random + "</RandomNo>");
			sb.append("<UserCode>9999</UserCode>");
			sb.append("<WorkGuid>{B02918B1-49E8-4AC9-BA60-D6E5A58EE0F6}</WorkGuid>");
			sb.append("<OrgCode>001</OrgCode>");
			// sb.append("<DepCode></DepCode>");
			// sb.append("<DepName></DepName>");
			sb.append("<DataSet>");
			sb.append("<Rec>");
			sb.append("<OldBillNo>784548512123</OldBillNo>");// 原始单据号（第三方交易流水号）
			sb.append("<OldJfBillNo>123456789</OldJfBillNo>");// 第三方积分单号
			// sb.append("<TranType><TranType>");//交易类型
			sb.append("<CardNo>151346</CardNo>");
			sb.append("<CardNoType>2</CardNoType>");
			//sb.append("<JfPrjCode></JfPrjCode >");// 积分活动方案如果为空，则由CRM系统自动取长期活动方案编码
			sb.append("<YwType>0002</YwType>");// 0002-网站消费产生积分
			sb.append("<JfTotal>3</JfTotal>");// 积分金额
			sb.append("<XsDate>"
					+ DateUtil.format(new Date(), ManageContext.TIME_FORMAT)
					+ "</XsDate>");// 格式YYYY-MM-DD hh:mm:ss
			//sb.append("<XsDate>2016-04-16 15:16:22</XsDate>");// 格式YYYY-MM-DD hh:mm:ss
			sb.append("</Rec>");
			sb.append("</DataSet>");
			//sb.append("<Remark>3</Remark>");// 备注
			//sb.append("<CardNo>2014120302</CardNo>");
			sb.append("</InputParameter>");
			String body =Des3Test.encrypt(sb.toString(), workkey) ;
			String tag = TxtUtil.digest(body+"AABC").toUpperCase();
			
			StringBuilder request = new StringBuilder();
			request.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			request.append("<InputParameter>");
			request.append("<Head>");
			request.append("<Tag>" + tag + "</Tag>");
			request.append("<ClientCode>9009_0007</ClientCode>");
			request.append("</Head>");
			request.append("<Body>");
			request.append(body);
			request.append("</Body>");
			request.append("</InputParameter>");
			System.out.println(request.toString());
			TReturnInfo rst;
			rst = da.IWsPosCommOperate(166, request.toString());
			System.out.println(rst.getReturnCode());
			//System.out.println(rst.getRtnMsg());
			System.out.println(getFromBASE64(rst.getRtnMsg()));
			//System.out.println(Des3Test.decrypt(getFromBASE64(rst.getRtnMsg()),workkey));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void doRegAccount() {
		IHsCRMWebSrvserviceLocator lo = new IHsCRMWebSrvserviceLocator();
		IHsCRMWebSrv da;
		try {
			String workkey="0020160503221810";
			// 注册完成后保存会员卡号用生日做字段
			String Random = "11"
				+ DateUtil.format(new Date(), "yyyyMMddHHmmss");
			da = lo.getIHsCRMWebSrvPort();
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<InputParameter>");
			sb.append("<Random>" + Random + "</Random>");
			sb.append("<WorkGuid>{00A05933-870A-4B53-B1FF-26FA42B7032B}</WorkGuid>");
			//sb.append("<AppID></AppID>");
			sb.append("<YwType>4</YwType>");
			sb.append("<Mobile>18868428646</Mobile>");
			//sb.append("<Mailaddr></Mailaddr>");
			sb.append("<OrgCode>001</OrgCode>");
			sb.append("<CertNo>140122199012080832</CertNo>");
			sb.append("<Password>123456</Password>");
			sb.append("<Gender>0</Gender>");
			sb.append("<UserCode>9999</UserCode>");
			sb.append("<VipName>测试</VipName>");
			//sb.append("<DepCode></DepCode>");
			sb.append("<CardTypeCode>01</CardTypeCode>");
			sb
			.append("<OldBillNo>"+Random+"</OldBillNo>");
			sb.append("</InputParameter>");
			String body =Des3Test.encrypt(sb.toString(), workkey) ;
			String tag = TxtUtil.digest(body+"AABC").toUpperCase();
			
			StringBuilder request = new StringBuilder();
			request.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			request.append("<InputParameter>");
			request.append("<Head>");
			request.append("<Tag>" + tag + "</Tag>");
			request.append("<ClientCode>9009_0007</ClientCode>");
			request.append("</Head>");
			request.append("<Body>");
			request.append(body);
			request.append("</Body>");
			request.append("</InputParameter>");
			System.out.println("body内容为："+sb.toString());
			System.out.println("请求内容为："+request.toString());
			TReturnInfo rst;
			
			rst = da.IWsPosCommOperate(122,request.toString());
			String returnmsg=Des3Test.decrypt(rst.getOutputPara(),workkey);
			String [] msg=returnmsg.split("</OutputParameter>");
			String [] con=msg[0].split("<OutputParameter>");
			OutputParameter crmRegistXml=	XmlHelper.toObj(OutputParameter.class,"<?xml version=\"1.0\" encoding=\"UTF-8\"?><OutputParameter>"+con[1].toUpperCase()+"</OutputParameter>");
			System.out.println("卡号========================="+crmRegistXml.getVIPCARDNO());
//			System.out.println("返回编号："+rst.getReturnCode());
			//System.out.println("错误说明："+Des3Test.decrypt(getFromBASE64(rst.getRtnMsg()),workkey));
			//System.out.println("错误说明："+getFromBASE64(rst.getRtnMsg()));
			System.out.println();
			
		} catch (Exception es) {
			es.printStackTrace();
		}
	}
	public static void checkOut() {
		
		IHsCRMWebSrvserviceLocator lo = new IHsCRMWebSrvserviceLocator();
		IHsCRMWebSrv da;
		try {
			String workkey="0020160421163888";
			
			String stringbBody="";
			da = lo.getIHsCRMWebSrvPort();
			  String Random="00"+DateUtil.format(new Date(), "yyyyMMddHHmmss");
			StringBuilder sb = new StringBuilder();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<InputParameter>");
			sb.append("<Head>");
			sb.append("<ClientCode>001_0001</ClientCode>");
			sb.append("</Head>");
			sb.append("<Body>");
			stringbBody=stringbBody+"<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
			stringbBody=stringbBody+"<InputParameter>";
			stringbBody=stringbBody+"<Random>"+Random+"</Random>";
			stringbBody=stringbBody+"<WorkGuid>{BFCF5C1F-40BE-4499-8475-B52BB48CE1FF}</WorkGuid>";
			stringbBody=stringbBody+"</InputParameter>";
			
			
			try {
				sb.append(Des3Test.encrypt(stringbBody, workkey));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			sb.append("</Body>");
			sb.append("</InputParameter>");
			System.out.println(sb.toString());
			TReturnInfo rst;
			try {
				rst = da.IWsPosCommOperate(2, sb.toString());
				System.out.println(rst.getReturnCode());
				//return getFromBASE64(rst.getRtnMsg());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
	}
	
}
