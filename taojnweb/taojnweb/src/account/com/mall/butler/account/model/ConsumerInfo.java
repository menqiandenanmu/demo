package com.mall.butler.account.model;

import java.util.Date;

/**
 * 前台用户
 * 
 * @author zhaoxs
 */
public class ConsumerInfo extends AccountInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// LOGININFO 括展信息
	// 静态变量
	public static final String ACCOUNTID = "accountId";
	public static final String LOGINNAME = "loginName";
	public static final String LOGINPASS = "loginPass";
	public static final String REALNAME = "realname";
	public static final String STATUS = "status";
	public static final String ADMINFLAG = "adminFlag";
	public static final String BIRTHDAY = "birthday";
	public static final String SEX = "sex";
	public static final String REMARK = "remark";
	public static final String LOGINCOUNT = "loginCount";
	public static final String LASTIP = "lastIp";
	public static final String LASTLOGINTIME = "lastLoginTime";
	public static final String MOBILE = "mobile";
	public static final String IDCARD = "idCard";
	public static final String CREATELOGINID = "createLoginId";

	private Long loginId;
	private Long accountId;
	private String loginName;
	private String loginPass;
	private String realname;
	private Integer status;
	private Boolean adminFlag;
	private String birthday;
	private Integer sex;
	private String remark;
	private Integer loginCount;
	private String lastIp;
	private Date lastLoginTime;
	private String mobile;
	private String idCard;
	private Long createLoginId;
	private String email;

	/**
	 *
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	/**
	 *
	 */
	public Long getAccountId() {
		return this.accountId;
	}

	/**
	 *
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 *
	 */
	public String getLoginName() {
		return this.loginName;
	}

	/**
	 *
	 */
	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}

	/**
	 *
	 */
	public String getLoginPass() {
		return this.loginPass;
	}

	/**
	 *真实姓名
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}

	/**
	 *真实姓名
	 */
	public String getRealname() {
		return this.realname;
	}

	/**
	 *用户状态 0:锁定1:正常
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 *用户状态 0:锁定1:正常
	 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 *账户管理员 1 非账户管理员 账户多操作员时标志
	 */
	public void setAdminFlag(Boolean adminFlag) {
		this.adminFlag = adminFlag;
	}

	/**
	 *账户管理员 1 非账户管理员 账户多操作员时标志
	 */
	public Boolean getAdminFlag() {
		return this.adminFlag;
	}

	/**
	 *生日 yyyy-MM-dd
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 *生日 yyyy-MM-dd
	 */
	public String getBirthday() {
		return this.birthday;
	}

	/**
	 *1男，0女
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 *1男，0女
	 */
	public Integer getSex() {
		return this.sex;
	}

	/**
	 *备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 *备注
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 *登录次数 每次登录+1
	 */
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	/**
	 *登录次数 每次登录+1
	 */
	public Integer getLoginCount() {
		return this.loginCount;
	}

	/**
	 *最后登录IP
	 */
	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}

	/**
	 *最后登录IP
	 */
	public String getLastIp() {
		return this.lastIp;
	}

	/**
	 *最后登录时间
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 *最后登录时间
	 */
	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	/**
	 *
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 *
	 */
	public String getMobile() {
		return this.mobile;
	}

	/**
	 *
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	/**
	 *
	 */
	public String getIdCard() {
		return this.idCard;
	}

	/**
	 *
	 */
	public void setCreateLoginId(Long createLoginId) {
		this.createLoginId = createLoginId;
	}

	/**
	 *
	 */
	public Long getCreateLoginId() {
		return this.createLoginId;
	}

	public Long getLoginId() {
		return loginId;
	}

	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}