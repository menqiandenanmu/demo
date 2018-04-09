package com.mall.butler.sys.m.vo;

import java.util.LinkedList;
import java.util.List;

/**
 * 类描述：
 * 类名称：FunctionsVo
 * 创建人：caedmon
 * 创建时间：2013-5-8 下午04:33:10
 * 修改人：caedmon
 * 修改时间：2013-5-8 下午04:33:10
 * 修改备注：
 * 
 * @version
 */
public class FunctionsVo {

	private Long id;
	/**
	 * 功能名
	 */
	private String funName;

	/**
	 * 功能类型
	 */
	private Integer funType;

	/**
	 * 功能URL
	 */
	private String funUrl;

	/**
	 * 父ID
	 */
	private Integer parentId;

	/**
	 * 权限是否已被设置
	 */
	private Boolean checkFlage;
	/**
	 * 子节点
	 */
	private List<FunctionsVo> childs = new LinkedList<FunctionsVo>();
	/**
	 * 父节点
	 */
	private FunctionsVo parent;

	public Boolean getCheckFlage() {
		return checkFlage;
	}

	public void setCheckFlage(Boolean checkFlage) {
		this.checkFlage = checkFlage;
	}

	public String getFunName() {
		return funName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
	}

	public Integer getFunType() {
		return funType;
	}

	public void setFunType(Integer funType) {
		this.funType = funType;
	}

	public String getFunUrl() {
		return funUrl;
	}

	public void setFunUrl(String funUrl) {
		this.funUrl = funUrl;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public List<FunctionsVo> getChilds() {
		return childs;
	}

	public void setChilds(List<FunctionsVo> childs) {
		this.childs = childs;
	}

	public FunctionsVo getParent() {
		return parent;
	}

	public void setParent(FunctionsVo parent) {
		this.parent = parent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
