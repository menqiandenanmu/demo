package com.mall.butler.account.m.action;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
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
import com.mall.butler.account.m.model.AgentExtendInfo;
import com.mall.butler.account.m.model.TradeAccountDetailVo;
import com.mall.butler.account.m.model.TradeAccountVo;
import com.mall.butler.account.m.model.TradeOrderDetailExtend;
import com.mall.butler.account.m.service.MAccountCouponService;
import com.mall.butler.account.m.service.MAgentAccountService;
import com.mall.butler.account.m.service.MAgentService;
import com.mall.butler.account.model.AgentInfo;
import com.mall.butler.account.model.CouponUseRecord;
import com.mall.butler.account.model.TradeAccount;
import com.mall.butler.account.model.TradeAccountDetail;
import com.mall.butler.account.model.TradeAccountInfo;
import com.mall.butler.account.model.TradeOrder;
import com.mall.butler.account.service.TradeAccountService;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.port.xml.OrderXml;
import com.mall.butler.service.ApplicationLogService;
import com.mall.butler.sys.m.service.MDictService;
import com.mall.butler.sys.model.SysDictDetail;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.common.lang.DateUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class MAgentAccountAction extends ManageBaseAction {

	private static final long serialVersionUID = 3821073599063977700L;

	private final static String ARTIFICIALCHARGE = "artificial";// 返回充值页面
	private final static String WARNLINEMODIFY = "warnLeftValue";// 返回充值页面
	@Autowired
	private MAgentAccountService mAgentAccountService;
	private TradeAccount tradeAccount;
	private TradeAccountDetail tradeAccountDetail;
	private Double opValue;// 充值金额
	private Double signAmount;// 签单金额
	private Integer rechargeType;// 充值方式 0现金1转账 2其它 3代表人工充值
	private String remark;// 备注
	private TradeAccountDetailVo tradeVo;
	@Autowired
	private TradeAccountService tradeAccountService;
	@Autowired
	private MAgentService mAgentService;
	private Page<TradeAccountVo> pages;// 交易账户查询分页数据
	private Page<TradeAccountDetail> accountPages;// 账户查询分页数据
	private Page<TradeAccountDetailVo> accountCountPages;// 交易账户统计分页数据
	private Page<TradeOrderDetailExtend> accountDetailPages;// 交易账户统计分页数据
	private List<TradeAccountInfo> accountList;
	private List<AgentInfo> agentList;
	private TradeOrder order;
	private TradeOrderDetailExtend tradeOrderDetailExtend;
	@Autowired
	private ApplicationLogService applicationLogService;
	private TradeAccountVo tradeAccountVo;
	private AgentInfo agentInfo;
	private List<SysDictDetail> dictDetails;
	@Autowired
	private MDictService dictService;
	private Double totalSum = 0.00;

	private MAccountCouponService accountCouponService;

	public String execute() {
		if (tradeAccountVo == null) {
			tradeAccountVo = new TradeAccountVo();
		}
		PageRequest<TradeAccountVo> filter = this.newPage(TradeAccountVo.class);
		TradeAccountVo trade = new TradeAccountVo();
		Map<String, Object> map = new HashMap<String, Object>();
		if (!TxtUtil.isEmpty(tradeAccountVo.getCorpName())) {
			trade.setCorpName("%" + tradeAccountVo.getCorpName().trim() + "%");
			map.put("corpName", "%" + tradeAccountVo.getCorpName().trim() + "%");
		}
		if (!TxtUtil.isEmpty(tradeAccountVo.getAccountName())) {
			trade.setAccountName("%" + tradeAccountVo.getAccountName().trim() + "%");
			map.put(TradeAccountVo.ACCOUNTNAME, "%" + tradeAccountVo.getAccountName().trim() + "%");
		}
		if (!TxtUtil.isEmpty(tradeAccountVo.getTradeAccName())) {
			trade.setTradeAccName("%" + tradeAccountVo.getTradeAccName().trim() + "%");
			map.put(TradeAccountVo.TRADEACCNAME, "%" + tradeAccountVo.getTradeAccName().trim() + "%");
		}
		if (null != tradeAccountVo.getStatus()) {
			trade.setStatus(tradeAccountVo.getStatus());
			map.put(TradeAccountVo.STATUS, tradeAccountVo.getStatus());
		}
		filter.setFilters(trade);
		filter.setPageNumber(this.currPage);
		pages = mAgentAccountService.queryPageAgentAccount(filter);
		dictDetails = dictService.queryDetail(ManageContext.DICT_STATIC_CLASS);

		// List<TradeAccountVo>
		// details=mAgentAccountService.findTradeAccountList(map);
		for (TradeAccountVo vo : pages.getResult()) {
			totalSum += vo.getLeftValue();
		}
		// for(TradeAccountVo vo:details){
		// totalSum+=vo.getLeftValue();
		// }
		return SUCCESS;
	}

	/**
	 * 新增用户交易账户
	 * 
	 * @return
	 */
	public String add() {
		agentList = mAgentService.queryList(new AgentExtendInfo());
		accountList = tradeAccountService.findAllAccount();
		return ADD;
	}

	/**
	 * 新增用户子账户
	 * 
	 * @return
	 */
	public String addSubAcc() {
		tradeAccount = tradeAccountService.getEntityById(TradeAccount.class, id);
		accountList = tradeAccountService.findAllAccount();
		return ADD;
	}

	/**
	 * @auth Mountain 保存新增账户的信息
	 */
	public String doAdd() {
		if (null == tradeAccount.getTradeAccId()) {
			throw new RuntimeException("请选择账户类型");
		}
		mAgentAccountService.doAddSubAcc(id, tradeAccount.getTradeAccId());
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("添加成功!");
		return JDIALOG;
	}

	/**
	 * @author Mountain 返回人工充值页面
	 * @return
	 */
	public String artificialCharge() {
		tradeAccount = tradeAccountService.getEntityById(TradeAccount.class, id);
		if (null == tradeAccount)
			throw new RuntimeException("用户账户信息出错，请刷新!");
		agentInfo = tradeAccountService.getEntityById(AgentInfo.class, id);
		if (null == agentInfo)
			throw new RuntimeException("用户账户信息出错，请刷新!");
		return ARTIFICIALCHARGE;
	}

	/**
	 * @author Mountain 人工充值ING
	 * @return
	 */
	public String doArtificialCharge() {
		// 查询账户信息，填充今对象当中，更新账户数值
		tradeAccount = new TradeAccount();
		tradeAccount.setId(id);
		TradeAccountDetail detail = new TradeAccountDetail();
		detail.setRemark(tradeAccountDetail.getRemark());
		detail.setOpType(5);// 后台充值
		detail.setOpValue(opValue);
		detail.setOpLoginId(getLoginId());
		detail.setOpLoginName(getLogin().getLoginName());
		if (order == null) {
			order = new TradeOrder();
		}
		mAgentAccountService.saveTradeAccountCharge(tradeAccount, detail, order);
		tradeAccount = mAgentAccountService.getEntityById(TradeAccount.class, id);
		if (null == tradeAccount)
			throw new RuntimeException("用户账户信息出错，请刷新!");
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("为账户 " + tradeAccount.getAccountName() + " 充值金额" + opValue + "成功!");
		return JDIALOG;
	}

	/**
	 * 充值
	 * 
	 * @return
	 */
	public String recharge() {
		if (tradeAccountDetail == null) {
			tradeAccountDetail = new TradeAccountDetail();
			tradeAccountDetail.setBegDate(DateUtil.getMonthFirstDay(new Date()));
			tradeAccountDetail.setEndDate(DateUtil.getMonthLastDay(new Date()));
		}
		PageRequest<Map<String, Object>> filter = new PageRequest<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begDate", tradeAccountDetail.getBegDate());
		map.put("endDate", tradeAccountDetail.getEndDate());
		if (!TxtUtil.isEmpty(tradeAccountDetail.getAccountName()))
			map.put(TradeAccountDetail.ACCOUNTNAME, tradeAccountDetail.getAccountName());
		if (!TxtUtil.isEmpty(tradeAccountDetail.getOpLoginName()))
			map.put(TradeAccountDetail.OPLOGINNAME, tradeAccountDetail.getOpLoginName());
		if (!TxtUtil.isEmpty(tradeAccountDetail.getRemark1()))
			map.put("remark1", tradeAccountDetail.getRemark1());
		if (!TxtUtil.isEmpty(tradeAccountDetail.getSerialNo()))
			map.put("serialNo", tradeAccountDetail.getSerialNo());
		if (null == tradeAccountDetail.getOpType()) {
			map.put(TradeAccountDetail.OPTYPE, 0);
		} else {
			map.put(TradeAccountDetail.OPTYPE, tradeAccountDetail.getOpType());
		}
		filter.setFilters(map);
		filter.setPageSize(pageSizeParam());
		filter.setPageNumber(this.currPage);
		accountPages = mAgentAccountService.findTradeDetailCount(filter);

		return SUCCESS;
	}

	/**
	 * 充值记录导出
	 * 
	 * @return
	 * @throws ParseException
	 */
	public void rechargeExport() throws ParseException {
		if (tradeAccountDetail == null) {
			tradeAccountDetail = new TradeAccountDetail();
			tradeAccountDetail.setBegDate(DateUtil.getMonthFirstDay(new Date()));
			tradeAccountDetail.setEndDate(DateUtil.getMonthLastDay(new Date()));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (!TxtUtil.isEmpty(tradeAccountDetail.getAccountName()))
			map.put(TradeAccountDetail.ACCOUNTNAME, tradeAccountDetail.getAccountName());
		if (!TxtUtil.isEmpty(tradeAccountDetail.getRemark1()))
			map.put("remark1", tradeAccountDetail.getRemark1());
		if (!TxtUtil.isEmpty(tradeAccountDetail.getOpLoginName()))
			map.put(TradeAccountDetail.OPLOGINNAME, tradeAccountDetail.getOpLoginName());
		if (null != tradeAccountDetail.getBegDate())
			map.put("begDate", tradeAccountDetail.getBegDate());
		if (null != tradeAccountDetail.getEndDate())
			map.put("endDate", tradeAccountDetail.getEndDate());
		if (null == tradeAccountDetail.getOpType()) {
			map.put(TradeAccountDetail.OPTYPE, 0);
		} else {
			map.put(TradeAccountDetail.OPTYPE, tradeAccountDetail.getOpType());
		}
		List<TradeAccountDetail> details = mAgentAccountService.findTradeDetailList(map);
		try {
			// 写EXCEL文件
			if (response == null) {
				super.response = ServletActionContext.getResponse();
			}
			String fileName = "充值记录" + DateUtil.format(new Date(), ManageContext.DATE_FORMAT) + ".xls";
			fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");// 解决文件中文名称问题
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setContentType("application/x-excel");
			OutputStream out = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(out);
			WritableSheet sheet = wwb.createSheet("充值记录", 0);
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
				sheet.setColumnView(x, 20);
			}
			sheet.mergeCells(0, 1, 6, 1);
			sheet.mergeCells(0, 0, 6, 0);
			StringBuffer sbBuffer = new StringBuffer();
			if (!TxtUtil.isEmpty(tradeAccountDetail.getAccountName()))
				sbBuffer.append("账户名称  :" + tradeAccountDetail.getAccountName());
			if (!TxtUtil.isEmpty(tradeAccountDetail.getRemark1()))
				sbBuffer.append("     交易号  :" + tradeAccountDetail.getRemark1());
			if (!TxtUtil.isEmpty(tradeAccountDetail.getOpLoginName()))
				sbBuffer.append("     操作员  :" + tradeAccountDetail.getOpLoginName());
			if (null != tradeAccountDetail.getBegDate())
				sbBuffer.append("     时间  :"
						+ DateUtil.format(tradeAccountDetail.getBegDate(), ManageContext.DATE_FORMAT));
			if (null != tradeAccountDetail.getEndDate())
				sbBuffer.append("至  :"
						+ DateUtil.format(tradeAccountDetail.getEndDate(), ManageContext.DATE_FORMAT));
			sheet.addCell(new Label(0, 0, "充值记录", wcf_title));
			sheet.addCell(new Label(0, 1, "  " + sbBuffer.toString(), title4));
			sheet.addCell(new Label(0, 2, "账户名称", title3));
			sheet.addCell(new Label(1, 2, "交易号", title3));
			sheet.addCell(new Label(2, 2, "操作值", title3));
			sheet.addCell(new Label(3, 2, "当前剩余金额", title3));
			sheet.addCell(new Label(4, 2, "操作员", title3));
			sheet.addCell(new Label(5, 2, "操作时间", title3));
			sheet.addCell(new Label(6, 2, "充值类型", title3));
			int i = 3;
			for (TradeAccountDetail traDetail : details) {
				sheet.addCell(new Label(0, i, traDetail.getAccountName(), title));
				sheet.addCell(new Label(1, i, traDetail.getRemark1(), title));
				sheet.addCell(new jxl.write.Number(2, i, Double.valueOf(traDetail.getOpValue() + ""), title));
				sheet
						.addCell(new jxl.write.Number(3, i, Double.valueOf(traDetail.getLeftValue() + ""),
								title));
				sheet.addCell(new Label(4, i, traDetail.getOpLoginName(), title));
				sheet.addCell(new Label(5, i, DateUtil.format(traDetail.getCreateTime(),
						ManageContext.TIME_FORMAT), title));
				String opString = "";
				if (traDetail.getOpType().intValue() == 0) {
					opString = "在线充值";
				} else if (traDetail.getOpType().intValue() == 3) {
					opString = "充值卡充值";
				} else if (traDetail.getOpType().intValue() == 4) {
					opString = "电子券充值";
				} else if (traDetail.getOpType().intValue() == 5) {
					opString = "后台充值";
				}
				sheet.addCell(new Label(6, i, opString, title));
				i++;
			}
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("类MAgentAccountAction方法tradeAccountExport执行出现异常, 原因：" + e.toString());
		}
	}

	/**
	 * 消费记录
	 * 
	 * @return
	 */
	public String purchase() {
		if (tradeAccountDetail == null) {
			tradeAccountDetail = new TradeAccountDetail();
			tradeAccountDetail.setBegDate(DateUtil.getMonthFirstDay(new Date()));
			tradeAccountDetail.setEndDate(DateUtil.getMonthLastDay(new Date()));
		}
		PageRequest<Map<String, Object>> filter = new PageRequest<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begDate", tradeAccountDetail.getBegDate());
		map.put("endDate", tradeAccountDetail.getEndDate());
		if (!TxtUtil.isEmpty(tradeAccountDetail.getAccountName()))
			map.put(TradeAccountDetail.ACCOUNTNAME, tradeAccountDetail.getAccountName());
		if (!TxtUtil.isEmpty(tradeAccountDetail.getOpLoginName()))
			map.put(TradeAccountDetail.OPLOGINNAME, tradeAccountDetail.getOpLoginName());
		if (!TxtUtil.isEmpty(tradeAccountDetail.getRemark1()))
			map.put("remark1", tradeAccountDetail.getRemark1());
		if (!TxtUtil.isEmpty(tradeAccountDetail.getRemark2()))
			map.put("remark2", tradeAccountDetail.getRemark2());
		if (!TxtUtil.isEmpty(tradeAccountDetail.getSerialNo()))
			map.put("serialNo", tradeAccountDetail.getSerialNo());
		// if(null==tradeAccountDetail.getOpType()){
		map.put(TradeAccountDetail.OPTYPE, 1);
		// }else {
		// map.put(TradeAccountDetail.OPTYPE, tradeAccountDetail.getOpType());
		// }
		filter.setFilters(map);
		filter.setPageNumber(this.currPage);
		filter.setPageSize(pageSizeParam());
		accountPages = mAgentAccountService.findTradeDetailCount(filter);
		return SUCCESS;
	}

	/**
	 * 消费记录导出
	 * 
	 * @return
	 * @throws ParseException
	 */
	public void purchaseExport() throws ParseException {
		if (tradeAccountDetail == null) {
			tradeAccountDetail = new TradeAccountDetail();
			tradeAccountDetail.setBegDate(DateUtil.getMonthFirstDay(new Date()));
			tradeAccountDetail.setEndDate(DateUtil.getMonthLastDay(new Date()));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (!TxtUtil.isEmpty(tradeAccountDetail.getAccountName()))
			map.put(TradeAccountDetail.ACCOUNTNAME, tradeAccountDetail.getAccountName());
		if (!TxtUtil.isEmpty(tradeAccountDetail.getRemark1()))
			map.put("remark1", tradeAccountDetail.getRemark1());
		if (!TxtUtil.isEmpty(tradeAccountDetail.getOpLoginName()))
			map.put(TradeAccountDetail.OPLOGINNAME, tradeAccountDetail.getOpLoginName());
		if (null != tradeAccountDetail.getBegDate())
			map.put("begDate", tradeAccountDetail.getBegDate());
		if (null != tradeAccountDetail.getEndDate())
			map.put("endDate", tradeAccountDetail.getEndDate());
		if (null == tradeAccountDetail.getOpType()) {
			map.put(TradeAccountDetail.OPTYPE, 1);
		} else {
			map.put(TradeAccountDetail.OPTYPE, tradeAccountDetail.getOpType());
		}
		if (!TxtUtil.isEmpty(tradeAccountDetail.getRemark2()))
			map.put("remark2", tradeAccountDetail.getRemark2());
		if (!TxtUtil.isEmpty(tradeAccountDetail.getSerialNo()))
			map.put("serialNo", tradeAccountDetail.getSerialNo());
		List<TradeAccountDetail> details = mAgentAccountService.findTradeDetailList(map);
		try {
			// 写EXCEL文件
			if (response == null) {
				super.response = ServletActionContext.getResponse();
			}
			String fileName = "消费记录" + DateUtil.format(new Date(), ManageContext.DATE_FORMAT) + ".xls";
			fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");// 解决文件中文名称问题
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setContentType("application/x-excel");
			OutputStream out = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(out);
			WritableSheet sheet = wwb.createSheet("消费记录", 0);
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
			for (int x = 0; x < 8; x++) {
				sheet.setColumnView(x, 20);
			}
			sheet.mergeCells(0, 1, 7, 1);
			sheet.mergeCells(0, 0, 7, 0);
			StringBuffer sbBuffer = new StringBuffer();
			if (!TxtUtil.isEmpty(tradeAccountDetail.getAccountName()))
				sbBuffer.append("账户名称  :" + tradeAccountDetail.getAccountName());
			if (!TxtUtil.isEmpty(tradeAccountDetail.getRemark1()))
				sbBuffer.append("     交易号  :" + tradeAccountDetail.getRemark1());
			if (!TxtUtil.isEmpty(tradeAccountDetail.getOpLoginName()))
				sbBuffer.append("     操作员  :" + tradeAccountDetail.getOpLoginName());
			if (null != tradeAccountDetail.getBegDate())
				sbBuffer.append("     时间  :"
						+ DateUtil.format(tradeAccountDetail.getBegDate(), ManageContext.DATE_FORMAT));
			if (null != tradeAccountDetail.getEndDate())
				sbBuffer.append("至  :"
						+ DateUtil.format(tradeAccountDetail.getEndDate(), ManageContext.DATE_FORMAT));
			sheet.addCell(new Label(0, 0, "消费记录", wcf_title));
			sheet.addCell(new Label(0, 1, "  " + sbBuffer.toString(), title4));
			sheet.addCell(new Label(0, 2, "商户名称", title3));
			sheet.addCell(new Label(1, 2, "账户名称", title3));
			sheet.addCell(new Label(2, 2, "交易号", title3));
			sheet.addCell(new Label(3, 2, "操作值", title3));
			sheet.addCell(new Label(4, 2, "当前剩余金额", title3));
			sheet.addCell(new Label(5, 2, "优惠券金额", title3));
			sheet.addCell(new Label(6, 2, "操作员", title3));
			sheet.addCell(new Label(7, 2, "操作时间", title3));
			int i = 3;
			for (TradeAccountDetail traDetail : details) {
				sheet.addCell(new Label(0, i, traDetail.getRemark2(), title));
				sheet.addCell(new Label(1, i, traDetail.getAccountName(), title));
				sheet.addCell(new Label(2, i, traDetail.getRemark1(), title));
				sheet.addCell(new jxl.write.Number(3, i, Double.valueOf(traDetail.getOpValue() + ""), title));
				sheet
						.addCell(new jxl.write.Number(4, i, Double.valueOf(traDetail.getLeftValue() + ""),
								title));
				sheet.addCell(new jxl.write.Number(5, i, Double.valueOf(traDetail.getTradeAccId() + ""),
						title));
				sheet.addCell(new Label(6, i, traDetail.getOpLoginName(), title));
				sheet.addCell(new Label(7, i, DateUtil.format(traDetail.getCreateTime(),
						ManageContext.TIME_FORMAT), title));
				i++;
			}
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("类MAgentAccountAction方法tradeAccountExport执行出现异常, 原因：" + e.toString());
		}
	}

	/**
	 * 单边帐退款
	 * 
	 * @return
	 */
	@SuppressWarnings( { "null", "unused" })
	public String singleRefund() {
		try {
			tradeAccountDetail = tradeAccountService.getEntityById(TradeAccountDetail.class, id);
			// 是否退款
			TradeAccountDetail reback = new TradeAccountDetail();
			reback.setOpType(2);
			reback.setRemark1(tradeAccountDetail.getOrderNo());
			List<TradeAccountDetail> rebackList = tradeAccountService.findReback(reback);
			if (rebackList.size() > 0) {
				this.msgInfo.setFlag(MessageDialog.ERROR);
				this.msgInfo.addMessage("退款交易已经存在！");
			} else {
				// 根据当前消费记录中的交易号查询券使用记录得到该次消费总额（钱包消费+券抵扣）
				CouponUseRecord filter = new CouponUseRecord();
				filter.setTransNo(tradeAccountDetail.getOrderNo());
				List<CouponUseRecord> couponUseRecords = accountCouponService.findByTransNo(filter);
				BigDecimal refundNum = new BigDecimal("0");
				// 获取该次钱包消费额
				BigDecimal refundNumMoney = BigDecimal.valueOf(tradeAccountDetail.getOpValue());
				if (couponUseRecords.size() > 0) {
					// 得到券+钱包消费总额
					refundNum = refundNumMoney.add(BigDecimal.valueOf(couponUseRecords.get(0).getPrice()));
				} else {
					// 没有券抵扣
					refundNum = refundNumMoney;
				}
				OrderXml orderXml = new OrderXml();
				String transNO = tradeAccountDetail.getOrderNo();
				orderXml.setTransNo(transNO);
				TradeAccountDetail trade = tradeAccountService.crmRefund(orderXml, refundNum.doubleValue());
				if (trade != null) {
					this.msgInfo.setFlag(MessageDialog.SUCCESS);
					this.msgInfo.addMessage("交易号:" + tradeAccountDetail.getOrderNo() + " 退款成功！");
				} else {
					this.msgInfo.setFlag(MessageDialog.ERROR);
					this.msgInfo.addMessage("交易号:" + tradeAccountDetail.getOrderNo() + "退款失败！");
				}
			}
		} catch (Exception e) {
			this.msgInfo.setFlag(MessageDialog.ERROR);
			this.msgInfo.addMessage("类MAgentAccountAction方法tradeAccountExport执行出现异常, 原因：" + e.toString());
		}
		return JDIALOG;
	}

	/**
	 * 退款记录
	 * 
	 * @return
	 */
	public String reback() {
		if (tradeAccountDetail == null) {
			tradeAccountDetail = new TradeAccountDetail();
			tradeAccountDetail.setBegDate(DateUtil.getMonthFirstDay(new Date()));
			tradeAccountDetail.setEndDate(DateUtil.getMonthLastDay(new Date()));
		}
		PageRequest<Map<String, Object>> filter = new PageRequest<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begDate", tradeAccountDetail.getBegDate());
		map.put("endDate", tradeAccountDetail.getEndDate());
		if (!TxtUtil.isEmpty(tradeAccountDetail.getAccountName()))
			map.put(TradeAccountDetail.ACCOUNTNAME, tradeAccountDetail.getAccountName());
		if (!TxtUtil.isEmpty(tradeAccountDetail.getOpLoginName()))
			map.put(TradeAccountDetail.OPLOGINNAME, tradeAccountDetail.getOpLoginName());
		if (!TxtUtil.isEmpty(tradeAccountDetail.getRemark1()))
			map.put("remark1", tradeAccountDetail.getRemark1());
		if (!TxtUtil.isEmpty(tradeAccountDetail.getRemark2()))
			map.put("remark2", tradeAccountDetail.getRemark2());
		if (!TxtUtil.isEmpty(tradeAccountDetail.getSerialNo()))
			map.put("serialNo", tradeAccountDetail.getSerialNo());
		if (null == tradeAccountDetail.getOpType()) {
			map.put(TradeAccountDetail.OPTYPE, 2);
		} else {
			map.put(TradeAccountDetail.OPTYPE, tradeAccountDetail.getOpType());
		}
		filter.setFilters(map);
		filter.setPageSize(pageSizeParam());
		filter.setPageNumber(this.currPage);
		accountPages = mAgentAccountService.findTradeDetailCount(filter);

		return SUCCESS;
	}

	/**
	 * 退款记录导出
	 * 
	 * @return
	 * @throws ParseException
	 */
	public void backExport() throws ParseException {
		if (tradeAccountDetail == null) {
			tradeAccountDetail = new TradeAccountDetail();
			tradeAccountDetail.setBegDate(DateUtil.getMonthFirstDay(new Date()));
			tradeAccountDetail.setEndDate(DateUtil.getMonthLastDay(new Date()));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (!TxtUtil.isEmpty(tradeAccountDetail.getAccountName()))
			map.put(TradeAccountDetail.ACCOUNTNAME, tradeAccountDetail.getAccountName());
		if (!TxtUtil.isEmpty(tradeAccountDetail.getRemark1()))
			map.put("remark1", tradeAccountDetail.getRemark1());
		if (!TxtUtil.isEmpty(tradeAccountDetail.getOpLoginName()))
			map.put(TradeAccountDetail.OPLOGINNAME, tradeAccountDetail.getOpLoginName());
		if (null != tradeAccountDetail.getBegDate())
			map.put("begDate", tradeAccountDetail.getBegDate());
		if (null != tradeAccountDetail.getEndDate())
			map.put("endDate", tradeAccountDetail.getEndDate());
		if (!TxtUtil.isEmpty(tradeAccountDetail.getRemark2()))
			map.put("remark2", tradeAccountDetail.getRemark2());
		if (!TxtUtil.isEmpty(tradeAccountDetail.getSerialNo()))
			map.put("serialNo", tradeAccountDetail.getSerialNo());
		map.put(TradeAccountDetail.OPTYPE, 2);
		List<TradeAccountDetail> details = mAgentAccountService.findTradeDetailList(map);
		try {
			// 写EXCEL文件
			if (response == null) {
				super.response = ServletActionContext.getResponse();
			}
			String fileName = "退款记录" + DateUtil.format(new Date(), ManageContext.DATE_FORMAT) + ".xls";
			fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");// 解决文件中文名称问题
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setContentType("application/x-excel");
			OutputStream out = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(out);
			WritableSheet sheet = wwb.createSheet("退款记录", 0);
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
			for (int x = 0; x < 8; x++) {
				sheet.setColumnView(x, 20);
			}
			sheet.mergeCells(0, 1, 7, 1);
			sheet.mergeCells(0, 0, 7, 0);
			StringBuffer sbBuffer = new StringBuffer();
			if (!TxtUtil.isEmpty(tradeAccountDetail.getAccountName()))
				sbBuffer.append("账户名称  :" + tradeAccountDetail.getAccountName());
			if (!TxtUtil.isEmpty(tradeAccountDetail.getRemark1()))
				sbBuffer.append("     交易号  :" + tradeAccountDetail.getRemark1());
			if (!TxtUtil.isEmpty(tradeAccountDetail.getOpLoginName()))
				sbBuffer.append("     操作员  :" + tradeAccountDetail.getOpLoginName());
			if (null != tradeAccountDetail.getBegDate())
				sbBuffer.append("     时间  :"
						+ DateUtil.format(tradeAccountDetail.getBegDate(), ManageContext.DATE_FORMAT));
			if (null != tradeAccountDetail.getEndDate())
				sbBuffer.append("至  :"
						+ DateUtil.format(tradeAccountDetail.getEndDate(), ManageContext.DATE_FORMAT));
			sheet.addCell(new Label(0, 0, "退款记录", wcf_title));
			sheet.addCell(new Label(0, 1, "  " + sbBuffer.toString(), title4));
			sheet.addCell(new Label(0, 2, "商户名称", title3));
			sheet.addCell(new Label(1, 2, "账户名称", title3));
			sheet.addCell(new Label(2, 2, "交易号", title3));
			sheet.addCell(new Label(3, 2, "操作值", title3));
			sheet.addCell(new Label(4, 2, "当前剩余金额", title3));
			sheet.addCell(new Label(5, 2, "优惠券金额", title3));
			sheet.addCell(new Label(6, 2, "操作员", title3));
			sheet.addCell(new Label(7, 2, "操作时间", title3));
			int i = 3;
			for (TradeAccountDetail traDetail : details) {
				sheet.addCell(new Label(0, i, traDetail.getRemark1(), title));
				sheet.addCell(new Label(1, i, traDetail.getAccountName(), title));
				sheet.addCell(new Label(2, i, traDetail.getRemark1(), title));
				sheet.addCell(new jxl.write.Number(3, i, Double.valueOf(traDetail.getOpValue() + ""), title));
				sheet
						.addCell(new jxl.write.Number(4, i, Double.valueOf(traDetail.getLeftValue() + ""),
								title));
				sheet.addCell(new jxl.write.Number(5, i, Double.valueOf(traDetail.getTradeAccId() + ""),
						title));
				sheet.addCell(new Label(6, i, traDetail.getOpLoginName(), title));
				sheet.addCell(new Label(7, i, DateUtil.format(traDetail.getCreateTime(),
						ManageContext.TIME_FORMAT), title));
				i++;
			}
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("类MAgentAccountAction方法tradeAccountExport执行出现异常, 原因：" + e.toString());
		}
	}

	/**
	 * 
	 * 信用额度设置
	 * 
	 * @return
	 */
	public String modifyWarnLeftValue() {
		tradeAccount = tradeAccountService.getEntityById(TradeAccount.class, id);
		if (null == tradeAccount)
			throw new RuntimeException("用户账户信息出错，请刷新!");
		return WARNLINEMODIFY;
	}

	/**
	 * 
	 * 信用额度设置ING
	 * 
	 * @return
	 */
	public String doModifyWarnLeftValue() {
		tradeAccount = mAgentAccountService.getEntityById(TradeAccount.class, id);
		if (null == tradeAccount)
			throw new RuntimeException("用户账户信息出错，请刷新!");
		mAgentAccountService.updateWarnLine(id, opValue);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("为账户 " + tradeAccount.getAccountName() + " 修改信用额度为" + opValue + "成功!");
		return JDIALOG;
	}

	/**
	 * 设置账户是否生效
	 */
	public String useFlagModify() {
		// 通过id检索要改变状态的对象
		tradeAccount = mAgentAccountService.getEntityById(TradeAccount.class, id);
		if (null == tradeAccount)
			throw new RuntimeException("用户账户信息出错，请刷新!");
		// 取反改变账户状态
		tradeAccount.setStatus(!tradeAccount.getStatus());
		mAgentAccountService.update(tradeAccount);
		String statusString = "";
		if (tradeAccount.getStatus())
			statusString = "启用";
		else
			statusString = "停用";
		applicationLogService.generic("操作员修改账户状态：【" + statusString + "】" + tradeAccount.getAccountName()
				+ " 账户" + tradeAccount.getTradeAccName() + "成功", "操作员修改账户状态", ApplicationLogService.GENERIC,
				ManageContext.LOG_SYS_TYPE, ManageContext.LOG_OPT_TYPE_ACC, null);
		// 清空查询条件，进入查询
		tradeAccount = null;
		return execute();
	}

	/**
	 * 用户账户去充值
	 * 
	 * @return
	 */
	public String doRecharge() {
		tradeAccount = mAgentAccountService.getEntityById(TradeAccount.class, id);
		if (null == tradeAccount)
			throw new RuntimeException("用户账户信息出错，请刷新!");
		return ADD;
	}

	/**
	 * 用户账户充值保存
	 * 
	 * @return
	 */
	public String doSave() {
		tradeAccount = mAgentAccountService.getEntityById(TradeAccount.class, id);
		if (tradeAccount != null) {
			TradeAccountDetail tradeDetail = new TradeAccountDetail();
			tradeDetail.setOpLoginId(this.getLoginId());
			tradeDetail.setOpLoginName(this.getLogin().getLoginName());
			tradeDetail.setOpValue(opValue);
			tradeDetail.setOpType(0);
			// tradeDetail.setRemark(remark);
			// tradeDetail.setOwnerId(this.getAccount().getId());
			// tradeDetail.setRechargeType(rechargeType);
			mAgentAccountService.saveTradeAccount(tradeAccount, tradeDetail);
			this.msgInfo.setFlag(MessageDialog.SUCCESS);
			this.msgInfo.addMessage("充值成功!");
		} else {
			this.msgInfo.setFlag(MessageDialog.ERROR);
			this.msgInfo.addMessage("账户出现问题，请刷新后再操作!");
		}
		return JDIALOG;
	}

	/**
	 * 用户账户去扣款
	 * 
	 * @return
	 */
	public String doCut() {
		tradeAccount = mAgentAccountService.getEntityById(TradeAccount.class, id);
		if (null == tradeAccount)
			throw new RuntimeException("用户账户信息出错，请刷新!");
		return "cut";
	}

	/**
	 * 用户账户扣款保存
	 * 
	 * @return
	 */
	public String doSaveCut() {
		tradeAccount = mAgentAccountService.getEntityById(TradeAccount.class, id);
		if (tradeAccount != null) {
			TradeAccountDetail tradeDetail = new TradeAccountDetail();
			tradeDetail.setOpLoginId(this.getLoginId());
			tradeDetail.setOpLoginName(this.getLogin().getLoginName());
			tradeDetail.setOpValue(opValue);
			tradeDetail.setOpType(5);
			mAgentAccountService.saveCutTradeAccount(tradeAccount, tradeDetail);
			this.msgInfo.setFlag(MessageDialog.SUCCESS);
			this.msgInfo.addMessage("扣款成功!");
		} else {
			this.msgInfo.setFlag(MessageDialog.ERROR);
			this.msgInfo.addMessage("账户出现问题，请刷新后再操作!");
		}
		return JDIALOG;
	}

	/**
	 * 信用额度去修改
	 * 
	 * @return
	 */
	public String doCredit() {
		tradeAccount = mAgentAccountService.getEntityById(TradeAccount.class, id);
		if (null == tradeAccount)
			throw new RuntimeException("用户账户信息出错，请刷新!");
		return "credit";
	}

	/**
	 * 信用额度编辑修改
	 * 
	 * @return
	 */
	public String doCreditSave() {
		tradeAccount = mAgentAccountService.getEntityById(TradeAccount.class, id);
		if (tradeAccount != null) {
			mAgentAccountService.editTradeAccount(tradeAccount);
			this.msgInfo.setFlag(MessageDialog.SUCCESS);
			this.msgInfo.addMessage("修改成功!");
		} else {
			this.msgInfo.setFlag(MessageDialog.ERROR);
			this.msgInfo.addMessage("账户出现问题，请刷新后再操作!");
		}
		return JDIALOG;
	}

	/**
	 * 用户账户去充值
	 * 
	 * @return
	 */
	public String doSign() {
		tradeAccount = mAgentAccountService.getEntityById(TradeAccount.class, id);
		if (null == tradeAccount)
			throw new RuntimeException("用户账户信息出错，请刷新!");
		return "sign";
	}

	/**
	 * 信用额度编辑修改
	 * 
	 * @return
	 */
	public String doSignSave() {
		tradeAccount = mAgentAccountService.getEntityById(TradeAccount.class, id);
		if (tradeAccount != null) {
			mAgentAccountService.signTradeAccount(tradeAccount);
			this.msgInfo.setFlag(MessageDialog.SUCCESS);
			this.msgInfo.addMessage("设置成功!");
		} else {
			this.msgInfo.setFlag(MessageDialog.ERROR);
			this.msgInfo.addMessage("账户出现问题，请刷新后再操作!");
		}
		return JDIALOG;
	}

	/**
	 * 用户交易账户清单分页查询
	 * 
	 * @return
	 */
	public String accountList() {
		if (tradeOrderDetailExtend == null) {
			tradeOrderDetailExtend = new TradeOrderDetailExtend();
			tradeOrderDetailExtend.setBegDate(DateUtil.getMonthFirstDay(new Date()));
			tradeOrderDetailExtend.setEndDate(DateUtil.getMonthLastDay(new Date()));
		}
		PageRequest<TradeOrderDetailExtend> filter = this.newPage(TradeOrderDetailExtend.class);
		filter.setFilters(tradeOrderDetailExtend);
		filter.setPageNumber(this.currPage);
		this.accountDetailPages = mAgentAccountService.findAgentAccountDetail(filter);
		accountList = tradeAccountService.findAllAccount();
		agentList = mAgentService.queryList(new AgentExtendInfo());
		return LIST;
	}

	/**
	 * @author Mountain 用户交易明细清单
	 */
	public String tradeDetailCount() {
		if (tradeAccountDetail == null) {
			tradeAccountDetail = new TradeAccountDetail();
			tradeAccountDetail.setBegDate(DateUtil.getMonthFirstDay(new Date()));
			tradeAccountDetail.setEndDate(DateUtil.getMonthLastDay(new Date()));
		}
		PageRequest<Map<String, Object>> filter = new PageRequest<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begDate", tradeAccountDetail.getBegDate());
		map.put("endDate", tradeAccountDetail.getEndDate());
		if (!TxtUtil.isEmpty(tradeAccountDetail.getAccountName()))
			map.put(TradeAccountDetail.ACCOUNTNAME, tradeAccountDetail.getAccountName());
		if (!TxtUtil.isEmpty(tradeAccountDetail.getOpLoginName()))
			map.put(TradeAccountDetail.OPLOGINNAME, tradeAccountDetail.getOpLoginName());
		if (!TxtUtil.isEmpty(tradeAccountDetail.getRemark1()))
			map.put("remark1", tradeAccountDetail.getRemark1());
		filter.setFilters(map);
		accountList = tradeAccountService.findAllAccount();
		agentList = mAgentService.queryList(new AgentExtendInfo());
		return LIST;
	}

	/**
	 * 用户账户清单分页查询
	 * 
	 * @return
	 */
	public String disAccountList() {
		if (tradeAccountDetail == null) {
			tradeAccountDetail = new TradeAccountDetail();
		}
		PageRequest<TradeAccountDetail> filter = this.newPage(TradeAccountDetail.class);
		filter.setFilters(tradeAccountDetail);
		filter.setPageNumber(this.currPage);
		accountPages = mAgentAccountService.finddisTradeAccountDetail(filter);
		return "disList";
	}

	/**
	 * 用户账户统计
	 * 
	 * @return
	 */
	public String accountCount() {
		if (tradeVo == null) {
			tradeVo = new TradeAccountDetailVo();
			tradeVo.setBegDate(DateUtil.getMonthFirstDay(new Date()));
			tradeVo.setEndDate(DateUtil.getMonthLastDay(new Date()));
		}
		PageRequest<TradeAccountDetailVo> filter = this.newPage(TradeAccountDetailVo.class);
		filter.setFilters(tradeVo);
		filter.setPageNumber(this.currPage);
		accountCountPages = mAgentAccountService.findAccountCount(filter);
		return "count";
	}

	/**
	 * 用户账户统计
	 * 
	 * @return
	 */
	public String disAccountCount() {
		if (tradeVo == null) {
			tradeVo = new TradeAccountDetailVo();
			tradeVo.setBegDate(DateUtil.getMonthFirstDay(new Date()));
			tradeVo.setEndDate(DateUtil.getMonthLastDay(new Date()));
		}
		PageRequest<TradeAccountDetailVo> filter = this.newPage(TradeAccountDetailVo.class);
		filter.setFilters(tradeVo);
		filter.setPageNumber(this.currPage);
		accountCountPages = mAgentAccountService.finddisAccountCount(filter);
		return "discount";
	}

	/**
	 * 用户清单导出
	 * 
	 * @return
	 */
	public List<TradeAccountDetail> detailList;

	public void mAgentAccountExport() {

		if (tradeAccountDetail == null) {
			tradeAccountDetail = new TradeAccountDetail();
		}
		Map<String, Object> params = new Hashtable<String, Object>();
		if (!TxtUtil.isEmpty(tradeAccountDetail.getOrderNo()))
			params.put(TradeAccountDetail.ORDERNO, tradeAccountDetail.getOrderNo().trim());

		if (!TxtUtil.isEmpty(tradeAccountDetail.getAccountName()))
			params.put("accountName", tradeAccountDetail.getAccountName().trim());
		if (tradeAccountDetail.getOpType() != null)
			params.put(TradeAccountDetail.OPTYPE, tradeAccountDetail.getOpType());
		detailList = mAgentAccountService.getAgentAccountList(params);
		if (detailList.size() <= 0)
			throw new RuntimeException("导出文件为空!");
		try {
			if (response == null) {
				super.response = ServletActionContext.getResponse();
			}
			String fileName = "用户账户清单" + DateUtil.format(new Date(), ManageContext.DATE_FORMAT) + ".xls";
			fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");// 解决文件中文名称问题
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setContentType("application/x-excel");
			OutputStream out = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(out);
			WritableSheet sheet = wwb.createSheet("用户账户清单", 0);
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
			sheet.mergeCells(0, 1, 9, 1);
			sheet.mergeCells(0, 0, 9, 0);

			sheet.addCell(new Label(0, 0, "用户账户清单", wcf_title));

			sheet.addCell(new Label(0, 2, "用户", title3));
			sheet.addCell(new Label(1, 2, "景区", title3));
			sheet.addCell(new Label(2, 2, "时间", title3));
			sheet.addCell(new Label(3, 2, "交易类型", title3));
			sheet.addCell(new Label(4, 2, "订单号", title3));
			sheet.addCell(new Label(5, 2, "操作金额", title3));
			sheet.addCell(new Label(6, 2, "余额", title3));
			sheet.addCell(new Label(7, 2, "透支", title3));
			sheet.addCell(new Label(8, 2, "冻结", title3));
			sheet.addCell(new Label(9, 2, "备注", title3));
			int i = 3;
			for (TradeAccountDetail temp : detailList) {
				sheet.addCell(new Label(0, i, temp.getAccountName(), title));
				sheet.addCell(new Label(2, i, DateUtil
						.format(temp.getCreateTime(), ManageContext.TIME_FORMAT), title));
				String type = null;
				if (temp.getOpType() == 0) {
					type = "充值";
				} else if (temp.getOpType() == 1) {
					type = "订单冻结";
				} else if (temp.getOpType() == 2) {
					type = "订单完成扣款";
				} else if (temp.getOpType() == 3) {
					type = "退订单";
				} else if (temp.getOpType() == 4) {
					type = "订单返利";
				}
				sheet.addCell(new Label(3, i, type, title));
				sheet.addCell(new Label(4, i, temp.getOrderNo(), title));
				sheet.addCell(new jxl.write.Number(5, i, Double.valueOf(temp.getOpValue() + ""), title));
				if (temp.getLeftValue() > 0) {
					sheet
							.addCell(new jxl.write.Number(6, i, Double.valueOf(temp.getLeftValue() + ""),
									title));
				} else {
					sheet.addCell(new jxl.write.Number(6, i, Double.valueOf(0), title));
				}
				if (temp.getLeftValue() < 0) {
					sheet
							.addCell(new jxl.write.Number(7, i, Double.valueOf(temp.getLeftValue() + ""),
									title));
				} else {
					sheet.addCell(new jxl.write.Number(7, i, Double.valueOf(0), title));
				}
				i++;
			}
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("类MAgentAccountAction方法mAgentAccountExport执行出现异常, 原因：" + e.toString());
		}
	}

	/**
	 * 客户管理导出
	 * 
	 * @return
	 * @throws ParseException
	 */
	public void agentAccountExport() throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		if (tradeAccountVo == null) {
			tradeAccountVo = new TradeAccountVo();
		}
		if (!TxtUtil.isEmpty(tradeAccountVo.getCorpName()))
			map.put("corpName", "%" + tradeAccountVo.getCorpName().trim() + "%");
		if (!TxtUtil.isEmpty(tradeAccountVo.getAccountName()))
			map.put(TradeAccountVo.ACCOUNTNAME, "%" + tradeAccountVo.getAccountName().trim() + "%");
		if (!TxtUtil.isEmpty(tradeAccountVo.getTradeAccName()))
			map.put(TradeAccountVo.TRADEACCNAME, "%" + tradeAccountVo.getTradeAccName().trim() + "%");
		if (null != tradeAccountVo.getStatus())
			map.put(TradeAccountVo.STATUS, tradeAccountVo.getStatus());
		List<TradeAccountVo> details = mAgentAccountService.findTradeAccountList(map);
		try {
			// 写EXCEL文件
			if (response == null) {
				super.response = ServletActionContext.getResponse();
			}
			String fileName = "账户清单" + DateUtil.format(new Date(), ManageContext.DATE_FORMAT) + ".xls";
			fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");// 解决文件中文名称问题
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setContentType("application/x-excel");
			OutputStream out = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(out);
			WritableSheet sheet = wwb.createSheet("账户清单", 0);
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
				sheet.setColumnView(x, 20);
			}
			sheet.mergeCells(0, 1, 6, 1);
			sheet.mergeCells(0, 0, 6, 0);

			StringBuffer sbBuffer = new StringBuffer();
			if (!TxtUtil.isEmpty(tradeAccountVo.getCorpName()))
				sbBuffer.append("客户名称  :" + tradeAccountVo.getCorpName().trim());
			if (!TxtUtil.isEmpty(tradeAccountVo.getAccountName()))
				sbBuffer.append("     账户名称  :" + tradeAccountVo.getAccountName().trim());
			if (!TxtUtil.isEmpty(tradeAccountVo.getTradeAccName()))
				sbBuffer.append("     钱包名称  :" + tradeAccountVo.getTradeAccName().trim());
			if (null != tradeAccountVo.getStatus()) {
				if (tradeAccountVo.getStatus())
					sbBuffer.append("    账户状态  :生效");
				else
					sbBuffer.append("    账户状态  :失效");
			}

			sheet.addCell(new Label(0, 0, "账户清单", wcf_title));
			sheet.addCell(new Label(0, 1, "  " + sbBuffer.toString(), title4));
			sheet.addCell(new Label(0, 2, "客户名称", title3));
			sheet.addCell(new Label(1, 2, "账户名称", title3));
			sheet.addCell(new Label(2, 2, "钱包名称", title3));
			sheet.addCell(new Label(3, 2, "账户余额", title3));
			sheet.addCell(new Label(4, 2, "账户状态", title3));
			sheet.addCell(new Label(5, 2, "创建时间", title3));
			sheet.addCell(new Label(6, 2, "用户来源", title3));
			int i = 3;
			String accStatuString = "失效";
			for (TradeAccountVo traDetail : details) {
				sheet.addCell(new Label(0, i, traDetail.getCorpName(), title));
				sheet.addCell(new Label(1, i, traDetail.getAccountName(), title));
				sheet.addCell(new Label(2, i, traDetail.getTradeAccName(), title));
				sheet.addCell(new jxl.write.Number(3, i, Double.valueOf(traDetail.getCurLeftValue() + ""),
						title));
				if (null != traDetail.getStatus() && traDetail.getStatus())
					accStatuString = "生效";
				sheet.addCell(new Label(4, i, accStatuString, title));
				sheet.addCell(new Label(5, i, DateUtil.format(traDetail.getCreateTime(),
						ManageContext.TIME_FORMAT), title));
				sheet.addCell(new Label(6, i, traDetail.getAccResource(), title));
				i++;
			}
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("类MAgentAccountAction方法agentAccountExport执行出现异常, 原因：" + e.toString());
		}
	}

	public TradeAccount getTradeAccount() {
		return tradeAccount;
	}

	public void setTradeAccount(TradeAccount tradeAccount) {
		this.tradeAccount = tradeAccount;
	}

	public Page<TradeAccountVo> getPages() {
		return pages;
	}

	public void setPages(Page<TradeAccountVo> pages) {
		this.pages = pages;
	}

	public Double getOpValue() {
		return opValue;
	}

	public void setOpValue(Double opValue) {
		this.opValue = opValue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public MAgentAccountService getmAgentAccountService() {
		return mAgentAccountService;
	}

	public void setmAgentAccountService(MAgentAccountService mAgentAccountService) {
		this.mAgentAccountService = mAgentAccountService;
	}

	public TradeAccountDetail getTradeAccountDetail() {
		return tradeAccountDetail;
	}

	public void setTradeAccountDetail(TradeAccountDetail tradeAccountDetail) {
		this.tradeAccountDetail = tradeAccountDetail;
	}

	public Page<TradeAccountDetail> getAccountPages() {
		return accountPages;
	}

	public void setAccountPages(Page<TradeAccountDetail> accountPages) {
		this.accountPages = accountPages;
	}

	public TradeAccountDetailVo getTradeVo() {
		return tradeVo;
	}

	public void setTradeVo(TradeAccountDetailVo tradeVo) {
		this.tradeVo = tradeVo;
	}

	public Page<TradeAccountDetailVo> getAccountCountPages() {
		return accountCountPages;
	}

	public void setAccountCountPages(Page<TradeAccountDetailVo> accountCountPages) {
		this.accountCountPages = accountCountPages;
	}

	public List<TradeAccountDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<TradeAccountDetail> detailList) {
		this.detailList = detailList;
	}

	public Double getSignAmount() {
		return signAmount;
	}

	public void setSignAmount(Double signAmount) {
		this.signAmount = signAmount;
	}

	public Integer getRechargeType() {
		return rechargeType;
	}

	public void setRechargeType(Integer rechargeType) {
		this.rechargeType = rechargeType;
	}

	public List<TradeAccountInfo> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<TradeAccountInfo> accountList) {
		this.accountList = accountList;
	}

	public List<AgentInfo> getAgentList() {
		return agentList;
	}

	public void setAgentList(List<AgentInfo> agentList) {
		this.agentList = agentList;
	}

	public TradeOrder getOrder() {
		return order;
	}

	public void setOrder(TradeOrder order) {
		this.order = order;
	}

	public Page<TradeOrderDetailExtend> getAccountDetailPages() {
		return accountDetailPages;
	}

	public void setAccountDetailPages(Page<TradeOrderDetailExtend> accountDetailPages) {
		this.accountDetailPages = accountDetailPages;
	}

	public TradeOrderDetailExtend getTradeOrderDetailExtend() {
		return tradeOrderDetailExtend;
	}

	public void setTradeOrderDetailExtend(TradeOrderDetailExtend tradeOrderDetailExtend) {
		this.tradeOrderDetailExtend = tradeOrderDetailExtend;
	}

	public TradeAccountVo getTradeAccountVo() {
		return tradeAccountVo;
	}

	public void setTradeAccountVo(TradeAccountVo tradeAccountVo) {
		this.tradeAccountVo = tradeAccountVo;
	}

	public AgentInfo getAgentInfo() {
		return agentInfo;
	}

	public void setAgentInfo(AgentInfo agentInfo) {
		this.agentInfo = agentInfo;
	}

	public List<SysDictDetail> getDictDetails() {
		return dictDetails;
	}

	public void setDictDetails(List<SysDictDetail> dictDetails) {
		this.dictDetails = dictDetails;
	}

	public MAccountCouponService getAccountCouponService() {
		return accountCouponService;
	}

	public void setAccountCouponService(MAccountCouponService accountCouponService) {
		this.accountCouponService = accountCouponService;
	}

	public Double getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(Double totalSum) {
		this.totalSum = totalSum;
	}

}
