package com.mall.butler.point.w.action;

import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.action.w.WebBaseAction;
import com.mall.butler.point.model.PointAccountInfo;
import com.mall.butler.point.model.PointChangeDetal;
import com.mall.butler.point.w.service.WUserPointService;
import com.mall.util.common.lang.DateUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 前台用户积分
 * 
 * @author zhaoxs
 * 
 */
public class WUserPointAction extends WebBaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private WUserPointService wUserPointService;

	private PointAccountInfo pointAccountInfo;
	private Date begCreateDate;
	private Date endCreateDate;
	private Page<PointChangeDetal> pointDetailPage;
	private String opType;
	private Integer tagFlag;

	@SuppressWarnings("unchecked")
	public String execute() {
		setTagFlag(11);
		pointAccountInfo = wUserPointService.getEntityById(PointAccountInfo.class, super.getAccount().getId());
		PageRequest<Map> filter = super.newPage(Map.class);
		filter.setPageSize(5);
		Map<String, Object> params = new Hashtable<String, Object>();
		params.put(PointChangeDetal.ACCOUNTID, super.getAccount().getId());

		if (begCreateDate != null) {
			params.put("begCreateDate", begCreateDate);
		} else {
			begCreateDate = DateUtil.getMonthFirstDay(new Date());
			params.put("begCreateDate", begCreateDate);
		}
		if (endCreateDate != null) {
			params.put("endCreateDate", endCreateDate);
		} else {
			endCreateDate = DateUtil.getMonthLastDay(new Date());
			params.put("endCreateDate", endCreateDate);
		}
		filter.setFilters(params);
		pointDetailPage = wUserPointService.queryPointAdd(filter);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String poionOut() {
		setTagFlag(12);
		pointAccountInfo = wUserPointService.getEntityById(PointAccountInfo.class, super.getAccount().getId());
		PageRequest<Map> filter = super.newPage(Map.class);
		filter.setPageSize(5);
		Map<String, Object> params = new Hashtable<String, Object>();
		params.put(PointChangeDetal.ACCOUNTID, super.getAccount().getId());

		if (begCreateDate != null) {
			params.put("begCreateDate", begCreateDate);
		} else {
			begCreateDate = DateUtil.getMonthFirstDay(new Date());
			params.put("begCreateDate", begCreateDate);
		}
		if (endCreateDate != null) {
			params.put("endCreateDate", endCreateDate);
		} else {
			endCreateDate = DateUtil.getMonthLastDay(new Date());
			params.put("endCreateDate", endCreateDate);
		}
		filter.setFilters(params);
		pointDetailPage = wUserPointService.queryPointCut(filter);
		return SUCCESS;
	}

	public PointAccountInfo getPointAccountInfo() {
		return pointAccountInfo;
	}

	public void setPointAccountInfo(PointAccountInfo pointAccountInfo) {
		this.pointAccountInfo = pointAccountInfo;
	}

	public Date getBegCreateDate() {
		return begCreateDate;
	}

	public void setBegCreateDate(Date begCreateDate) {
		this.begCreateDate = begCreateDate;
	}

	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public Page<PointChangeDetal> getPointDetailPage() {
		return pointDetailPage;
	}

	public void setPointDetailPage(Page<PointChangeDetal> pointDetailPage) {
		this.pointDetailPage = pointDetailPage;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	/**
	 * //会员中心左边模块标志 1用户资料 2修改密码 3门票未支付订单 4 门票已支付订单 //5酒店未支付订单 6 酒店已支付订单 7交通未支付订单
	 * 8 交通已已支付订单 //9娱乐未支付订单 10娱乐已民支付订单 11.用户积分
	 * 
	 * @return
	 */
	public Integer getTagFlag() {
		return tagFlag;
	}

	/**
	 * //会员中心左边模块标志 1用户资料 2修改密码 3门票未支付订单 4 门票已支付订单 //5酒店未支付订单 6 酒店已支付订单 7交通未支付订单
	 * 8 交通已已支付订单 //9娱乐未支付订单 10娱乐已民支付订单 11.用户积分
	 * 
	 * @param tagFlag
	 */
	public void setTagFlag(Integer tagFlag) {
		this.tagFlag = tagFlag;
	}
}
