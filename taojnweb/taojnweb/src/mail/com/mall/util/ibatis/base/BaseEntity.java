package com.mall.util.ibatis.base;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.mall.util.ibatis.util.DateConvertUtils;

public class BaseEntity<E extends Serializable> implements java.io.Serializable {

	private static final long serialVersionUID = -1660962356681587446L;

	protected static final String DATE_FORMAT = "yyyy-MM-dd";

	protected static final String TIME_FORMAT = "HH:mm:ss";

	protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	protected static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";

	public static String date2String(java.util.Date date, String dateFormat) {
		return DateConvertUtils.format(date, dateFormat);
	}

	public static <T extends java.util.Date> T string2Date(String dateString,
			String dateFormat, Class<T> targetResultType) {
		return DateConvertUtils.parse(dateString, dateFormat, targetResultType);
	}

	// 主键Id
	private E id;
	// 修改时间
	private Timestamp modifiedTime;
	// 创建时间
	private Timestamp createTime;
	// 删除标志
	private boolean deleted;

	private Timestamp now;

	public BaseEntity() {
		modifiedTime = new Timestamp(new Date().getTime());
		createTime = new Timestamp(new Date().getTime());
	}

	/**
	 * 获得主键Id
	 * 
	 * <pre>
	 * 使用sequence自动生成策略
	 * </pre>
	 * 
	 * <code>**SEQUENCE.NEXTVAL</code> <code>**SEQUENCE.CURRVAL</code>
	 * 
	 * @return
	 */
	public E getId() {
		return id;
	}

	/**
	 * 设置主键Id
	 * 
	 * <pre>
	 * 使用sequence自动生成策略
	 * </pre>
	 * 
	 * <code>**SEQUENCE.NEXTVAL</code> <code>**SEQUENCE.CURRVAL</code>
	 * 
	 * @param id
	 */
	public void setId(E id) {
		this.id = id;
	}

	/**
	 * 获取修改时间
	 * 
	 * @return
	 */
	public Timestamp getModifiedTime() {
		return modifiedTime;
	}

	/**
	 * 设置最后修改时间
	 * 
	 * <pre>
	 * 时间默认为timestamp类型，返回当前系统时间戳类型
	 * </pre>
	 */
	public void setModifiedTime(Timestamp timestamp) {
		this.modifiedTime = timestamp;
	}

	/**
	 * 获得创建时间
	 * 
	 * @return
	 */
	public Timestamp getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 * 
	 * <pre>
	 * 时间默认为timestamp类型，返回当前系统时间戳类型
	 * </pre>
	 */
	public void setCreateTime(Timestamp timestamp) {
		this.createTime = timestamp;
	}

	/**
	 * 获取删除标志 0：未删除；1：已删除
	 * 
	 * @return
	 */
	public boolean getDeleted() {
		return deleted;
	}

	/**
	 * 设置删除标志 0：未删除；1：已删除
	 * 
	 * @param deleted
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Timestamp getNow() {
		return now;
	}

	public void setNow(Timestamp now) {
		this.now = now;
	}

}
