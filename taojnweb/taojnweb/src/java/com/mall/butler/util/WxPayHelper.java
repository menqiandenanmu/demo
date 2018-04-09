package com.mall.butler.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.mall.butler.weixin.WxJSApiPayDto;
import com.mall.butler.weixin.WxPrepayDto;
import com.mall.butler.weixin.WxPrepayResp;
import com.mall.butler.weixin.WxTradeType;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.WeixinHttpUtil;
import com.mall.util.common.lang.StringUtil;

/**
 * 微信支付相关
 * 
 */

public class WxPayHelper {
	private static Logger log = Logger.getLogger(Logger.class);
	@SuppressWarnings("unused")
	private static final String SUCCESS = "SUCCESS";
	private static final String FAIL = "FAIL";

	/**
	 * 微信统一预支付接口(生成Prepayid)
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String doUnifiedPrePay(String url, WxPrepayDto wxPrepayDto) {
		// http请求微信服务器
		String reqParamStr = XmlUtil.toXml(wxPrepayDto);
		
		log.info("doUnifiedPrePay 请求： " + reqParamStr);
		System.out.println("doUnifiedPrePay 请求： " + reqParamStr);
		
		String resultStr;
		try {
			resultStr = WeixinHttpUtil.doPost1(url, null,reqParamStr, "utf-8");
		} catch (Exception e) {
			throw new RuntimeException("微信预支付返回空,原因：" + e);
		}
		if (StringUtil.isBlank(resultStr))
			throw new RuntimeException("微信预支付返回空, 请求参数：" + reqParamStr);

		// 返回结果转化成对象
		log.info("doUnifiedPrePay 返回： " + resultStr);
		System.out.println("=====doUnifiedPrePay 返回： " + resultStr);
		System.out.println(resultStr.substring(0, 56));
		WxPrepayResp prepayResp = XmlUtil.toObj(WxPrepayResp.class, resultStr);
		if (prepayResp.getReturn_code().equals(FAIL))
			throw new RuntimeException("微信预支付失败, 请求参数：" + reqParamStr + "; 返回错误：" + prepayResp.getReturn_msg());

		if (prepayResp.getResult_code().equals(FAIL))
			throw new RuntimeException("微信预支付失败, 请求参数：" + reqParamStr + "; 返回错误：" + prepayResp.getErr_code_des());

		// 成功，返回Prepayid
		return prepayResp.getPrepay_id();
	}

	public static WxJSApiPayDto getWxJSApiParam(WxPrepayDto wxPrepayDto, String prepayId, String key) {
		WxJSApiPayDto wxJsApiPayDto = new WxJSApiPayDto();
		wxJsApiPayDto.setAppId(wxPrepayDto.getAppid());
		wxJsApiPayDto.setNonceStr(OidUtils.newId());
		wxJsApiPayDto.setPackageStr("prepay_id=" + prepayId);
		wxJsApiPayDto.setTimeStamp(String.valueOf(System.currentTimeMillis()));
		wxJsApiPayDto.setSignType("MD5");
		wxJsApiPayDto.setPaySign(signJsApiPay(wxJsApiPayDto, key));

		return wxJsApiPayDto;
	}

	/**
	 * 预支付签名
	 * 
	 * @param prepayDto
	 * @param privateKey
	 * @return
	 */
	public static String signPrePay(WxPrepayDto prepayDto, String privateKey) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", prepayDto.getAppid());
		params.put("mch_id", prepayDto.getMch_id());
		params.put("nonce_str", prepayDto.getNonce_str());
		params.put("body", prepayDto.getBody());
		params.put("out_trade_no", prepayDto.getOut_trade_no());
		params.put("total_fee", prepayDto.getTotal_fee());
		params.put("spbill_create_ip", prepayDto.getSpbill_create_ip());
		params.put("notify_url", prepayDto.getNotify_url());
		params.put("trade_type", prepayDto.getTrade_type());
		// trade_type 为 JSAPI时额外必填项
		if (prepayDto.getTrade_type().equalsIgnoreCase(WxTradeType.JSAPI.getCode())) {
			params.put("openid", prepayDto.getOpenid());
			// trade_type 为 NATIVE时额外必填项
		} else if (prepayDto.getTrade_type().equalsIgnoreCase(WxTradeType.NATIVE.getCode())) {
			params.put("product_id", prepayDto.getProduct_id());
		}

		// 以下为选填项
		// params.put("device_info", "");
		// params.put("attach", "");
		// params.put("time_start", "");
		// params.put("time_expire", "");
		// params.put("goods_tag", "");

		String beSignStr = formatParam(params, privateKey);

		return TxtUtil.digest(beSignStr).toUpperCase();
	}

	/**
	 * JSApi支付调用签名
	 * 
	 * @param wxJsApiPayDto
	 * @param privateKey
	 * @return
	 */
	public static String signJsApiPay(WxJSApiPayDto wxJsApiPayDto, String privateKey) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("appId", wxJsApiPayDto.getAppId());
		params.put("timeStamp", wxJsApiPayDto.getTimeStamp());
		params.put("nonceStr", wxJsApiPayDto.getNonceStr());
		params.put("package", wxJsApiPayDto.getPackageStr());
		params.put("signType", wxJsApiPayDto.getSignType());

		String beSignStr = formatParam(params, privateKey);
		String sign = TxtUtil.digest(beSignStr).toUpperCase();
		return sign;
	}

	/**
	 * 参数排序重组
	 * 
	 * @param params
	 * @param privateKey
	 * @return
	 */
	private static String formatParam(Map<String, String> params, String privateKey) {
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
		prestr = prestr + "&key=" + privateKey.trim();

		return prestr;
	}
}

