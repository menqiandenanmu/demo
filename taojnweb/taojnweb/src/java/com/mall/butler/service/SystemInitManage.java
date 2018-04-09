package com.mall.butler.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.sys.m.service.MSysParamService;


/**
 * 类描述：初始化系统参数等
 * 类名称：SystemInitManage
 * 创建人：caedmon
 * 修改备注：
 * 
 * @version
 */
public class SystemInitManage implements InitializingBean {
	@Autowired
	private MSysParamService mSysParamService;

	@Override
	public void afterPropertiesSet() throws Exception {
		/** 刷新系统参数 */
		mSysParamService.refresnParam();
	}

}
