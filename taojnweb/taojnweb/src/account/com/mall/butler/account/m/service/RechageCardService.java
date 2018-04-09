package com.mall.butler.account.m.service;

import java.util.List;

import com.mall.butler.account.model.RechageCard;
import com.mall.butler.service.BaseService;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public interface RechageCardService extends BaseService {
	/**
	 * 分页查询
	 * 
	 * @param filter
	 * @return
	 */
	public Page<RechageCard> page(PageRequest<RechageCard> filter);

	public void doAddSave(RechageCard rechageCard);

	public void doUpdate(RechageCard rechageCard);

	public void doUse(RechageCard rechageCard);

	public void doDel(RechageCard rechageCard);

	public RechageCard getEntity(RechageCard rechageCard);

	/**
	 * 充值卡充值
	 * 
	 * @param rechageCard
	 * @param id
	 */
	public void doAddRechargeCard(RechageCard rechageCard, Long id);

	/**
	 * 批量删除
	 * 
	 * @param messageIds
	 */
	public void doDelAll(Long[] messageIds);

	/**
	 * 批量启用
	 * 
	 * @param messageIds
	 */
	public void doUseAll(String[] messageIds);

	/**
	 * 批量审核
	 * 
	 * @param messageIds
	 */
	public void doAuditAll(String[] messageIds, String auditRemark);

	/**
	 * 批量新增
	 * 
	 * @param messageIds
	 */
	public void doInsertRechageCard(List<RechageCard> rechageCardList);

	/**
	 * 查找充值卡集合
	 * 
	 * @param rechageCard
	 * @return
	 */
	public List<RechageCard> findRechageCardDetailList(RechageCard rechageCard);
}
