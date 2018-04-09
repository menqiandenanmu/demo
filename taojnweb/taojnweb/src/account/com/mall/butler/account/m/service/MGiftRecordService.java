package com.mall.butler.account.m.service;

import com.mall.butler.account.model.GiftRecord;
import com.mall.butler.service.BaseService;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public interface MGiftRecordService extends BaseService{

	Page<GiftRecord> pageQuery(PageRequest<GiftRecord> pageRequest);

	void doSave(GiftRecord giftRecord);

	void doUpdate(GiftRecord giftRecord);

	void doDel(GiftRecord giftRecord);

}
