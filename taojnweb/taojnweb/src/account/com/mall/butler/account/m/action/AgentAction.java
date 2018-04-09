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
import com.mall.butler.account.m.model.AgentExtendInfo;
import com.mall.butler.account.m.service.MAgentService;
import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.account.model.AgentInfo;
import com.mall.butler.account.model.TradeAccount;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.helper.SessionHelper;
import com.mall.butler.sys.m.service.MDictService;
import com.mall.butler.sys.model.SysDictDetail;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.common.lang.DateUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 后台用户管理
 */
public class AgentAction extends ManageBaseAction {

	private static final long serialVersionUID = -7537522484798405723L;
	@Autowired
	private SessionHelper sessionHelper;
	@Autowired
	private MAgentService mAgentService;
	private AgentInfo agentInfo;

	private Page<AgentExtendInfo> pages;

	private AgentExtendInfo agentExtendInfo;

	private AccountLogin accountLogin;

	private AccountInfo accountInfo;

	private TradeAccount tradeAccount;
	private Integer mobileFlag;
	
	//与深大对接参数
	private String agentJson;
	private String sign;
	private String agentName;
	private String agentNo;
	private Integer agentAccountType;
	private List<SysDictDetail> dictDetails;
	@Autowired
	private MDictService dictService;

	/**
	 * 用户列表
	 */
	public String execute() {
		if (agentExtendInfo == null) {
			agentExtendInfo = new AgentExtendInfo();
		}
		PageRequest<AgentExtendInfo> filter = this.newPage(AgentExtendInfo.class);
		filter.setFilters(agentExtendInfo);
		filter.setPageNumber(this.currPage);
		pages = mAgentService.queryPageAgent(filter);
		//客户来源
		dictDetails=dictService.queryDetail(ManageContext.DICT_STATIC_CLASS);
		return LIST;
	}

	/**
	 * 新增页面
	 * 
	 * @return
	 */
	public String add() {
		dictDetails=dictService.queryDetail(ManageContext.DICT_STATIC_CLASS);
		return ADD;
	}

	/**
	 * 保存操作
	 * 
	 * @return
	 */
	public String save() {
		if (accountLogin == null) {
			accountLogin = new AccountLogin();
		}
		accountLogin.setCreateLoginId(getLoginId());
		accountLogin.setLoginPass(TxtUtil.digest(accountLogin.getLoginPass()));
		if(TxtUtil.isEmpty(agentInfo.getUrl()))
			throw new RuntimeException("请选择用户来源");
		mAgentService.doAddAgent(agentInfo, accountLogin);
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
		agentInfo.setId(id);
		mAgentService.doUpdateAgent(agentInfo);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("编辑成功!");
		return JDIALOG;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String del() {
		if (agentInfo == null) {
			agentInfo = new AgentInfo();
		}
		agentInfo.setId(id);
		mAgentService.doDelAgent(agentInfo);
		this.msgInfo.setFlag(MessageDialog.SUCCESS);
		this.msgInfo.addMessage("删除成功!");
		return JDIALOG;
	}

	/**
	 * 编辑操作
	 * 
	 * @return
	 */
	public String edit() {
		agentInfo = mAgentService.getEntityById(AgentInfo.class, id);
		if (agentInfo == null) {
			throw new RuntimeException("无效的信息!");
		}
		accountLogin = mAgentService.getEntityById(AccountLogin.class, id);
		if (agentInfo == null) {
			throw new RuntimeException("无效的信息!");
		}
		dictDetails=dictService.queryDetail(ManageContext.DICT_STATIC_CLASS);
		return EDIT;
	}

	/**
	 * 详细信息
	 * 
	 * @return
	 */
	public String info() {
		//agentExtendInfo = mAgentService.findByPkForM(id);
		agentInfo= mAgentService.getEntityById(AgentInfo.class, id);
		if (agentInfo == null) {
			throw new RuntimeException("无效的信息!");
		}
		tradeAccount= mAgentService.getEntityById(TradeAccount.class, id);
		if (tradeAccount == null) {
			throw new RuntimeException("无效的信息!");
		}
		accountLogin= mAgentService.getEntityById(AccountLogin.class, id);
		if (accountLogin == null) {
			throw new RuntimeException("无效的信息!");
		}
		dictDetails=dictService.queryDetail(ManageContext.DICT_STATIC_CLASS);
		return INFO;
	}
	
	public String validateAgent() {
		return INFO;
	}

	
	/**
	 * 新增页面
	 * 
	 * @return
	 */
	public String bindAgent() {
		agentInfo = new AgentInfo();
		agentInfo.setCorpName((String) sessionHelper.get("agentName"));
		agentInfo.setAgentAccountType((Integer) sessionHelper.get("agentAccountType"));
		return "bindAgent";
	}

	public String register() {
		return "bindAgent";
	}
	
	
	public String bindAgentInfo() {
		agentExtendInfo = mAgentService.findByPkForM(id);
		agentInfo= mAgentService.getEntityById(AgentInfo.class, id);
		if (agentExtendInfo == null) {
			throw new RuntimeException("无效的信息!");
		}
		return "bindAgentInfo";
	}


	/**
	 * 客户管理导出
	 * 
	 * @return
	 * @throws ParseException
	 */
	public void agentExport() throws ParseException {
		if (agentExtendInfo == null) {
			agentExtendInfo = new AgentExtendInfo();
		}
		
		Map<String, Object> map=new HashMap<String, Object>();
		if(!TxtUtil.isEmpty(agentExtendInfo.getCorpName()))
			map.put(AgentExtendInfo.CORPNAME,agentExtendInfo.getCorpName());
		if(!TxtUtil.isEmpty(agentExtendInfo.getCode()))
			map.put(AgentExtendInfo.CODE,agentExtendInfo.getCode());
		if(!TxtUtil.isEmpty(agentExtendInfo.getLinkmanTel()))
			map.put(AgentExtendInfo.LINKMANTEL,agentExtendInfo.getLinkmanTel());
		if(!TxtUtil.isEmpty(agentExtendInfo.getBankName()))
			map.put(AgentExtendInfo.BANKNAME,agentExtendInfo.getBankName());
		if(!TxtUtil.isEmpty(agentExtendInfo.getUrl()))
			map.put(AgentExtendInfo.URL,agentExtendInfo.getUrl());
		if(null!=agentExtendInfo.getAccStatus())
			map.put("accStatus",agentExtendInfo.getAccStatus());
		List<AgentExtendInfo> details=mAgentService.findTradeDetailList(map);
		try {
			// 写EXCEL文件
			if (response == null) {
				super.response = ServletActionContext.getResponse();
			}
			String fileName = "客户清单" + DateUtil.format(new Date(), ManageContext.DATE_FORMAT) + ".xls";
			fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");// 解决文件中文名称问题
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setContentType("application/x-excel");
			OutputStream out = response.getOutputStream();
			WritableWorkbook wwb = Workbook.createWorkbook(out);
			WritableSheet sheet = wwb.createSheet("客户清单", 0);
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
			sheet.mergeCells(0, 0,6, 0);
			
			StringBuffer sbBuffer=new StringBuffer();
			if(!TxtUtil.isEmpty(agentExtendInfo.getCorpName()))
				sbBuffer.append("客户名称  :"+agentExtendInfo.getCorpName());
			if(!TxtUtil.isEmpty(agentExtendInfo.getCode()))
				sbBuffer.append("    客户编号  :"+agentExtendInfo.getCode());
			if(!TxtUtil.isEmpty(agentExtendInfo.getLinkmanTel()))
				sbBuffer.append("    联系方式  :"+agentExtendInfo.getLinkmanTel());
			if(!TxtUtil.isEmpty(agentExtendInfo.getBankName()))
				sbBuffer.append("    钱包名称  :"+agentExtendInfo.getBankName());
			if(null!=agentExtendInfo.getAccStatus())
				{
				if(agentExtendInfo.getAccStatus()==0)
					sbBuffer.append("    客户状态  :停用");
				else
					sbBuffer.append("    客户状态  :启用");
				}
			
			sheet.addCell(new Label(0, 0, "客户清单", wcf_title));
			sheet.addCell(new Label(0, 1, "  " + sbBuffer.toString(), title4));
			sheet.addCell(new Label(0, 2, "客户名称", title3));
			sheet.addCell(new Label(1, 2, "客户编号", title3));
			sheet.addCell(new Label(2, 2, "钱包名称", title3));
			sheet.addCell(new Label(3, 2, "联系方式", title3));
			sheet.addCell(new Label(4, 2, "客户状态", title3));
			sheet.addCell(new Label(5, 2, "创建时间", title3));
			sheet.addCell(new Label(6, 2, "客户来源", title3));
			int i = 3;
			String accStatuString="停用";
			for (AgentExtendInfo traDetail : details) {
				sheet.addCell(new Label(0, i, traDetail.getCorpName(), title));
				sheet.addCell(new Label(1, i, traDetail.getCode(), title));
				sheet.addCell(new Label(2, i, traDetail.getBankName(), title));
				sheet.addCell(new Label(3, i, traDetail.getLinkmanTel(), title));
				if(null!=traDetail.getAccStatus()&&traDetail.getAccStatus()==1)
					accStatuString="启用";
				sheet.addCell(new Label(4, i,accStatuString , title));
				sheet.addCell(new Label(5, i, DateUtil.format(traDetail.getCreateTime(), ManageContext.TIME_FORMAT), title));
				sheet.addCell(new Label(6, i,traDetail.getFax() , title));
				i++;
			}
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("类MAgentAction方法agentExport执行出现异常, 原因：" + e.toString());
		}
	}

	public AgentInfo getAgentInfo() {
		return agentInfo;
	}

	public void setAgentInfo(AgentInfo agentInfo) {
		this.agentInfo = agentInfo;
	}

	public Page<AgentExtendInfo> getPages() {
		return pages;
	}

	public void setPages(Page<AgentExtendInfo> pages) {
		this.pages = pages;
	}

	public AccountLogin getAccountLogin() {
		return accountLogin;
	}

	public void setAccountLogin(AccountLogin accountLogin) {
		this.accountLogin = accountLogin;
	}

	public AccountInfo getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(AccountInfo accountInfo) {
		this.accountInfo = accountInfo;
	}

	public AgentExtendInfo getAgentExtendInfo() {
		return agentExtendInfo;
	}

	public void setAgentExtendInfo(AgentExtendInfo agentExtendInfo) {
		this.agentExtendInfo = agentExtendInfo;
	}

	public Integer getMobileFlag() {
		return mobileFlag;
	}

	public void setMobileFlag(Integer mobileFlag) {
		this.mobileFlag = mobileFlag;
	}

	public String getAgentJson() {
		return agentJson;
	}

	public void setAgentJson(String agentJson) {
		this.agentJson = agentJson;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	public Integer getAgentAccountType() {
		return agentAccountType;
	}

	public void setAgentAccountType(Integer agentAccountType) {
		this.agentAccountType = agentAccountType;
	}

	public TradeAccount getTradeAccount() {
		return tradeAccount;
	}

	public void setTradeAccount(TradeAccount tradeAccount) {
		this.tradeAccount = tradeAccount;
	}

	public List<SysDictDetail> getDictDetails() {
		return dictDetails;
	}

	public void setDictDetails(List<SysDictDetail> dictDetails) {
		this.dictDetails = dictDetails;
	}

}
