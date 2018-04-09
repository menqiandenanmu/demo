package com.mall.butler.account.m.action;

import java.io.OutputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
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
import com.mall.butler.account.m.service.MConsumerCouponsService;
import com.mall.butler.account.model.AccountCoupon;
import com.mall.butler.account.model.ConsumerCoupons;
import com.mall.butler.account.model.CouponUseRecord;
import com.mall.butler.account.model.SendRules;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.common.lang.DateUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class ConsumerCouponsAction extends ManageBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ConsumerCoupons conConsumerCoupons;
	private SendRules sendRules;
	private Page<ConsumerCoupons> page;
	@Autowired
	private MConsumerCouponsService mConsumerCouponsService;

	public String execute() {
		// sendRules=mConsumerCouponsService.getEntityById(SendRules.class,conConsumerCoupons.getRuleId());
		if (null == conConsumerCoupons)
			conConsumerCoupons = new ConsumerCoupons();
		PageRequest<ConsumerCoupons> pageRequest = super
				.newPage(ConsumerCoupons.class);
		pageRequest.setFilters(conConsumerCoupons);
		pageRequest.setPageNumber(currPage);
		page = mConsumerCouponsService.pageQuery(pageRequest);
		return LIST;
	}

	/**
	 * 新增页面
	 * 
	 * @return
	 */
	public String add() {
		// 查询赠券如果有赠券则进入编辑的页面
		conConsumerCoupons = mConsumerCouponsService.getEntityById(
				ConsumerCoupons.class, id);
		if (conConsumerCoupons == null) {
			// 新增
			sendRules = mConsumerCouponsService.getEntityById(SendRules.class,
					id);
			if (null == conConsumerCoupons)
				conConsumerCoupons = new ConsumerCoupons();
			return ADD;
		} else {
			return EDIT;

		}

	}

	/**
	 * 保存操作
	 * 
	 * @return
	 */
	public String save() {
		conConsumerCoupons.setId(id);
		mConsumerCouponsService.doSave(conConsumerCoupons);
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
		conConsumerCoupons.setId(id);
		mConsumerCouponsService.doUpdate(conConsumerCoupons);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("更新成功!");
		return JDIALOG;
	}

	/**
	 * 更新操作
	 * 
	 * @return
	 */
	public String updateStatus() {
		ConsumerCoupons consumerCoupons = mConsumerCouponsService
				.getEntityById(ConsumerCoupons.class, id);
		consumerCoupons.setCouponStatus(conConsumerCoupons.getCouponStatus());
		mConsumerCouponsService.doUpdate(consumerCoupons);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("更新成功!");
		return JDIALOG;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String del() {
		conConsumerCoupons = mConsumerCouponsService.getEntityById(
				ConsumerCoupons.class, id);
		mConsumerCouponsService.doDel(conConsumerCoupons);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("更新成功!");
		return JDIALOG;
	}

	/**
	 * 编辑操作
	 * 
	 * @return
	 */
	public String edit() {
		conConsumerCoupons = mConsumerCouponsService.getEntityById(
				ConsumerCoupons.class, id);
		if (conConsumerCoupons == null) {
			throw new RuntimeException("无效的信息!");
		}
		return EDIT;
	}

	private Page<AccountCoupon> countPage;
	private CouponUseRecord couponUseRecord;
	private Date beginDate;
	private Date endDate;

	/**
	 * 赠券统计
	 * 
	 * @return
	 */
	public String couponCount() {
		if (null == couponUseRecord)
			couponUseRecord = new CouponUseRecord();
		PageRequest<Map<String, Object>> request = new PageRequest<Map<String, Object>>();
		request.setPageNumber(currPage);
		request.setPageSize(super.pageSizeParam());
		Map<String, Object> map = new HashMap<String, Object>();
		if (!TxtUtil.isEmpty(couponUseRecord.getTransNo()))
			map.put(CouponUseRecord.TRANSNO, couponUseRecord.getTransNo()
					.trim());
		if (!TxtUtil.isEmpty(couponUseRecord.getAccountName()))
			map.put(CouponUseRecord.ACCOUNTNAME, couponUseRecord
					.getAccountName().trim());
		if (null == beginDate)
			beginDate = DateUtil.getMonthFirstDay(new Date());
		if (null == endDate)
			endDate = DateUtil.getMonthLastDay(new Date());
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		request.setFilters(map);
		countPage = mConsumerCouponsService.countCoupon(request);
		return LIST;
	}

	/**
	 * 赠券管理导出
	 * 
	 * @return
	 * @throws ParseException
	 */
	public void couponExport() throws ParseException {
		if (null == couponUseRecord)
			couponUseRecord = new CouponUseRecord();
		PageRequest<Map<String, Object>> request = new PageRequest<Map<String, Object>>();
		request.setPageNumber(currPage);
		request.setPageSize(super.pageSizeParam());
		Map<String, Object> map = new HashMap<String, Object>();
		if (!TxtUtil.isEmpty(couponUseRecord.getTransNo()))
			map.put(CouponUseRecord.TRANSNO, couponUseRecord.getTransNo()
					.trim());
		if (!TxtUtil.isEmpty(couponUseRecord.getAccountName()))
			map.put(CouponUseRecord.ACCOUNTNAME, couponUseRecord
					.getAccountName().trim());
		if (null == beginDate)
			beginDate = DateUtil.getMonthFirstDay(new Date());
		if (null == endDate)
			endDate = DateUtil.getMonthLastDay(new Date());
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		List<AccountCoupon> details = mConsumerCouponsService
				.queryCountCoupon(map);
		try {
			// 写EXCEL文件
			if (response == null) {
				super.response = ServletActionContext.getResponse();
			}
			String fileName = "赠券清单"
					+ DateUtil.format(new Date(), ManageContext.DATE_FORMAT)
					+ ".xls";
			fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");// 解决文件中文名称问题
			response.setHeader("Content-Disposition", "attachment;filename="
					+ fileName);
			response.setContentType("application/x-excel");
			OutputStream out = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(out);
			WritableSheet sheet = wwb.createSheet("赠券清单", 0);
			WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 15,
					WritableFont.BOLD);
			WritableFont BoldFont1 = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.NO_BOLD);
			WritableFont BoldFont2 = new WritableFont(WritableFont.ARIAL, 14,
					WritableFont.NO_BOLD);
			WritableFont BoldFont3 = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.BOLD);
			WritableFont BoldFont4 = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.NO_BOLD);
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
				sheet.setColumnView(x, 20);
			}
			sheet.mergeCells(0, 1, 6, 1);
			sheet.mergeCells(0, 0, 6, 0);

			StringBuffer sbBuffer = new StringBuffer();
			if (!TxtUtil.isEmpty(couponUseRecord.getAccountName()))
				sbBuffer.append("账户名称  :" + couponUseRecord.getAccountName());
			if (!TxtUtil.isEmpty(couponUseRecord.getTransNo()))
				sbBuffer.append("    交易流水号  :" + couponUseRecord.getTransNo());
			sbBuffer.append("    开始时间 :"
					+ DateUtil.format(beginDate, ManageContext.DATE_FORMAT));
			sbBuffer.append("    结束时间  :"
					+ DateUtil.format(endDate, ManageContext.DATE_FORMAT));
			if (null != couponUseRecord.getCouponStatus()) {
				if (couponUseRecord.getCouponStatus() == 0)
					sbBuffer.append("    客户状态  :未消费");
				else if (couponUseRecord.getCouponStatus() == 1) {
					sbBuffer.append("    客户状态  :已消费");
				} else {
					sbBuffer.append("    客户状态  :已失效");
				}
			}
			sheet.addCell(new Label(0, 0, "赠券清单", wcf_title));
			sheet.addCell(new Label(0, 1, "  " + sbBuffer.toString(), title4));
			sheet.addCell(new Label(0, 2, "交易流水号", title3));
			sheet.addCell(new Label(1, 2, "账户名称", title3));
			sheet.addCell(new Label(2, 2, "消费金额", title3));
			sheet.addCell(new Label(3, 2, "赠券金额", title3));
			sheet.addCell(new Label(4, 2, "赠券状态", title3));
			sheet.addCell(new Label(5, 2, "用券时间", title3));
			sheet.addCell(new Label(6, 2, "赠券时间", title3));
			int i = 3;
			String accStatuString = "";
			for (AccountCoupon traDetail : details) {
				sheet.addCell(new Label(0, i, traDetail.getTransNo(), title));
				sheet
						.addCell(new Label(1, i, traDetail.getAccountName(),
								title));
				sheet.addCell(new jxl.write.Number(2, i, Double
						.valueOf(traDetail.getPaySum()), title));
				sheet.addCell(new jxl.write.Number(3, i, Double
						.valueOf(traDetail.getPrice()), title));
				if (traDetail.getCouponStatus() == 0)
					accStatuString = "未使用";
				if (traDetail.getCouponStatus() == 1)
					accStatuString = "已使用";
				if (traDetail.getCouponStatus() == 2)
					accStatuString = "已失效";
				sheet.addCell(new Label(4, i, accStatuString, title));
				sheet.addCell(new Label(5, i, DateUtil.format(traDetail
						.getPayTime(), ManageContext.TIME_FORMAT), title));
				sheet.addCell(new Label(6, i, DateUtil.format(traDetail
						.getCreateTime(), ManageContext.TIME_FORMAT), title));
				i++;
			}
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					"类consumercOUPONACTION方法couponExport执行出现异常, 原因："
							+ e.toString());
		}
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public CouponUseRecord getCouponUseRecord() {
		return couponUseRecord;
	}

	public void setCouponUseRecord(CouponUseRecord couponUseRecord) {
		this.couponUseRecord = couponUseRecord;
	}

	public ConsumerCoupons getConConsumerCoupons() {
		return conConsumerCoupons;
	}

	public void setConConsumerCoupons(ConsumerCoupons conConsumerCoupons) {
		this.conConsumerCoupons = conConsumerCoupons;
	}

	public Page<AccountCoupon> getCountPage() {
		return countPage;
	}

	public void setCountPage(Page<AccountCoupon> countPage) {
		this.countPage = countPage;
	}

	public Page<ConsumerCoupons> getPage() {
		return page;
	}

	public void setPage(Page<ConsumerCoupons> page) {
		this.page = page;
	}

	public SendRules getSendRules() {
		return sendRules;
	}

	public void setSendRules(SendRules sendRules) {
		this.sendRules = sendRules;
	}

}
