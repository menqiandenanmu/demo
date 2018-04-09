package com.mall.butler.account.m.action;

import java.io.OutputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.ManageContext;
import com.mall.butler.account.m.model.TradeAccountVo;
import com.mall.butler.account.m.service.ElectronicCoupService;
import com.mall.butler.account.m.service.MAgentAccountService;
import com.mall.butler.account.model.ElectronicCoup;
import com.mall.butler.account.model.TradeAccountInfo;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.sys.m.service.MDictService;
import com.mall.butler.sys.model.SysDictDetail;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.common.lang.DateUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class ElectronicCoupAction extends ManageBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TradeAccountInfo tradeAccountInfo;// 前台检索条件
	@Autowired
	private ElectronicCoupService electronicCoupService;
	@Autowired
	private MAgentAccountService agentAccountService;
	private Long[] messageIds;
	private String ids;

	private ElectronicCoup electronicCoup;
	
	private TradeAccountVo tradeAccount;
	// 分页对象
	private Page<ElectronicCoup> pages;
	private Page<TradeAccountVo> tradeAccountPages;
	@Autowired
	private MDictService mDictService;
	private  List<SysDictDetail> sysDictDetails;

	public String execute() {
		if (null == electronicCoup)
			electronicCoup = new ElectronicCoup();
		PageRequest<ElectronicCoup> filter = this.newPage(ElectronicCoup.class);
		filter.setFilters(electronicCoup);
		sysDictDetails= mDictService.queryDetail(ManageContext.DICT_STATIC_CLASS);
		filter.setPageNumber(this.currPage);
		pages = electronicCoupService.page(filter);
		return SUCCESS;
	}

	public String add() {
		if (null == electronicCoup)
			electronicCoup = new ElectronicCoup();
		sysDictDetails= mDictService.queryDetail(ManageContext.DICT_STATIC_CLASS);
		return ADD;
	}

	public String save() {
		electronicCoup.setAccountId(id);
		ElectronicCoup electronicCoupOld = new ElectronicCoup();

		electronicCoupOld.setEletCode(electronicCoup.getEletCode());
		electronicCoupOld.setDeleted(false);
		if (null != electronicCoupService.getEntity(electronicCoupOld)) {

			throw new RuntimeException("电子券编号"+electronicCoup.getEletCode()+"不能重复");
		}
		electronicCoupService.doSave(electronicCoup);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("操作成功!");
		return JDIALOG;
	}

	public String edit() {
		electronicCoup = electronicCoupService.getEntityById(
				ElectronicCoup.class, id);
		if (null == electronicCoup)
			throw new RuntimeException("记录不存在");
		
		sysDictDetails= mDictService.queryDetail(ManageContext.DICT_STATIC_CLASS);
		return EDIT;
	}
	
	public String info() {
		electronicCoup = electronicCoupService.getEntityById(
				ElectronicCoup.class, id);
		if (null == electronicCoup)
			throw new RuntimeException("记录不存在");
		
		sysDictDetails= mDictService.queryDetail(ManageContext.DICT_STATIC_CLASS);
		return INFO;
	}
	/**
	 * 审核列表页
	 * @return
	 */
	public String audit() {
		if (null == electronicCoup){
			electronicCoup = new ElectronicCoup();
			electronicCoup.setAuditSyatus(0);
		}
		PageRequest<ElectronicCoup> filter = this.newPage(ElectronicCoup.class);
		filter.setFilters(electronicCoup);
		sysDictDetails= mDictService.queryDetail(ManageContext.DICT_STATIC_CLASS);
		filter.setPageNumber(this.currPage);
		pages = electronicCoupService.page(filter);
		return "electronicCoupAudit";
	}
	
	
	/**
	 * 审核页面
	 * @return
	 */
	public String toAudit() {
		if(null==ids){
			throw new RuntimeException("记录不存在");
			
		}
		return "electronicCoupAuditInfo";
	}
	
	
	/**
	 * 审核保存
	 * @return
	 */
	public String auditSave() {
		electronicCoupService.doAuditAll(ids.split(","),electronicCoup.getAuditRemark());
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("审核通过");
		return JDIALOG;
	}
	

	/**
	 * 保存更新
	 * 
	 * @date 2010-10-21 上午09:26:04
	 * @return
	 */
	public String update() {
		electronicCoup.setId(id);
		electronicCoupService.doUpdate(electronicCoup);
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
		if (electronicCoup == null) {
			electronicCoup = new ElectronicCoup();
		}
		electronicCoup.setId(id);
		electronicCoupService.doDel(electronicCoup);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("删除成功!");
		return JDIALOG;
	}

	/**
	 * 绑定用户
	 * 
	 * @return
	 */
	public String tradeAccount() {
		if (tradeAccount == null) {
			tradeAccount = new TradeAccountVo();
		}
		PageRequest<TradeAccountVo> filter = this
				.newPage(TradeAccountVo.class);
		TradeAccountVo tradeVo=new TradeAccountVo();
		if(!TxtUtil.isEmpty(tradeAccount.getCorpName()))
			tradeVo.setCorpName("%"+tradeAccount.getCorpName().trim()+"%");
		if(!TxtUtil.isEmpty(tradeAccount.getAccountName()))
			tradeVo.setAccountName("%"+tradeAccount.getAccountName().trim()+"%");
		if(!TxtUtil.isEmpty(tradeAccount.getTradeAccName()))
			tradeVo.setTradeAccName("%"+tradeAccount.getTradeAccName().trim()+"%");
		filter.setFilters(tradeVo);
		filter.setPageNumber(this.currPage);
		tradeAccountPages = agentAccountService.queryPageAgentAccount(filter);
		return "tradeAccount";


	}
	
	
	

	/**
	 * 电子卡导出
	 * 
	 * @return
	 * @throws ParseException
	 */
	public void electronicCoupExport() throws ParseException {
		if (electronicCoup == null) {
			electronicCoup = new ElectronicCoup();
		}
		
		List<ElectronicCoup> details=electronicCoupService.findElectronicCoupDetailList(electronicCoup);
		try {
			// 写EXCEL文件
			if (response == null) {
				super.response = ServletActionContext.getResponse();
			}
			String fileName = "电子充值券" + DateUtil.format(new Date(), ManageContext.DATE_FORMAT) + ".xls";
			fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");// 解决文件中文名称问题
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setContentType("application/x-excel");
			OutputStream out = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(out);
			WritableSheet sheet = wwb.createSheet("电子充值券", 0);
			WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 15, WritableFont.BOLD);
			WritableFont BoldFont1 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
			WritableFont BoldFont2 = new WritableFont(WritableFont.ARIAL, 14, WritableFont.NO_BOLD);
			WritableFont BoldFont3 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
			WritableFont BoldFont4 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
			// 用于标题
			WritableCellFormat wcf_title = new WritableCellFormat(BoldFont);
			WritableCellFormat title = new WritableCellFormat(BoldFont1);
			WritableCellFormat title2 = new WritableCellFormat(BoldFont2);
			WritableCellFormat title3 = new WritableCellFormat(BoldFont3);
			WritableCellFormat title4 = new WritableCellFormat(BoldFont4);
			wcf_title.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			title.setBorder(Border.ALL, BorderLineStyle.THIN);// 线条
			title2.setBorder(Border.ALL, BorderLineStyle.THIN);// 线条
			title3.setBorder(Border.ALL, BorderLineStyle.THIN);// 线条
			title4.setBorder(Border.ALL, BorderLineStyle.THIN);// 线条
			wcf_title.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
			wcf_title.setAlignment(Alignment.CENTRE); // 水平对齐
			title4.setAlignment(Alignment.CENTRE); // 水平对齐
			title3.setAlignment(Alignment.CENTRE); // 水平对齐
			title2.setAlignment(Alignment.RIGHT); // 向右对齐
			for (int x = 0; x < 7; x++) {
				
				if(x==6){
					sheet.setColumnView(x, 30);
				}else{
					sheet.setColumnView(x, 20);
				}
			}
			sheet.mergeCells(0, 1, 7, 1);
			sheet.mergeCells(0, 0, 7, 0);
			
			//系统参数
			sysDictDetails= mDictService.queryDetail(ManageContext.DICT_STATIC_CLASS);
			
			StringBuffer sbBuffer=new StringBuffer();
			if(!TxtUtil.isEmpty(electronicCoup.getAccountName())){
				sbBuffer.append("客户名称:"+electronicCoup.getAccountName());
			}
			if(!TxtUtil.isEmpty(electronicCoup.getEletName())){
				sbBuffer.append("    充值来源:"+electronicCoup.getEletName());
			}
			if(!TxtUtil.isEmpty(electronicCoup.getAuditName())){
				sbBuffer.append("    审核人:"+electronicCoup.getAuditName());
			}
			if (!TxtUtil.isEmpty(electronicCoup.getResource())) {

				for (SysDictDetail item : sysDictDetails) {
					if (item.getDictDetailCode().equals(
							electronicCoup.getResource())) {
						sbBuffer.append("    来源:" + item.getDictDetailName());
					}
				}
			}
			if (null != electronicCoup.getAuditSyatus()
					&& !TxtUtil.isEmpty(electronicCoup.getAuditSyatus()
							.toString())) {
				if (electronicCoup.getAuditSyatus() == 1) {
					sbBuffer.append("    审核状态:审核通过");
				} else {
					sbBuffer.append("    审核状态:未审核");
				}

			}
			if (null != electronicCoup.getStatus()
					&& !TxtUtil.isEmpty(electronicCoup.getStatus().toString())) {
				if (electronicCoup.getStatus() == 1) {
					sbBuffer.append("    充值状态:已充值");
				} else {
					sbBuffer.append("    充值状态:未充值");
				}
			}
			if (null != electronicCoup.getRechgeTime()
					&& !TxtUtil.isEmpty(electronicCoup.getRechgeTime()
							.toString())) {
				sbBuffer.append("    充值时间:"
						+ DateUtil.format(electronicCoup.getRechgeTime(),
								ManageContext.TIME_FORMAT));
			}
			
			sheet.addCell(new Label(0, 0, "电子充值券", wcf_title));
			sheet.addCell(new Label(0, 1, "  "+sbBuffer.toString(), title4));
			sheet.addCell(new Label(0, 2, "客户名称", title3));
			sheet.addCell(new Label(1, 2, "充值来源", title3));
			sheet.addCell(new Label(2, 2, "金额", title3));
			sheet.addCell(new Label(3, 2, "来源", title3));
			sheet.addCell(new Label(4, 2, "充值状态", title3));
			sheet.addCell(new Label(5, 2, "审核状态", title3));
			sheet.addCell(new Label(6, 2, "充值时间", title3));
			sheet.addCell(new Label(7, 2, "审核人", title3));
			int i = 3;
			for (ElectronicCoup electronicCoupItem : details) {
				String accStatuString="未充值";
				sheet.addCell(new Label(0, i, electronicCoupItem.getAccountName(), title));
				sheet.addCell(new Label(1, i, electronicCoupItem.getEletName(), title));
				sheet.addCell(new jxl.write.Number(2, i, Double.valueOf(electronicCoupItem.getLeftValue() + ""), title));
				for(SysDictDetail item :sysDictDetails)
				{
					if(item.getDictDetailCode().equals(electronicCoupItem.getResource())){
						sheet.addCell(new Label(3, i, item.getDictDetailName(), title));
					}
				}
				
				if(null!=electronicCoupItem.getStatus()&&electronicCoupItem.getStatus()==1)
					accStatuString="已充值";
				sheet.addCell(new Label(4, i,accStatuString , title));
				if(null!=electronicCoupItem.getAuditSyatus()&&electronicCoupItem.getAuditSyatus()==1){
					
					sheet.addCell(new Label(5, i,"审核通过" , title));
				}
				else{
					sheet.addCell(new Label(5, i,"未审核" , title));
				}
				
				if(null!=electronicCoupItem.getRechgeTime())
				sheet.addCell(new Label(6, i, DateUtil.format(electronicCoupItem.getRechgeTime(), ManageContext.TIME_FORMAT), title));
				else
					sheet.addCell(new Label(6, i,"未审核" , title));
				sheet.addCell(new Label(7, i,  electronicCoupItem.getAuditName(), title));
				i++;
			}
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("类ElectronicCoupAction方法electronicCoupExport执行出现异常, 原因：" + e.toString());
		}
	}

	/**
	 * 审核
	 * 
	 * @return
	 */
	public String verify() {
		if (electronicCoup == null) {
			electronicCoup = new ElectronicCoup();
		}
		electronicCoup.setId(id);
		electronicCoup.setAuditSyatus(1);
		electronicCoup.setAuditId(super.getLoginId());
		electronicCoup.setAuditName(super.getLogin().getLoginName());
		try{
			electronicCoupService.doAudit(electronicCoup);
		}catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("审核成功!");
		return JDIALOG;
	}

	
	/**
	 * 批量删除
	 * 
	 * @return
	 */

	public String delAll() {
		electronicCoupService.doDelAll(messageIds);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("删除成功");
		return JDIALOG;
	}
	
	/**
	 * 批量充值
	 * 
	 * @return
	 */

	public String useAll() {
		electronicCoupService.doUseAll(ids.split(","));
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("启用成功");
		return JDIALOG;
	}

	
	public ElectronicCoup getElectronicCoup() {
		return electronicCoup;
	}

	public void setElectronicCoup(ElectronicCoup electronicCoup) {
		this.electronicCoup = electronicCoup;
	}

	public Page<ElectronicCoup> getPages() {
		return pages;
	}

	public void setPages(Page<ElectronicCoup> pages) {
		this.pages = pages;
	}

	public TradeAccountInfo getTradeAccountInfo() {
		return tradeAccountInfo;
	}

	public void setTradeAccountInfo(TradeAccountInfo tradeAccountInfo) {
		this.tradeAccountInfo = tradeAccountInfo;
	}

	public Page<TradeAccountVo> getTradeAccountPages() {
		return tradeAccountPages;
	}

	public void setTradeAccountPages(Page<TradeAccountVo> tradeAccountPages) {
		this.tradeAccountPages = tradeAccountPages;
	}

	public TradeAccountVo getTradeAccount() {
		return tradeAccount;
	}

	public void setTradeAccount(TradeAccountVo tradeAccount) {
		this.tradeAccount = tradeAccount;
	}

	public List<SysDictDetail> getSysDictDetails() {
		return sysDictDetails;
	}

	public void setSysDictDetails(List<SysDictDetail> sysDictDetails) {
		this.sysDictDetails = sysDictDetails;
	}

	public Long[] getMessageIds() {
		return messageIds;
	}

	public void setMessageIds(Long[] messageIds) {
		this.messageIds = messageIds;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}



}
