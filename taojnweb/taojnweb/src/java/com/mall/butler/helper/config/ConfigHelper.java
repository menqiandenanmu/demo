package com.mall.butler.helper.config;

import java.io.IOException;

public interface ConfigHelper{
	public void load() throws IOException;

	public String get(String name);
}
