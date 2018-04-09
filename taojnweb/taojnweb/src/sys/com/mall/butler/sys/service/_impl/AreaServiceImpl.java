package com.mall.butler.sys.service._impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.butler.sys.dao.SysAreaDao;
import com.mall.butler.sys.model.SysArea;
import com.mall.butler.sys.service.AreaService;

public class AreaServiceImpl extends BaseServiceImpl implements AreaService {
	@Autowired
	private SysAreaDao sysAreaDao;

	@Override
	public SysArea findByCode(String code) {
		SysArea filter = new SysArea();
		filter.setAreaCode(code);
		List<SysArea> areaList = sysAreaDao.find(filter);
		return areaList.size() > 0 ? areaList.get(0) : null;
	}
}
