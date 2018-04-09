package com.mall.butler.web.w.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.WebsiteContext;
import com.mall.butler.action.w.WebBaseAction;
import com.mall.butler.sys.model.SysTextLibrary;
import com.mall.butler.web.model.StaticInfo;
import com.mall.butler.web.w.service.WStaticService;

/**
 * 帮助中心
 * 
 * @author caedmon
 * @version 创建时间：2013-3-26 下午02:50:42
 */
public class HelpAction extends WebBaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private WStaticService wStaticService;
	private StaticInfo staticInfo;
	private String content;// 内容

	/**
	 * 帮助中心
	 * 
	 * @return
	 */
	public String execute() {
		staticInfo = wStaticService.getByCode(WebsiteContext.HELP_CENTER);
		if (staticInfo != null && !staticInfo.getDeleted()) {
			SysTextLibrary text = wStaticService.getEntityById(SysTextLibrary.class, staticInfo.getContentId());
			if (text != null)
				content = text.getContext();
		}
		return SUCCESS;
	}

	/**
	 * 常见问题
	 * 
	 * @return
	 */
	public String commonProblem() {
		staticInfo = wStaticService.getByCode(WebsiteContext.HELP_COMMONP_ROBLEM);
		if (staticInfo != null && !staticInfo.getDeleted()) {
			SysTextLibrary text = wStaticService.getEntityById(SysTextLibrary.class, staticInfo.getContentId());
			if (text != null)
				content = text.getContext();
		}
		return SUCCESS;
	}

	/**
	 * 法律声明
	 * 
	 * @return
	 */
	public String legalNotices() {
		staticInfo = wStaticService.getByCode(WebsiteContext.HELP_LEGAL_NOTICES);
		if (staticInfo != null && !staticInfo.getDeleted()) {
			SysTextLibrary text = wStaticService.getEntityById(SysTextLibrary.class, staticInfo.getContentId());
			if (text != null)
				content = text.getContext();
		}
		return SUCCESS;
	}

	/**
	 * 服务条款
	 * 
	 * @return
	 */
	public String service() {
		staticInfo = wStaticService.getByCode(WebsiteContext.HELP_SERVICE);
		if (staticInfo != null && !staticInfo.getDeleted()) {
			SysTextLibrary text = wStaticService.getEntityById(SysTextLibrary.class, staticInfo.getContentId());
			if (text != null)
				content = text.getContext();
		}
		return SUCCESS;
	}

	public WStaticService getwStaticService() {
		return wStaticService;
	}

	public void setwStaticService(WStaticService wStaticService) {
		this.wStaticService = wStaticService;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public StaticInfo getStaticInfo() {
		return staticInfo;
	}

	public void setStaticInfo(StaticInfo staticInfo) {
		this.staticInfo = staticInfo;
	}

}
