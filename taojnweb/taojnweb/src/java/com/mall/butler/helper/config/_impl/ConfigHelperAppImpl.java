package com.mall.butler.helper.config._impl;

import com.mall.butler.helper.config.ConfigHelper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

public final class ConfigHelperAppImpl implements ConfigHelper {
	private static final long serialVersionUID = -495204423028846325L;
	private String[] configs;
	private Properties context = new Properties();

	@Override
	public String get(String name) {
		return this.context.getProperty(name);
	}

	@Override
	public void load() {
		for (String config : this.configs)
			try {
				InputStream in = new ClassPathResource(config, this.getClass())
						.getInputStream();
				this.context.load(in);
			} catch (IOException ex) {
			}
	}

	public void setConfigs(String[] configs) {
		this.configs = configs;
		this.load();
	}
}
