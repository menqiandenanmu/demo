package com.mall.butler.sys.service;

import com.mall.butler.service.BaseService;
import com.mall.butler.sys.model.SysArea;

public interface AreaService extends BaseService {
	/**
	 * 得到编号的区域
	 * 
	 * @param code
	 * @return
	 */
	public SysArea findByCode(String code);
}
