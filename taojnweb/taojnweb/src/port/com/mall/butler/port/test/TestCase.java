package com.mall.butler.port.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;

import net.sf.json.JSONObject;
import cn.emay.sdk.client.api.Client;

import com.mall.butler.ManageContext;
import com.mall.butler.alipay.AlipayQueryResultXml;
import com.mall.butler.alipay.AlipayTradeXml;
import com.mall.butler.alipay.AlipayUtil;
import com.mall.butler.port.xml.OrderXml;
import com.mall.butler.port.xml.TradeAccountXml;
import com.mall.butler.util.WeixinUtil;
import com.mall.butler.util.XmlHelper;
import com.mall.util.common.HttpUtil;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.lang.DateUtil;
import com.mall.util.common.lang.io.ByteArrayInputStream;

public class TestCase {

	public static void main(String[] args) {
		// crmPay();
		// queryUser();
		// queryFromAlipay("");

		// System.out
		// .println(TxtUtil.digest("10698000886904" + "md5" + "04" +
		// "2017-03-31 16:15:56" + "886904"));
		crmPay();
	}

	public static void testsendmsg() {

		try {
			Client sdkclient = new Client("6SDK-EMY-6688-KKULN", "203376");
			// Client sdkclient = new Client("6SDK-EMY-6688-KKULN", "309922");
			// 注册序列号
			// int b = sdkclient.registEx("791916");
			// System.out.println("注册序列号:" + b);
			// // 注册企业信息
			// int b = sdkclient.registDetailInfo("雁荡山旅游股份有限公司", "李守鑫",
			// "057762180126", "15825600167",
			// "757865020@qq.com", "057762180126", "浙江省乐清市雁荡山镇雁山路88号",
			// "066000");
			// System.out.println("注册企业信息:" + b);
			int a = sdkclient.sendSMS(new String[] { "17503448316" }, "【河北智慧旅游服务有限公司】河北智慧旅游服务有限公司111", 3);
			System.out.println("短信发送结果:" + a);
			// 注销
			// int a = sdkclient.logout();
			// System.out.println(a);
			// 查询单价
			// double price = sdkclient.getEachFee();
			// System.out.println("price============" + price);
			// 查询余额
			double amount = sdkclient.getBalance();
			System.out.println("余额============" + amount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void xxx() {
		Date date = new Date();
		Date orderDate = DateUtil.toDate("2016-01-02 10:10:10");
		if (DateUtil.addDays(orderDate, 365).before(date))
			System.out.println("tet");
	}

	public static String queryWeChat() {
		String string = "<xml><appid>wx2421b1c4370ec43b</appid><mch_id>10000100</mch_id><nonce_str>ec2316275641faa3aacf3cc599e8730f</nonce_str><transaction_id>20161214104435563240LB</transaction_id><sign>FDD167FAA73459FD921B144BAF4F4CA2</sign></xml>";
		JSONObject jsonObject = WeixinUtil.httpRequest(string, "GET", null);
		System.out.println("jsonObject");
		return jsonObject.toString();

	}

	public static Boolean queryFromAlipay(String trans) {
		// 订单处理

		// 查询支付宝交易信息,service、partner、out_trade_no、_input_charset、sign_type赋值顺序不可改变，否则返回签名非法错误
		AlipayQueryResultXml result = new AlipayQueryResultXml();
		Map<String, String> params = new TreeMap<String, String>();
		params.put("service", "single_trade_query");
		params.put("partner", "2088701194650441");
		params.put("out_trade_no", "20161207100230273720LB");
		params.put("_input_charset", "GBK");
		String str = AlipayUtil.getContent(params, "boomqvn9er8wctsg36cmc4k0pr07ggmk");
		params.put("sign_type", "MD5");

		params.put("sign", TxtUtil.digest(str).toLowerCase());
		String responseText = HttpUtil.doGet("https://mapi.alipay.com/gateway.do", params, "GBK");
		System.out.println("支付宝返回结果" + responseText);
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(AlipayQueryResultXml.class);
			InputStream buf = new ByteArrayInputStream(responseText.getBytes("GBK"));
			result = (AlipayQueryResultXml) context.createUnmarshaller().unmarshal(buf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<AlipayTradeXml> list = result.getResponse();
		if (list == null || list.size() == 0)
			return false;
		String alipayTransno = "";
		String trade_status = list.get(0).getTrade_status();
		if ("TRADE_FINISHED".equals(trade_status) || "TRADE_SUCCESS".equals(trade_status)) {
			alipayTransno = list.get(0).getTrade_no();
			return true;
		}

		return false;
	}

	public static String getDatePoor(Date endDate, Date nowDate) {

		long nd = 1000 * 24 * 60 * 60;
		long nm = 1000 * 60;
		// long ns = 1000;
		// 获得两个时间的毫秒时间差异
		long diff = endDate.getTime() - nowDate.getTime();
		// 计算差多少分钟
		long min = diff % nd / nm;

		return min + "";
	}

	public static void syncOrder() {

		Map<String, String> params = new HashMap<String, String>();
		params.put("transTime", DateUtil.format(new Date(), ManageContext.TIME_FORMAT));
		// 改成查询当前链接数据库景区编号
		// params.put("storeCode", CommonContext.STORE_CODE);
		params.put("storeCode", "0002");
		String sign = makeSign(params, "cb6e9afe-3490-486e-be7e-aee98ae8ca32");
		params.put("signType", "MD5");
		params.put("sign", sign);
		String str = doPost("http://192.168.0.179:8201/sycnMessage.htm", params, "UTF-8");
		System.out.println("收到返回值========================" + str);
	}

	public static String makeSign(Map<String, String> params, String privateKey) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		boolean first = true;
		for (int i = 0; i < keys.size(); i++) {
			String key = (String) keys.get(i);
			String value = (String) params.get(key);
			if (value == null || value.trim().length() == 0) {
				continue;
			}
			if (first) {
				prestr = prestr + key + "=" + value;
				first = false;
			} else {
				prestr = prestr + "&" + key + "=" + value;
			}
		}
		return TxtUtil.digest(prestr + "&" + privateKey);
	}

	/**
	 * 账户查询
	 */
	public static void queryUser() {

		TradeAccountXml requestXml = new TradeAccountXml();
		requestXml.setPassword("001612211007");
		String partner = "800823beb20a45f9a6f77252b8850100";
		String key = "99998288882ojljkji992k7782211tjn";
		String transTime = DateUtil.getSimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String signData = partner + transTime + XmlHelper.toXml(requestXml) + key;
		// System.out.println("加密前值"+signData);
		String sign = TxtUtil.digest(signData);
		// System.out.println("加密值"+sign);
		Map<String, String> m = new HashMap<String, String>();
		m.put("data", XmlHelper.toXml(requestXml));
		m.put("partner", "800823beb20a45f9a6f77252b8850100");
		m.put("signType", "md5");
		m.put("sign", sign);
		m.put("transTime", transTime);
		System.out.println(m.toString());
		String ss = doPost("http://jiangnanpay.com/port/crmUserInfo.htm", m, "utf-8");
		// String ss = doPost("http://114.55.61.63/port/crmUserInfo.htm", m,
		// "utf-8");
		System.out.println(ss);
	}

	/**
	 * 账户查询
	 */
	public static void crmTransInfo() {

		OrderXml requestXml = new OrderXml();
		requestXml.setOrderNo("2016045654322");
		requestXml.setOpFlag("01");
		requestXml.setPassword("sdfsdfds");
		String partner = "800823beb20a45f9a6f77252b8850100";
		String key = "99998288882ojljkji992k7782211tjn";
		String transTime = DateUtil.getSimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String signData = partner + transTime + XmlHelper.toXml(requestXml) + key;
		String sign = TxtUtil.digest(signData);
		Map<String, String> m = new HashMap<String, String>();
		m.put("data", XmlHelper.toXml(requestXml));
		m.put("partner", "800823beb20a45f9a6f77252b8850100");
		m.put("signType", "md5");
		m.put("sign", sign);
		m.put("transTime", transTime);
		System.out.println(m.toString());
		String ss = doPost("http://114.55.61.63/port/crmTransInfo.htm", m, "utf-8");
		System.out.println(ss);
	}

	/**
	 * 账户绑定
	 */
	public static void doBindUser() {

		TradeAccountXml requestXml = new TradeAccountXml();
		requestXml.setPassword("E10ADC3949BA59ABBE56E057F20F883E");
		requestXml.setAccountCode("13606528316");
		requestXml.setMallCode("2016080945633852");
		String partner = "099923be123a45f9a6f77252b8856700";
		String key = "99998288882ojljkji992k7782211tjn";
		String transTime = DateUtil.getSimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String signData = partner + transTime + XmlHelper.toXml(requestXml) + key;
		String sign = TxtUtil.digest(signData);
		Map<String, String> m = new HashMap<String, String>();
		m.put("data", XmlHelper.toXml(requestXml));
		m.put("partner", "800823beb20a45f9a6f77252b8850100");
		m.put("signType", "md5");
		m.put("sign", sign);
		m.put("transTime", transTime);
		System.out.println(m.toString());
		String ss = doPost("http://114.55.157.116/port/doBindUser.htm", m, "utf-8");
		System.out.println(ss);
	}

	/**
	 * 支付
	 */
	public static void pay() {

		OrderXml requestXml = new OrderXml();
		// requestXml.setPassword("861660645886");
		requestXml.setPassword("E10ADC3949BA59ABBE56E057F20F883E");
		requestXml.setOrderNo("2016080945633852");
		requestXml.setOpFlag("01");
		requestXml.setStoreCode("001");
		requestXml.setPosCode("0000");
		requestXml.setOperateCode("123456");
		// requestXml.setPayAmount("000000000011");
		requestXml.setPaySum(13241.70);
		requestXml.setAccountCode("13606528316");
		requestXml.setTradeNo("2016080945633852");
		requestXml.setPayOverTime(DateUtil.format(new Date(), ManageContext.TIME_FORMAT));
		requestXml.setOrderInfo("测试");
		String partner = "099923be123a45f9a6f77252b8856700";
		String key = "99998288882ojljkji992k7782211tjn";
		String transTime = DateUtil.getSimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String signData = partner + transTime + XmlHelper.toXml(requestXml) + key;
		String sign = TxtUtil.digest(signData);
		Map<String, String> m = new HashMap<String, String>();
		m.put("data", XmlHelper.toXml(requestXml));
		m.put("partner", "800823beb20a45f9a6f77252b8850100");
		m.put("signType", "md5");
		m.put("sign", sign);
		m.put("transTime", transTime);
		System.out.println(m.toString());
		String ss = doPost("http://114.55.157.116/port/doPay.htm", m, "utf-8");
		System.out.println(ss);
	}

	/**
	 * 扫码支付
	 */
	public static void crmPay() {

		OrderXml requestXml = new OrderXml();
		// requestXml.setPassword("039888131391");
		requestXml.setPassword("6358070279340");
		requestXml.setOrderNo("0398881333476666");
		requestXml.setOpFlag("01");
		requestXml.setStoreCode("001");
		requestXml.setPosCode("0000");
		requestXml.setOperateCode("123456");
		// requestXml.setPayAmount("000000000011");
		requestXml.setPayAmount("000000000502");
		requestXml.setOrderInfo("测试");
		String partner = "800823beb20a45f9a6f77252b8850100";
		String key = "99998288882ojljkji992k7782211tjn";
		String transTime = DateUtil.getSimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String signData = partner + transTime + XmlHelper.toXml(requestXml) + key;
		String sign = TxtUtil.digest(signData);
		Map<String, String> m = new HashMap<String, String>();
		m.put("data", XmlHelper.toXml(requestXml));
		m.put("partner", "800823beb20a45f9a6f77252b8850100");
		m.put("signType", "md5");
		m.put("sign", sign);
		m.put("transTime", transTime);
		System.out.println(m.toString());
		String ss = doPost("http://jiangnanpay.com/port/doCrmPay.htm", m, "utf-8");
		System.out.println(ss);
	}

	/**
	 * 订单退款
	 */
	public static void refund() {

		OrderXml requestXml = new OrderXml();
		requestXml.setTransNo("2016080945633822");
		requestXml.setOrderNo("00120188831202tjn");
		requestXml.setOpFlag("04");
		requestXml.setPayAmount("000000000001");
		requestXml.setRefundAmount("000000000001");
		String partner = "800823beb20a45f9a6f77252b8850100";
		String key = "99998288882ojljkji992k7782211tjn";
		String transTime = DateUtil.getSimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String signData = partner + transTime + XmlHelper.toXml(requestXml) + key;
		String sign = TxtUtil.digest(signData);
		Map<String, String> m = new HashMap<String, String>();
		m.put("data", XmlHelper.toXml(requestXml));
		m.put("partner", "800823beb20a45f9a6f77252b8850100");
		m.put("signType", "md5");
		m.put("sign", sign);
		m.put("transTime", transTime);
		System.out.println(m.toString());
		String ss = doPost("http://jiangnanpay.com/port/doCrmRefund.htm", m, "utf-8");
		System.out.println(ss);
	}

	@SuppressWarnings("unchecked")
	public static String doPost(String reqUrl, Map parameters, String recvEncoding) {
		HttpURLConnection url_con = null;
		String responseContent = null;
		try {
			StringBuffer params = new StringBuffer();
			for (Iterator iter = parameters.entrySet().iterator(); iter.hasNext(); params.append("&")) {
				java.util.Map.Entry element = (java.util.Map.Entry) iter.next();
				params.append(((String) element.getKey()).toString());
				params.append("=");
				params.append(URLEncoder.encode(((String) element.getValue()).toString(), recvEncoding));
			}

			if (params.length() > 0)
				params = params.deleteCharAt(params.length() - 1);
			URL url = new URL(reqUrl);
			url_con = (HttpURLConnection) url.openConnection();
			url_con.setRequestMethod("POST");
			url_con.setConnectTimeout(60 * 1000);
			url_con.setReadTimeout(60 * 1000);
			url_con.setDoOutput(true);
			byte b[] = params.toString().getBytes();
			url_con.getOutputStream().write(b, 0, b.length);
			url_con.getOutputStream().flush();
			url_con.getOutputStream().close();
			InputStream in = url_con.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in, recvEncoding));
			String tempLine = rd.readLine();
			StringBuffer tempStr = new StringBuffer();
			String crlf = System.getProperty("line.separator");
			for (; tempLine != null; tempLine = rd.readLine()) {
				tempStr.append(tempLine);
				tempStr.append(crlf);
			}

			responseContent = tempStr.toString();
			rd.close();
			in.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return responseContent;
	}

}
