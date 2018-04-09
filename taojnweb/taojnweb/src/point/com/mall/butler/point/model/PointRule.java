package com.mall.butler.point.model;
import com.mall.butler.model.BaseEntity;

public class PointRule extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//静态变量
	public static final String OPVALUE="opValue";
	public static final String BNSTYPE="bnsType";
	public static final String AMOUNT="amount";
	public static final String USESTATUS="useStatus";
	public static final String REMARK4="remark4";
	public static final String REMARK3="remark3";
	public static final String REMARK2="remark2";
	public static final String REMARK="remark";

	private String opValue;
	private String bnsType;
	private String amount;
	private Integer useStatus;
	private String remark4;
	private String remark3;
	private String remark2;
	private String remark;
	private String source;

	/**
	 *充值金额,或者消费金额，或者退款金额
	 */
	public void setOpValue(String opValue){
		this.opValue = opValue;
	}
	/**
	 *充值金额,或者消费金额，或者退款金额
	 */
	public String getOpValue(){
		return this.opValue;
	}
	/**
	 *0：充值1：消费2：退款
	 */
	public void setBnsType(String bnsType){
		this.bnsType = bnsType;
	}
	/**
	 *0：充值1：消费2：退款
	 */
	public String getBnsType(){
		return this.bnsType;
	}
	/**
	 *赠送数量
	 */
	public void setAmount(String amount){
		this.amount = amount;
	}
	/**
	 *赠送数量
	 */
	public String getAmount(){
		return this.amount;
	}
	/**
	 *0:停用 1:启用
	 */
	public void setUseStatus(Integer useStatus){
		this.useStatus = useStatus;
	}
	/**
	 *0:停用 1:启用
	 */
	public Integer getUseStatus(){
		return this.useStatus;
	}
	/**
	 *备用字段
	 */
	public void setRemark4(String remark4){
		this.remark4 = remark4;
	}
	/**
	 *备用字段
	 */
	public String getRemark4(){
		return this.remark4;
	}
	/**
	 *备用字段
	 */
	public void setRemark3(String remark3){
		this.remark3 = remark3;
	}
	/**
	 *备用字段
	 */
	public String getRemark3(){
		return this.remark3;
	}
	/**
	 *备用字段
	 */
	public void setRemark2(String remark2){
		this.remark2 = remark2;
	}
	/**
	 *备用字段
	 */
	public String getRemark2(){
		return this.remark2;
	}
	/**
	 *备用字段
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}
	/**
	 *备用字段
	 */
	public String getRemark(){
		return this.remark;
	}
	/**
	 * 来源
	 */
	public String getSource() {
		return source;
	}
	/**
	 * 来源
	 */
	public void setSource(String source) {
		this.source = source;
	}
}