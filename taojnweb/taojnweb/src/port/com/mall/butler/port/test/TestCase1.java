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

import com.mall.butler.ManageContext;
import com.mall.butler.util.WeixinUtil;
import com.mall.butler.weixin.content.WxApiContext;
import com.mall.butler.weixin.poji.UserAccess;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.lang.DateUtil;

public class TestCase1 {

	public static void main(String[] args) {
		UserAccess userAccess = WeixinUtil.getUserAccessToken(
				"wx2b9f69c2e62b7e13".trim(), "a0fc571ed8b386d0293594c7cbf60835".trim(), "041sh7KA1VyDKg0rsLIA1TN7KA1sh7K-".trim());
		System.out.println(userAccess.getOpenid());
//		UserAccess userAccess = WeixinUtil.getUserAccessToken(
//				"wx5fc69e25a9cfb15d", "24fb48c10f6dca217bbqc1d228b65d22", "021T05UY0X1LAN1NLATY0is7UY0T05Uv");
//	String	codeUrl = WeixinUtil.getCodeUrl("wx5fc69e25a9cfb15d", "http://jiangnan.com"
//				+ "weixin/wxReturnUserCheck.htm", "snsapi_userinfo");
//	System.out.println(codeUrl);
	}


	public static void syncOrder() {

		Map<String, String> params = new HashMap<String, String>();
		params.put("transTime", DateUtil.format(new Date(),
				ManageContext.TIME_FORMAT));
		// 改成查询当前链接数据库景区编号
		// params.put("storeCode", CommonContext.STORE_CODE);
		params.put("storeCode", "0002");
		String sign = makeSign(params, "cb6e9afe-3490-486e-be7e-aee98ae8ca32");
		params.put("signType", "MD5");
		params.put("sign", sign);
		String str = doPost("http://192.168.0.179:8201/sycnMessage.htm",
				params, "UTF-8");
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
		return TxtUtil.digest(prestr +"&"+ privateKey);
	}

	@SuppressWarnings("unchecked")
	public static String doPost(String reqUrl, Map parameters,
			String recvEncoding) {
		HttpURLConnection url_con = null;
		String responseContent = null;
		try {
			StringBuffer params = new StringBuffer();
			for (Iterator iter = parameters.entrySet().iterator(); iter
					.hasNext(); params.append("&")) {
				java.util.Map.Entry element = (java.util.Map.Entry) iter.next();
				params.append(((String) element.getKey()).toString());
				params.append("=");
				params.append(URLEncoder.encode(((String) element.getValue())
						.toString(), recvEncoding));
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
			BufferedReader rd = new BufferedReader(new InputStreamReader(in,
					recvEncoding));
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
