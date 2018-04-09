package com.mall.butler.easyui;

import java.util.Collection;

/**
 * EasyUI数据列表返回格式封装实体。
 * 
 * @author andy
 * @param <T>
 *            数据列表类型
 */
public class DataGrid<T> implements java.io.Serializable {

	private static final long serialVersionUID = -4353059775338989141L;

	// 总数量
	private Integer total;
	// 当前分页记录列表
	private Collection<T> rows;

	public DataGrid(Collection<T> rows) {
		this.rows = rows;
		this.total = rows.size();
	}

	public DataGrid(Collection<T> rows, Integer total) {
		this.rows = rows;
		this.total = total;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Collection<T> getRows() {
		return rows;
	}

	public void setRows(Collection<T> rows) {
		this.rows = rows;
	}
}
