package com.mall.butler.sys.m.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.sys.m.service.MDictService;
import com.mall.butler.sys.model.SysDict;
import com.mall.butler.sys.model.SysDictDetail;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.action.MessageDialog;

/**
 * 类描述：数据字典管理
 * 类名称：DictAction
 * 创建人：caedmon
 * 创建时间：2013-5-18 下午09:24:14
 * 修改人：caedmon
 * 修改时间：2013-5-18 下午09:24:14
 * 修改备注：
 * 
 * @version
 */
public class DictAction extends ManageBaseAction {

	private static final long serialVersionUID = -2148896972382745188L;
	private static final String DETAILLIST = "detailList";
	private static final String DETAILEDIT = "detailEdit";
	private static final String DETAILADD = "detailAdd";
	@Autowired
	private MDictService mDictService;
	/**
	 * 列表信息
	 */
	private List<SysDict> dictL;
	private List<SysDictDetail> detailL;
	private SysDict dict;
	private SysDictDetail detail;
	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 列表
	 */
	public String execute() {
		dictL = mDictService.queryAllDict();
		return LIST;
	}

	/**
	 * 明细列表
	 * 
	 * @return
	 */
	public String detailList() {
		dict = mDictService.getEntityById(SysDict.class, id);
		if (dict == null)
			throw new RuntimeException("无效的信息!");
		detailL = mDictService.queryDetail(dict);
		return DETAILLIST;
	}

	/**
	 * 添加明细
	 * 
	 * @return
	 */
	public String detailAdd() {
		dict = mDictService.getEntityById(SysDict.class, id);
		if (dict == null)
			throw new RuntimeException("无效的信息!");
		return DETAILADD;
	}

	/**
	 * 保存明细信息
	 * 
	 * @return
	 */
	public String detailSave() {
		dict = mDictService.getEntityById(SysDict.class, id);
		if (dict == null)
			throw new RuntimeException("无效的信息!");
		detail.setDictId(dict.getId());
		if (TxtUtil.isEmpty(detail.getDictDetailName())) {
			throw new RuntimeException("名称不可以为空!");
		}
		detail.setDictDetailName(detail.getDictDetailName().trim());
		if (TxtUtil.isEmpty(detail.getDictDetailCode())) {
			throw new RuntimeException("编号不可以为空!");
		}
		detail.setDictDetailCode(dict.getDictCode() + detail.getDictDetailCode().trim());
		if (TxtUtil.isEmpty(detail.getDictDetailValue())) {
			throw new RuntimeException("值不可以为空!");
		}
		detail.setDictDetailValue(detail.getDictDetailValue().trim());
		mDictService.doAddDetail(detail);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("添加成功!");
		return JDIALOG;
	}

	/**
	 * @Title: 修改页面
	 * @auth：caedmon
	 * @date 2013-5-18 下午09:24:53
	 * @return
	 */
	public String detailEdit() {
		detail = mDictService.getEntityById(SysDictDetail.class, id);
		dict = mDictService.getEntityById(SysDict.class, detail.getDictId());
		return DETAILEDIT;
	}

	/**
	 * @Title: 保存修改
	 * @auth：caedmon
	 * @date 2013-5-18 下午09:25:06
	 * @return
	 */
	public String detailUpdate() {
		detail.setId(id);
		if (TxtUtil.isEmpty(detail.getDictDetailName())) {
			throw new RuntimeException("名称不可以为空!");
		}
		detail.setDictDetailName(detail.getDictDetailName().trim());
		if (TxtUtil.isEmpty(detail.getDictDetailValue())) {
			throw new RuntimeException("值不可以为空!");
		}
		detail.setDictDetailValue(detail.getDictDetailValue().trim());
		mDictService.doUpdateDetail(detail);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("参数修改成功!");
		return JDIALOG;
	}

	/**
	 * 删除明细
	 * 
	 * @return
	 */
	public String detailDel() {
		detail = mDictService.getEntityById(SysDictDetail.class, id);
		if (detail == null || detail.getDeleted()) {
			msgInfo.setFlag(MessageDialog.ERROR);
			msgInfo.addMessage("无效的数据!");
		} else {
			mDictService.doDelDetail(detail);
			msgInfo.setFlag(MessageDialog.SUCCESS);
			msgInfo.addMessage("删除操作完成!");
		}
		return JDIALOG;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SysDictDetail getDetail() {
		return detail;
	}

	public void setDetail(SysDictDetail detail) {
		this.detail = detail;
	}

	public List<SysDict> getDictL() {
		return dictL;
	}

	public SysDict getDict() {
		return dict;
	}

	public void setDict(SysDict dict) {
		this.dict = dict;
	}

	public List<SysDictDetail> getDetailL() {
		return detailL;
	}

}
