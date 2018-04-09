package com.mall.butler.store.m.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.ManageContext;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.store.GoodsContext;
import com.mall.butler.store.m.service.MGoodsInfoService;
import com.mall.butler.store.model.GoodsInfo;
import com.mall.butler.sys.m.service.MDictService;
import com.mall.butler.sys.m.service.MSysTextLibrayService;
import com.mall.butler.sys.model.SysDictDetail;
import com.mall.butler.sys.model.SysImageLibrary;
import com.mall.butler.sys.model.SysTextLibrary;
import com.mall.butler.util.FckeditorUtil;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 类描述：商品管理
 * 类名称：GoodsInfoAction
 * 创建人：caedmon
 * 创建时间：2013-5-18 下午10:29:10
 * 修改人：caedmon
 * 修改时间：2013-5-18 下午10:29:10
 * 修改备注：
 * 
 * @version
 */
public class GoodsInfoAction extends ManageBaseAction {
	private static final long serialVersionUID = -428652350327352850L;
	@Autowired
	private MGoodsInfoService mGoodsInfoService;
	@Autowired
	private MSysTextLibrayService mSysTextLibrayService;
	@Autowired
	private MDictService mDictService;
	private Page<GoodsInfo> resultPage;
	private GoodsInfo goodsInfo;
	private String fileName;
	private String titleImg;
	private List<SysDictDetail> dictTagsL;// 商品标签
	private List<SysDictDetail> colorDicts;// 颜色分类
	private List<SysDictDetail> styleDicts;// 款式分类
	private List<SysDictDetail> sizeDicts;// 尺寸分类
	@Autowired
	private MDictService dictService;
	// 标签
	private String[] tagsCode;

	// 查询
	public String execute() {
		if (goodsInfo == null)
			goodsInfo = new GoodsInfo();
		PageRequest<GoodsInfo> pageRequest = super.newPage(GoodsInfo.class);
		pageRequest.setPageNumber(currPage);
		pageRequest.setFilters(goodsInfo);
		resultPage = mGoodsInfoService.findPage(pageRequest);
		// 查询颜色
		colorDicts = dictService.queryDetail(ManageContext.DICT_COLOR_CLASS);
		// 查询款式
		styleDicts = dictService.queryDetail(ManageContext.DICT_STYLE_CLASS);
		// 查询尺寸
		sizeDicts = dictService.queryDetail(ManageContext.DICT_SIZE_CLASS);
		return LIST;
	}

	// 去新增
	public String add() {
		// 商品标签
		dictTagsL = mDictService.queryDetail(GoodsContext.STORE_TAGS_DICT_CODE);
		// 查询颜色
		colorDicts = dictService.queryDetail(ManageContext.DICT_COLOR_CLASS);
		// 查询款式
		styleDicts = dictService.queryDetail(ManageContext.DICT_STYLE_CLASS);
		// 查询尺寸
		sizeDicts = dictService.queryDetail(ManageContext.DICT_SIZE_CLASS);
		if (goodsInfo == null) {
			goodsInfo = new GoodsInfo();
		}
		String contentFckHtml = FckeditorUtil.getFckeditorHtml(request, "goodsInfo.contentStr",
				"{fck:true}", "");
		goodsInfo.setContentStr(contentFckHtml);
		String infoFckHtml = FckeditorUtil.getFckeditorHtml(request, "goodsInfo.infoStr",
				"{fck:true}", "");
		goodsInfo.setInfoStr(infoFckHtml);
		return ADD;
	}

	// 新增保存
	public String save() {
		AccountLogin loginer = this.getLogin();
		if (tagsCode != null && tagsCode.length > 0) {
			String codeStr = ",,";
			String nameStr = ",,";
			for (String codeIndex : tagsCode) {
				SysDictDetail dict = mDictService.getDetail(codeIndex);
				if (dict != null) {
					codeStr += "," + dict.getDictDetailCode();
					nameStr += "," + dict.getDictDetailValue();
				}
			}
			if (codeStr.equals(",,"))
				codeStr = "";
			else
				codeStr = codeStr.replaceAll(",,,", "");
			if (codeStr.equals(",,"))
				nameStr = "";
			else
				nameStr = nameStr.replaceAll(",,,", "");
			goodsInfo.setTagCodes(codeStr);
			goodsInfo.setTagNames(nameStr);
		}
		mGoodsInfoService.doSave(goodsInfo, fileName, loginer);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("新增商品成功!");
		return JDIALOG;
	}

	// 去修改
	public String edit() {
		// 商品标签
		dictTagsL = mDictService.queryDetail(GoodsContext.STORE_TAGS_DICT_CODE);
		// 查询颜色
		colorDicts = dictService.queryDetail(ManageContext.DICT_COLOR_CLASS);
		// 查询款式
		styleDicts = dictService.queryDetail(ManageContext.DICT_STYLE_CLASS);
		// 查询尺寸
		sizeDicts = dictService.queryDetail(ManageContext.DICT_SIZE_CLASS);
		goodsInfo = mGoodsInfoService.getEntityById(GoodsInfo.class, id);
		if (goodsInfo == null || goodsInfo.getDeleted())
			throw new RuntimeException("商品信息不存在或已被删除");
		SysTextLibrary text_content = mSysTextLibrayService.getEntityById(SysTextLibrary.class,
				goodsInfo.getContentId());
		String contentFckHtml = FckeditorUtil.getFckeditorHtml(request, "goodsInfo.contentStr",
				"{fck:true}", text_content == null ? "" : text_content.getContext());
		goodsInfo.setContentStr(contentFckHtml);
		SysTextLibrary text_info = mSysTextLibrayService.getEntityById(SysTextLibrary.class,
				goodsInfo.getInfoId());
		String infoFckHtml = FckeditorUtil.getFckeditorHtml(request, "goodsInfo.infoStr",
				"{fck:true}", text_info == null ? "" : text_info.getContext());
		goodsInfo.setInfoStr(infoFckHtml);
		if (goodsInfo.getTitleImgId() != null) {
			SysImageLibrary image = mGoodsInfoService.getEntityById(SysImageLibrary.class,
					goodsInfo.getTitleImgId());
			if (image != null)
				titleImg = image.getImageUrl();
		}
		return EDIT;
	}

	// 修改保存
	public String update() {
		goodsInfo.setId(id);
		AccountLogin loginer = this.getLogin();
		if (tagsCode != null && tagsCode.length > 0) {
			String codeStr = ",,";
			String nameStr = ",,";
			for (String codeIndex : tagsCode) {
				SysDictDetail dict = mDictService.getDetail(codeIndex);
				if (dict != null) {
					codeStr += "," + dict.getDictDetailCode();
					nameStr += "," + dict.getDictDetailValue();
				}
			}
			if (codeStr.equals(",,"))
				codeStr = "";
			else
				codeStr = codeStr.replaceAll(",,,", "");
			if (codeStr.equals(",,"))
				nameStr = "";
			else
				nameStr = nameStr.replaceAll(",,,", "");
			goodsInfo.setTagCodes(codeStr);
			goodsInfo.setTagNames(nameStr);
		}
		mGoodsInfoService.doUpdate(goodsInfo, titleImg, loginer);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("更新商品成功!");
		return JDIALOG;
	}

	public String enableSell() {
		goodsInfo = mGoodsInfoService.getEntityById(GoodsInfo.class, id);
		if (goodsInfo == null || goodsInfo.getDeleted())
			throw new RuntimeException("无效的商品信息!");
		mGoodsInfoService.doEnabledSell(goodsInfo);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("操作成功!");
		return JDIALOG;
	}

	public String disableSell() {
		goodsInfo = mGoodsInfoService.getEntityById(GoodsInfo.class, id);
		if (goodsInfo == null || goodsInfo.getDeleted())
			throw new RuntimeException("无效的商品信息!");
		mGoodsInfoService.doDisableSell(goodsInfo);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("操作成功!");
		return JDIALOG;
	}

	// 删除保存
	public String del() {
		goodsInfo = mGoodsInfoService.getEntityById(GoodsInfo.class, id);
		if (goodsInfo == null || goodsInfo.getDeleted())
			throw new RuntimeException("商品信息不存在或已被删除");
		mGoodsInfoService.doDel(goodsInfo);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("删除商品成功!");
		return JDIALOG;
	}

	// 详情
	public String info() {
		goodsInfo = mGoodsInfoService.getEntityById(GoodsInfo.class, id);
		if (goodsInfo == null || goodsInfo.getDeleted())
			throw new RuntimeException("商品信息不存在或已被删除");
		SysTextLibrary text_content = mSysTextLibrayService.getEntityById(SysTextLibrary.class,
				goodsInfo.getContentId());
		goodsInfo.setContentStr(text_content.getContext());
		SysTextLibrary text_info = mSysTextLibrayService.getEntityById(SysTextLibrary.class,
				goodsInfo.getInfoId());
		goodsInfo.setInfoStr(text_info.getContext());
		return INFO;
	}

	public List<SysDictDetail> getDictTagsL() {
		return dictTagsL;
	}

	public void setTagsCode(String[] tagsCode) {
		this.tagsCode = tagsCode;
	}

	public Page<GoodsInfo> getResultPage() {
		return resultPage;
	}

	public List<SysDictDetail> getColorDicts() {
		return colorDicts;
	}

	public List<SysDictDetail> getStyleDicts() {
		return styleDicts;
	}

	public List<SysDictDetail> getSizeDicts() {
		return sizeDicts;
	}

	public String[] getTagsCode() {
		return tagsCode;
	}

	public void setResultPage(Page<GoodsInfo> resultPage) {
		this.resultPage = resultPage;
	}

	public GoodsInfo getGoodsInfo() {
		return goodsInfo;
	}

	public void setGoodsInfo(GoodsInfo goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	public void setDictTagsL(List<SysDictDetail> dictTagsL) {
		this.dictTagsL = dictTagsL;
	}

	public void setStyleDicts(List<SysDictDetail> styleDicts) {
		this.styleDicts = styleDicts;
	}

	public void setSizeDicts(List<SysDictDetail> sizeDicts) {
		this.sizeDicts = sizeDicts;
	}
}
