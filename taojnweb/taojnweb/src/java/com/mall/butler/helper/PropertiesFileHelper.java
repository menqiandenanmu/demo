package com.mall.butler.helper;

import java.util.Properties;

/**
 * property文件读取工具类
 * @author caedmon
 *
 */
public interface PropertiesFileHelper {
	
	String readDate(String name);
	
	void writeDate(String name, String value);
	
	String getFileName();
	
	void setFileName(String fileName);
	
	void finalize();
	
	Properties getProperties();
}
