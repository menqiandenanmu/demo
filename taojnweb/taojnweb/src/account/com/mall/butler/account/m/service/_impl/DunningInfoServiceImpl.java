package com.mall.butler.account.m.service._impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.dao.DunningInfoDao;
import com.mall.butler.account.m.service.DunningInfoService;
import com.mall.butler.account.model.DunningInfo;

import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class DunningInfoServiceImpl extends BaseServiceImpl implements DunningInfoService{
@Autowired
private DunningInfoDao dunningInfoDao;
	@Override
	public void doAddSave(DunningInfo dunningInfo) {
		dunningInfo.setId(dunningInfoDao.getNewId());
		dunningInfoDao.insert(dunningInfo);
		
	}

	@Override
	public Page<DunningInfo> page(PageRequest<DunningInfo> filter) {
		// TODO Auto-generated method stub
		return dunningInfoDao.page(filter);
	}

	@Override
	public void doUpdate(DunningInfo dunningInfo) {
		DunningInfo dunnin=dunningInfoDao.getById(dunningInfo.getId());
		dunnin.setContentInfo(dunningInfo.getContentInfo());
		dunningInfoDao.update(dunnin);
	}

}
