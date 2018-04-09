package com.mall.butler.web.w.service;

import com.mall.butler.service.BaseService;
import com.mall.butler.web.model.StaticInfo;

/**
 * @author caedmon
 * @version 创建时间：2013-3-26 下午03:05:48
 */
public interface WStaticService extends BaseService {

	/**
	 * 通过字典明细编号查询字典明细
	 * 
	 * @param hELPCENTER
	 * @return
	 */
	StaticInfo getByCode(String classCode);

}
