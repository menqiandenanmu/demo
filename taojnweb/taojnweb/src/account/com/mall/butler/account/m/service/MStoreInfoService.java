package com.mall.butler.account.m.service;

import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.account.model.DiscountRules;
import com.mall.butler.account.model.StoreInfo;
import com.mall.butler.service.BaseService;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public interface MStoreInfoService extends BaseService {

	Page<StoreInfo> pageQuery(PageRequest<StoreInfo> pageRequest);

	void doSave(StoreInfo storeInfo, AccountLogin accountLogin);

	void doUpdate(StoreInfo storeInfo);

	void doDel(StoreInfo storeInfo);

	/**
	 * 规则列表
	 * 
	 * @param pageRequest
	 * @return
	 */
	Page<DiscountRules> pageRuleQuery(PageRequest<DiscountRules> pageRequest);

	/**
	 * 更新
	 * 
	 * @param storeInfo
	 * @param accountLogin
	 */
	void doUpdateUser(StoreInfo storeInfo, AccountLogin accountLogin);

	void doDelRule(DiscountRules discountRules);

	void doUpdateRule(DiscountRules discountRules);

	void doSaveRule(DiscountRules discountRules);

	/**
	 * 通过条码查找
	 * 
	 * @param barCode
	 * @return
	 */
	StoreInfo queryByQrcode(String barCode);

	/**
	 * 支付
	 * 
	 * @param storeInfo
	 * @param login
	 * @param payNum
	 */
	Double qrPay(StoreInfo storeInfo, AccountLogin login, double payNum);

}
