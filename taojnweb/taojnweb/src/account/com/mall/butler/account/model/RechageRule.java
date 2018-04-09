package com.mall.butler.account.model;
import com.mall.butler.model.BaseEntity;

public class RechageRule extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//静态变量
	public static final String SERIALNO="serialNo";
	public static final String STARTTIME="startTime";
	public static final String ENDTIME="endTime";
	public static final String REALNAME2="realname2";
	public static final String REALNAME="realname";
	public static final String MAXNUM="maxNum";
	public static final String ACCOUNTID="accountId";
	public static final String ACCOUNTNAME="accountName";
	public static final String TRADEACCID="tradeAccId";
	public static final String TRADEACCNAME="tradeAccName";
	public static final String OPTYPE="opType";
	public static final String ORDERNO="orderNo";
	public static final String CURRECHAGETIME="curRechageTime";
	public static final String TODAYNUM="todayNum";
	public static final String OPLOGINNAME="opLoginName";
	public static final String OPLOGINID="opLoginId";
	public static final String REMARK="remark";

	private String serialNo;
	private Integer startTime;
	private String endTime;
	private String realname2;
	private String realname;
	private Integer maxNum;
	private Long accountId;
	private String accountName;
	private Long tradeAccId;
	private String tradeAccName;
	private Integer opType;
	private String orderNo;
	private Double curRechageTime;
	private Double todayNum;
	private String opLoginName;
	private Long opLoginId;
	private String remark;

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
	 *开始时间
	 */
	public void setStartTime(Integer startTime){
		this.startTime = startTime;
	}
	/**
	 *开始时间
	 */
	public Integer getStartTime(){
		return this.startTime;
	}
	/**
	 *结束时间
	 */
	public void setEndTime(String endTime){
		this.endTime = endTime;
	}
	/**
	 *结束时间
	 */
	public String getEndTime(){
		return this.endTime;
	}
	/**
	 *满额参数
	 */
	public void setRealname2(String realname2){
		this.realname2 = realname2;
	}
	/**
	 *满额参数
	 */
	public String getRealname2(){
		return this.realname2;
	}
	/**
	 *赠送参数
	 */
	public void setRealname(String realname){
		this.realname = realname;
	}
	/**
	 *赠送参数
	 */
	public String getRealname(){
		return this.realname;
	}
	/**
	 *最大值
	 */
	public void setMaxNum(Integer maxNum){
		this.maxNum = maxNum;
	}
	/**
	 *最大值
	 */
	public Integer getMaxNum(){
		return this.maxNum;
	}
	/**
	 *代理或分销商id号
	 */
	public void setAccountId(Long accountId){
		this.accountId = accountId;
	}
	/**
	 *代理或分销商id号
	 */
	public Long getAccountId(){
		return this.accountId;
	}
	/**
	 *代理或分销商名称
	 */
	public void setAccountName(String accountName){
		this.accountName = accountName;
	}
	/**
	 *代理或分销商名称
	 */
	public String getAccountName(){
		return this.accountName;
	}
	/**
	 *账户类型ID
	 */
	public void setTradeAccId(Long tradeAccId){
		this.tradeAccId = tradeAccId;
	}
	/**
	 *账户类型ID
	 */
	public Long getTradeAccId(){
		return this.tradeAccId;
	}
	/**
	 *账户类型名
	 */
	public void setTradeAccName(String tradeAccName){
		this.tradeAccName = tradeAccName;
	}
	/**
	 *账户类型名
	 */
	public String getTradeAccName(){
		return this.tradeAccName;
	}
	/**
	 *0:转账
	 */
	public void setOpType(Integer opType){
		this.opType = opType;
	}
	/**
	 *0:转账
	 */
	public Integer getOpType(){
		return this.opType;
	}
	/**
	 *业务订单（订票订单号等）
	 */
	public void setOrderNo(String orderNo){
		this.orderNo = orderNo;
	}
	/**
	 *业务订单（订票订单号等）
	 */
	public String getOrderNo(){
		return this.orderNo;
	}
	/**
	 *活动期兑换次数
	 */
	public void setCurRechageTime(Double curRechageTime){
		this.curRechageTime = curRechageTime;
	}
	/**
	 *活动期兑换次数
	 */
	public Double getCurRechageTime(){
		return this.curRechageTime;
	}
	/**
	 *当日兑换次数
	 */
	public void setTodayNum(Double todayNum){
		this.todayNum = todayNum;
	}
	/**
	 *当日兑换次数
	 */
	public Double getTodayNum(){
		return this.todayNum;
	}
	/**
	 *操作员
	 */
	public void setOpLoginName(String opLoginName){
		this.opLoginName = opLoginName;
	}
	/**
	 *操作员
	 */
	public String getOpLoginName(){
		return this.opLoginName;
	}
	/**
	 *操作员ID
	 */
	public void setOpLoginId(Long opLoginId){
		this.opLoginId = opLoginId;
	}
	/**
	 *操作员ID
	 */
	public Long getOpLoginId(){
		return this.opLoginId;
	}
	/**
	 *说明
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}
	/**
	 *说明
	 */
	public String getRemark(){
		return this.remark;
	}
}