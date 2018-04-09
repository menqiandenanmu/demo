package com.mall.butler.order.m.action;

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
import com.mall.butler.order.m.service.MOrderService;
import com.mall.butler.order.model.OrderInfo;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.lang.DateUtil;

/**
 * 订单统计
 * 类描述：
 * 类名称：OrderCountAction
 * 创建人：caedmon
 * 创建时间：2013-5-28 下午09:58:28
 * 修改人：caedmon
 * 修改时间：2013-5-28 下午09:58:28
 * 修改备注：
 * 
 * @version
 */
public class OrderCountAction extends ManageBaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private MOrderService mOrderService;
	private OrderInfo order;
	private Date begCreateDate;
	private Date endCreateDate;

	/**
	 * 导出订单列表
	 */
	public void doListExpor() {
		if (order == null)
			order = new OrderInfo();
		Map<String, Object> params = new Hashtable<String, Object>();
		if (!TxtUtil.isEmpty(order.getOrderNo()))
			params.put(OrderInfo.ORDERNO, order.getOrderNo().trim());
		if (!TxtUtil.isEmpty(order.getBuyerName()))
			params.put(OrderInfo.BUYERNAME, order.getBuyerName());
		if (!TxtUtil.isEmpty(order.getLoginName()))
			params.put(OrderInfo.LOGINNAME, order.getLoginName().trim());
		if (begCreateDate != null)
			params.put("begCreateDate", begCreateDate);
		if (endCreateDate != null)
			params.put("endCreateDate", endCreateDate);
		List<OrderInfo> orders = mOrderService.getorderList(params);
		try {
			// 写EXCEL文件
			if (response == null) {
				super.response = ServletActionContext.getResponse();
			}
			String fileName = "订单列表" + DateUtil.format(new Date(), ManageContext.DATE_FORMAT)
					+ ".xls";
			fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");// 解决文件中文名称问题
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setContentType("application/x-excel");
			OutputStream out = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(out);
			WritableSheet sheet = wwb.createSheet("订单列表", 0);
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
			for (int x = 0; x < 4; x++) {
				sheet.setColumnView(x, 20);
			}
			sheet.mergeCells(0, 1, 4, 1);
			sheet.mergeCells(0, 0, 4, 0);
			String buyName;
			if (order.getBuyerName() == null) {
				buyName = "";
			} else {
				buyName = order.getBuyerName();
			}
			String loginName;
			if (order.getLoginName() == null) {
				loginName = "";
			} else {
				loginName = order.getLoginName();
			}
			String orderNo;
			if (order.getOrderNo() == null) {
				orderNo = "";
			} else {
				orderNo = order.getOrderNo();
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
			sheet.addCell(new Label(0, 0, "订单列表", wcf_title));
			sheet.addCell(new Label(0, 1, "  " + "     订单编号:" + orderNo + "操作员   :" + loginName
					+ "用户名   :" + buyName + "  订单时间：" + orderBeginDate + "--到--：" + orderEndDate,
					title4));
			sheet.addCell(new Label(0, 2, "订单号", title3));
			sheet.addCell(new Label(1, 2, "操作员", title3));
			sheet.addCell(new Label(2, 2, "购票人", title3));
			sheet.addCell(new Label(3, 2, "订单时间", title3));
			sheet.addCell(new Label(4, 2, "订单总价", title3));
			int i = 3;
			for (OrderInfo orderInfo : orders) {
				sheet.addCell(new Label(0, i, orderInfo.getOrderNo(), title));
				sheet.addCell(new Label(1, i, orderInfo.getLoginName(), title));
				sheet.addCell(new Label(2, i, orderInfo.getBuyerName(), title));
				sheet.addCell(new Label(3, i, DateUtil.format(orderInfo.getCreateTime(),
						ManageContext.TIME_FORMAT), title));
				sheet.addCell(new jxl.write.Number(4, i, Double.valueOf(orderInfo.getPaySum()
						.doubleValue()
						+ ""), title));
				i++;
			}
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("类OrderInfoAction方法dolistExport执行出现异常, 原因：" + e.toString());
		}
	}
}
