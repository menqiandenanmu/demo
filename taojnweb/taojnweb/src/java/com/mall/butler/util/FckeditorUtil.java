package com.mall.butler.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

/**
 * 生成fck文本
 */
public class FckeditorUtil {
	public static final String TOOLBARSET_DEFAULT = "Default";
	public static final String TOOLBARSET_BASIC = "Basic";

	/**
	 * @param request
	 *            当前请求对象
	 * @param instanceName
	 *            表单控件名字
	 * @param context
	 *            文本初始内容
	 * @param validate
	 *            jqueryvalidate框架验证表达式 fck:true 不为空
	 * @return
	 */
	public static String getFckeditorHtml(HttpServletRequest request,
			String instanceName, String validate, String context) {
		return getFckeditorHtml(request, instanceName, validate, context,
				"700", "400", TOOLBARSET_DEFAULT);
	}

	/**
	 * 
	 * @param request
	 *            当前请求对象
	 * @param instanceName
	 *            表单控件名字
	 * @param context
	 *            文本初始内容
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 * @param toolbarSet
	 *            工具栏
	 * @return
	 */
	public static String getFckeditorHtml(HttpServletRequest request,
			String instanceName, String validate, String context, String width,
			String height, String toolbarSet) {
		if (null == request) {
			request = ServletActionContext.getRequest();
		}
		FCKValidateEditor fckeditor = new FCKValidateEditor(request,
				instanceName, validate, width, height, toolbarSet, context,
				null);
		String basePath = "/script/fckeditor";
		fckeditor.setBasePath(basePath);
		fckeditor.setValue(context);
		fckeditor.setConfig("SkinPath", request.getContextPath() + basePath
				+ "/editor/skins/office2003/");
		return fckeditor.createHtml();
	}

	public static String getTest() {
		return "test";
	}

	public static String getTest(String str) {
		return str;
	}
}
