package com.mall.butler.account.m.service;

import com.mall.butler.service.BaseService;
import java.util.List;
import java.util.Map;

import com.mall.butler.account.m.model.TradeAccountDetailVo;
import com.mall.butler.account.m.model.TradeAccountVo;
import com.mall.butler.account.m.model.TradeOrderDetailExtend;
import com.mall.butler.account.model.StoreAccountDetail;
import com.mall.butler.account.model.TradeAccount;
import com.mall.butler.account.model.TradeAccountDetail;
import com.mall.butler.account.model.TradeOrder;

import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public interface MAgentAccountService extends BaseService {
	/**
	 * 用户分页列表
	 * @param filter
	 * @return
	 */
	Page<TradeAccountVo> queryPageAgentAccount(PageRequest<TradeAccountVo> filter);
	/**
	 * 用户账户充值
	 * @param tradeAccount
	 * @param tradeDetail
	 */
	void saveTradeAccount(TradeAccount tradeAccount, TradeAccountDetail tradeAccountDetail);
	/**
	 * 人工为用户账户充值
	 * @author Mountain
	 */
	void saveTradeAccountCharge(TradeAccount tradeAccount, TradeAccountDetail tradeAccountDetail,TradeOrder order);
	/**
	 * 用户账户扣款
	 * @param tradeAccount
	 * @param tradeDetail
	 */
	void saveCutTradeAccount(TradeAccount tradeAccount, TradeAccountDetail tradeAccountDetail);
	/**
	 * 查询用户账户清单
	 * @param filter
	 * @return
	 */
	Page<TradeAccountDetail> findTradeAccountDetail(PageRequest<TradeAccountDetail> filter);
	
	/**
	 * 用户信用编辑修改
	 * @param tradeAccount
	 * @param account
	 */
	void editTradeAccount(TradeAccount tradeAccount);
	/**
	 * 用户签单编辑修改
	 * @param tradeAccount
	 * @param account
	 */
	void signTradeAccount(TradeAccount tradeAccount);
	/**
	 * 用户账户清单
	 * @param filter
	 * @return
	 */
	Page<TradeAccountDetail> finddisTradeAccountDetail(PageRequest<TradeAccountDetail> filter);
	/**
	 * 用户账户统计
	 * @param filter
	 * @return
	 */
	Page<TradeAccountDetailVo> findAccountCount(PageRequest<TradeAccountDetailVo> filter);
	/**
	 *  用户账户统计
	 * @param filter
	 * @return
	 */
	Page<TradeAccountDetailVo> finddisAccountCount(PageRequest<TradeAccountDetailVo> filter);
	/**
	 * 导出列表查询
	 * @param params
	 * @return
	 */
	List<TradeAccountDetail> getAgentAccountList(Map<String, Object> params);
	/**
	 * 用户导出
	 * @param params
	 * @return
	 */
	List<TradeAccountDetail> getdisAgentAccountList(Map<String, Object> params);

	/**
	 * 增加用户账户
	 * @author Mountain
	 */
	void insert(TradeAccount tradeAccount);
	
	/**
	 * @Author Mountain
	 * @param filter
	 * @return
	 */
	Page<TradeAccountDetail> findAgentAccountCount(PageRequest<TradeAccountDetailVo> filter);
	
	/**
	 * @Author Mountain
	 * @param filter
	 * @return
	 */
	Page<TradeOrderDetailExtend> findAgentAccountDetail(PageRequest<TradeOrderDetailExtend> filter);
	
	/**
	 * @Author Mountain
	 * @param filter
	 * @return
	 */
	Page<TradeAccountDetail> findTradeDetailCount(PageRequest<Map<String, Object>> filter);
	
	/**
	 * @Author Mountain
	 * @param filter
	 * @return
	 */
	Page<StoreAccountDetail> findStoreDetailCount(PageRequest<Map<String, Object>> filter);
	/**
	 * 信用额度设置
	 * @param tradeAccount
	 */
	void updateWarnLine(Long id, Double opValue);
	/**
	 * 新增子账户
	 * @param id
	 * @param tradeAccId
	 */
	void doAddSubAcc(Long id, Long tradeAccId);
	/**
	 * 查询账户交易记录
	 * @param map
	 * @return
	 */
	List<TradeAccountDetail> findTradeDetailList(
			Map<String, Object> map);
	/**
	 * 查找用户集合
	 * @param map
	 * @return
	 */
	List<TradeAccountVo> findTradeAccountList(Map<String, Object> map);
}
