package com.mall.butler.account.m.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.m.service.MAccountCouponService;
import com.mall.butler.account.model.AccountCoupon;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 用户消费券
 * @author caedmon
 *
 */
public class AccountCouponAction extends ManageBaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AccountCoupon accountCoupon;
	private Page<AccountCoupon> page;
	@Autowired
	private MAccountCouponService accountCouponService;
	public String execute() {
		PageRequest<AccountCoupon> pageRequest = super.newPage(AccountCoupon.class);
		if (null == accountCoupon)
			accountCoupon = new AccountCoupon();
		AccountCoupon fCoupon=new AccountCoupon();
		if(!TxtUtil.isEmpty(accountCoupon.getCouponName()))
			fCoupon.setCouponName("%"+accountCoupon.getCouponName().trim()+"%");
		if(!TxtUtil.isEmpty(accountCoupon.getRealName()))
			fCoupon.setRealName("%"+accountCoupon.getRealName().trim()+"%");
		if(!TxtUtil.isEmpty(accountCoupon.getAccountName()))
			fCoupon.setAccountName("%"+accountCoupon.getAccountName().trim()+"%");
		if(null!=accountCoupon.getCouponStatus())
			fCoupon.setCouponStatus(accountCoupon.getCouponStatus());
		pageRequest.setFilters(fCoupon);
		pageRequest.setPageNumber(currPage);
		page = accountCouponService.pageQuery(pageRequest);
		return LIST;
	}
	
	/**
	 * 新增页面
	 * 
	 * @return
	 */
	public String add() {
		return ADD;
	}

	/**
	 * 保存操作
	 * 
	 * @return
	 */
	public String save() {
		accountCouponService.doSave(accountCoupon);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("添加成功!");
		return JDIALOG;
	}

	/**
	 * 更新操作
	 * 
	 * @return
	 */
	public String update() {
		accountCoupon.setId(id);
		accountCouponService.doUpdate(accountCoupon);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("编辑成功!");
		return JDIALOG;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String del() {
		accountCoupon=accountCouponService.getEntityById(AccountCoupon.class,id);
		accountCouponService.doDel(accountCoupon);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("删除成功!");
		return JDIALOG;
	}

	/**
	 * 编辑操作
	 * 
	 * @return
	 */
	public String edit() {
		accountCoupon=accountCouponService.getEntityById(AccountCoupon.class,id);
		if (accountCoupon == null) {
			throw new RuntimeException("无效的信息!");
		}
		return EDIT;
	}

	public AccountCoupon getAccountCoupon() {
		return accountCoupon;
	}

	public void setAccountCoupon(AccountCoupon accountCoupon) {
		this.accountCoupon = accountCoupon;
	}

	public Page<AccountCoupon> getPage() {
		return page;
	}

	public void setPage(Page<AccountCoupon> page) {
		this.page = page;
	}
	
	
}
