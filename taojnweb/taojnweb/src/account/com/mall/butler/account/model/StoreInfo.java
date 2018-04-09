package com.mall.butler.account.model;
import com.mall.butler.model.BaseEntity;

public class StoreInfo extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//静态变量
	public static final String STORENAME="storeName";
	public static final String STORECODE="storeCode";
	public static final String QRCODE="qrCode";
	public static final String LINKMAN="linkman";
	public static final String LINKMANTEL="linkmanTel";
	public static final String TELPHONE="telphone";
	public static final String FAX="fax";
	public static final String ADDRESS="address";
	public static final String REMARK="remark";

	private String storeName;
	private String storeCode;
	private String qrCode;
	private String linkman;
	private String linkmanTel;
	private String telphone;
	private String fax;
	private String address;
	private String remark;

	/**
	 *店铺名称
	 */
	public void setStoreName(String storeName){
		this.storeName = storeName;
	}
	/**
	 *店铺名称
	 */
	public String getStoreName(){
		return this.storeName;
	}
	/**
	 *编号
	 */
	public void setStoreCode(String storeCode){
		this.storeCode = storeCode;
	}
	/**
	 *编号
	 */
	public String getStoreCode(){
		return this.storeCode;
	}
	/**
	 *条形码编号
	 */
	public void setQrCode(String qrCode){
		this.qrCode = qrCode;
	}
	/**
	 *条形码编号
	 */
	public String getQrCode(){
		return this.qrCode;
	}
	/**
	 *联系人
	 */
	public void setLinkman(String linkman){
		this.linkman = linkman;
	}
	/**
	 *联系人
	 */
	public String getLinkman(){
		return this.linkman;
	}
	/**
	 *联系人电话
	 */
	public void setLinkmanTel(String linkmanTel){
		this.linkmanTel = linkmanTel;
	}
	/**
	 *联系人电话
	 */
	public String getLinkmanTel(){
		return this.linkmanTel;
	}
	/**
	 *公司电话
	 */
	public void setTelphone(String telphone){
		this.telphone = telphone;
	}
	/**
	 *公司电话
	 */
	public String getTelphone(){
		return this.telphone;
	}
	/**
	 *公司传真
	 */
	public void setFax(String fax){
		this.fax = fax;
	}
	/**
	 *公司传真
	 */
	public String getFax(){
		return this.fax;
	}
	/**
	 *公司地址
	 */
	public void setAddress(String address){
		this.address = address;
	}
	/**
	 *公司地址
	 */
	public String getAddress(){
		return this.address;
	}
	/**
	 *备注信息
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}
	/**
	 *备注信息
	 */
	public String getRemark(){
		return this.remark;
	}
}