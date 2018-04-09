package com.mall.butler.web.m.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.ManageContext;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.sys.m.service.MDictService;
import com.mall.butler.sys.model.SysDictDetail;
import com.mall.butler.sys.model.SysTextLibrary;
import com.mall.butler.util.FckeditorUtil;
import com.mall.butler.web.m.service.MStaticService;
import com.mall.butler.web.model.StaticInfo;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 类描述：系统静态内容管理
 * 类名称：StaticAction
 * 创建人：caedmon
 * 创建时间：2013-5-18 下午09:47:07
 * 修改人：caedmon
 * 修改时间：2013-5-18 下午09:47:07
 * 修改备注：
 * 
 * @version
 */
public class StaticAction extends ManageBaseAction {

	private static final long serialVersionUID = -2148896972382745188L;
	@Autowired
	private MDictService dictService;
	@Autowired
	private MStaticService mStaticService;
	/**
	 * 列表信息
	 */
	private Page<StaticInfo> staticPage;
	private StaticInfo info;
	private String content;
	private String str;
	private String fckContent;
	private List<SysDictDetail> dictList;

	/**
	 * 列表
	 */
	public String execute() {
		// 查找静态字典分类
		dictList = dictService.queryDetail(ManageContext.DICT_STATIC_CLASS);
		PageRequest<StaticInfo> request = super.newPage(StaticInfo.class);
		StaticInfo filter = new StaticInfo();
		if (info != null && !TxtUtil.isEmpty(info.getName()))
			filter.setName("%" + info.getName().trim() + "%");
		if (info != null && !TxtUtil.isEmpty(info.getClassCode()))
			filter.setClassCode(info.getClassCode());
		request.setPageNumber(this.currPage);
		request.setFilters(filter);
		staticPage = mStaticService.queryStatic(request);
		return LIST;
	}

	/**
	 * 添加静态内容
	 * 
	 * @return
	 */
	public String add() {
		// 查找静态字典分类
		dictList = dictService.queryDetail(ManageContext.DICT_STATIC_CLASS);
		fckContent = FckeditorUtil.getFckeditorHtml(request, "content", "{fck:true}", "");
		return ADD;
	}

	/**
	 * 保存新增
	 * 
	 * @return
	 */
	public String save() {
		if (TxtUtil.isEmpty(info.getName()))
			throw new RuntimeException("名称不可以为空!");
		info.setName(info.getName().trim());
		if (TxtUtil.isEmpty(info.getClassCode()))
			throw new RuntimeException("分类不可以为空!");
		info.setClassCode(info.getClassCode().trim());
		if (TxtUtil.isEmpty(content))
			throw new RuntimeException("内容不可以为空!");
		mStaticService.doSaveStatic(info, content);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("添加成功!");
		return JDIALOG;
	}

	/**
	 * 修改
	 * 
	 * @return
	 */
	public String edit() {
		info = mStaticService.getEntityById(StaticInfo.class, id);
		if (info == null || info.getDeleted())
			throw new RuntimeException("数据不存在或已经删除!");
		SysTextLibrary text = mStaticService.getEntityById(SysTextLibrary.class, info
				.getContentId());
		content = text == null ? "" : text.getContext();
		fckContent = FckeditorUtil.getFckeditorHtml(request, "content", "{fck:true}", content);
		return EDIT;
	}

	/**
	 * 保存修改
	 * 
	 * @return
	 */
	public String update() {
		info = mStaticService.getEntityById(StaticInfo.class, id);
		if (TxtUtil.isEmpty(content))
			throw new RuntimeException("内容不可以为空!");
		mStaticService.doUpdateStatic(info, content);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("修改成功!");
		return JDIALOG;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String del() {
		info = mStaticService.getEntityById(StaticInfo.class, id);
		if (info == null) {
			msgInfo.setFlag(MessageDialog.ERROR);
			msgInfo.addMessage("无效的数据!");
		} else {
			mStaticService.doDelStatic(info);
			msgInfo.setFlag(MessageDialog.SUCCESS);
			msgInfo.addMessage("删除操作完成!");
		}
		return JDIALOG;
	}

	public List<SysDictDetail> getDictList() {
		return dictList;
	}

	public void setDictList(List<SysDictDetail> dictList) {
		this.dictList = dictList;
	}

	public void setFckContent(String fckContent) {
		this.fckContent = fckContent;
	}

	public StaticInfo getInfo() {
		return info;
	}

	public void setInfo(StaticInfo info) {
		this.info = info;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Page<StaticInfo> getStaticPage() {
		return staticPage;
	}

	public String getFckContent() {
		return fckContent;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

}
