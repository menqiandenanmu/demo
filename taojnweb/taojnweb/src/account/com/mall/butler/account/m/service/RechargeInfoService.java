package com.mall.butler.account.m.service;

import com.mall.butler.account.model.RechargeInfo;
import com.mall.butler.service.BaseService;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public interface RechargeInfoService extends BaseService{

	void doAddRecharge(RechargeInfo rechargeInfo, Long id);

	Page<RechargeInfo> page(PageRequest<RechargeInfo> filter);

	RechargeInfo find();

}
