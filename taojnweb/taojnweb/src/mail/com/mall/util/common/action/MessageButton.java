package com.mall.util.common.action;

public class MessageButton {
	protected  String caption;
	protected  String info;
	protected  String script;
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public void setURL(String url){
		this.info =url;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getScript() {
		return script;
	}
	public void setScript(String script) {
		this.script = "javascript:"+script;
	}
	
	

}
