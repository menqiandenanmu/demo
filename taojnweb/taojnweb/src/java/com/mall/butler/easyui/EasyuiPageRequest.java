package com.mall.butler.easyui;

import com.mall.util.ibatis.page.PageRequest;

public class EasyuiPageRequest<T> extends PageRequest<T> {

	private static final long serialVersionUID = -842042499925645421L;

	private String order;
	private String sort;

	public EasyuiPageRequest() {
		super();
	}

	public EasyuiPageRequest(int pageNumber, int pageSize, String sortColumns) {
		super(pageNumber, pageSize, sortColumns);
	}

	public EasyuiPageRequest(int pageNumber, int pageSize, T filters, String sortColumns) {
		super(pageNumber, pageSize, filters, sortColumns);
	}

	public EasyuiPageRequest(int pageNumber, int pageSize, T filters) {
		super(pageNumber, pageSize, filters);
	}

	public EasyuiPageRequest(int pageNumber, int pageSize) {
		super(pageNumber, pageSize);
	}

	public EasyuiPageRequest(T filters) {
		super(filters);
	}

	public void addSortField(String sort, String order) {
		this.sort = sort;
		this.order = order;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
}
