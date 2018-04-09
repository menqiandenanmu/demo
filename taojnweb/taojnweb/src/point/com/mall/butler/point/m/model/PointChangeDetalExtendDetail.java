package com.mall.butler.point.m.model;


import com.mall.butler.point.model.PointChangeDetal;

public class PointChangeDetalExtendDetail extends PointChangeDetal{

	private static final long serialVersionUID = 1L;
	private String accName;
	private Double addPoint;
	private Double cutPoint;
	private String timeDate;
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
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
	public String getTimeDate() {
		return timeDate;
	}
	public void setTimeDate(String timeDate) {
		this.timeDate = timeDate;
	}
	

}
