package com.mall.butler.web.w.service._impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.web.dao.PageAreaDetailDao;
import com.mall.butler.web.model.PageAreaDetail;
import com.mall.butler.web.w.service.WPageService;

public class WPageServiceImpl implements WPageService {

	@Autowired
	PageAreaDetailDao pageAreaDetailDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<PageAreaDetail> findByCode(String code, Integer count) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("count", count);
		return pageAreaDetailDao.queryObjects("AREACODE", map);
	}

}
