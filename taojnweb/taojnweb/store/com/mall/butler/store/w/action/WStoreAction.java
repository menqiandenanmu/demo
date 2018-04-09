package com.mall.butler.store.w.action;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.action.w.WebBaseAction;
import com.mall.butler.store.model.GoodsInfo;
import com.mall.butler.store.w.service.WGoodsInfoService;
import com.mall.butler.sys.model.SysImageLibrary;
import com.mall.butler.sys.model.SysTextLibrary;
import com.mall.util.common.TxtUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 前台商品
 * 
 * @author caedmon
 * @version 创建时间：2013-2-28 上午11:35:41
 */
public class WStoreAction extends WebBaseAction {

	private static final long serialVersionUID = 1L;

	private String content;
	// 商品图片
	private String titleImg;
	private Page<GoodsInfo> resultPage;
	@Autowired
	private WGoodsInfoService wGoodsInfoService;
	// 商品集合
	private List<GoodsInfo> goodsInfos;
	private GoodsInfo goodsInfo;

	/**
	 * 查询商铺
	 */
	public String execute() {
		if (goodsInfo == null)
			goodsInfo = new GoodsInfo();
		PageRequest<Map<String, Object>> pageRequest = new PageRequest<Map<String, Object>>();
		Map<String, Object> map = new Hashtable<String, Object>();
		if (!(goodsInfo.getGoodsName() != null && goodsInfo.getGoodsName().trim().equals("请输入关键字"))) {
			if (!TxtUtil.isEmpty(goodsInfo.getGoodsName()))
				map.put(GoodsInfo.GOODSNAME, "%" + goodsInfo.getGoodsName().trim() + "%");
			else {
				goodsInfo.setGoodsName("请输入关键字");
			}
		}
		map.put(GoodsInfo.USEFLAG, true);
		pageRequest.setPageNumber(currPage);
		pageRequest.setFilters(map);
		resultPage = wGoodsInfoService.queryPage(pageRequest);

		// 商品集合
		// goodsInfos = wGoodsStoreInfoService.getGoodsByStoreId(super.getId());
		return SUCCESS;
	}

	/**
	 * 店铺详细及商品集合
	 */
	public String info() {
		goodsInfo = wGoodsInfoService.getEntityById(GoodsInfo.class, super.getId());
		if (goodsInfo == null || goodsInfo.getDeleted())
			throw new RuntimeException("无效的商品信息");
		if (goodsInfo.getContentId() != null) {
			SysTextLibrary textLibrary = wGoodsInfoService.getEntityById(SysTextLibrary.class,
					goodsInfo.getContentId());
			if (textLibrary != null)
				content = textLibrary.getContext();
		}
		if (goodsInfo.getTitleImgId() != null) {
			SysImageLibrary image = wGoodsInfoService.getEntityById(SysImageLibrary.class,
					goodsInfo.getTitleImgId());
			if (image != null)
				titleImg = image.getImageUrl();
		}
		return INFO;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	public Page<GoodsInfo> getResultPage() {
		return resultPage;
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

	public List<GoodsInfo> getGoodsInfos() {
		return goodsInfos;
	}

	public void setGoodsInfos(List<GoodsInfo> goodsInfos) {
		this.goodsInfos = goodsInfos;
	}

}
