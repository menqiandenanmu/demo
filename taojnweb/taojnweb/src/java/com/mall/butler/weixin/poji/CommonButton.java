package com.mall.butler.weixin.poji;

/**
 * 普通按钮（子按钮）
 * 没有子菜单的菜单项，有可能是二级菜单项，
 * 也有可能是不含二级菜单的一级菜单。
 * 这类子菜单项一定会包含三个属性：type、name和key
 * @author wangxy 2014-11-28
 */
public class CommonButton extends Button {
	private String type;
	private String key;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
