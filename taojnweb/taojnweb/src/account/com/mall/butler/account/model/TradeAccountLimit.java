package com.mall.butler.account.model;
import com.mall.butler.model.BaseEntity;

public class TradeAccountLimit extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//静�?变量
	public static final String TRADEACCOUNTID="tradeAccountId";
	public static final String SUPPLYID="supplyId";
	public static final String SUPPLYNAME="supplyName";
	public static final String OBJTYPE="objType";
	public static final String OBJID="objId";
	public static final String USEFLAG="useFlag";

	private Long tradeAccountId;
	private Long supplyId;
	private String supplyName;
	private Integer objType;
	private Long objId;
	private Boolean useFlag;

	/**
	 *�˻�����ID
	 */
	public void setTradeAccountId(Long tradeAccountId){
		this.tradeAccountId = tradeAccountId;
	}
	/**
	 *�˻�����ID
	 */
	public Long getTradeAccountId(){
		return this.tradeAccountId;
	}
	/**
	 *��Ӧ��ID
	 */
	public void setSupplyId(Long supplyId){
		this.supplyId = supplyId;
	}
	/**
	 *��Ӧ��ID
	 */
	public Long getSupplyId(){
		return this.supplyId;
	}
	/**
	 *��Ӧ����
	 */
	public void setSupplyName(String supplyName){
		this.supplyName = supplyName;
	}
	/**
	 *��Ӧ����
	 */
	public String getSupplyName(){
		return this.supplyName;
	}
	/**
	 *�������� -1:��ʾ���� (0, "������Ʊ "), (1, "�Ƶ�"),  (2,"��Ȫ"),(3,"�߶��"),(4,"�ݳ�")(5,"�꿨")(9,"�ײ�")
	 */
	public void setObjType(Integer objType){
		this.objType = objType;
	}
	/**
	 *�������� -1:��ʾ���� (0, "������Ʊ "), (1, "�Ƶ�"),  (2,"��Ȫ"),(3,"�߶��"),(4,"�ݳ�")(5,"�꿨")(9,"�ײ�")
	 */
	public Integer getObjType(){
		return this.objType;
	}
	/**
	 *����ID -1 ��ʾ���ж���     ��һ��������ж�����ѡ����
	 */
	public void setObjId(Long objId){
		this.objId = objId;
	}
	/**
	 *����ID -1 ��ʾ���ж���     ��һ��������ж�����ѡ����
	 */
	public Long getObjId(){
		return this.objId;
	}
	/**
	 *0:������ 1:���� 
	 */
	public void setUseFlag(Boolean useFlag){
		this.useFlag = useFlag;
	}
	/**
	 *0:������ 1:���� 
	 */
	public Boolean getUseFlag(){
		return this.useFlag;
	}
}