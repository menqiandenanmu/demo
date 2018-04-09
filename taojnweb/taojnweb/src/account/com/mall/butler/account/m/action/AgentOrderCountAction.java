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
import com.mall.butler.account.model.TradeAccount;
import com.mall.butler.account.model.TradeAccountDetail;
import com.mall.butler.account.service.TradeAccountService;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.lang.DateUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class AgentOrderCountAction  extends ManageBaseAction {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TradeAccountDetail tradeAccountDetail;
	
	private Page<TradeAccountDetail>  tradeAccountDetailPage;
	
	@Autowired
	private TradeAccountService TradeAccountService;
	private Double amountTotal;
	
	/**
	 * 账户统计列表
	 */
	public String execute() {
		if (tradeAccountDetail == null) {
			tradeAccountDetail = new TradeAccountDetail();
		}
		PageRequest<TradeAccountDetail> filter = this.newPage(TradeAccountDetail.class);
		filter.setFilters(tradeAccountDetail);
		filter.setPageNumber(this.currPage);
		tradeAccountDetailPage = TradeAccountService.page(filter);
		//统计所有用户的余额
		 TradeAccount tradeAccount=new TradeAccount();
		 List<TradeAccount> tradeAccounts=	TradeAccountService.getTradeAccountList(tradeAccount);
		 amountTotal=0.00;
		 for(TradeAccount tac:tradeAccounts){
			 amountTotal+=tac.getCurLeftValue();
		}
		return LIST;
	}

	public TradeAccountDetail getTradeAccountDetail() {
		return tradeAccountDetail;
	}

	public void setTradeAccountDetail(TradeAccountDetail tradeAccountDetail) {
		this.tradeAccountDetail = tradeAccountDetail;
	}

	public Page<TradeAccountDetail> getTradeAccountDetailPage() {
		return tradeAccountDetailPage;
	}

	public void setTradeAccountDetailPage(
			Page<TradeAccountDetail> tradeAccountDetailPage) {
		this.tradeAccountDetailPage = tradeAccountDetailPage;
	}

	
	
	/**
	 * 电子卡导出
	 * 
	 * @return
	 * @throws ParseException
	 */
	public void agentOrderCountExport() throws ParseException {
		if (tradeAccountDetail == null) {
			tradeAccountDetail = new TradeAccountDetail();
		}
		
		List<TradeAccountDetail> details=TradeAccountService.findAgentOrderCountList(tradeAccountDetail);
		try {
			// 写EXCEL文件
			if (response == null) {
				super.response = ServletActionContext.getResponse();
			}
			String fileName = "账户统计" + DateUtil.format(new Date(), ManageContext.DATE_FORMAT) + ".xls";
			fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");// 解决文件中文名称问题
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setContentType("application/x-excel");
			OutputStream out = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(out);
			WritableSheet sheet = wwb.createSheet("账户统计", 0);
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
			for (int x = 0; x < 3; x++) {
				sheet.setColumnView(x, 20);
			}
			sheet.mergeCells(0, 1, 2, 1);
			sheet.mergeCells(0, 0, 2, 0);
			
			
			StringBuffer sbBuffer=new StringBuffer();
			if(!TxtUtil.isEmpty(tradeAccountDetail.getRechargeTime())){
				sbBuffer.append("日期:"+tradeAccountDetail.getRechargeTime());
			}
			if(null!=tradeAccountDetail.getOpType()){
				if(tradeAccountDetail.getOpType()==0)
					sbBuffer.append("    充值类型:充值");
				else if(tradeAccountDetail.getOpType()==1)
					sbBuffer.append("    充值类型:消费");
				else if(tradeAccountDetail.getOpType()==2)
					sbBuffer.append("    充值类型:退卡");
				else if(tradeAccountDetail.getOpType()==3)
					sbBuffer.append("    充值类型:充值卡充值");
				else if(tradeAccountDetail.getOpType()==4)
					sbBuffer.append("    充值类型:电子卡充值");
			}
			
			sheet.addCell(new Label(0, 0, "账户统计", wcf_title));
			sheet.addCell(new Label(0, 1, "  "+sbBuffer.toString(), title4));
			sheet.addCell(new Label(0, 2, "日期", title3));
			sheet.addCell(new Label(1, 2, "充值类型", title3));
			sheet.addCell(new Label(2, 2, "金额", title3));
			int i = 3;
			for (TradeAccountDetail tradeAccountDetail : details) {
				sheet.addCell(new Label(0, i,tradeAccountDetail.getRechargeTime(), title));
				String opType="";
				if(tradeAccountDetail.getOpType()==0)
					opType="充值";
				else if(tradeAccountDetail.getOpType()==1)
					opType="消费";
				else if(tradeAccountDetail.getOpType()==2)
					opType="退卡";
				else if(tradeAccountDetail.getOpType()==3)
					opType="充值卡充值";
				else if(tradeAccountDetail.getOpType()==4)
					opType="电子卡充值";
				sheet.addCell(new Label(1, i, opType, title));
				sheet.addCell(new jxl.write.Number(2, i, Double.valueOf(tradeAccountDetail.getOpValue() + ""), title));
				i++;
			}
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("类ElectronicCoupAction方法electronicCoupExport执行出现异常, 原因：" + e.toString());
		}
	}

	public Double getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(Double amountTotal) {
		this.amountTotal = amountTotal;
	}


}
