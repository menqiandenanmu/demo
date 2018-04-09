package com.mall.butler.util;

import java.io.StringWriter;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public final class TemplateUtil {
	/**
	 * 合并模板
	 * 
	 * @param templateContent
	 *            模板内容
	 * @param rootMap
	 *            合并对象
	 * @return 合并后的内容
	 */
	public static String mergeTemplate(String templateContent, Object rootMap) {
		try {
			Configuration cfg = new Configuration();
			cfg.setTemplateLoader(new StringTemplateLoader(templateContent));
			cfg.setDefaultEncoding("UTF-8");
			Template template = cfg.getTemplate("");
			StringWriter writer = new StringWriter();
			template
					.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
			template.process(rootMap, writer);
			return writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("模板合并异常!");
		}
	}

}