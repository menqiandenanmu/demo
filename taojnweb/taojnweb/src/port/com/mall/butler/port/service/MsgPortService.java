package com.mall.butler.port.service;

import com.mall.butler.service.BaseService;

public interface MsgPortService extends BaseService {
	/**
	 * 发送短信接口
	 * 
	 * @param mobile
	 * @param msg
	 * @return
	 */
	public String sendMsg(String mobile, String msg);
}
