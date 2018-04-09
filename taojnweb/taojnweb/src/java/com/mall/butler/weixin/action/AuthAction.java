package com.mall.butler.weixin.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.ManageContext;
import com.mall.butler.action.w.WebBaseAction;
import com.mall.butler.helper.SessionHelper;
import com.mall.butler.util.WeixinUtil;
import com.mall.butler.weixin.WxPayConfig;
import com.mall.butler.weixin.content.WxApiContext;
import com.mall.butler.weixin.poji.UserAccess;

/**
 * 微信平台登录
 * 
 */
public class AuthAction extends WebBaseAction {
	private static final long serialVersionUID = 1L;
	private String codeUrl;
	private String wxPayUrl;
	private String code;
	@Autowired
	private WxPayConfig wxPayConfig;
	@Autowired
	public SessionHelper sessionHelper;

	/**
	 * 获取code
	 * 
	 * @return
	 */
	public String wxUserCheck() {
		System.out.println("appid=========================="+wxPayConfig.getAppId()+"===============================");
		codeUrl = WeixinUtil.getCodeUrl(wxPayConfig.getAppId(), WxApiContext.WapUrl
				+ "weixin/wxReturnUserCheck.htm", "snsapi_userinfo");
		//codeUrl = WeixinUtil.getCodeUrl(WxApiContext.AppID, "http://127.0.0.1/weixin/wxReturnUserCheck.htm", "snsapi_userinfo");
		return SUCCESS;
	}

	public String wxReturnUserCheck() {
		if (null == code)
			throw new RuntimeException("客户端未获取到");
		System.out.println("收到微信返回值============================="+code);
		UserAccess userAccess = WeixinUtil.getUserAccessToken(
				wxPayConfig.getAppId(), wxPayConfig.getAppSecret(), code);
		
		if(null==userAccess)
			throw new RuntimeException("客户端未获取到");
		System.out.println("openid============================="+userAccess.getOpenid());
		sessionHelper.set(ManageContext.WXSESSION_OPEN_ID, userAccess.getOpenid());
		Object object = sessionHelper.get(ManageContext.WXSESSION_OPEN_ID);
		if (object != null) {
			System.out.println("===================code不为空==========================");
			// 设置微信用户openid
			Object beforeUrl = sessionHelper.get(ManageContext.WXSESSION_PAY_URL);
			if (beforeUrl != null) {
				wxPayUrl = (String) beforeUrl;
				System.out.println("回退地址为======="+wxPayUrl);
				return SUCCESS;
			}

			return "index";
		} else {
			System.out.println("===================code为空==========================");
			// 记录授权前地址,授权后注册登录
			super.abtainBeforeUrl();
			return "wxUserCheck";
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}

	public String getWxPayUrl() {
		return wxPayUrl;
	}

	public void setWxPayUrl(String wxPayUrl) {
		this.wxPayUrl = wxPayUrl;
	}

}
