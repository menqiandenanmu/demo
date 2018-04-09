package com.mall.butler.web.m.service._impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.butler.web.dao.PageAreaDetailDao;
import com.mall.butler.web.dao.PageAreaInfoDao;
import com.mall.butler.web.m.service.MPageService;
import com.mall.butler.web.model.PageAreaDetail;
import com.mall.butler.web.model.PageAreaInfo;
import com.mall.util.common.TxtUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class MPageServiceImpl extends BaseServiceImpl implements MPageService {
	@Autowired
	PageAreaInfoDao pageAreaInfoDao;
	@Autowired
	PageAreaDetailDao pageAreaDetailDao;

	@Override
	public void doSavePageArea(PageAreaInfo area) {
		PageAreaInfo filter = new PageAreaInfo();
		filter.setCode(area.getCode());
		List<PageAreaInfo> paiList = pageAreaInfoDao.find(filter);
		if (paiList.size() > 0)
			throw new RuntimeException("编号重复!" + area.getCode());
		filter = new PageAreaInfo();
		filter.setName(area.getName());
		paiList = pageAreaInfoDao.find(filter);
		if (paiList.size() > 0)
			throw new RuntimeException("名称重复!" + area.getName());
		area.setId(pageAreaInfoDao.getNewId());
		pageAreaInfoDao.insert(area);
	}

	@Override
	public void doUpdatePageArea(PageAreaInfo area) {
		PageAreaInfo areaInfo = pageAreaInfoDao.getById(area.getId());
		areaInfo.setInfo(area.getInfo());
		areaInfo.setLen(area.getLen());
		pageAreaInfoDao.update(areaInfo);
	}

	@Override
	public void doSavePageDetail(PageAreaInfo area, PageAreaDetail detail) {
		if (area.getShowFlag() > 0 && TxtUtil.isEmpty(detail.getShowUrl()))
			throw new RuntimeException("显示内容不可以为空!");
		detail.setId(pageAreaDetailDao.getNewId());
		detail.setAreaId(area.getId());
		detail.setAreaName(area.getName());
		pageAreaDetailDao.insert(detail);
	}

	@Override
	public void doUpdatePageDetail(PageAreaDetail detail) {
		PageAreaDetail areaDetail = pageAreaDetailDao.getById(detail.getId());
		areaDetail.setInfo(detail.getInfo());
		areaDetail.setShowUrl(detail.getShowUrl());
		areaDetail.setTitle(detail.getTitle());
		areaDetail.setTargetFlag(detail.getTargetFlag());
		areaDetail.setOrderid(detail.getOrderid());
		pageAreaDetailDao.update(areaDetail);
	}

	@Override
	public Page<PageAreaDetail> queryPageDetail(PageRequest<PageAreaDetail> area) {
		return pageAreaDetailDao.page(area);
	}

	@Override
	public Page<PageAreaInfo> queryPageAreaPage(PageRequest<PageAreaInfo> area) {
		return pageAreaInfoDao.page(area);
	}

	@Override
	@Transactional
	public void doDelPageArea(PageAreaInfo area) {
		// 删除主表
		pageAreaInfoDao.delete(area);
		// 删除明细
		PageAreaDetail filter = new PageAreaDetail();
		filter.setAreaId(area.getId());
		List<PageAreaDetail> padList = pageAreaDetailDao.find(filter);
		for (PageAreaDetail padIndex : padList) {
			pageAreaDetailDao.delete(padIndex);
		}
	}

	@Override
	public void doDelPageDetail(PageAreaDetail detail) {
		// 删除主表
		pageAreaDetailDao.delete(detail);
	}
}
