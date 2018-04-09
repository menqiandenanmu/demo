package com.mall.butler.point.m.service;

import java.util.List;
import java.util.Map;

import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.point.m.model.PointChangeDetalExtendDetail;
import com.mall.butler.point.model.PointAccountInfo;
import com.mall.butler.point.model.PointChangeDetal;
import com.mall.butler.service.BaseService;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public interface MUserPointService extends BaseService {

	/**
	 * 查询用户积分
	 * 
	 * @param filter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	Page<PointAccountInfo> queryUserPoint(PageRequest<Map> filter);

	/**
	 * 积分赠送
	 * 
	 * @param pointAccountInfo
	 * @param accountLogin
	 * @param pointChangeDetal
	 */
	void doSavePoint(AccountLogin accountLogin, PointAccountInfo pointAccountInfo, PointChangeDetal pointChangeDetal);

	/**
	 * 用户积分清单
	 * 
	 * @param filter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	Page<PointChangeDetalExtendDetail> queryPointDetail(PageRequest<Map> filter);

	/**
	 * 积分日统计
	 * 
	 * @param filter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	Page<PointChangeDetalExtendDetail> countPointDetail(PageRequest<Map> filter);

	/**
	 * 用户有积分
	 * 
	 * @param filter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	Page<PointAccountInfo> userHasPoint(PageRequest<Map> filter);

	/**
	 * 用户无积分
	 * 
	 * @param filter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	Page<PointAccountInfo> userNoPoint(PageRequest<Map> filter);

	/**
	 * 积分合计
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> querySum(Map<String, Object> params);

	/**
	 * 用户积分集合
	 * 
	 * @param params
	 * @return
	 */
	List<PointAccountInfo> getPointAccountList(Map<String, Object> params);

	/**
	 * 用户有积分
	 * 
	 * @param params
	 */
	List<PointAccountInfo> hssPointAccountList(Map<String, Object> params);

	/**
	 * 用户无积分
	 * 
	 * @param params
	 * @return
	 */
	List<PointAccountInfo> noPointAccountList(Map<String, Object> params);

	/**
	 * 积分清单集合
	 * 
	 * @param params
	 * @return
	 */
	List<PointChangeDetalExtendDetail> pointDetailList(Map<String, Object> params);

	/**
	 * 积分日统计集合
	 * 
	 * @param params
	 * @return
	 */
	List<PointChangeDetalExtendDetail> pointCountList(Map<String, Object> params);

}
