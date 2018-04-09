package com.mall.butler.sys.model;
import com.mall.butler.model.BaseEntity;
import java.util.Date;

public class SysOptLog extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//静态变量
	public static final String LOGTYPE="logType";
	public static final String ACCOUNTID="accountId";
	public static final String LOGINID="loginId";
	public static final String ACCOUNTTYPE="accountType";
	public static final String TITLE="title";
	public static final String CONTENT="content";
	public static final String LOGTIME="logTime";
	public static final String OPTTYPE="optType";
	public static final String LOGLEVEL="logLevel";
	public static final String OPTIP="optIp";

	private Integer logType;
	private Long accountId;
	private Long loginId;
	private Integer accountType;
	private String title;
	private String content;
	private Date logTime;
	private Integer optType;
	private String logLevel;// 日志等级
	private String optIp;

	/**
	 *日志类型 0：系统日志1：用户日志

	 */
	public void setLogType(Integer logType){
		this.logType = logType;
	}
	/**
	 *日志类型 0：系统日志1：用户日志

	 */
	public Integer getLogType(){
		return this.logType;
	}
	/**
	 *
	 */
	public void setAccountId(Long accountId){
		this.accountId = accountId;
	}
	/**
	 *
	 */
	public Long getAccountId(){
		return this.accountId;
	}
	/**
	 *
	 */
	public void setLoginId(Long loginId){
		this.loginId = loginId;
	}
	/**
	 *
	 */
	public Long getLoginId(){
		return this.loginId;
	}
	/**
	 *0:系统管理1:供应商2::代分销商
	 */
	public void setAccountType(Integer accountType){
		this.accountType = accountType;
	}
	/**
	 *0:系统管理1:供应商2::代分销商
	 */
	public Integer getAccountType(){
		return this.accountType;
	}
	/**
	 *日志主题
	 */
	public void setTitle(String title){
		this.title = title;
	}
	/**
	 *日志主题
	 */
	public String getTitle(){
		return this.title;
	}
	/**
	 *日志内容
	 */
	public void setContent(String content){
		this.content = content;
	}
	/**
	 *日志内容
	 */
	public String getContent(){
		return this.content;
	}
	/**
	 *日志时间
	 */
	public void setLogTime(Date logTime){
		this.logTime = logTime;
	}
	/**
	 *日志时间
	 */
	public Date getLogTime(){
		return this.logTime;
	}
	/**
	 *类型
	 */
	public void setOptType(Integer optType){
		this.optType = optType;
	}
	/**
	 *类型
	 */
	public Integer getOptType(){
		return this.optType;
	}
	/**
	 *等级
	 */
	public void setLogLevel(String logLevel){
		this.logLevel = logLevel;
	}
	/**
	 *等级
	 */
	public String getLogLevel(){
		return this.logLevel;
	}
	/**
	 *客户端地址

	 */
	public void setOptIp(String optIp){
		this.optIp = optIp;
	}
	/**
	 *客户端地址

	 */
	public String getOptIp(){
		return this.optIp;
	}
}