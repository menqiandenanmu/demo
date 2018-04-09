package com.mall.butler.account.m.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.ManageContext;
import com.mall.butler.account.m.service.RechageCardService;
import com.mall.butler.account.model.RechageCard;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.sys.m.service.MDictService;
import com.mall.butler.sys.model.SysDictDetail;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.common.lang.DateUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class RechageCardAction extends ManageBaseAction {
	/**
	 * 充值卡
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private MDictService mDictService;
	private List<SysDictDetail> sysDictDetails;
	private List<SysDictDetail> sysDictDetailsType;
	@Autowired
	private RechageCardService rechageCardService;

	private RechageCard rechageCard;
	// 分页对象
	private Page<RechageCard> pages;

	private Long[] messageIds;
	private String ids;

	public String execute() {
		if (null == rechageCard)
			rechageCard = new RechageCard();
		String cardCode = rechageCard.getCardCode();
		if (null != cardCode && "" != cardCode) {
			rechageCard.setCardCode("%" + rechageCard.getCardCode() + "%");
		}
		PageRequest<RechageCard> filter = this.newPage(RechageCard.class);
		sysDictDetailsType = mDictService
				.queryDetail(ManageContext.DICT_STYLE_CLASS);
		filter.setFilters(rechageCard);
		filter.setPageNumber(this.currPage);
		pages = rechageCardService.page(filter);
		rechageCard.setCardCode(cardCode);
		return SUCCESS;
	}

	public String add() {
		rechageCard = new RechageCard();
		sysDictDetails = mDictService
				.queryDetail(ManageContext.DICT_STATIC_CLASS);
		sysDictDetailsType = mDictService
				.queryDetail(ManageContext.DICT_STYLE_CLASS);
		return ADD;
	}

	public String addAll() {
		return "addAll";
	}

	public String save() {
		RechageCard rechageCardOld = new RechageCard();
		rechageCardOld.setCardCode(rechageCard.getCardCode());
		rechageCardOld.setDeleted(false);
		if (null != rechageCardService.getEntity(rechageCardOld)) {

			throw new RuntimeException("充值卡卡号" + rechageCard.getCardCode()
					+ "不能重复");
		}
		rechageCardService.doAddSave(rechageCard);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("操作成功!");
		return JDIALOG;
	}

	public String edit() {
		rechageCard = rechageCardService.getEntityById(RechageCard.class, id);
		if (null == rechageCard)
			throw new RuntimeException("记录不存在");
		sysDictDetails = mDictService
				.queryDetail(ManageContext.DICT_STATIC_CLASS);
		sysDictDetailsType = mDictService
				.queryDetail(ManageContext.DICT_STYLE_CLASS);
		return EDIT;
	}

	public String info() {
		rechageCard = rechageCardService.getEntityById(RechageCard.class, id);
		if (null == rechageCard)
			throw new RuntimeException("记录不存在");
		return INFO;
	}

	/**
	 * 保存更新
	 * 
	 * @date 2010-10-21 上午09:26:04
	 * @return
	 */
	public String update() {
		if (null == rechageCard)
			rechageCard = new RechageCard();
		rechageCard.setId(id);
		rechageCardService.doUpdate(rechageCard);
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
		if (rechageCard == null) {
			rechageCard = new RechageCard();
		}
		rechageCard.setId(id);
		rechageCardService.doDel(rechageCard);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("删除成功!");
		return JDIALOG;
	}
	/**
	 * 审核
	 * 
	 * @return
	 */
	public String auditById() {
		return "auditById";
	}
	private Integer startNum; 
	private Integer endNum; 
	/**
	 * 审核
	 * 
	 * @return
	 */
	public String doAuditById() {
		if(startNum>endNum)
			throw new RuntimeException("开始id不能大于结束id");
		String[] ids = new String[endNum-startNum+1] ;
		String idstr;
		for(int i=0;i<endNum-startNum+1;i++){
			 idstr=String.valueOf(startNum+i);
			ids[i]=idstr;
		}
		rechageCardService.doAuditAll(ids, rechageCard
				.getAuditRemark());
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("审核成功!");
		return JDIALOG;
	}
	
	public Integer getStartNum() {
		return startNum;
	}

	public void setStartNum(Integer startNum) {
		this.startNum = startNum;
	}

	public Integer getEndNum() {
		return endNum;
	}

	public void setEndNum(Integer endNum) {
		this.endNum = endNum;
	}

	/**
	 * 审核
	 * 
	 * @return
	 */
	public String verify() {
		if (rechageCard == null) {
			rechageCard = new RechageCard();
		}
		rechageCard.setId(id);
		rechageCard.setAuditSyatus(1);
		rechageCardService.doUpdate(rechageCard);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("审核成功!");
		return JDIALOG;
	}

	/**
	 * 启用
	 * 
	 * @return
	 */
	public String doUse() {
		if (rechageCard == null) {
			rechageCard = new RechageCard();
		}
		rechageCard.setId(id);
		rechageCard.setStatus(1);
		rechageCardService.doUse(rechageCard);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("启用成功!");
		return JDIALOG;
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */

	public String delAll() {
		rechageCardService.doDelAll(messageIds);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("删除成功");
		return JDIALOG;
	}

	/**
	 * 批量启用
	 * 
	 * @return
	 */

	public String useAll() {
		rechageCardService.doUseAll(ids.split(","));
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("启用成功");
		return JDIALOG;
	}

	/**
	 * 审核列表页
	 * 
	 * @return
	 */
	public String audit() {
		if (null == rechageCard) {
			rechageCard = new RechageCard();
			rechageCard.setAuditSyatus(0);
		}
		sysDictDetailsType = mDictService
				.queryDetail(ManageContext.DICT_STYLE_CLASS);
		PageRequest<RechageCard> filter = this.newPage(RechageCard.class);
		filter.setFilters(rechageCard);
		filter.setPageNumber(this.currPage);
		pages = rechageCardService.page(filter);
		return "rechageCardAudit";
	}

	/**
	 * 审核页面
	 * 
	 * @return
	 */
	public String toAudit() {
		if (null == ids) {
			throw new RuntimeException("记录不存在");

		}
		return "rechageCardAuditInfo";
	}

	/**
	 * 审核保存
	 * 
	 * @return
	 */
	public String auditSave() {
		rechageCardService.doAuditAll(ids.split(","), rechageCard
				.getAuditRemark());
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("审核通过");
		return JDIALOG;
	}

	private String excel;

	public String readExcel() {
		String fileName = ManageContext.UPLOAD_PATH.replace("/upload", "")
				+ excel;
		Workbook wb = null;
		try {
			// 构造Workbook（工作薄）对象
			wb = Workbook.getWorkbook(new File(fileName));
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (wb == null)
			return null;
		Sheet[] sheet = wb.getSheets();
		// 得到当前工作表的行数
		List<RechageCard> rechageCardList = new ArrayList<RechageCard>();
		int rowNum = sheet[0].getRows();
		RechageCard rechageCard;
		for (int j = 1; j < rowNum; j++) {
			rechageCard = new RechageCard();
			// 得到当前行的所有单元格
			Cell[] cells = sheet[0].getRow(j);
			if (cells != null && cells.length > 0) {
				if (cells[1].getContents().equals("1"))
					break;
				if (cells[1].getContents() == "") {
					continue;
				}
				// 对每个单元格进行循环
				// 读取当前单元格的值
				// String cellValue = cells[k].getContents();
				// excel中的数据读入到guider对象
				String wordCode = cells[0].getContents();
				if (StringUtils.isBlank(wordCode)) {
					break;
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				rechageCard = new RechageCard();
				rechageCard.setCardName(cells[0].getContents());
				rechageCard.setCardCode(cells[1].getContents());
				rechageCard.setCardPassword(cells[2].getContents());
				rechageCard.setCardType(cells[3].getContents());
				rechageCard.setCreateYear(cells[4].getContents());
				try {
					rechageCard.setFailureTime(sdf
							.parse(cells[5].getContents()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				rechageCard.setCardMoney(Double.parseDouble(cells[6]
						.getContents()));
				rechageCard.setSource(cells[7].getContents());
				rechageCard.setRemark(cells[8].getContents());

				rechageCardList.add(rechageCard);

			}
		}
		if (rechageCardList.size() > 0)
			try {
				rechageCardService.doInsertRechageCard(rechageCardList);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}

		// 最后关闭资源，释放内存
		wb.close();
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("批量新增成功!");
		return JDIALOG;
	}

	/**
	 * 客户管理导出
	 * 
	 * @return
	 * @throws ParseException
	 */
	public void rechageCardExport() throws ParseException {
		if (rechageCard == null) {
			rechageCard = new RechageCard();
		}

		List<RechageCard> details = rechageCardService
				.findRechageCardDetailList(rechageCard);
		try {
			// 写EXCEL文件
			if (response == null) {
				super.response = ServletActionContext.getResponse();
			}
			String fileName = "充值卡"
					+ DateUtil.format(new Date(), ManageContext.DATE_FORMAT)
					+ ".xls";
			fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");// 解决文件中文名称问题
			response.setHeader("Content-Disposition", "attachment;filename="
					+ fileName);
			response.setContentType("application/x-excel");
			OutputStream out = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(out);
			WritableSheet sheet = wwb.createSheet("充值卡", 0);
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
			for (int x = 0; x < 8; x++) {

				if (x == 4) {
					sheet.setColumnView(x, 25);
				} else {
					sheet.setColumnView(x, 15);
				}
			}
			sheet.mergeCells(0, 1, 8, 1);
			sheet.mergeCells(0, 0,8, 0);
			sysDictDetailsType = mDictService
					.queryDetail(ManageContext.DICT_STYLE_CLASS);

			StringBuffer sbBuffer = new StringBuffer();
			if (!TxtUtil.isEmpty(rechageCard.getCardName())) {
				sbBuffer.append("充值卡名称:" + rechageCard.getCardName());
			}
			if (!TxtUtil.isEmpty(rechageCard.getCardCode())) {
				sbBuffer.append("    充值卡卡号:" + rechageCard.getCardCode());
			}
			if (!TxtUtil.isEmpty(rechageCard.getCardType())) {
				for (SysDictDetail item : sysDictDetailsType) {
					if (item.getDictDetailCode().equals(
							rechageCard.getCardType())) {
						sbBuffer
								.append("    充值卡类型:" + item.getDictDetailName());
					}
				}

			}
			if (!TxtUtil.isEmpty(rechageCard.getCreateYear())) {
				sbBuffer.append("    生成年度:" + rechageCard.getCreateYear());
			}
			if (null != rechageCard.getFailureTime()
					&& !TxtUtil
							.isEmpty(rechageCard.getFailureTime().toString())) {
				sbBuffer.append("    失效时间:"
						+ DateUtil.format(rechageCard.getFailureTime(),
								ManageContext.TIME_FORMAT));
			}
			if (null != rechageCard.getStatus()
					&& !TxtUtil.isEmpty(rechageCard.getStatus().toString())) {
				if (rechageCard.getStatus() == 1) {
					sbBuffer.append("    状态:启用");
				} else {
					sbBuffer.append("    状态:停用");
				}

			}
			if (null != rechageCard.getAuditSyatus()
					&& !TxtUtil
							.isEmpty(rechageCard.getAuditSyatus().toString())) {
				if (rechageCard.getAuditSyatus() == 1) {
					sbBuffer.append("    审核状态:审核通过");
				} else {
					sbBuffer.append("    审核状态:未审核");
				}
			}

			if (!TxtUtil.isEmpty(rechageCard.getAuditPerson())) {
				sbBuffer.append("    审核人:" + rechageCard.getAuditPerson());
			}

			sheet.addCell(new Label(0, 0, "充值卡", wcf_title));
			sheet.addCell(new Label(0, 1, "  " + sbBuffer.toString(), title4));
			sheet.addCell(new Label(0, 2, "充值卡名称", title3));
			sheet.addCell(new Label(1, 2, "充值卡卡号", title3));
			sheet.addCell(new Label(2, 2, "类型", title3));
			sheet.addCell(new Label(3, 2, "生成年度", title3));
			sheet.addCell(new Label(4, 2, "失效时间", title3));
			sheet.addCell(new Label(5, 2, "充值面额", title3));
			sheet.addCell(new Label(6, 2, "状态", title3));
			sheet.addCell(new Label(7, 2, "审核状态", title3));
			sheet.addCell(new Label(8, 2, "审核人", title3));
			//sheet.addCell(new Label(9, 2, "充值卡密码", title3));
			int i = 3;

			for (RechageCard rechageCard : details) {
				String accStatuString = "停用";
				sheet
						.addCell(new Label(1, i, rechageCard.getCardName(),
								title));
				sheet
						.addCell(new Label(0, i, rechageCard.getCardCode(),
								title));

				for (SysDictDetail item : sysDictDetailsType) {
					if (item.getDictDetailCode().equals(
							rechageCard.getCardType())) {
						sheet.addCell(new Label(2, i, item.getDictDetailName(),
								title));
					}
				}

				sheet.addCell(new Label(3, i, rechageCard.getCreateYear(),
						title));
				sheet.addCell(new Label(4, i, DateUtil.format(rechageCard
						.getFailureTime(), ManageContext.TIME_FORMAT), title));
				sheet.addCell(new jxl.write.Number(5, i, Double
						.valueOf(rechageCard.getCardMoney() + ""), title));
				if (null != rechageCard.getStatus()
						&& rechageCard.getStatus() == 1)
					accStatuString = "启用";
				sheet.addCell(new Label(6, i, accStatuString, title));
				if (null != rechageCard.getAuditSyatus()
						&& rechageCard.getAuditSyatus() == 1) {

					sheet.addCell(new Label(7, i, "审核通过", title));
				} else {
					sheet.addCell(new Label(7, i, "未审核", title));
				}

				sheet.addCell(new Label(8, i, rechageCard.getAuditPerson(),
						title));
//				sheet.addCell(new Label(9, i, rechageCard.getCardPassword(),
//						title));
				i++;
			}
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					"类RechageCardAction方法rechageCardExportExport执行出现异常, 原因："
							+ e.toString());
		}
	}

	public RechageCard getRechageCard() {
		return rechageCard;
	}

	public void setRechageCard(RechageCard rechageCard) {
		this.rechageCard = rechageCard;
	}

	public Page<RechageCard> getPages() {
		return pages;
	}

	public void setPages(Page<RechageCard> pages) {
		this.pages = pages;
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

	public String getExcel() {
		return excel;
	}

	public void setExcel(String excel) {
		this.excel = excel;
	}

	public List<SysDictDetail> getSysDictDetailsType() {
		return sysDictDetailsType;
	}

	public void setSysDictDetailsType(List<SysDictDetail> sysDictDetailsType) {
		this.sysDictDetailsType = sysDictDetailsType;
	}

}
