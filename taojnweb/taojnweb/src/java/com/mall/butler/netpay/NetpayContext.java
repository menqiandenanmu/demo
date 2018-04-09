package com.mall.butler.netpay;

import java.net.URL;

public class NetpayContext {
	public static String CUR_ID;
	public static String GATE_ID;
	public static String TRANS_TYPE;
	public static String BACK_TYPE;
	
	public static String MER_ID;
	public static String MER_LAST;
	public static String RES_CODE;
	public static String RETURN_PAGE;
	public static String NOTIFY_PAGE;
	
	public static String MER_PRK_PATH;
	public static String PG_PUBK_PATH;
	
	public static String SERVICE_URL;
	public void setCur_id(String curId) {
		CUR_ID = curId;
	}
	public void setGate_id(String gateId) {
		GATE_ID = gateId;
	}
	public void setTrans_type(String transType) {
		TRANS_TYPE = transType;
	}
	public void setBack_type(String backType) {
		BACK_TYPE = backType;
	}
	public void setMer_id(String merId) {
		MER_ID = merId;
	}
	public void setMer_last(String merLast) {
		MER_LAST = merLast;
	}
	public void setReturn_page(String returnPage) {
		RETURN_PAGE = returnPage;
	}
	public void setNotify_page(String notifyPage) {
		NOTIFY_PAGE = notifyPage;
	}
	public void setRes_code(String resCode) {
		RES_CODE = resCode;
	}
	public void setMer_prk_path(String merPrkPath) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			URL url = loader.getResource(merPrkPath);
			MER_PRK_PATH=url.toURI().getPath();
		}catch(Exception ex){
			throw new RuntimeException("网银key未找到!");
		}
	}
	public void setPg_pubk_path(String pgPubkPath) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			URL url = loader.getResource(pgPubkPath);
			PG_PUBK_PATH=url.toURI().getPath();
		}catch(Exception ex){
			throw new RuntimeException("网银key未找到!");
		}
	}
	public void setService_url(String serviceUrl) {
		SERVICE_URL = serviceUrl;
	}

}
