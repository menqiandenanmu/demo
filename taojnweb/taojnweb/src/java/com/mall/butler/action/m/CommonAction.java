package com.mall.butler.action.m;

public class CommonAction extends ManageBaseAction {

	private static final long serialVersionUID = -7885100241990701082L;
	private static String INDEX = "index"; 
	private static String LEFT = "left";
	private static String PANEL = "panel";
	
	public String execute(){
		return INDEX;
	}
	public String left(){
		return LEFT;
	}
	public String top(){
		return PANEL;
	}
}
