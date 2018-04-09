package com.mall.butler.sys.m.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.ManageContext;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.sys.m.service.MSysOptLogService;
import com.mall.butler.sys.m.vo.SysOptLogVo;
import com.mall.butler.sys.model.SysOptLog;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.lang.DateUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 系统日志
 * 
 * @author zhaoxs 2013-1-30 上午09:37:36
 */
public class SysOptLogAction extends ManageBaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private MSysOptLogService mSysOptLogService;

	/**
	 * 系统日志查询
	 */
	private SysOptLogVo optLogVo;
	private SysOptLog optLog;

	private Page<SysOptLogVo> pages;
private AccountLogin accLogin;
	public String execute() {
		PageRequest<Map<String, Object>> pageRequest = new PageRequest<Map<String, Object>>();
		pageRequest.setPageNumber(currPage);
		pageRequest.setPageSize(super.pageSizeParam());
		if (optLogVo == null) {
			optLogVo = new SysOptLogVo();
			Date date = new Date();
			optLogVo.setBeginDate(date);
			optLogVo.setEndDate(date);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (optLogVo.getBeginDate() != null)
			map.put("beginDate", DateUtil.format(optLogVo.getBeginDate(),ManageContext.DATE_FORMAT));
		if (optLogVo.getEndDate() != null)
			map.put("endDate", DateUtil.format(optLogVo.getEndDate(),ManageContext.DATE_FORMAT));
		if (!TxtUtil.isEmpty(optLogVo.getLoginName()))
			map.put("loginName", optLogVo.getLoginName().trim());
		if (optLogVo.getLogType() != null)
			map.put(SysOptLog.LOGTYPE, optLogVo.getLogType());
		if (optLogVo.getOptType() != null)
			map.put(SysOptLog.OPTTYPE, optLogVo.getOptType());
		if (!TxtUtil.isEmpty(optLogVo.getTitle()))
			map.put(SysOptLog.TITLE, optLogVo.getTitle());
		if (!TxtUtil.isEmpty(optLogVo.getContent()))
			map.put(SysOptLog.CONTENT, "%"+optLogVo.getContent()+"%");
		pageRequest.setFilters(map);
		pages = mSysOptLogService.querySysOptLogPage(pageRequest);
		return SUCCESS;
	}

	public String info() {
		optLog = mSysOptLogService.getEntityById(SysOptLog.class, id);
		accLogin=mSysOptLogService.getEntityById(AccountLogin.class, optLog.getLoginId());
		return INFO;
	}

	public SysOptLog getOptLog() {
		return optLog;
	}

	public void setOptLog(SysOptLog optLog) {
		this.optLog = optLog;
	}

	public SysOptLogVo getOptLogVo() {
		return optLogVo;
	}

	public void setOptLogVo(SysOptLogVo optLogVo) {
		this.optLogVo = optLogVo;
	}

	public Page<SysOptLogVo> getPages() {
		return pages;
	}

	public void setPages(Page<SysOptLogVo> pages) {
		this.pages = pages;
	}

	public AccountLogin getAccLogin() {
		return accLogin;
	}

	public void setAccLogin(AccountLogin accLogin) {
		this.accLogin = accLogin;
	}


}
