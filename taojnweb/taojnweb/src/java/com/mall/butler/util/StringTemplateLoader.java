package com.mall.butler.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import freemarker.cache.TemplateLoader;

public class StringTemplateLoader implements TemplateLoader {
	private String templateContent;
	public StringTemplateLoader(String templateContent) {
		this.templateContent = templateContent;
	}

	@Override
	public Object findTemplateSource(String name) throws IOException {
		return templateContent;
	}

	@Override
	public long getLastModified(Object templateSource) {
		return 0;
	}

	@Override
	public Reader getReader(Object templateSource, String encoding) throws IOException {
		return new StringReader((String) templateSource);
	}

	@Override
	public void closeTemplateSource(Object templateSource) throws IOException {

	}

}
