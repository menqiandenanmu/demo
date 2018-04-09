package com.mall.butler.account.m.service;

import java.util.List;

import com.mall.butler.account.model.ElectronicCoup;
import com.mall.butler.service.BaseService;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public interface ElectronicCoupService extends BaseService {

	/**
	 * 分页查询
	 * 
	 * @param filter
	 * @return
	 */
	public Page<ElectronicCoup> page(PageRequest<ElectronicCoup> filter);

	/**
	 * 新增
	 * 
	 * @param electronicCoup
	 */
	public void doSave(ElectronicCoup electronicCoup);

	/**
	 * 修改
	 * 
	 * @param electronicCoup
	 */
	public void doUpdate(ElectronicCoup electronicCoup);

	public ElectronicCoup getEntity(ElectronicCoup electronicCoup);

	public void doDel(ElectronicCoup electronicCoup);

	/**
	 * 充值
	 * 
	 * @param electronicCoup
	 */
	public void doVerify(ElectronicCoup electronicCoup);

	/**
	 * 查询集合
	 * 
	 * @param map
	 * @return
	 */
	public List<ElectronicCoup> findElectronicCoupDetailList(
			ElectronicCoup electronicCoup);

	/**
	 * 电子券批量删除
	 * 
	 * @param messageIds
	 * @param auditRemark
	 */
	public void doDelAll(Long[] messageIds);

	/**
	 * 电子券批量充值
	 * 
	 * @param messageIds
	 * @param auditRemark
	 */
	public void doUseAll(String[] messageIds);

	/**
	 * 电子券批量审核
	 * 
	 * @param messageIds
	 * @param auditRemark
	 */
	public void doAuditAll(String[] messageIds, String auditRemark);

	/**
	 * 电子券审核
	 * 
	 * @param electronicCoup
	 */
	public void doAudit(ElectronicCoup electronicCoup);

}
