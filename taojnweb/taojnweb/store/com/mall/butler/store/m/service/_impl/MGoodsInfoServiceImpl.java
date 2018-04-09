package com.mall.butler.store.m.service._impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.butler.store.dao.GoodsInfoDao;
import com.mall.butler.store.dao.GoodsStockDao;
import com.mall.butler.store.m.service.MGoodsInfoService;
import com.mall.butler.store.model.GoodsInfo;
import com.mall.butler.store.model.GoodsStock;
import com.mall.butler.sys.dao.SysTextLibraryDao;
import com.mall.butler.sys.m.service.MDictService;
import com.mall.butler.sys.m.service.MLibraryService;
import com.mall.butler.sys.model.SysDictDetail;
import com.mall.butler.sys.model.SysImageLibrary;
import com.mall.butler.sys.model.SysTextLibrary;
import com.mall.util.common.TxtUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 商品业务
 * 
 * @author zhaoxs
 */
public class MGoodsInfoServiceImpl extends BaseServiceImpl implements MGoodsInfoService {
	@Autowired
	private GoodsInfoDao goodsInfoDao;
	@Autowired
	private SysTextLibraryDao sysTextLibraryDao;
	@Autowired
	private MLibraryService mLibraryService;
	@Autowired
	private MDictService mDictService;
	@Autowired
	private GoodsStockDao goodsStockDao;

	/**
	 * 验证数据
	 */
	private void validateData(GoodsInfo goodsInfo) {
		GoodsInfo codeFilter = new GoodsInfo();
		codeFilter.setGoodsCode(goodsInfo.getGoodsCode());
		codeFilter.setDeleted(false);
		if (goodsInfoDao.queryEntitys("ALL", codeFilter).size() > 0) {
			throw new RuntimeException("商品码重复");
		}
		GoodsInfo nameFilter = new GoodsInfo();
		nameFilter.setDeleted(false);
		nameFilter.setGoodsName(goodsInfo.getGoodsName().trim());
		if (goodsInfoDao.queryEntitys("ALL", nameFilter).size() > 0) {
			throw new RuntimeException("商品名称重复");
		}
	}

	@Override
	public void doDel(GoodsInfo goodsInfo) {
		goodsInfo = goodsInfoDao.getById(goodsInfo.getId());
		goodsInfoDao.delete(goodsInfo);
	}

	@Override
	public List<GoodsInfo> findList(GoodsInfo goodsInfo) {
		return goodsInfoDao.find(goodsInfo);
	}

	@Override
	public Page<GoodsInfo> findPage(PageRequest<GoodsInfo> pageRequest) {
		return goodsInfoDao.page(pageRequest);
	}

	@Transactional
	@Override
	public void doSave(GoodsInfo goodsInfo, String fileName, AccountLogin loginer) {
		// 验证数据
		validateData(goodsInfo);
		Long newTextId1 = this.sysTextLibraryDao.getNewId();
		SysTextLibrary text1 = new SysTextLibrary();
		text1.setId(newTextId1);
		text1.setContext(goodsInfo.getContentStr());
		this.sysTextLibraryDao.insert(text1);
		goodsInfo.setContentId(newTextId1);

		Long newTextId2 = this.sysTextLibraryDao.getNewId();
		SysTextLibrary text2 = new SysTextLibrary();
		text2.setId(newTextId2);
		text2.setContext(goodsInfo.getInfoStr());
		this.sysTextLibraryDao.insert(text2);
		goodsInfo.setInfoId(newTextId2);

		if (fileName != null) {
			if (!"".equals(fileName)) {
				SysImageLibrary image = mLibraryService.doSaveImage(fileName);
				goodsInfo.setTitleImgId(image.getId());
			}
		}
		// 款式
		SysDictDetail styleDict = mDictService.getDetail(goodsInfo.getStyleClassCode());
		if (styleDict == null)
			throw new RuntimeException("无效的款式信息!");
		goodsInfo.setStyleClassCode(styleDict.getDictDetailCode());
		goodsInfo.setStyleClassName(styleDict.getDictDetailValue());
		// 颜色
		SysDictDetail colorDict = mDictService.getDetail(goodsInfo.getColorClassCode());
		if (colorDict == null)
			throw new RuntimeException("无效的款式信息!");
		goodsInfo.setColorClassCode(colorDict.getDictDetailCode());
		goodsInfo.setColorClassName(colorDict.getDictDetailValue());
		// 尺寸
		SysDictDetail sizeDict = mDictService.getDetail(goodsInfo.getSizeClassCode());
		if (sizeDict == null)
			throw new RuntimeException("无效的款式信息!");
		goodsInfo.setSizeClassCode(sizeDict.getDictDetailCode());
		goodsInfo.setSizeClassName(sizeDict.getDictDetailValue());
		goodsInfo.setId(goodsInfoDao.getNewId());
		goodsInfoDao.insert(goodsInfo);
		// 添加库存表
		GoodsStock goodsStock = new GoodsStock();
		goodsStock.setId(goodsStockDao.getNewId());
		goodsStock.setGoodsId(goodsInfo.getId());
		goodsStock.setGoodsName(goodsInfo.getGoodsName());
		goodsStock.setLoginId(loginer.getId());
		goodsStock.setLoginName(loginer.getLoginName());
		goodsStock.setNum(0);
		goodsStock.setTotalNum(0);
		goodsStockDao.insert(goodsStock);
	}

	@Transactional
	@Override
	public void doUpdate(GoodsInfo goodsInfo, String titleImg, AccountLogin loginer) {
		GoodsInfo oldObj = goodsInfoDao.getById(goodsInfo.getId());
		SysTextLibrary oldText1 = sysTextLibraryDao.getById(oldObj.getContentId());
		SysTextLibrary oldText2 = sysTextLibraryDao.getById(oldObj.getInfoId());
		if (oldObj != null) {
			if (goodsInfo.getContentStr() != null) {
				if (oldText1 != null) {
					if (!goodsInfo.getContentStr().equals(oldText1.getContext())) {
						Long newTextId = this.sysTextLibraryDao.getNewId();
						SysTextLibrary text = new SysTextLibrary();
						text.setId(newTextId);
						text.setContext(goodsInfo.getContentStr());
						this.sysTextLibraryDao.insert(text);
						oldObj.setContentId(newTextId);
					}
				}
			}
			if (goodsInfo.getInfoStr() != null) {
				if (oldText2 != null) {
					if (!goodsInfo.getInfoStr().equals(oldText2.getContext())) {
						Long newTextId = this.sysTextLibraryDao.getNewId();
						SysTextLibrary text = new SysTextLibrary();
						text.setId(newTextId);
						text.setContext(goodsInfo.getInfoStr());
						this.sysTextLibraryDao.insert(text);
						oldObj.setInfoId(newTextId);
					}
				}
			}
			if (oldObj.getTitleImgId() != null) {
				if (TxtUtil.isEmpty(titleImg))
					oldObj.setTitleImgId(null);
				else {
					SysImageLibrary image = this.getEntityById(SysImageLibrary.class, oldObj
							.getTitleImgId());
					if (image == null || !image.getImageUrl().endsWith(titleImg)) {
						image = mLibraryService.doSaveImage(titleImg);
						oldObj.setTitleImgId(image.getId());
					}
				}
			} else {
				if (!TxtUtil.isEmpty(titleImg)) {
					SysImageLibrary image = mLibraryService.doSaveImage(titleImg);
					oldObj.setTitleImgId(image.getId());
				}
			}
			oldObj.setPurchasePrice(goodsInfo.getPurchasePrice());
			oldObj.setSellPrice(goodsInfo.getSellPrice());
			oldObj.setSummarize(goodsInfo.getSummarize());
			oldObj.setMaxAmount(goodsInfo.getMaxAmount());
			oldObj.setMinAmount(goodsInfo.getMinAmount());
			oldObj.setTagNames(goodsInfo.getTagNames());
			oldObj.setTagCodes(goodsInfo.getTagCodes());
			oldObj.setUseFlag(goodsInfo.getUseFlag());
			oldObj.setRemark(goodsInfo.getRemark());
			oldObj.setOrderid(goodsInfo.getOrderid());
			oldObj.setSeason(goodsInfo.getSeason());
			// 款式
			SysDictDetail styleDict = mDictService.getDetail(goodsInfo.getStyleClassCode());
			if (styleDict == null)
				throw new RuntimeException("无效的款式信息!");
			oldObj.setStyleClassCode(styleDict.getDictDetailCode());
			oldObj.setStyleClassName(styleDict.getDictDetailValue());
			// 颜色
			SysDictDetail colorDict = mDictService.getDetail(goodsInfo.getColorClassCode());
			if (colorDict == null)
				throw new RuntimeException("无效的款式信息!");
			oldObj.setColorClassCode(colorDict.getDictDetailCode());
			oldObj.setColorClassName(colorDict.getDictDetailValue());
			// 尺寸
			SysDictDetail sizeDict = mDictService.getDetail(goodsInfo.getSizeClassCode());
			if (sizeDict == null)
				throw new RuntimeException("无效的款式信息!");
			oldObj.setSizeClassCode(sizeDict.getDictDetailCode());
			oldObj.setSizeClassName(sizeDict.getDictDetailValue());
			this.goodsInfoDao.update(oldObj);
		}
	}

	@Override
	public void doDisableSell(GoodsInfo goods) {
		goods.setUseFlag(false);
		goodsInfoDao.update(goods);
	}

	@Override
	public void doEnabledSell(GoodsInfo goods) {
		goods.setUseFlag(true);
		goodsInfoDao.update(goods);
	}
}
