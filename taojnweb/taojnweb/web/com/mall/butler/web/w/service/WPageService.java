package com.mall.butler.web.w.service;

import java.util.List;

import com.mall.butler.web.model.PageAreaDetail;

public interface WPageService {

	/**
	 * 根据编号 、数量 查询前几条的广告信息
	 * @param code
	 * @param count
	 * @return
	 */
	public List<PageAreaDetail> findByCode(String code,Integer count);
}
