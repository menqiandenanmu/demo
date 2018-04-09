package com.mall.butler.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UrlStack implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4793392988634698575L;
	private List<String> urlList = new ArrayList<String>();

	/**
	 * 压入url
	 * @param url
	 */
	public void addUrl(String url) {
		int i = 0;
		boolean flag = false;
		for (i = 0; i < urlList.size(); i++) {
			String s = urlList.get(i);
			if (s.equals(url)) {
				for (int j = i + 1; j < urlList.size(); j++)
					urlList.remove(i + 1);
				flag = true;
				break;
			}
		}
		if (!flag) {
			urlList.add(url);
			if (urlList.size() > 10)
				urlList.remove(0);
		}
	}

	/**
	 * 得到上一个url
	 * @return
	 */
	public String getBackUrl() {
		int index = urlList.size() - 1 - 1;
		if (index >= 0)
			return urlList.get(index);
		return "";
	}

	/**
	 * 得到当前URl
	 * @return
	 */
	public String getCurrUrl() {
		int index = urlList.size() - 1;
		if (index >= 0)
			return urlList.get(index);
		return "";
	}
}
