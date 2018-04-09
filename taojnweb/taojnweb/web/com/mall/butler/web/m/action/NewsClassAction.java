package com.mall.butler.web.m.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.web.m.service.MNewsService;
import com.mall.butler.web.model.NewsClass;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 类描述：资讯内容管理
 * 类名称：NewsClassAction
 * 创建人：caedmon
 * 创建时间：2013-5-18 下午09:46:21
 * 修改人：caedmon
 * 修改时间：2013-5-18 下午09:46:21
 * 修改备注：
 * 
 * @version
 */
public class NewsClassAction extends ManageBaseAction {

	private static final long serialVersionUID = 1L;
	private NewsClass newsClass;
	@Autowired
	private MNewsService mNewsService;
	private Page<NewsClass> page;
	/**
	 * 主键
	 */
	private String content;
	private List<NewsClass> classList;
	private String code;// 编号
	private String codeFather;// 父类编号

	public String execute() {
		PageRequest<NewsClass> request = super.newPage(NewsClass.class);
		request.setPageNumber(this.currPage);
		if (newsClass == null)
			newsClass = new NewsClass();
		request.setFilters(newsClass);
		page = mNewsService.queryPage(request);
		return LIST;
	}

	/**
	 * 显示添加资讯页面
	 * 
	 * @return
	 */
	public String add() {
		newsClass = mNewsService.getEntityById(NewsClass.class, id);
		return ADD;
	}

	/**
	 * 显示保存资讯页面
	 * 
	 * @return
	 */
	public String save() {
		if (TxtUtil.isEmpty(newsClass.getCode()))
			throw new RuntimeException("编号不可以为空!");
		newsClass.setCode(newsClass.getCode().trim());
		if (TxtUtil.isEmpty(newsClass.getName()))
			throw new RuntimeException("名称不可以为空!");
		newsClass.setName(newsClass.getName().trim());
		if (newsClass.getParentId() != null) {
			code = codeFather + newsClass.getCode();
			newsClass.setCode(code);
			NewsClass pClass = mNewsService.getEntityById(NewsClass.class, newsClass.getParentId());
			if (pClass == null || pClass.getDeleted())
				throw new RuntimeException("数据异常，资讯信息不存在");
			mNewsService.doSaveClass(pClass, newsClass);
		} else {
			mNewsService.doSaveClass(null, newsClass);
		}
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
		newsClass = mNewsService.getEntityById(NewsClass.class, id);
		if (newsClass == null || newsClass.getDeleted())
			throw new RuntimeException("数据不存在或已经删除!");
		return EDIT;
	}

	/**
	 * 保存修改
	 * 
	 * @return
	 */
	public String update() {
		newsClass.setId(id);
		mNewsService.doUpdateClass(newsClass);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("修改成功!");
		return JDIALOG;
	}

	/**
	 * 删除资讯信息
	 * 
	 * @return
	 */
	public String del() {
		newsClass = mNewsService.getEntityById(NewsClass.class, id);
		if (newsClass == null || newsClass.getDeleted())
			throw new RuntimeException("数据不存在或已经删除!");
		mNewsService.delete(newsClass);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("删除操作完成!");
		return JDIALOG;
	}

	public NewsClass getNewsClass() {
		return newsClass;
	}

	public void setNewsClass(NewsClass newsClass) {
		this.newsClass = newsClass;
	}

	public Page<NewsClass> getPage() {
		return page;
	}

	public void setPage(Page<NewsClass> page) {
		this.page = page;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<NewsClass> getClassList() {
		return classList;
	}

	public void setClassList(List<NewsClass> classList) {
		this.classList = classList;
	}

	public String getCodeFather() {
		return codeFather;
	}

	public void setCodeFather(String codeFather) {
		this.codeFather = codeFather;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
