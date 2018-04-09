package com.mall.butler.account.service;

import java.util.List;
import java.util.Map;

import com.mall.butler.account.model.GiroInfo;
import com.mall.butler.account.model.TradeAccount;
import com.mall.butler.account.model.TradeAccountDetail;
import com.mall.butler.account.model.TradeAccountInfo;
import com.mall.butler.account.model.TradeOrder;
import com.mall.butler.port.xml.OrderXml;
import com.mall.butler.port.xml.TradeAccountXml;
import com.mall.butler.service.BaseService;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public interface TradeAccountService extends BaseService {

	/**
	 * 分页账户类型查询
	 * 
	 * @param pageRequest
	 * @return
	 */
	public Page<TradeAccountInfo> queryPageTradeAccountInfo(
			PageRequest<TradeAccountInfo> pageRequest);

	/**
	 * 
	 * 查询所有的账户
	 */
	public List<TradeAccountInfo> findAllAccount();

	/**
	 * 条件查询结果集
	 * 
	 * @param HashMap
	 * @return
	 */
	public List<TradeAccountInfo> getTradeAccountInfoList(
			Map<String, Object> params);

	/**
	 * 新增账户类型
	 * 
	 */
	public void insert(TradeAccountInfo tradeAccountInfo);

	/**
	 * 编辑该类型是否生效
	 * 
	 * @param tradeAccountInfo
	 */
	public void updateUseFlag(TradeAccountInfo tradeAccountInfo);

	/**
	 * 得到用户列表
	 */
	public List<TradeAccount> getTradeAccountList(TradeAccount tradeAccount);

	/**
	 * 根据ID得到用户
	 */
	public TradeAccount getTradeAccountById(Long id);

	/**
	 * 
	 * 更新对象
	 */
	public void updateTradeAccount(TradeAccount tradeAccount);

	/**
	 * 账户绑定
	 * 
	 * @param tradeXml
	 */
	public void bindTrade(TradeAccountXml tradeXml);

	/**
	 * 查询账户信息
	 * 
	 * @param trim
	 * @return
	 */
	public TradeAccount queryTradeByLoginName(String trim);

	/**
	 * 支付后操作更新账户交易金额及订单状态
	 * 
	 * @param tradeOrder
	 * @param transNo
	 * @param reportInfo
	 */

	public void payed(OrderXml orderXml);

	/**
	 * 退款
	 * 
	 * @param orderXml
	 */
	public void refund(OrderXml orderXml);

	/**
	 * 查询交易明细
	 * 
	 * @param trim
	 * @param opType
	 * @return
	 */
	public List<TradeAccountDetail> queryTradeDetails(String trim, String opType);

	/**
	 * 解除绑定
	 * 
	 * @param tradeXml
	 */
	public void unBindTrade(TradeAccountXml tradeXml);

	/**
	 * 分页查询交易记录
	 * 
	 * @param tradeRequest
	 * @return
	 */
	public Page<TradeAccountDetail> pageQuery(
			PageRequest<TradeAccountDetail> tradeRequest);

	/**
	 * 添加支付订单
	 * 
	 * @param tradeAccount
	 *            操作账户id
	 * @param opVal
	 *            操作金额
	 * @param transNo
	 *            外部交易号
	 * @param opId
	 *            操作员id
	 * @param opType
	 *            充值类型 0 手动充值 1网上充值
	 * @param remark
	 *            充值说明
	 */

	TradeOrder doRechageTrade(TradeAccount tradeAccount, Double opVal,
			String transNo, Long opId, Integer opType, String remark);

	/**
	 * 支付后操作更新账户交易金额及订单状态
	 * 
	 * @param tradeOrder
	 * @param transNo
	 * @param reportInfo
	 */
	public void payed(String transNo, String tradeNo, String queryString);

	public Page<TradeAccountDetail> page(PageRequest<TradeAccountDetail> request);

	public List<TradeAccountDetail> findAgentOrderCountList(
			TradeAccountDetail tradeAccountDetail);

	/**
	 * crm钱包扣款
	 * 
	 * @param orderXml
	 * @param payAmount
	 * @return
	 */
	public String crmPayed(OrderXml orderXml, double payAmount);

	/**
	 * 通过条形码查找用户
	 * 
	 * @param trim
	 * @return
	 */
	public TradeAccount queryTradeByPassword(String trim);

	/**
	 * CRM退款
	 * 
	 * @param orderXml
	 * @param payAmount
	 */
	public TradeAccountDetail crmRefund(OrderXml orderXml, double payAmount);

	/**
	 * 根据交易号查询订单支付状态
	 * 
	 * @param orderNo
	 * @return
	 */
	public TradeAccountDetail findByOrderNo(String orderNo);

	/**
	 * 查询退款记录
	 * @param reback
	 * @return
	 */
	public List<TradeAccountDetail> findReback(TradeAccountDetail reback);
	/**
	 * 转账
	 * 
	 * @param tradeAccount
	 * @param accountName
	 * @param paysum
	 */
	public Long payToPay(TradeAccount tradeAccount, String accountName,
			Double paysum);

	/**
	 * 转账查询
	 * 
	 * @param pageRequest
	 * @return
	 */
	public Page<GiroInfo> pageQueryGiro(PageRequest<GiroInfo> pageRequest);

	/**
	 * 查询集合
	 * 
	 * @param giroInfo
	 * @return
	 */
	public List<GiroInfo> findGiroInfo(GiroInfo giroInfo);
}
