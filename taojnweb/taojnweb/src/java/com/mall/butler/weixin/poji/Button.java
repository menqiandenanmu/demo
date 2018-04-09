package com.mall.butler.weixin.poji;

/**
 * 按钮的基类
 * 所有一级菜单、二级菜单都共有一个相同的属性，那就是name
 * @author wangxy 2014-11-28
 */
public class Button {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
