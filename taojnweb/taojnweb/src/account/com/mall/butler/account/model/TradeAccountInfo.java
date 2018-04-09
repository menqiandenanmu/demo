package com.mall.butler.account.model;
import com.mall.butler.model.BaseEntity;
public class TradeAccountInfo extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//静�?变量
	public static final String ACCCODE="accCode";
	public static final String ACCNAME="accName";
	public static final String CONTENT="content";
	public static final String USEFLAG="useFlag";

	private String accCode;
	private String accName;
	private String content;
	private Boolean useFlag;

	/**
	 *�˻����ͱ��
	 */
	public void setAccCode(String accCode){
		this.accCode = accCode;
	}
	/**
	 *�˻����ͱ��
	 */
	public String getAccCode(){
		return this.accCode;
	}
	/**
	 *�˻�������
	 */
	public void setAccName(String accName){
		this.accName = accName;
	}
	/**
	 *�˻�������
	 */
	public String getAccName(){
		return this.accName;
	}
	/**
	 *˵����Ϣ
	 */
	public void setContent(String content){
		this.content = content;
	}
	/**
	 *˵����Ϣ
	 */
	public String getContent(){
		return this.content;
	}
	/**
	 *0:��Ч 1:��Ч
	 */
	public void setUseFlag(Boolean useFlag){
		this.useFlag = useFlag;
	}
	/**
	 *0:��Ч 1:��Ч
	 */
	public Boolean getUseFlag(){
		return this.useFlag;
	}
}