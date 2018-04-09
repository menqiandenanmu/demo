package com.mall.butler.store.m.action;

import java.io.OutputStream;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

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
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.store.m.service.GoodsPurchaseService;
import com.mall.butler.store.model.GoodsPurchase;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.lang.DateUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 入库统计
 * 类描述：
 * 类名称：PurchaseCountAction
 * 创建人：caedmon
 * 创建时间：2013-5-28 下午09:52:45
 * 修改人：caedmon
 * 修改时间：2013-5-28 下午09:52:45
 * 修改备注：
 * 
 * @version
 */
public class PurchaseCountAction extends ManageBaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private GoodsPurchaseService goodsPurchaseService;
	private GoodsPurchase goodsPurchase;
	private Page<GoodsPurchase> goodsPurchasePage;
	private Date begCreateDate;
	private Date endCreateDate;

	/**
	 * 入库统计
	 * 
	 * @return
	 */
	public String execute() {
		if (this.goodsPurchase == null)
			goodsPurchase = new GoodsPurchase();
		PageRequest<Map<String, Object>> filter = new PageRequest<Map<String, Object>>();
		filter.setPageNumber(currPage);
		Map<String, Object> params = new Hashtable<String, Object>();
		if (!TxtUtil.isEmpty(goodsPurchase.getGoodsName()))
			params.put(GoodsPurchase.GOODSNAME, "%" + goodsPurchase.getGoodsName().trim() + "%");
		Date date = new Date();
		if (begCreateDate == null)
			begCreateDate = DateUtil.getMonthFirstDay(date);
		if (endCreateDate == null)
			endCreateDate = DateUtil.getMonthLastDay(date);
		params.put("begCreateDate", begCreateDate);
		params.put("endCreateDate", endCreateDate);
		filter.setFilters(params);
		goodsPurchasePage = goodsPurchaseService.purchaseCountPage(filter);
		return SUCCESS;
	}

	/**
	 * 入库统计导出
	 * 
	 * @return
	 */
	public void dayInExport() {
		if (this.goodsPurchase == null)
			goodsPurchase = new GoodsPurchase();
		Map<String, Object> params = new Hashtable<String, Object>();
		if (!TxtUtil.isEmpty(goodsPurchase.getGoodsName()))
			params.put(GoodsPurchase.GOODSNAME, "%" + goodsPurchase.getGoodsName().trim() + "%");
		Date date = new Date();
		if (begCreateDate == null)
			begCreateDate = DateUtil.getMonthFirstDay(date);
		if (endCreateDate == null)
			endCreateDate = DateUtil.getMonthLastDay(date);
		params.put("begCreateDate", begCreateDate);
		params.put("endCreateDate", endCreateDate);
		List<GoodsPurchase> goodsPurchases = goodsPurchaseService.getCountList(params);
		try {
			// 写EXCEL文件
			if (response == null) {
				super.response = ServletActionContext.getResponse();
			}
			String fileName = "入库统计" + DateUtil.format(new Date(), ManageContext.DATE_FORMAT)
					+ ".xls";
			fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");// 解决文件中文名称问题
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setContentType("application/x-excel");
			OutputStream out = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(out);
			WritableSheet sheet = wwb.createSheet("入库统计", 0);
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
			for (int x = 0; x < 2; x++) {
				sheet.setColumnView(x, 30);
			}
			sheet.mergeCells(0, 1, 2, 1);
			sheet.mergeCells(0, 0, 2, 0);

			String goodsName;
			if (goodsPurchase.getGoodsName() == null) {
				goodsName = "";
			} else {
				goodsName = goodsPurchase.getGoodsName();
			}
			String orderBeginDate;
			String orderEndDate;
			if (begCreateDate == null) {
				orderBeginDate = "空";
			} else {
				orderBeginDate = DateUtil.format(begCreateDate, ManageContext.TIME_FORMAT);
			}
			if (endCreateDate == null) {
				orderEndDate = "空";
			} else {
				orderEndDate = DateUtil.format(endCreateDate, ManageContext.TIME_FORMAT);
			}
			sheet.addCell(new Label(0, 0, "入库统计", wcf_title));
			sheet.addCell(new Label(0, 1, "  " + "商品名称  ：" + goodsName + "  时间：" + orderBeginDate
					+ "--到--：" + orderEndDate, title4));
			sheet.addCell(new Label(0, 2, "商品名称", title3));
			sheet.addCell(new Label(1, 2, "数量", title3));
			int i = 2;
			for (GoodsPurchase goodsPurchase : goodsPurchases) {
				sheet.addCell(new Label(1, i, goodsPurchase.getGoodsName(), title));
				sheet.addCell(new jxl.write.Number(1, i, Double.valueOf(goodsPurchase.getNum()
						.doubleValue()
						+ ""), title));
				i++;
			}
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("类goodsPurchaseCoountAction方法dayInExport执行出现异常, 原因："
					+ e.toString());
		}
	}

	public GoodsPurchase getGoodsPurchase() {
		return goodsPurchase;
	}

	public void setGoodsPurchase(GoodsPurchase goodsPurchase) {
		this.goodsPurchase = goodsPurchase;
	}

	public Page<GoodsPurchase> getGoodsPurchasePage() {
		return goodsPurchasePage;
	}

	public void setGoodsPurchasePage(Page<GoodsPurchase> goodsPurchasePage) {
		this.goodsPurchasePage = goodsPurchasePage;
	}

	public Date getBegCreateDate() {
		return begCreateDate;
	}

	public void setBegCreateDate(Date begCreateDate) {
		this.begCreateDate = begCreateDate;
	}

	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

}
