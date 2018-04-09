package com.mall.butler.account.m.service._impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.dao.GiftRecordDao;
import com.mall.butler.account.m.service.MGiftRecordService;
import com.mall.butler.account.model.GiftRecord;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class MGiftRecordServiceImpl extends BaseServiceImpl implements MGiftRecordService{

	@Autowired
	private GiftRecordDao giftRecordDao;
	@Override
	public void doDel(GiftRecord giftRecord) {
		// TODO Auto-generated method stub
		giftRecordDao.delete(giftRecord);
	}

	@Override
	public void doSave(GiftRecord giftRecord) {
		// TODO Auto-generated method stub
		giftRecord.setId(giftRecordDao.getNewId());
		giftRecordDao.insert(giftRecord);
	}

	@Override
	public void doUpdate(GiftRecord giftRecord) {
		// TODO Auto-generated method stub
		GiftRecord obj=giftRecordDao.getById(giftRecordDao.getNewId());
		giftRecordDao.update(obj);
	}

	@Override
	public Page<GiftRecord> pageQuery(PageRequest<GiftRecord> pageRequest) {
		// TODO Auto-generated method stub
		return giftRecordDao.page(pageRequest);
	}

}
