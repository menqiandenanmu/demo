package com.mall.butler.alipay;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;

import org.apache.log4j.Logger;

import com.mall.util.common.HttpUtil;
import com.mall.util.common.TxtUtil;

public class AlipayUtil {
	private static Logger logger = Logger.getLogger(AlipayUtil.class);

	  public static String getContent(Map<String, String> params, String privateKey)
	  {
	    List keys = new ArrayList(params.keySet());
	    Collections.sort(keys);
	    String prestr = "";
	    boolean first = true;
	    for (int i = 0; i < keys.size(); i++) {
	      String key = (String)keys.get(i);
	      String value = (String)params.get(key);
	      if ((value != null) && (value.trim().length() != 0))
	      {
	        if (first) {
	          prestr = prestr + key + "=" + value;
	          first = false;
	        } else {
	          prestr = prestr + "&" + key + "=" + value;
	        }
	      }
	    }
	    return prestr + privateKey;
	  }

	  public static boolean check(String checkUrl, String partner, String notifyId)
	  {
	    String inputLine = "";
	    try {
	      String urlvalue = checkUrl + "?partner=" + partner + "&notify_id=" + notifyId;
	      URL url = new URL(urlvalue);
	      HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
	      BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

	      inputLine = in.readLine().toString();

	      if ("true".equals(inputLine)) {
	        return true;
	      }
	      return false;
	    } catch (Exception e) {
	      e.printStackTrace();
	    }return false;
	  }

	  public static boolean royaltyExec(AlipayRoyaltyVo royalty)
	  {
	    String inputLine = "";
	    try {
	      String urlStr = royalty.getUrl() + "?service=" + royalty.getService();
	      urlStr = urlStr + "&partner=" + royalty.getPartner();
	      urlStr = urlStr + "&notify_url=" + URLEncoder.encode(royalty.getNotify_url(), royalty.getInput_charset());
	      urlStr = urlStr + "&trade_no=" + royalty.getTrade_no();
	      urlStr = urlStr + "&out_bill_no=" + royalty.getOut_bill_no();
	      urlStr = urlStr + "&royalty_type=" + royalty.getRoyalty_type();
	      urlStr = urlStr + "&royalty_parameters=" + URLEncoder.encode(royalty.getRoyalty_parameters(), royalty.getInput_charset());
	      urlStr = urlStr + "&_input_charset=" + royalty.getInput_charset();
	      urlStr = urlStr + "&sign=" + royalty.getSign();
	      urlStr = urlStr + "&sign_type=" + royalty.getSign_type();
	      URL url = new URL(urlStr);
	      HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
	      BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
	      String t = in.readLine();
	      while (!TxtUtil.isEmpty(t)) {
	        inputLine = inputLine + t;
	        t = in.readLine();
	      }
	      JAXBContext context = JAXBContext.newInstance(new Class[] { AlipayResultXml.class });
	      InputStream buf = new ByteArrayInputStream(inputLine.getBytes("UTF-8"));
	      AlipayResultXml result = (AlipayResultXml)context.createUnmarshaller().unmarshal(buf);
	      if (!"T".equals(result.getIs_success())) {
	        return false;
	      }
	      return true;
	    } catch (Exception ex) {
	      ex.printStackTrace();
	    }return false;
	  }

	  public static String Sign(AlipayTransVo trans, String privateKey)
	  {
	    Map params = new HashMap();
	    params.put("service", trans.getService());
	    params.put("partner", trans.getPartner());
	    params.put("return_url", trans.getReturnUrl());
	    params.put("notify_url", trans.getNotifyUrl());
	    params.put("payment_type", trans.getPayment_type());
	    params.put("seller_email", trans.getSeller_email());
	    params.put("out_trade_no", trans.getOut_trade_no());
	    params.put("out_order_no", trans.getOut_order_no());

	    params.put("total_fee", trans.getTotal_fee());
	    params.put("subject", trans.getSubject());
	    params.put("body", trans.getBody());
	    params.put("_input_charset", trans.getInput_charset());
	    params.put("paymethod", trans.getPaymethod());
	    params.put("lt_b_pay", trans.getLt_b_pay());
	    String str = getContent(params, privateKey);
	    return TxtUtil.digest(str).toLowerCase();
	  }

	  public static AlipayQueryResultXml tradeQuery(String outOrderNo)
	  {
	    AlipayQueryResultXml result = new AlipayQueryResultXml();
	    Map params = new HashMap();
	    params.put("service", AlipayContext.SERVICE_TRADE_QUERY);
	    params.put("partner", AlipayContext.PARTNER);
	    params.put("out_trade_no", outOrderNo);
	    params.put("_input_charset", AlipayContext.INPUT_CHARSET);
	    String str = getContent(params, AlipayContext.KEY);
	    params.put("sign_type", AlipayContext.SIGN_TYPE);
	    params.put("sign", TxtUtil.digest(str).toLowerCase());
	    String responseText = HttpUtil.doGet(AlipayContext.SERVICE_URL_HTTPS, params, "utf-8");
	    try
	    {
	      JAXBContext context = JAXBContext.newInstance(new Class[] { AlipayQueryResultXml.class });
	      InputStream buf = new ByteArrayInputStream(responseText.getBytes("UTF-8"));
	      return (AlipayQueryResultXml)context.createUnmarshaller().unmarshal(buf);
	    }
	    catch (Exception e) {
	      logger.error(e);
	      e.printStackTrace();
	    }return null;
	  }

	  public static AlipayResultXml closeTrade(String outOrderNo)
	  {
	    AlipayResultXml result = new AlipayResultXml();
	    Map params = new HashMap();
	    params.put("service", AlipayContext.SERVICE_CLOSE_TRADE);
	    params.put("partner", AlipayContext.PARTNER);
	    params.put("out_order_no", outOrderNo);
	    params.put("_input_charset", AlipayContext.INPUT_CHARSET);
	    String str = getContent(params, AlipayContext.KEY);
	    params.put("sign", TxtUtil.digest(str).toLowerCase());
	    params.put("sign_type", AlipayContext.SIGN_TYPE);
	    String responseText = HttpUtil.doGet(AlipayContext.SERVICE_URL_HTTPS, params, "utf-8");
	    try
	    {
	      JAXBContext context = JAXBContext.newInstance(new Class[] { AlipayResultXml.class });
	      InputStream buf = new ByteArrayInputStream(responseText.getBytes("UTF-8"));
	      return (AlipayResultXml)context.createUnmarshaller().unmarshal(buf);
	    }
	    catch (Exception e) {
	      logger.error(e);
	      e.printStackTrace();
	    }return null;
	  }

	  public static String alipayReturnSign(Map params, String privateKey)
	  {
	    List keys = new ArrayList(params.keySet());
	    Collections.sort(keys);
	    String prestr = "";
	    boolean first = true;
	    for (int i = 0; i < keys.size(); i++) {
	      String key = (String)keys.get(i);
	      String value = ((String[])params.get(key))[0];
	      if ((key != null) && (!key.equalsIgnoreCase("sign")) && (!key.equalsIgnoreCase("sign_type")))
	      {
	        if ((value != null) && (value.trim().length() != 0))
	        {
	          if (first) {
	            prestr = prestr + key + "=" + value;
	            first = false;
	          } else {
	            prestr = prestr + "&" + key + "=" + value;
	          }
	        }
	      }
	    }
	    return TxtUtil.digest(prestr + privateKey).toLowerCase();
	  }
}
