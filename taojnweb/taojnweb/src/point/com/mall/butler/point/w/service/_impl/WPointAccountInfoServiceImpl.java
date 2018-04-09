package com.mall.butler.point.w.service._impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.point.dao.PointAccountInfoDao;
import com.mall.butler.point.model.PointAccountInfo;
import com.mall.butler.point.w.service.WPointAccountInfoService;
import com.mall.butler.service._impl.BaseServiceImpl;

public class WPointAccountInfoServiceImpl extends BaseServiceImpl implements WPointAccountInfoService{
	@Autowired
	private PointAccountInfoDao pointAccountInfoDao;

	@Override
	public PointAccountInfo findById(Long id) {
		return 	pointAccountInfoDao.getById(id);
	}


}
