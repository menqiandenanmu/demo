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
import com.mall.butler.account.model.GiroInfo;
import com.mall.butler.account.service.TradeAccountService;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.lang.DateUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class GiroInfoAction extends ManageBaseAction{

	/**
	 * 转账管理
	 */
	private static final long serialVersionUID = 1L;

	private GiroInfo giroInfo;
	@Autowired
	private TradeAccountService tradeAccountService;
	private Page<GiroInfo> page;
	public String execute() {
		if(null==giroInfo)
			giroInfo=new GiroInfo();
		PageRequest<GiroInfo> pageRequest = super.newPage(GiroInfo.class);
		pageRequest.setFilters(giroInfo);
		pageRequest.setPageNumber(currPage);
		page = tradeAccountService.pageQueryGiro(pageRequest);
		return LIST;
	}
	
	/**
	 * 消费记录导出
	 * 
	 * @return
	 * @throws ParseException
	 */
	public void purchaseExport() throws ParseException {
		List<GiroInfo> details=tradeAccountService.findGiroInfo(giroInfo);
		try {
			// 写EXCEL文件
			if (response == null) {
				super.response = ServletActionContext.getResponse();
			}
			String fileName = "转账记录" + DateUtil.format(new Date(), ManageContext.DATE_FORMAT) + ".xls";
			fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");// 解决文件中文名称问题
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setContentType("application/x-excel");
			OutputStream out = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(out);
			WritableSheet sheet = wwb.createSheet("转账记录", 0);
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
			for (int x = 0; x < 9; x++) {
				sheet.setColumnView(x, 20);
			}
			sheet.mergeCells(0, 1,8, 1);
			sheet.mergeCells(0, 0,8, 0);
			StringBuffer sbBuffer=new StringBuffer();
			if(!TxtUtil.isEmpty(giroInfo.getAccountName()))
			sbBuffer.append("转出名称  :"+giroInfo.getAccountName());
			if(!TxtUtil.isEmpty(giroInfo.getOrderNo()))
				sbBuffer.append("     交易号  :"+giroInfo.getOrderNo());
			if(!TxtUtil.isEmpty(giroInfo.getAccountName2()))
				sbBuffer.append("    转入账户  :"+giroInfo.getAccountName2());
			sheet.addCell(new Label(0, 0, "转账记录", wcf_title));
			sheet.addCell(new Label(0, 1, "  " + sbBuffer.toString(), title4));
			sheet.addCell(new Label(0, 2, "转入账号", title3));
			sheet.addCell(new Label(1, 2, "转入姓名", title3));
			sheet.addCell(new Label(2, 2, "转出账号", title3));
			sheet.addCell(new Label(3, 2, "转出姓名", title3));
			sheet.addCell(new Label(4, 2, "交易号", title3));
			sheet.addCell(new Label(5, 2, "操作值", title3));
			sheet.addCell(new Label(6, 2, "转出前剩余金额", title3));
			sheet.addCell(new Label(7, 2, "转出后剩余金额", title3));
			sheet.addCell(new Label(8, 2, "时间", title3));
			int i = 3;
			for (GiroInfo traDetail : details) {
				sheet.addCell(new Label(0, i, traDetail.getAccountName2(), title));
				sheet.addCell(new Label(1, i, traDetail.getRealname2(), title));
				sheet.addCell(new Label(2, i, traDetail.getAccountName(), title));
				sheet.addCell(new Label(3, i, traDetail.getRealname(), title));
				sheet.addCell(new Label(4, i, traDetail.getOrderNo(), title));
				sheet.addCell(new jxl.write.Number(5, i, Double.valueOf(traDetail.getOpValue()), title));
				sheet.addCell(new jxl.write.Number(6, i, Double.valueOf(traDetail.getLeftValue()), title));
				sheet.addCell(new jxl.write.Number(7, i, Double.valueOf(traDetail.getInLeftValue() ), title));
				sheet.addCell(new Label(8, i, DateUtil.format(traDetail.getCreateTime(), ManageContext.TIME_FORMAT), title));
				i++;
			}
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("类GiroInfoAction方法tradeAccountExport执行出现异常, 原因：" + e.toString());
		}
	}

	public GiroInfo getGiroInfo() {
		return giroInfo;
	}
	public void setGiroInfo(GiroInfo giroInfo) {
		this.giroInfo = giroInfo;
	}
	public Page<GiroInfo> getPage() {
		return page;
	}
	public void setPage(Page<GiroInfo> page) {
		this.page = page;
	}
	
	
}
