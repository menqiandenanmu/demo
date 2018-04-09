package com.mall.butler.point.w.model;

import java.util.Date;

import com.mall.butler.point.model.PointChangeDetal;

public class PointChangeDetalExtendDetail extends PointChangeDetal{

	private static final long serialVersionUID = 1L;
	public static final String BEGCREATEDATE="begCreateDate";
	public static final String ENDCREATEDATE="endCreateDate";
	private Double addPoint;
	private Double cutPoint;
	private Date begCreateDate;
	private Date endCreateDate;
	public Double getAddPoint() {
		return addPoint;
	}
	public void setAddPoint(Double addPoint) {
		this.addPoint = addPoint;
	}
	public Double getCutPoint() {
		return cutPoint;
	}
	public void setCutPoint(Double cutPoint) {
		this.cutPoint = cutPoint;
	}
	public Date getBegCreateDate() {
		return begCreateDate;
	}
	public void setBegCreateDate(Date begCreateDate) {
		this.begCreateDate = begCreateDate;
	}
	public Date getEndCreateDate() {
		return endCreateDate;
	}
	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
	

}
