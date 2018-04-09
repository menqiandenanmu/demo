package com.mall.butler.action.w;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.ManageContext;
import com.mall.butler.WebsiteContext;
import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.account.w.service.WAccountService;
import com.mall.butler.action.BaseAction;
import com.mall.butler.helper.SessionHelper;
import com.mall.util.common.TxtUtil;
import com.mall.util.ibatis.page.PageRequest;

public abstract class WebBaseAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8991076931943204409L;
	private AccountLogin login;
	private AccountInfo account;
	@Autowired
	private SessionHelper sessionHelper;
	@Autowired
	protected WAccountService wAccountService;
	// 当前页面值
	protected int currPage;

	private Long id;

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
			login = wAccountService.getEntityById(AccountLogin.class, pk);
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
			account = wAccountService.getEntityById(AccountInfo.class, login
					.getAccountId());
		return account;
	}

	/**
	 * 得到登录id
	 * 
	 * @return
	 */
	public Long getLoginId() {
		Object o = sessionHelper.get(WebsiteContext.SESSION_LOGINID);
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
		sessionHelper.set(WebsiteContext.SESSION_LOGINID, loginId);
	}

	/**
	 * 权限控制
	 */
	public void prepare() throws Exception {

	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
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
		result.setPageSize(WebsiteContext.LIST_PAGE_NUM);
		result.setPageNumber(currPage);
		return result;
	}
	/**
	 * 记录跳转地址
	 */
	public void abtainBeforeUrl() {
		// 记录跳转地址
		HttpServletRequest req = ServletActionContext.getRequest();
		String url = req.getRequestURL().toString();
		if (!TxtUtil.isEmpty(req.getQueryString()))
			url = url + "?" + req.getQueryString();
		sessionHelper.set(ManageContext.WXSESSION_PAY_URL, url);
	}
}
