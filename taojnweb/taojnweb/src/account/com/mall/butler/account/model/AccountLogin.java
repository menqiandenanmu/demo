package com.mall.butler.account.model;

import java.util.Date;

import com.mall.butler.model.BaseEntity;

public class AccountLogin extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 静态变量
	public static final String ACCOUNTID = "accountId";
	public static final String LOGINNAME = "loginName";
	public static final String LOGINPASS = "loginPass";
	public static final String REALNAME = "realname";
	public static final String EMAIL = "email";
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

	private Long accountId;
	private String loginName;
	private String loginPass;
	private String realname;
	private String email;
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
	

	/**
	 *账户信息主键
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	/**
	 *账户信息主键
	 */
	public Long getAccountId() {
		return this.accountId;
	}

	/**
	 *登录名
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 *登录名
	 */
	public String getLoginName() {
		return this.loginName;
	}

	/**
	 *登录密码
	 */
	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}

	/**
	 *登录密码
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
	 *电子邮件
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 *电子邮件
	 */
	public String getEmail() {
		return this.email;
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
	 *手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 *手机号
	 */
	public String getMobile() {
		return this.mobile;
	}

	/**
	 *身份证号
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	/**
	 *身份证号
	 */
	public String getIdCard() {
		return this.idCard;
	}

	/**
	 *用户创建人
	 */
	public void setCreateLoginId(Long createLoginId) {
		this.createLoginId = createLoginId;
	}

	/**
	 *用户创建人
	 */
	public Long getCreateLoginId() {
		return this.createLoginId;
	}
}