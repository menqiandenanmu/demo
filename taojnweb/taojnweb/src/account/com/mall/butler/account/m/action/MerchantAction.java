package com.mall.butler.account.m.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.m.service.MerchantService;
import com.mall.butler.account.model.Merchant;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class MerchantAction extends ManageBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Merchant merchant;
	
	private String merchantId;
	
	// 分页对象
	private Page<Merchant> pages;
	@Autowired
	private MerchantService merchantService;

	public String execute() {
		if (null == merchant)
			merchant = new Merchant();
		PageRequest<Merchant> filter = this.newPage(Merchant.class);
		filter.setFilters(merchant);
		filter.setPageNumber(this.currPage);
		pages = merchantService.page(filter);
		return SUCCESS;
	}


	public String edit() {
		merchant = merchantService.getEntityById(
				Merchant.class, id);
		if (null == merchant)
			throw new RuntimeException("记录不存在");
		
		return EDIT;
	}
	
	

	/**
	 * 保存更新
	 * 
	 * @date 2010-10-21 上午09:26:04
	 * @return
	 */
	public String update() {
		merchant.setId(id);
		merchantService.doUpdate(merchant);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("更新成功!");
		return JDIALOG;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String del() {
		if (merchant == null) {
			merchant = new Merchant();
		}
		merchant.setId(id);
		merchantService.doDel(merchant);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("删除成功!");
		return JDIALOG;
	}
	
	/**
	 * 新增商户
	 * @return
	 */
	public String add(){
		
		return ADD;
	}
	
	/**
	 * 新增商户保存
	 * @return
	 */
	public String save(){
		if(merchant == null){
			merchant = new Merchant();
		}
		//判断商户ID是否存在
		merchant.setId(Long.valueOf(merchantId));
		Merchant filter = merchantService.getEntityById(Merchant.class, merchant.getId());
		if(filter!=null){
			this.msgInfo.setFlag(MessageDialog.ERROR);
			this.msgInfo.addMessage("商户Id已存在!");
		}else{
			merchantService.doSave(merchant);
			this.msgInfo.setFlag(MessageDialog.SUCCESS);
			this.msgInfo.addMessage("保存成功!");
		}
		return JDIALOG;
	}


	public Merchant getMerchant() {
		return merchant;
	}


	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}


	public Page<Merchant> getPages() {
		return pages;
	}


	public void setPages(Page<Merchant> pages) {
		this.pages = pages;
	}


	public String getMerchantId() {
		return merchantId;
	}


	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	
	



}
