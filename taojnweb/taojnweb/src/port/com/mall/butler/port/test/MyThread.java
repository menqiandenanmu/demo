package com.mall.butler.port.test;

import java.util.HashMap;
import java.util.Map;

import com.mall.util.common.HttpUtil;

public class MyThread extends Thread {
	private int i = 0;

	public MyThread(Runnable runnable) {
		super(runnable);
	}

	@Override
	public void run() {
		String bnsType;// 业务类型
		String syncType;// 同步类型
		String rowSeatNumber;// 排座号
		String orderNo;// 订单号如果订单号为空获取排座号，订单号存在以订单号为准
		String urlString;
		for (int i = 0; i < 10; i++) {
			bnsType = "bnsType" + i;
			syncType = "syncType" + i;// 同步类型
			rowSeatNumber = "rowSeatNumber" + i;// 排座号
			orderNo = "orderNo" + i;// 订单号如果订单号为空获取排座号，订单号存在以订单号为准
			Map<String, String> params = new HashMap<String, String>();
			params.put("bnsType", bnsType);
			params.put("orderNo", orderNo);
			params.put("rowSeatNumber", rowSeatNumber);
			params.put("syncType", syncType);
			System.out.println("请求值========================"
					+ params.toString());
			urlString = "http://localhost:190/dmq_sync/sycnMessage.htm";
			// urlString="http://localhost:190/dmq_sync/sycnMessage.htm?bnsType="+bnsType+"rowSeatNumber="+rowSeatNumber+"orderNo="+orderNo;
			// System.out.println(urlString);
			String result = HttpUtil.doPost(urlString, params, "UTF-8");
			System.out.println("返回结果============" + result);
		}
	}

}
