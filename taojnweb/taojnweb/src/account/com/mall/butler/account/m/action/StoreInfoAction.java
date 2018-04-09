package com.mall.butler.account.m.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.m.service.MStoreInfoService;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.account.model.StoreInfo;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;
/**
 * 店铺管理
 * @author caedmon
 *
 */
public class StoreInfoAction extends ManageBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StoreInfo storeInfo;
	private AccountLogin accountLogin;
	private Page<StoreInfo> page;
	@Autowired
	private MStoreInfoService mStoreInfoService;
	public String execute() {
		if(null==storeInfo)
			storeInfo=new StoreInfo();
		PageRequest<StoreInfo> pageRequest = super.newPage(StoreInfo.class);
		StoreInfo filter=new StoreInfo();
		if(!TxtUtil.isEmpty(storeInfo.getStoreName()))
			filter.setStoreName("%"+storeInfo.getStoreName().trim()+"%");
		if(!TxtUtil.isEmpty(storeInfo.getLinkman()))
			filter.setLinkman("%"+storeInfo.getLinkman().trim()+"%");
		if(!TxtUtil.isEmpty(storeInfo.getLinkmanTel()))
			filter.setLinkmanTel(storeInfo.getLinkmanTel().trim());
		pageRequest.setFilters(filter);
		pageRequest.setPageNumber(currPage);
		page = mStoreInfoService.pageQuery(pageRequest);
		return LIST;
	}
	
	/**
	 * 新增页面
	 * 
	 * @return
	 */
	public String add() {
		accountLogin=new AccountLogin();
		storeInfo=new StoreInfo();
		return ADD;
	}

	/**
	 * 保存操作
	 * 
	 * @return
	 */
	public String save() {
		mStoreInfoService.doSave(storeInfo,accountLogin);
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
		storeInfo.setId(id);
		mStoreInfoService.doUpdate(storeInfo);
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
		storeInfo=mStoreInfoService.getEntityById(StoreInfo.class,id);
		mStoreInfoService.doDel(storeInfo);
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
		storeInfo=mStoreInfoService.getEntityById(StoreInfo.class,id);
		if (storeInfo == null) {
			throw new RuntimeException("无效的信息!");
		}
		return EDIT;
	}

	
	public StoreInfo getStoreInfo() {
		return storeInfo;
	}

	public AccountLogin getAccountLogin() {
		return accountLogin;
	}

	public void setAccountLogin(AccountLogin accountLogin) {
		this.accountLogin = accountLogin;
	}

	public void setStoreInfo(StoreInfo storeInfo) {
		this.storeInfo = storeInfo;
	}

	public Page<StoreInfo> getPage() {
		return page;
	}

	public void setPage(Page<StoreInfo> page) {
		this.page = page;
	}
	
	
}
