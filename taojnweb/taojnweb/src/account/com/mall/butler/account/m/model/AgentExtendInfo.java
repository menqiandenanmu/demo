package com.mall.butler.account.m.model;

import com.mall.butler.account.model.AgentInfo;

/**
 * 用户拓展信息
 */
public class AgentExtendInfo extends AgentInfo{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7943775403811893385L;
	
	private Integer accStatus;//账户状态  0:停用 1:正常
	
	private String loginName;//登录名
	
	private Integer checkFlag;//选择标志  0：未选择 1：已选择
	
	private Double agentPrice;//代理价
	
	private Integer minAmount;//最小购买数量
	
	private Integer maxAmount;//最大购买数量
	
	private Long priceId;///价格Id
	

	public Integer getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(Integer accStatus) {
		this.accStatus = accStatus;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Integer getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(Integer checkFlag) {
		this.checkFlag = checkFlag;
	}

	public Double getAgentPrice() {
		return agentPrice;
	}

	public void setAgentPrice(Double agentPrice) {
		this.agentPrice = agentPrice;
	}

	public Integer getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(Integer minAmount) {
		this.minAmount = minAmount;
	}

	public Integer getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(Integer maxAmount) {
		this.maxAmount = maxAmount;
	}

	public Long getPriceId() {
		return priceId;
	}

	public void setPriceId(Long priceId) {
		this.priceId = priceId;
	}
	
	
	
	

	
}