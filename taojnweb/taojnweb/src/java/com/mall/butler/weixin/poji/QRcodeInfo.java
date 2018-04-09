package com.mall.butler.weixin.poji;

public class QRcodeInfo extends QRcodeBase {
	
	private String expire_seconds;
	
	private ActionInfo action_info;

	public ActionInfo getAction_info() {
		return action_info;
	}

	public void setAction_info(ActionInfo actionInfo) {
		action_info = actionInfo;
	}

	public String getExpire_seconds() {
		return expire_seconds;
	}

	public void setExpire_seconds(String expireSeconds) {
		expire_seconds = expireSeconds;
	}

}
