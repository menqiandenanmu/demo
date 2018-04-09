package com.mall.butler.account.m.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.m.service.MGiftRecordService;
import com.mall.butler.account.model.GiftRecord;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class GiftRecordAction extends ManageBaseAction{
	private static final long serialVersionUID = 1L;
	private GiftRecord giftRecord;
	private Page<GiftRecord> page;
	@Autowired
	private MGiftRecordService giftRecordService;
	public String execute() {
		if(null==giftRecord)
			giftRecord=new GiftRecord();
		PageRequest<GiftRecord> pageRequest = super.newPage(GiftRecord.class);
		pageRequest.setFilters(giftRecord);
		pageRequest.setPageNumber(currPage);
		page = giftRecordService.pageQuery(pageRequest);
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
		giftRecordService.doSave(giftRecord);
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
		giftRecord.setId(id);
		giftRecordService.doUpdate(giftRecord);
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
		giftRecord=giftRecordService.getEntityById(GiftRecord.class,id);
		giftRecordService.doDel(giftRecord);
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
		giftRecord=giftRecordService.getEntityById(GiftRecord.class,id);
		if (giftRecord == null) {
			throw new RuntimeException("无效的信息!");
		}
		return EDIT;
	}

	public GiftRecord getGiftRecord() {
		return giftRecord;
	}

	public void setGiftRecord(GiftRecord giftRecord) {
		this.giftRecord = giftRecord;
	}

	public Page<GiftRecord> getPage() {
		return page;
	}

	public void setPage(Page<GiftRecord> page) {
		this.page = page;
	}
	
	
}
