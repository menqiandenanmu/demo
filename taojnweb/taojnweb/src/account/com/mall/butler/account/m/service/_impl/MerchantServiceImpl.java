package com.mall.butler.account.m.service._impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mall.butler.ManageContext;
import com.mall.butler.account.dao.MerchantDao;
import com.mall.butler.account.m.service.MAccountService;
import com.mall.butler.account.m.service.MerchantService;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.account.model.Merchant;
import com.mall.butler.helper.SessionHelper;
import com.mall.butler.service.ApplicationLogService;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class MerchantServiceImpl extends BaseServiceImpl implements
		MerchantService {

	@Autowired
	private MerchantDao merchantDao;
	@Autowired
	private SessionHelper sessionHelper;
	@Autowired
	protected MAccountService accountService;
	@Autowired
	private ApplicationLogService applicationLogService;
	
	@Transactional
	@Override
	public void doDel(Merchant merchant) {
		merchant = merchantDao.getById(merchant.getId());
		merchantDao.delete(merchant);
		
		//获取当前登录账户
		Long pk = (Long)sessionHelper.get(ManageContext.SESSION_LOGINID);
		AccountLogin login = null;
		if (pk != null)
			 login = accountService.getEntityById(AccountLogin.class, pk);
		
		applicationLogService.generic("操作员"+login.getLoginName()+"删除商户:" + merchant.getMerchantName()
				+ "成功商户号:" + merchant.getMerchantNumber(), "删除商户成功",
				ApplicationLogService.GENERIC, ManageContext.LOG_SYS_TYPE,
				ManageContext.LOG_OPT_TYPE_ACC, null);

	}

	@Override
	public void doSave(Merchant merchant) {
		Merchant filter = new Merchant();
		if(null != merchant.getId()){
			filter.setId(merchant.getId());
		}
		if(null != merchant.getMerchantNumber()){
			filter.setMerchantNumber(merchant.getMerchantNumber());
		}
		if(null != merchant.getMerchantName()){
			filter.setMerchantName(merchant.getMerchantName());
		}
		if(null != merchant.getMerchantKey()){
			filter.setMerchantKey(merchant.getMerchantKey());
		}
		if(null != merchant.getMerchantStatus()){
			filter.setMerchantStatus(merchant.getMerchantStatus());
		}
		merchantDao.insert(filter);

	}

	@Override
	public void doUpdate(Merchant merchant) {
		Merchant merchantOld = merchantDao.getById(merchant.getId());

		if (null != merchant.getMerchantNumber()) {
			merchantOld.setMerchantNumber(merchant.getMerchantNumber());

		}
		if (null != merchant.getMerchantName()) {
			merchantOld.setMerchantName(merchant.getMerchantName());

		}
		if (null != merchant.getMerchantStatus()) {
			merchantOld.setMerchantStatus(merchant.getMerchantStatus());

		}
		if (null != merchant.getMerchantKey()) {
			merchantOld.setMerchantKey(merchant.getMerchantKey());

		}

		merchantDao.update(merchantOld);

	}

	@Override
	public Page<Merchant> page(PageRequest<Merchant> filter) {
		return merchantDao.page(filter);
	}

}
