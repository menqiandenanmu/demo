package com.mall.butler.account.m.service;

import com.mall.butler.account.model.DunningInfo;
import com.mall.butler.service.BaseService;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public interface DunningInfoService extends BaseService{

	Page<DunningInfo> page(PageRequest<DunningInfo> filter);

	void doAddSave(DunningInfo dunningInfo);

	void doUpdate(DunningInfo dunningInfo);

}
