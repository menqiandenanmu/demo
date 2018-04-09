package com.mall.butler.account.model;
import com.mall.butler.model.BaseEntity;
import java.util.Date;

public class AgentInfo extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//静态变量
	public static final String CORPNAME="corpName";
	public static final String CODE="code";
	public static final String URL="url";
	public static final String LINKMAN="linkman";
	public static final String LINKMANTEL="linkmanTel";
	public static final String TELPHONE="telphone";
	public static final String FAX="fax";
	public static final String ADDRESS="address";
	public static final String REMARK="remark";
	public static final String BEGDATE="begDate";
	public static final String ENDDATE="endDate";
	public static final String PARENTAGENTID="parentAgentId";
	public static final String PARENTAGENTNAME="parentAgentName";
	public static final String AGENTTYPE="agentType";
	public static final String PARTNERKEY="partnerKey";
	public static final String BANKCODE="bankCode";
	public static final String BANKNAME="bankName";
	public static final String BANKOPENINFO="bankOpenInfo";

	private String agentNo;
	private Integer agentAccountType;
	private String corpName;
	private String code;
	private String url;//客户来源数据字典编号
	private String linkman;
	private String linkmanTel;
	private String telphone;
	private String fax;//客户来源数据字典值
	private String address;
	private String remark;
	private Date begDate;
	private Date endDate;
	private Long parentAgentId;
	private String parentAgentName;//卡面号
	private Integer agentType;//用户激活标志1crm激活
	private String partnerKey;//crm条形码
	private String bankCode;//会员卡号
	private String bankName;//卡类型名称
	private String bankOpenInfo;//卡类型编码
	
	/**
	 * 用户编号，唯一且不可修改
	 */
	public void setAgentNo(String agentNo){
		this.agentNo = agentNo;
	}
	/**
	 * 用户编号，唯一且不可修改
	 */
	public String getAgentNo(){
		return this.agentNo;
	}
	/**
	 * 账户类型(1,"综合类账户"),(2, "景区门票专用账户"),(3, "酒店专用账户"),(4,"套餐专用账户")
	 */
	public void setAgentAccountType(Integer agentAccountType){
		this.agentAccountType = agentAccountType;
	}
	/**
	 * 账户类型(1,"综合类账户"),(2, "景区门票专用账户"),(3, "酒店专用账户"),(4,"套餐专用账户")
	 */
	public Integer getAgentAccountType(){
		return this.agentAccountType;
	}
	/**
	 *公司名称
	 */
	public void setCorpName(String corpName){
		this.corpName = corpName;
	}
	/**
	 *公司名称
	 */
	public String getCorpName(){
		return this.corpName;
	}
	/**
	 *编号
	 */
	public void setCode(String code){
		this.code = code;
	}
	/**
	 *编号
	 */
	public String getCode(){
		return this.code;
	}
	/**
	 *网址
	 */
	public void setUrl(String url){
		this.url = url;
	}
	/**
	 *网址
	 */
	public String getUrl(){
		return this.url;
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
	 *联系方式及账户名
	 */
	public void setLinkmanTel(String linkmanTel){
		this.linkmanTel = linkmanTel;
	}
	/**
	 *联系方式及账户名
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
	/**
	 *开始有效期
	 */
	public void setBegDate(Date begDate){
		this.begDate = begDate;
	}
	/**
	 *开始有效期
	 */
	public Date getBegDate(){
		return this.begDate;
	}
	/**
	 *结束有效期
	 */
	public void setEndDate(Date endDate){
		this.endDate = endDate;
	}
	/**
	 *结束有效期
	 */
	public Date getEndDate(){
		return this.endDate;
	}
	/**
	 *
	 */
	public void setParentAgentId(Long parentAgentId){
		this.parentAgentId = parentAgentId;
	}
	/**
	 *
	 */
	public Long getParentAgentId(){
		return this.parentAgentId;
	}
	/**
	 *卡面号
	 */
	public void setParentAgentName(String parentAgentName){
		this.parentAgentName = parentAgentName;
	}
	/**
	 *卡面号
	 */
	public String getParentAgentName(){
		return this.parentAgentName;
	}
	/**
	 *
	 */
	public void setAgentType(Integer agentType){
		this.agentType = agentType;
	}
	/**
	 *
	 */
	public Integer getAgentType(){
		return this.agentType;
	}
	/**
	 *crm条形码
	 */
	public void setPartnerKey(String partnerKey){
		this.partnerKey = partnerKey;
	}
	/**
	 *crm条形码
	 */
	public String getPartnerKey(){
		return this.partnerKey;
	}
	/**
	 *会员卡号
	 */
	public void setBankCode(String bankCode){
		this.bankCode = bankCode;
	}
	/**
	 *会员卡号
	 */
	public String getBankCode(){
		return this.bankCode;
	}
	/**
	 *卡类型名称
	 */
	public void setBankName(String bankName){
		this.bankName = bankName;
	}
	/**
	 *卡类型名称
	 */
	public String getBankName(){
		return this.bankName;
	}
	/**
	 *卡类型编码
	 */
	public void setBankOpenInfo(String bankOpenInfo){
		this.bankOpenInfo = bankOpenInfo;
	}
	/**
	 *卡类型编码
	 */
	public String getBankOpenInfo(){
		return this.bankOpenInfo;
	}
}