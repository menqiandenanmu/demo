package com.mall.butler.account.model;
import com.mall.butler.model.BaseEntity;
import java.util.Date;

public class TradeNotifyTask extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//静态变量
	public static final String SERIALNO="serialNo";
	public static final String SYNCTIME="syncTime";
	public static final String SYNCNUM="syncNum";
	public static final String SYNCSTATUS="syncStatus";
	public static final String OPTYPE="opType";

	private String serialNo;
	private Date syncTime;
	private Integer syncNum;
	private String syncStatus;
	private Integer opType;

	/**
	 *流水号
	 */
	public void setSerialNo(String serialNo){
		this.serialNo = serialNo;
	}
	/**
	 *流水号
	 */
	public String getSerialNo(){
		return this.serialNo;
	}
	/**
	 *可同步时间(1，3，5分钟同步)
	 */
	public void setSyncTime(Date syncTime){
		this.syncTime = syncTime;
	}
	/**
	 *可同步时间(1，3，5分钟同步)
	 */
	public Date getSyncTime(){
		return this.syncTime;
	}
	/**
	 *同步次数
	 */
	public void setSyncNum(Integer syncNum){
		this.syncNum = syncNum;
	}
	/**
	 *同步次数
	 */
	public Integer getSyncNum(){
		return this.syncNum;
	}
	/**
	 *同步状态默认为空 success成功 fail 失败
	 */
	public void setSyncStatus(String syncStatus){
		this.syncStatus = syncStatus;
	}
	/**
	 *同步状态默认为空 success成功 fail 失败
	 */
	public String getSyncStatus(){
		return this.syncStatus;
	}
	/**
	 *0:充值  2:订单完成扣款 3:退订单 4:订单返利
	 */
	public void setOpType(Integer opType){
		this.opType = opType;
	}
	/**
	 *0:充值  2:订单完成扣款 3:退订单 4:订单返利
	 */
	public Integer getOpType(){
		return this.opType;
	}
}