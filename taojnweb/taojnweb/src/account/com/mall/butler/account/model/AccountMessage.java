package com.mall.butler.account.model;

import com.mall.butler.model.BaseEntity;

public class AccountMessage extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 静态变量
	public static final String ACCOUNTID = "accountId";
	public static final String ACCOUNTNAME = "accountName";
	public static final String TITLE = "title";
	public static final String TYPE = "type";
	public static final String SENDERID = "senderId";
	public static final String CONTENT = "content";
	public static final String SENDERNAME = "senderName";
	public static final String READFLAG = "readFlag";

	private Long accountId;
	private String accountName;
	private String title;
	private String type;
	private Long senderId;
	private String content;
	private String senderName;
	private Boolean readFlag;

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
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 *
	 */
	public String getAccountName() {
		return this.accountName;
	}

	/**
	 *用户名
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 *用户名
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 *0：普通消息 1：系统通知
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 *0：普通消息 1：系统通知
	 */
	public String getType() {
		return this.type;
	}

	/**
	 *
	 */
	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	/**
	 *
	 */
	public Long getSenderId() {
		return this.senderId;
	}

	/**
	 *
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 *
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 *
	 */
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	/**
	 *
	 */
	public String getSenderName() {
		return this.senderName;
	}

	/**
	 *0:未读 1:已读
	 */
	public void setReadFlag(Boolean readFlag) {
		this.readFlag = readFlag;
	}

	/**
	 *0:未读 1:已读
	 */
	public Boolean getReadFlag() {
		return this.readFlag;
	}
}