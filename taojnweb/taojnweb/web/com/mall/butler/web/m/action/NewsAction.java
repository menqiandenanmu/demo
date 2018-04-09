package com.mall.butler.web.m.action;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.sys.model.SysImageLibrary;
import com.mall.butler.sys.model.SysTextLibrary;
import com.mall.butler.util.FckeditorUtil;
import com.mall.butler.web.m.model.NewsExtendInfo;
import com.mall.butler.web.m.service.MNewsService;
import com.mall.butler.web.model.NewsClass;
import com.mall.butler.web.model.NewsInfo;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 类描述：资讯内容管理
 * 类名称：NewsAction
 * 创建人：caedmon
 * 创建时间：2013-5-18 下午09:46:10
 * 修改人：caedmon
 * 修改时间：2013-5-18 下午09:46:10
 * 修改备注：
 * 
 * @version
 */
public class NewsAction extends ManageBaseAction {

	private static final long serialVersionUID = 1L;
	private NewsClass newsClass;
	private NewsInfo newsInfo;
	@Autowired
	private MNewsService mNewsService;
	private Page<NewsExtendInfo> page;
	// 内容
	private String content;
	private String fckContent;
	private List<NewsClass> classList;
	// 资讯分类id
	private Long newsClassCodeId;
	// 图片url
	private String titleImg;
	private AccountLogin accountLogin;

	public String execute() {
		PageRequest<NewsInfo> request = super.newPage(NewsInfo.class);
		request.setPageNumber(this.currPage);
		if (newsInfo == null) {
			newsInfo = new NewsInfo();
		}
		request.setFilters(newsInfo);
		page = mNewsService.queryNews(request);
		classList = mNewsService.findNewsList();
		return LIST;
	}

	/**
	 * 详细
	 */
	public String info() {
		newsInfo = mNewsService.getEntityById(NewsInfo.class, id);
		if (newsInfo == null || newsInfo.getDeleted())
			throw new RuntimeException("无效的数据");
		accountLogin = mNewsService.getEntityById(AccountLogin.class, newsInfo.getLoginId());
		if (newsInfo.getContentId() != null) {
			SysTextLibrary textLibrary = mNewsService.getEntityById(SysTextLibrary.class, newsInfo
					.getContentId());
			if (textLibrary != null)
				content = textLibrary.getContext();
		}
		if (newsInfo.getTitleImageId() != null) {
			SysImageLibrary image = mNewsService.getEntityById(SysImageLibrary.class, newsInfo
					.getTitleImageId());
			if (image != null)
				titleImg = image.getImageUrl();
		}

		return INFO;
	}

	/**
	 * 显示添加资讯页面 查询所有分类
	 * 
	 * @return
	 */
	public String add() {
		classList = mNewsService.findNewsList();
		fckContent = FckeditorUtil.getFckeditorHtml(request, "content", "{fck:true}", "");
		return ADD;
	}

	/**
	 * 显示保存资讯页面
	 * 
	 * @return
	 */
	public String save() {
		this.getAccount();
		newsInfo.setLoginId(this.getLoginId());
		NewsClass newsClass = mNewsService.getEntityById(NewsClass.class, newsClassCodeId);
		if (newsClass == null || newsClass.getDeleted())
			throw new RuntimeException("无效的分类");
		mNewsService.doSaveNews(newsClass, newsInfo, content, titleImg);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("添加成功!");
		return JDIALOG;
	}

	/**
	 * 显示修改资讯页面
	 * 
	 * @return
	 */
	public String edit() {
		newsInfo = mNewsService.getEntityById(NewsInfo.class, id);
		if (newsInfo == null || newsInfo.getDeleted()) {
			throw new RuntimeException("数据不存在或已经删除!");
		}
		classList = mNewsService.findNewsList();
		if (newsInfo.getContentId() != null) {
			SysTextLibrary textLibrary = mNewsService.getEntityById(SysTextLibrary.class, newsInfo
					.getContentId());
			if (textLibrary != null)
				content = textLibrary.getContext();
		}
		fckContent = FckeditorUtil.getFckeditorHtml(request, "content", "{fck:true}", content);
		if (newsInfo.getTitleImageId() != null) {
			SysImageLibrary image = mNewsService.getEntityById(SysImageLibrary.class, newsInfo
					.getTitleImageId());
			if (image != null)
				titleImg = image.getImageUrl();
		}
		return EDIT;
	}

	/**
	 * 保存修改
	 * 
	 * @return
	 */
	public String update() {
		this.getAccount();
		newsInfo.setId(id);
		newsInfo.setLoginId(this.getLoginId());
		NewsClass newsClass = mNewsService.getEntityById(NewsClass.class, newsClassCodeId);
		if (newsClass == null || newsClass.getDeleted())
			throw new RuntimeException("无效的数据");
		newsInfo.setClassCode(newsClass.getCode());
		newsInfo.setClassName(newsClass.getName());
		mNewsService.doUpdateNews(newsInfo, content, titleImg);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("修改成功!");
		return JDIALOG;
	}

	/**
	 * 资讯信息置顶
	 * 
	 * @return
	 */
	public String top() {
		newsInfo = mNewsService.getEntityById(NewsInfo.class, id);
		if (newsInfo == null || newsInfo.getDeleted())
			throw new RuntimeException("数据异常，资讯信息不存在");
		// 设置排序值为当前时间的转整型
		newsInfo.setOrderid((int) (new Date().getTime() / 10000));
		mNewsService.update(newsInfo);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("置顶成功!");
		return JDIALOG;
	}

	/**
	 * 删除资讯信息
	 * 
	 * @return
	 */
	public String del() {
		newsInfo = mNewsService.getEntityById(NewsInfo.class, id);
		if (newsInfo == null || newsInfo.getDeleted())
			throw new RuntimeException("数据异常，资讯信息不存在");
		mNewsService.delete(newsInfo);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("删除操作完成!");
		return JDIALOG;
	}

	public NewsInfo getNewsInfo() {
		return newsInfo;
	}

	public void setNewsInfo(NewsInfo newsInfo) {
		this.newsInfo = newsInfo;
	}

	public Page<NewsExtendInfo> getPage() {
		return page;
	}

	public void setPage(Page<NewsExtendInfo> page) {
		this.page = page;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFckContent() {
		return fckContent;
	}

	public void setFckContent(String fckContent) {
		this.fckContent = fckContent;
	}

	public NewsClass getNewsClass() {
		return newsClass;
	}

	public void setNewsClass(NewsClass newsClass) {
		this.newsClass = newsClass;
	}

	public List<NewsClass> getClassList() {
		return classList;
	}

	public AccountLogin getAccountLogin() {
		return accountLogin;
	}

	public void setAccountLogin(AccountLogin accountLogin) {
		this.accountLogin = accountLogin;
	}

	public void setClassList(List<NewsClass> classList) {
		this.classList = classList;
	}

	public Long getNewsClassCodeId() {
		return newsClassCodeId;
	}

	public void setNewsClassCodeId(Long newsClassCodeId) {
		this.newsClassCodeId = newsClassCodeId;
	}

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

}
