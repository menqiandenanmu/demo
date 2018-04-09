package com.mall.butler.point.w.service._impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.point.dao.PointChangeDetalDao;
import com.mall.butler.point.model.PointChangeDetal;
import com.mall.butler.point.w.model.PointChangeDetalExtendDetail;
import com.mall.butler.point.w.service.WUserPointService;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class WUserPointServiceImpl extends BaseServiceImpl implements WUserPointService{

	@Autowired
	private PointChangeDetalDao pointChangeDetalDao;
	@SuppressWarnings("unchecked")
	@Override
	public Page<PointChangeDetal> queryPointDetail(PageRequest<Map> filter) {
		return  pointChangeDetalDao.pageQuery(filter, "BY_PAGE_DETAIL_ALL_W");
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<PointChangeDetal> queryPointAdd(PageRequest<Map> filter) {
		return  pointChangeDetalDao.pageQuery(filter, "BY_PAGE_DETAIL_ADD_W");
	}


	@SuppressWarnings("unchecked")
	@Override
	public Page<PointChangeDetal> queryPointCut(PageRequest<Map> filter) {
		return  pointChangeDetalDao.pageQuery(filter, "BY_PAGE_DETAIL_CUT_W");
	}


	@SuppressWarnings("unchecked")
	@Override
	public Page<PointChangeDetalExtendDetail> queryPointAddDetail(PageRequest<Map> filter) {
		return (Page<PointChangeDetalExtendDetail>) pointChangeDetalDao.pageQueryObj(filter, "BY_PAGE_DETAIL_ADD_W");
	}
	@SuppressWarnings("unchecked")
	@Override
	public Page<PointChangeDetalExtendDetail> queryPointCutDetail(PageRequest<Map> filter) {
		return (Page<PointChangeDetalExtendDetail>) pointChangeDetalDao.pageQueryObj(filter, "BY_PAGE_DETAIL_CUT_W");
	}

}
