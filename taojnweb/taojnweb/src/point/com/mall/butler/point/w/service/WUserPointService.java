package com.mall.butler.point.w.service;

import java.util.Map;

import com.mall.butler.point.model.PointChangeDetal;
import com.mall.butler.point.w.model.PointChangeDetalExtendDetail;
import com.mall.butler.service.BaseService;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public interface WUserPointService extends BaseService {
	/**
	 * 查询积分清单(全部)
	 * 
	 * @param filter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	Page<PointChangeDetal> queryPointDetail(PageRequest<Map> filter);
	/**
	 * 查询积分清单(收入)
	 * 
	 * @param filter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	Page<PointChangeDetalExtendDetail> queryPointAddDetail(PageRequest<Map> filter);
	/**
	 * 查询积分清单(支出)
	 * 
	 * @param filter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	Page<PointChangeDetalExtendDetail> queryPointCutDetail(PageRequest<Map> filter);
	/**
	 * 收入
	 * @param filter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	Page<PointChangeDetal> queryPointAdd(PageRequest<Map> filter);
	/**
	 * 支出
	 * @param filter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	Page<PointChangeDetal> queryPointCut(PageRequest<Map> filter);

}
