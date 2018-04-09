package com.mall.butler;

public class CommonContext {

	public static String KEY;// 私钥
	public static String PARTNER;// 公钥
	public static String SIGN_TYPE = "md5";// 约定加密方式
	public static String API_URL_QUERY = "";
	public static String API_URL_UPDATE = "";
	public static String API_URL = "";
	public static String API_URL_MESSAGE = "";



	public void setSign_type(String signtype) {
		SIGN_TYPE = signtype;
	}

	public void setApi_url_query(String apiUrlQuery) {
		API_URL_QUERY = apiUrlQuery;
	}

	public void setApi_url_update(String apiUrlUpdate) {
		API_URL_UPDATE = apiUrlUpdate;
	}

	public void setApi_url_message(String apiUrlMessage) {
		API_URL_MESSAGE = apiUrlMessage;
	}

	public void setApi_url(String apiUrl) {
		API_URL = apiUrl;
	}

	public void setKey(String key) {
		KEY = key;
	}

	public void setPartner(String partner) {
		PARTNER = partner;
	}

	

}
