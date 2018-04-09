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
import com.mall.butler.store.model.GoodsStock;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.common.lang.DateUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 类描述：入库管理
 * 类名称：GoodsPurchaseAction
 * 创建人：caedmon
 * 创建时间：2013-5-26 下午01:08:22
 * 修改人：caedmon
 * 修改时间：2013-5-26 下午01:08:22
 * 修改备注：
 * 
 * @version
 */
public class GoodsPurchaseAction extends ManageBaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private GoodsPurchaseService goodsPurchaseService;
	private Page<GoodsPurchase> goodsPurchasePage;
	private GoodsPurchase goodsPurchase;
	private Date begCreateDate;
	private Date endCreateDate;
	private GoodsStock goodsStock;

	/**
	 * 列查询
	 */
	public String execute() {
		if (goodsPurchase == null)
			goodsPurchase = new GoodsPurchase();
		PageRequest<Map<String, Object>> request = new PageRequest<Map<String, Object>>();
		request.setPageNumber(currPage);
		Map<String, Object> params = new Hashtable<String, Object>();
		if (!TxtUtil.isEmpty(goodsPurchase.getGoodsName()))
			params.put(GoodsPurchase.GOODSNAME, "%" + goodsPurchase.getGoodsName().trim() + "%");
		if (!TxtUtil.isEmpty(goodsPurchase.getLoginName()))
			params.put(GoodsPurchase.LOGINNAME, "%" + goodsPurchase.getLoginName().trim() + "%");
		if (begCreateDate != null) {
			params.put("begCreateDate", begCreateDate);
		} else {
			begCreateDate = DateUtil.cutTimeToDate(new Date());
			params.put("begCreateDate", begCreateDate);
		}
		if (endCreateDate != null) {
			params.put("endCreateDate", endCreateDate);
		} else {
			endCreateDate = DateUtil.addDays(new Date(), 1);
			params.put("endCreateDate", endCreateDate);
		}
		request.setFilters(params);
		goodsPurchasePage = goodsPurchaseService.queryPage(request);
		return SUCCESS;
	}

	/**
	 * 明细
	 */
	public String info() {
		goodsPurchase = goodsPurchaseService.getEntityById(GoodsPurchase.class, id);
		if (goodsPurchase == null || goodsPurchase.getDeleted())
			throw new RuntimeException("信息不存在,或已被删除!");
		return INFO;
	}

	/**
	 * 添加
	 */
	public String add() {
		goodsStock = goodsPurchaseService.getEntityById(GoodsStock.class, id);
		if (goodsStock == null || goodsStock.getDeleted())
			throw new RuntimeException("信息不存在,或已被删除!");
		return ADD;
	}

	/**
	 * 保存
	 */
	public String save() {
		goodsStock = goodsPurchaseService.getEntityById(GoodsStock.class, id);
		if (goodsStock == null || goodsStock.getDeleted())
			throw new RuntimeException("信息不存在,或已被删除!");
		goodsPurchase.setStockId(goodsStock.getId());
		goodsPurchase.setLoginId(super.getLoginId());
		goodsPurchase.setLoginName(super.getLogin().getLoginName());
		goodsPurchaseService.doSave(goodsPurchase);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("入库成功!");
		return JDIALOG;
	}

	/**
	 * 入库清单导出
	 * 
	 * @return
	 */
	public void addExport() {
		if (goodsPurchase == null)
			goodsPurchase = new GoodsPurchase();
		Map<String, Object> params = new Hashtable<String, Object>();
		if (!TxtUtil.isEmpty(goodsPurchase.getGoodsName()))
			params.put(GoodsPurchase.GOODSNAME, "%" + goodsPurchase.getGoodsName().trim() + "%");
		if (!TxtUtil.isEmpty(goodsPurchase.getLoginName()))
			params.put(GoodsPurchase.LOGINNAME, "%" + goodsPurchase.getLoginName().trim() + "%");
		if (begCreateDate != null) {
			params.put("begCreateDate", begCreateDate);
		} else {
			begCreateDate = DateUtil.cutTimeToDate(new Date());
			params.put("begCreateDate", begCreateDate);
		}
		if (endCreateDate != null) {
			params.put("endCreateDate", endCreateDate);
		} else {
			endCreateDate = DateUtil.addDays(new Date(), 1);
			params.put("endCreateDate", endCreateDate);
		}
		List<GoodsPurchase> goodsPurchases = goodsPurchaseService.getList(params);
		try {
			// 写EXCEL文件
			if (response == null) {
				super.response = ServletActionContext.getResponse();
			}
			String fileName = "入库清单" + DateUtil.format(new Date(), ManageContext.DATE_FORMAT)
					+ ".xls";
			fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");// 解决文件中文名称问题
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setContentType("application/x-excel");
			OutputStream out = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(out);
			WritableSheet sheet = wwb.createSheet("入库清单", 0);
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
			for (int x = 0; x < 5; x++) {
				sheet.setColumnView(x, 20);
			}
			sheet.setColumnView(5, 40);
			sheet.mergeCells(0, 1, 5, 1);
			sheet.mergeCells(0, 0, 5, 0);

			String goodsName;
			if (goodsPurchase.getGoodsName() == null) {
				goodsName = "";
			} else {
				goodsName = goodsPurchase.getGoodsName();
			}
			String loginName;
			if (goodsPurchase.getLoginName() == null) {
				loginName = "";
			} else {
				loginName = goodsPurchase.getLoginName();
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
			sheet.addCell(new Label(0, 0, "入库清单", wcf_title));
			sheet.addCell(new Label(0, 1, "  " + "商品名称  ：" + goodsName + "   操作员：" + loginName
					+ "  时间：" + orderBeginDate + "--到--：" + orderEndDate, title4));
			sheet.addCell(new Label(0, 2, "商品名称", title3));
			sheet.addCell(new Label(1, 2, "操作员", title3));
			sheet.addCell(new Label(2, 2, "入库数量", title3));
			sheet.addCell(new Label(3, 2, "最后操作时间", title3));
			sheet.addCell(new Label(4, 2, "概要", title3));
			int i = 3;
			for (GoodsPurchase goodsPurchase : goodsPurchases) {
				sheet.addCell(new Label(0, i, goodsPurchase.getGoodsName(), title));
				sheet.addCell(new Label(1, i, goodsPurchase.getLoginName(), title));
				sheet.addCell(new jxl.write.Number(2, i, Double.valueOf(goodsPurchase.getNum()
						.doubleValue()
						+ ""), title));
				sheet.addCell(new Label(3, i, DateUtil.format(goodsPurchase.getModifiedTime(),
						ManageContext.TIME_FORMAT), title));
				sheet.addCell(new Label(4, i, goodsPurchase.getRemark(), title));
				i++;
			}

			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("类GoodsPurchaseAction方法addExport执行出现异常, 原因：" + e.toString());
		}
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

	public void setGoodsPurchasePage(Page<GoodsPurchase> goodsPurchasePage) {
		this.goodsPurchasePage = goodsPurchasePage;
	}

	public GoodsStock getGoodsStock() {
		return goodsStock;
	}

	public void setGoodsStock(GoodsStock goodsStock) {
		this.goodsStock = goodsStock;
	}

	public Page<GoodsPurchase> getGoodsPurchasePage() {
		return goodsPurchasePage;
	}

	public GoodsPurchase getGoodsPurchase() {
		return goodsPurchase;
	}

	public void setGoodsPurchase(GoodsPurchase goodsPurchase) {
		this.goodsPurchase = goodsPurchase;
	}
}
