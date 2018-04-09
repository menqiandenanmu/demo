package com.mall.butler.sys.m.service;

import java.util.List;

import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.service.BaseService;
import com.mall.butler.sys.model.SysParam;

/**
 * 系统参数管理
 * 
 * @author zhaoxs
 */
public interface MSysParamService extends BaseService {

	/**
	 * 查找所有系统参数信息
	 * 
	 * @param account
	 * @return
	 */
	List<SysParam> queryAll(SysParam sysParam);

	/**
	 * 修改系统参数
	 * 
	 * @param param
	 */
	void doUpdate(SysParam param);

	/**
	 * 刷新系统参数到静态变量
	 */
	void refresnParam();

	/**
	 * 系统参数添加
	 * 
	 * @param param
	 * @param accountLogin
	 */
	void doAddParam(SysParam sysParam, AccountLogin accountLogin);

	/**
	 * 删除参数
	 * 
	 * @param sysParam
	 */
	void doDelParam(SysParam sysParam);

}
