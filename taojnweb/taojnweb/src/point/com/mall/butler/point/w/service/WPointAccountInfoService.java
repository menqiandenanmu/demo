package com.mall.butler.point.w.service;

import com.mall.butler.point.model.PointAccountInfo;
import com.mall.butler.service.BaseService;

public interface WPointAccountInfoService extends BaseService {
	
	
	public PointAccountInfo findById(Long id);
}
