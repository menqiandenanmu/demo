package com.mall.butler.point.m.action;

import java.io.OutputStream;
import java.text.ParseException;
import java.util.Date;
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
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.point.m.model.PointChangeDetalExtendDetail;
import com.mall.butler.point.m.service.MUserPointService;
import com.mall.butler.point.model.PointAccountInfo;
import com.mall.butler.point.model.PointChangeDetal;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.common.lang.DateUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 用户积分
 * 
 * @author zhaoxs
 * 
 */
public class UserPointAction extends ManageBaseAction {

	private static final long serialVersionUID = 1L;
	private PointAccountInfo pointAccountInfo;
	private Page<PointAccountInfo> pointPage;
	@Autowired
	private MUserPointService userPointService;
	private PointChangeDetal pointChangeDetal;
	private String pointNum;
	private Date begCreateDate;
	private Date endCreateDate;
	private Page<PointChangeDetalExtendDetail> detailPage;
	private String accName;
	private Map<String, Object> sumCount;
	private List<PointAccountInfo> detailList;
	private List<PointChangeDetalExtendDetail> pDetals;

	/**
	 * 
	 * 用户积分列表
	 */
	@SuppressWarnings("unchecked")
	public String execute() {
		if (pointAccountInfo == null)
			pointAccountInfo = new PointAccountInfo();
		PageRequest<Map> filter = super.newPage(Map.class);
		Map<String, Object> params = new java.util.Hashtable<String, Object>();
		if (!TxtUtil.isEmpty(pointAccountInfo.getAccName()))
			params.put(PointAccountInfo.ACCNAME, pointAccountInfo.getAccName().trim());
		if (pointNum != null) {
			if (pointNum.equals("0")) {
				filter.setFilters(params);
				pointPage = userPointService.userHasPoint(filter);
				return SUCCESS;
			}
			if (pointNum.equals("1")) {
				filter.setFilters(params);
				pointPage = userPointService.userNoPoint(filter);
				return SUCCESS;
			}
		}
		filter.setFilters(params);
		pointPage = userPointService.queryUserPoint(filter);
		return SUCCESS;
	}

	public String add() {
		pointAccountInfo = userPointService.getEntityById(PointAccountInfo.class, id);
		return ADD;
	}

	/**
	 * 保存赠送
	 */

	public String savePoint() {
		AccountLogin accountLogin = super.getLogin();
		pointAccountInfo = userPointService.getEntityById(PointAccountInfo.class, id);
		userPointService.doSavePoint(accountLogin, pointAccountInfo, pointChangeDetal);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("赠送成功!");
		return JDIALOG;
	}

	/**
	 * 用户积分清单
	 * 
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public String info() throws ParseException {
		if (pointChangeDetal == null) {
			pointChangeDetal = new PointChangeDetal();
			begCreateDate = DateUtil.dayFirstTime(new Date());
			endCreateDate = DateUtil.dayLastTime(new Date());
		}
		PageRequest<Map> filter = super.newPage(Map.class);
		Map<String, Object> params = new java.util.Hashtable<String, Object>();
		if (pointChangeDetal.getOpType() != null)
			params.put(PointChangeDetal.OPTYPE, pointChangeDetal.getOpType());
		if (!TxtUtil.isEmpty(pointChangeDetal.getOrderNo()))
			params.put(PointChangeDetal.ORDERNO, pointChangeDetal.getOrderNo().trim());
		if (begCreateDate != null)
			params.put("begCreateDate", begCreateDate);
		if (endCreateDate != null)
			params.put("endCreateDate", endCreateDate);
		if (accName != null)
			params.put("accName", accName.trim());
		filter.setFilters(params);
		detailPage = userPointService.queryPointDetail(filter);
		return INFO;
	}

	/**
	 * 积分日统计
	 * 
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public String count() throws ParseException {
		if (pointChangeDetal == null) {
			pointChangeDetal = new PointChangeDetal();
			begCreateDate = DateUtil.dayFirstTime(new Date());
			endCreateDate = DateUtil.dayLastTime(new Date());
		}
		PageRequest<Map> filter = super.newPage(Map.class);
		Map<String, Object> params = new java.util.Hashtable<String, Object>();
		if (pointChangeDetal.getOpType() != null)
			params.put(PointChangeDetal.OPTYPE, pointChangeDetal.getOpType());
		if (!TxtUtil.isEmpty(pointChangeDetal.getOrderNo()))
			params.put(PointChangeDetal.ORDERNO, pointChangeDetal.getOrderNo().trim());
		if (begCreateDate != null)
			params.put("begCreateDate", begCreateDate);
		if (endCreateDate != null)
			params.put("endCreateDate", endCreateDate);
		if (accName != null)
			params.put("accName", accName.trim());
		filter.setFilters(params);
		detailPage = userPointService.countPointDetail(filter);
		sumCount = userPointService.querySum(params);
		return SUCCESS;
	}

	/**
	 * 用户积分导出
	 * 
	 * @return
	 * @throws ParseException
	 */
	public void listExport() throws ParseException {
		if (pointAccountInfo == null)
			pointAccountInfo = new PointAccountInfo();
		Map<String, Object> params = new java.util.Hashtable<String, Object>();
		if (!TxtUtil.isEmpty(pointAccountInfo.getAccName()))
			params.put(PointAccountInfo.ACCNAME, pointAccountInfo.getAccName().trim());
		if (pointNum != null) {
			if (pointNum.equals("0")) {
				detailList = userPointService.hssPointAccountList(params);
			} else if (pointNum.equals("1")) {
				detailList = userPointService.noPointAccountList(params);
			}
		} else {
			detailList = userPointService.getPointAccountList(params);
		}
		sumCount = userPointService.querySum(params);
		try {
			// 写EXCEL文件
			if (response == null) {
				super.response = ServletActionContext.getResponse();
			}
			String fileName = "用户积分" + DateUtil.format(new Date(), ManageContext.DATE_FORMAT) + ".xls";
			fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");// 解决文件中文名称问题
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setContentType("application/x-excel");
			OutputStream out = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(out);
			WritableSheet sheet = wwb.createSheet("用户积分", 0);
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
				sheet.setColumnView(x, 30);
			}
			sheet.mergeCells(0, 1, 2, 1);
			sheet.mergeCells(0, 0, 2, 0);
			if (accName == null)
				accName = "";
			String pointType = null;
			if (pointNum == null) {
				pointType = "全部";
			} else if (pointNum.equals("0")) {
				pointType = "有积分";
			} else if (pointNum.equals("1")) {
				pointType = "无积分";
			}
			sheet.addCell(new Label(0, 0, "用户积分", wcf_title));
			sheet.addCell(new Label(0, 1, "  " + "用户   :" + accName + "     积分:" + pointType, title4));
			sheet.addCell(new Label(0, 2, "用户", title3));
			sheet.addCell(new Label(1, 2, "数量", title3));
			sheet.addCell(new Label(2, 2, "最后时间", title3));
			int i = 3;
			for (PointAccountInfo point : detailList) {
				sheet.addCell(new Label(0, i, point.getAccName(), title));
				sheet.addCell(new jxl.write.Number(1, i, Integer.valueOf(point.getPoint() + ""), title));
				sheet.addCell(new Label(2, i, DateUtil.format(point.getCreateTime(), ManageContext.TIME_FORMAT), title));
				i++;
			}
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("类UserPointAction方法listExport执行出现异常, 原因：" + e.toString());
		}
	}

	/**
	 * 用户积分清单导出
	 * 
	 * @throws ParseException
	 */
	public void detailExport() throws ParseException {
		if (pointChangeDetal == null) {
			pointChangeDetal = new PointChangeDetal();
			begCreateDate = DateUtil.dayFirstTime(new Date());
			endCreateDate = DateUtil.dayLastTime(new Date());
		}
		Map<String, Object> params = new java.util.Hashtable<String, Object>();
		if (pointChangeDetal.getOpType() != null)
			params.put(PointChangeDetal.OPTYPE, pointChangeDetal.getOpType());
		if (!TxtUtil.isEmpty(pointChangeDetal.getOrderNo()))
			params.put(PointChangeDetal.ORDERNO, pointChangeDetal.getOrderNo().trim());
		if (begCreateDate != null)
			params.put("begCreateDate", begCreateDate);
		if (endCreateDate != null)
			params.put("endCreateDate", endCreateDate);
		if (accName != null)
			params.put("accName", accName.trim());
		pDetals = userPointService.pointDetailList(params);
		try {
			// 写EXCEL文件
			if (response == null) {
				super.response = ServletActionContext.getResponse();
			}
			String fileName = "用户积分清单" + DateUtil.format(new Date(), ManageContext.DATE_FORMAT) + ".xls";
			fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");// 解决文件中文名称问题
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setContentType("application/x-excel");
			OutputStream out = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(out);
			WritableSheet sheet = wwb.createSheet("用户积分清单", 0);
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
			sheet.mergeCells(0, 1, 5, 1);
			sheet.mergeCells(0, 0, 5, 0);
			if (accName == null)
				accName = "";
			String opType = "全部";
			if (pointChangeDetal.getOpType() != null)
				if (pointChangeDetal.getOpType() == 0) {
					opType = "订单";
				} else if (pointChangeDetal.getOpType() == 1) {
					opType = "系统赠送";
				} else if (pointChangeDetal.getOpType() == 2) {
					opType = "兑换";
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
			sheet.addCell(new Label(0, 0, "用户积分清单", wcf_title));
			sheet.addCell(new Label(0, 1, "  " + "用户:" + accName + "     交易类型:" + opType + "   订单号:" + (pointChangeDetal.getOrderNo() == null ? "" : pointChangeDetal.getOrderNo()) + "   时间"
					+ orderBeginDate + "到--" + orderEndDate, title4));
			sheet.addCell(new Label(0, 2, "用户", title3));
			sheet.addCell(new Label(1, 2, "时间", title3));
			sheet.addCell(new Label(2, 2, "交易类型", title3));
			sheet.addCell(new Label(3, 2, "订单号", title3));
			sheet.addCell(new Label(4, 2, "数量", title3));
			sheet.addCell(new Label(5, 2, "当前数量", title3));
			int i = 3;
			for (PointChangeDetalExtendDetail pointDetail : pDetals) {
				sheet.addCell(new Label(0, i, pointDetail.getAccName(), title));
				sheet.addCell(new Label(1, i, DateUtil.format(pointDetail.getCreateTime(), ManageContext.TIME_FORMAT), title));
				String optype = "";
				if (pointDetail.getOpType() != null)
					if (pointDetail.getOpType() == 0) {
						optype = "订单";
					} else if (pointDetail.getOpType() == 1) {
						optype = "系统赠送";
					} else if (pointDetail.getOpType() == 2) {
						optype = "兑换";
					}
				sheet.addCell(new Label(2, i, optype, title));
				sheet.addCell(new Label(3, i, pointDetail.getOrderNo(), title));
				sheet.addCell(new jxl.write.Number(4, i, Integer.valueOf(pointDetail.getPoint() + ""), title));
				sheet.addCell(new jxl.write.Number(5, i, Integer.valueOf(pointDetail.getLeftPoint() + ""), title));
				i++;
			}
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("类UserPointAction方法detailExport执行出现异常, 原因：" + e.toString());
		}
	}

	/**
	 * 积分日统计导出
	 * 
	 * @throws ParseException
	 */
	public void daycountExport() throws ParseException {
		if (pointChangeDetal == null) {
			pointChangeDetal = new PointChangeDetal();
			begCreateDate = DateUtil.dayFirstTime(new Date());
			endCreateDate = DateUtil.dayLastTime(new Date());
		}
		Map<String, Object> params = new java.util.Hashtable<String, Object>();
		if (pointChangeDetal.getOpType() != null)
			params.put(PointChangeDetal.OPTYPE, pointChangeDetal.getOpType());
		if (!TxtUtil.isEmpty(pointChangeDetal.getOrderNo()))
			params.put(PointChangeDetal.ORDERNO, pointChangeDetal.getOrderNo().trim());
		if (begCreateDate != null)
			params.put("begCreateDate", begCreateDate);
		if (endCreateDate != null)
			params.put("endCreateDate", endCreateDate);
		if (accName != null)
			params.put("accName", accName.trim());
		pDetals = userPointService.pointCountList(params);
		sumCount = userPointService.querySum(params);
		try {
			// 写EXCEL文件
			if (response == null) {
				super.response = ServletActionContext.getResponse();
			}
			String fileName = "积分日统计" + DateUtil.format(new Date(), ManageContext.DATE_FORMAT) + ".xls";
			fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");// 解决文件中文名称问题
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setContentType("application/x-excel");
			OutputStream out = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(out);
			WritableSheet sheet = wwb.createSheet("积分日统计", 0);
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
				sheet.setColumnView(x, 30);
			}
			sheet.mergeCells(0, 1, 2, 1);
			sheet.mergeCells(0, 0, 2, 0);
			String opType = "全部";
			if (pointChangeDetal.getOpType() != null)
				if (pointChangeDetal.getOpType() == 0) {
					opType = "订单";
				} else if (pointChangeDetal.getOpType() == 1) {
					opType = "系统赠送";
				} else if (pointChangeDetal.getOpType() == 2) {
					opType = "兑换";
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
			sheet.addCell(new Label(0, 0, "积分日统计", wcf_title));
			sheet.addCell(new Label(0, 1, "  " + "交易类型   :" + opType + "     时间:" + orderBeginDate + "到--" + orderEndDate, title4));
			sheet.addCell(new Label(0, 2, "日期", title3));
			sheet.addCell(new Label(1, 2, "产生", title3));
			sheet.addCell(new Label(2, 2, "消费", title3));
			int i = 3;
			for (PointChangeDetalExtendDetail pointDetail : pDetals) {
				sheet.addCell(new Label(0, i, pointDetail.getTimeDate(), title));
				sheet.addCell(new jxl.write.Number(1, i, Double.valueOf(pointDetail.getAddPoint() + ""), title));
				sheet.addCell(new jxl.write.Number(2, i, Double.valueOf(pointDetail.getCutPoint() + ""), title));
				i++;
			}
			sheet.addCell(new Label(0, i, "合计", title2));
			if (sumCount.get("ADD_POINT") != null) {
				sheet.addCell(new jxl.write.Number(1, i, Double.valueOf(sumCount.get("ADD_POINT") + ""), title));
			} else {
				sheet.addCell(new jxl.write.Number(1, i, Double.valueOf(0 + ""), title));

			}
			if (sumCount.get("CUT_POINT") != null) {
				sheet.addCell(new jxl.write.Number(2, i, Double.valueOf(sumCount.get("CUT_POINT") + ""), title));
			} else {
				sheet.addCell(new jxl.write.Number(2, i, Double.valueOf(0 + ""), title));
			}
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("类UserPointAction方法daycountExport执行出现异常, 原因：" + e.toString());
		}
	}

	public PointAccountInfo getPointAccountInfo() {
		return pointAccountInfo;
	}

	public void setPointAccountInfo(PointAccountInfo pointAccountInfo) {
		this.pointAccountInfo = pointAccountInfo;
	}

	public Page<PointAccountInfo> getPointPage() {
		return pointPage;
	}

	public void setPointPage(Page<PointAccountInfo> pointPage) {
		this.pointPage = pointPage;
	}

	public PointChangeDetal getPointChangeDetal() {
		return pointChangeDetal;
	}

	public void setPointChangeDetal(PointChangeDetal pointChangeDetal) {
		this.pointChangeDetal = pointChangeDetal;
	}

	public String getPointNum() {
		return pointNum;
	}

	public void setPointNum(String pointNum) {
		this.pointNum = pointNum;
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

	public Page<PointChangeDetalExtendDetail> getDetailPage() {
		return detailPage;
	}

	public void setDetailPage(Page<PointChangeDetalExtendDetail> detailPage) {
		this.detailPage = detailPage;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public Map<String, Object> getSumCount() {
		return sumCount;
	}

	public void setSumCount(Map<String, Object> sumCount) {
		this.sumCount = sumCount;
	}

	public List<PointAccountInfo> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<PointAccountInfo> detailList) {
		this.detailList = detailList;
	}

	public List<PointChangeDetalExtendDetail> getpDetals() {
		return pDetals;
	}

	public void setpDetals(List<PointChangeDetalExtendDetail> pDetals) {
		this.pDetals = pDetals;
	}

}
