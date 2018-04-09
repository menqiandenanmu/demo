package com.mall.butler.web.model;
import com.mall.butler.model.BaseEntity;

public class StaticInfo extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//静态变量
	public static final String KEYSTR="keystr";
	public static final String NAME="name";
	public static final String CLASSCODE="classCode";
	public static final String CLASSNAME="className";
	public static final String CONTENTID="contentId";

	private String keystr;
	private String name;
	private String classCode;
	private String className;
	private Long contentId;

	/**
	 *key信息字母简写
	 */
	public void setKeystr(String keystr){
		this.keystr = keystr;
	}
	/**
	 *key信息字母简写
	 */
	public String getKeystr(){
		return this.keystr;
	}
	/**
	 *名称
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 *名称
	 */
	public String getName(){
		return this.name;
	}
	/**
	 *分类编号数据字典得 002
	 */
	public void setClassCode(String classCode){
		this.classCode = classCode;
	}
	/**
	 *分类编号数据字典得 002
	 */
	public String getClassCode(){
		return this.classCode;
	}
	/**
	 *分类名字
	 */
	public void setClassName(String className){
		this.className = className;
	}
	/**
	 *分类名字
	 */
	public String getClassName(){
		return this.className;
	}
	/**
	 *内容 对应 文本库ID
	 */
	public void setContentId(Long contentId){
		this.contentId = contentId;
	}
	/**
	 *内容 对应 文本库ID
	 */
	public Long getContentId(){
		return this.contentId;
	}
}