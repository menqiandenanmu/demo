package com.mall.butler.account.m.service._impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.dao.RechargeInfoDao;
import com.mall.butler.account.m.service.RechargeInfoService;
import com.mall.butler.account.model.RechargeInfo;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class RechargeInfoServiceImpl extends BaseServiceImpl implements RechargeInfoService{

	@Autowired
	private RechargeInfoDao rechargeInfoDao;
	@Override
	public void doAddRecharge(RechargeInfo rechargeInfo, Long id) {
		// TODO Auto-generated method stub
		if(null!=id){
			RechargeInfo newInfo=rechargeInfoDao.getById(id);
			newInfo.setContentInfo(rechargeInfo.getContentInfo());
			rechargeInfoDao.update(newInfo);	
		}else {
			rechargeInfo.setId(rechargeInfoDao.getNewId());
			rechargeInfoDao.insert(rechargeInfo);
		}
		
	}

	@Override
	public RechargeInfo find() {
		List<RechargeInfo> list=rechargeInfoDao.find(new RechargeInfo());
		if(null!=list&&list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public Page<RechargeInfo> page(PageRequest<RechargeInfo> filter) {
		// TODO Auto-generated method stub
		return rechargeInfoDao.page(filter);
	}

}
