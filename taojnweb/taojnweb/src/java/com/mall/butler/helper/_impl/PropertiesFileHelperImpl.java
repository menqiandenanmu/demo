package com.mall.butler.helper._impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import com.mall.butler.helper.PropertiesFileHelper;
import com.mall.util.common.lang.DateUtil;

/**
 * 配置文件读写
 * 
 * @author caedmon
 * 
 */
public class PropertiesFileHelperImpl implements PropertiesFileHelper {
	private String fileName;
	private Properties properties;
	private InputStream in;
	private OutputStream out;

	@Override
	public String readDate(String name) {
		return properties.getProperty(name, "");
	}

	@Override
	public void writeDate(String name, String value) {
		properties.setProperty(name, value);
		this.store();
	}

	/**
	 * 写文件
	 */
	synchronized private void store() {
		try {
			out = new BufferedOutputStream(new FileOutputStream(fileName));
			properties.store(out, DateUtil.format(new Date(), ""));
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileNamea) {
		this.fileName = fileNamea;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			URL url = loader.getResource(fileNamea);
			this.fileName = url.toURI().getPath();
			File f = new File(fileName);
			if (!f.exists()) {
				throw new RuntimeException("文件不存在");
			}
			properties = new Properties();
			in = new BufferedInputStream(new FileInputStream(this.fileName));
			properties.load(in);
			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void finalize() {
		this.store();
	}

	public Properties getProperties() {
		return properties;
	}
}
