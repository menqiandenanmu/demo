package com.mall.butler.action.m;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.ManageContext;
import com.mall.butler.account.m.service.MAccountService;
import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.action.BaseAction;
import com.mall.butler.easyui.DataGrid;
import com.mall.butler.easyui.EasyuiPageRequest;
import com.mall.butler.helper.SessionHelper;
import com.mall.butler.sys.dao.SysParamDao;
import com.mall.butler.util.UrlStack;
import com.mall.util.common.TxtUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public abstract class ManageBaseAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8991076931943204409L;
	private AccountLogin login;
	private AccountInfo account;
	@Autowired
	private SessionHelper sessionHelper;
	@Autowired
	protected MAccountService accountService;
	@Autowired
	protected SysParamDao sysParamDao;
	protected int pageSizeParam;
	// 当前页面值
	protected int currPage;

	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 得到当前用户登录信息
	 * 
	 * @author caedmon
	 * @date 2010-10-15 下午06:13:11
	 * @return
	 */
	public AccountLogin getLogin() {
		if (login != null)
			return login;
		Long pk = getLoginId();
		if (pk != null)
			login = accountService.getEntityById(AccountLogin.class, pk);
		return login;
	}

	/**
	 * 得到当前用户信息
	 * 
	 * @author caedmon
	 * @date 2010-10-15 下午06:13:29
	 * @return
	 */
	public AccountInfo getAccount() {
		if (account != null)
			return account;
		AccountLogin login = getLogin();
		if (login != null)
			account = accountService.getEntityById(AccountInfo.class, login.getAccountId());
		return account;
	}

	/**
	 * 得到登录id
	 * 
	 * @return
	 */
	public Long getLoginId() {
		Object o = sessionHelper.get(ManageContext.SESSION_LOGINID);
		if (null != o)
			return (Long) o;
		else
			return null;
	}

	/**
	 * 设置登录id
	 * 
	 * @param loginId
	 */
	public void putLoginId(Long loginId) {
		sessionHelper.set(ManageContext.SESSION_LOGINID, loginId);
	}

	/**
	 * 权限控制
	 */
	public void prepare() throws Exception {

	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public int pageSizeParam() {
		return Integer.parseInt(sysParamDao.getById(1l).getParamValue());
	}

	public String getRequestUrl() {
		String url = this.getRequest().getRequestURL().toString();
		return url;
	}

	/**
	 * 去掉url中有currPage=xxx的字符串 htpp;axxx/a.htm?key=afa&p=asx&currPage=11
	 * 
	 * @return
	 */
	public String getPageUrl() {
		String reg = "[&|?|]\\bcurrPage=\\d*\\b";
		String reg1 = "\\bcurrPage=\\d*\\b";
		// String reg="[&|?|]\\bcurrPage=\\d\\b|\\bcurrPage=\\d\\b";
		String result = this.getRequestUrl();
		String queryStr = this.getRequest().getQueryString();
		if (TxtUtil.isEmpty(queryStr)) {
			queryStr = "rand=" + Math.random();
		} else {
			queryStr = queryStr.replaceAll(reg, "").replaceAll(reg1, "");
		}
		result = result + "?" + queryStr;
		return result;
	}

	/**
	 * 新建分页查询对象
	 * 
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public <T> PageRequest<T> newPage(Class<T> clazz) {
		PageRequest<T> result = new PageRequest<T>();
		result.setPageSize(ManageContext.LIST_PAGE_NUM);
		result.setPageNumber(currPage);
		return result;
	}

	/**
	 * 构造分页查询对象
	 * 
	 * @param clazz
	 * @return
	 */
	protected <T> PageRequest<T> buildPageRequest() {
		String currPage = this.request.getParameter("page");
		if (currPage != null) {
			String rows = this.request.getParameter("rows");
			String sort = this.request.getParameter("sort");
			String order = this.request.getParameter("order");
			EasyuiPageRequest<T> page = new EasyuiPageRequest<T>();
			page.setPageNumber(Integer.parseInt(currPage));
			page.setPageSize(Integer.parseInt(rows));
			page.setSort(sort);
			page.setOrder(order);
			return page;
		}
		return null;
	}

	/**
	 * 返回JSON格式的数据
	 * 
	 * @param page
	 */
	protected <T> void renderDatagridJson(Page<T> page) {
		this.renderJson(new DataGrid<T>(page.getResult(), page.getTotalCount()));
	}

	/**
	 * 得到上一级页面
	 * 
	 * @return
	 */
	public String getBackUrl() {
		String backUrl = "";
		Object obj = sessionHelper.get(ManageContext.SESSION_URLSTACKID);
		UrlStack urlStack;
		if (obj != null) {
			urlStack = (UrlStack) obj;
			backUrl = urlStack.getBackUrl();
		}
		if (TxtUtil.isEmpty(backUrl))
			backUrl = this.getBase() + "/manage/index!panel.htm";
		return backUrl;
	}

	/**
	 * 返回查询字符串
	 * 
	 * @return
	 */
	public String getQueryString() {
		return this.getRequest().getQueryString();
	}
}
