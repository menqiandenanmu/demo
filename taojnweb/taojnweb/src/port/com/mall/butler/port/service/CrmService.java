package com.mall.butler.port.service;

import java.util.Date;

import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.point.model.PointChangeDetal;
import com.mall.butler.service.BaseService;

public interface CrmService extends BaseService{

/**
 * 签到
 * @param piFunctionCode
 */
	String crmLogin(String workGuid);
	/**
	 * 签退
	 * @return
	 */
	String loginOut(String workGuid);
	/**
	 * 获取公钥
	 * @return
	 */
	 String getKey();
	 /**
	  * 申请会员
	  */
	 String doRegAccount(AccountLogin accountLogin,String workGuid);
	 /**
	  * 查询会员
	  */
	 String queryAccount(AccountLogin accountLogin,String workGuid);
	 /**
	  * 会员更新
	  */
	 String updateAccount(AccountLogin accountLogin,String workGuid);
	 /**
	  * 新增积分接口
	  */
	 String doAddPoint(AccountLogin accountLogin,String workGuid,PointChangeDetal point);
	 /**
	  * 查询积分
	  */
	 String queryPoint(AccountLogin accountLogin,String workGuid,Date date);
	 /**
	  * 挂失
	  */
	 String displayAccount(AccountLogin accountLogin,String workGuid);
	 /**
	  * 解挂
	  */
	 String playAccount(AccountLogin accountLogin,String workGuid);
}
