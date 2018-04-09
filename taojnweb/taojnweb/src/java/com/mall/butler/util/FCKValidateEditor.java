package com.mall.butler.util;

import javax.servlet.http.HttpServletRequest;

import net.fckeditor.FCKeditorConfig;
import net.fckeditor.handlers.PropertiesLoader;
import net.fckeditor.tool.Compatibility;
import net.fckeditor.tool.Utils;
import net.fckeditor.tool.XHtmlTagTool;

/**
 * 加入validate验证
 * 
 * @author caedmon
 * 
 */
public class FCKValidateEditor {
	public FCKValidateEditor(HttpServletRequest request, String instanceName, String inputName, String validate, String width, String height,
			String toolbarSet, String value, String basePath) {
		this(request, instanceName, inputName, validate);
		this.width = width;
		this.height = height;
		this.toolbarSet = toolbarSet;
		this.value = value;
		this.basePath = basePath;
	}

	public FCKValidateEditor(HttpServletRequest request, String instanceName, String validate, String width, String height, String toolbarSet, String value,
			String basePath) {
		this(request, instanceName, null, validate, width, height, toolbarSet, value, basePath);
	}

	public FCKValidateEditor(HttpServletRequest request, String instanceName, String validate) {
		fckConfig = new FCKeditorConfig();
		value = "";
		toolbarSet = PropertiesLoader.getEditorToolbarSet();
		width = PropertiesLoader.getEditorWidth();
		height = PropertiesLoader.getEditorHeight();
		basePath = PropertiesLoader.getEditorBasePath();
		this.validate = validate;
		if (request == null) {
			throw new NullPointerException("the request cannot be null");
		} else {
			this.request = request;
			setInstanceName(instanceName);
			return;
		}
	}

	public FCKValidateEditor(HttpServletRequest request, String instanceName, String inputName, String validate) {
		this(request, instanceName, validate);
		setInputName(inputName);
	}

	public void setInstanceName(String instanceName) {
		if (Utils.isEmpty(instanceName))
			throw new IllegalArgumentException("instanceName cannot be empty");
		if (!instanceName.matches("\\p{Alpha}[\\p{Alnum}:_.-]*")) {
			throw new IllegalArgumentException("instanceName must be a valid XHTML id containing only \"\\p{Alpha}[\\p{Alnum}:_.-]*\"");
		} else {
			this.instanceName = instanceName;
			return;
		}
	}

	public void setInputName(String inputName) {
		if (Utils.isEmpty(inputName))
			this.inputName = instanceName;
		else
			this.inputName = inputName;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public void setToolbarSet(String toolbarSet) {
		this.toolbarSet = toolbarSet;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getConfig(String name) {
		return (String) fckConfig.get(name);
	}

	public void setConfig(String name, String value) {
		if (value != null)
			fckConfig.put(name, value);
	}

	private String escapeXml(String str) {
		if (Utils.isEmpty(str))
			return str;
		StringBuffer sb = new StringBuffer();
		int len = str.length();
		for (int i = 0; i < len; i++) {
			char c = str.charAt(i);
			switch (c) {
			case 38: // '&'
				sb.append("&amp;");
				break;

			case 60: // '<'
				sb.append("&lt;");
				break;

			case 62: // '>'
				sb.append("&gt;");
				break;

			case 34: // '"'
				sb.append("&quot;");
				break;

			case 39: // '\''
				sb.append("&#39;");
				break;

			default:
				sb.append(c);
				break;
			}
		}

		return sb.toString();
	}

	public String toString() {
		StringBuffer strEditor = new StringBuffer();
		strEditor.append("<div>");
		String encodedValue = escapeXml(value);
		setInputName(inputName);
		if (Compatibility.isCompatibleBrowser(request)) {
			StringBuffer editorLink = new StringBuffer(request.getContextPath());
			editorLink.append(basePath);
			editorLink.append("/editor/fckeditor.html?InstanceName=").append(instanceName);
			if (Utils.isNotEmpty(toolbarSet))
				editorLink.append("&amp;Toolbar=").append(toolbarSet);
			XHtmlTagTool iframeTag = new XHtmlTagTool("iframe", " ");
			iframeTag.addAttribute("id", instanceName.concat("___Frame"));
			iframeTag.addAttribute("src", editorLink.toString());
			iframeTag.addAttribute("width", width);
			iframeTag.addAttribute("height", height);
			iframeTag.addAttribute("frameborder", "0");
			iframeTag.addAttribute("scrolling", "no");
			strEditor.append(iframeTag);
			strEditor.append(createInputForVariable(instanceName, inputName, encodedValue, validate));
			String configStr = fckConfig.getUrlParams();
			if (Utils.isNotEmpty(configStr))
				strEditor.append(createInputForVariable(instanceName.concat("___Config"), null, configStr, ""));
		} else {
			XHtmlTagTool textareaTag = new XHtmlTagTool("textarea", encodedValue);
			textareaTag.addAttribute("name", inputName);
			textareaTag.addAttribute("rows", "4");
			textareaTag.addAttribute("cols", "40");
			textareaTag.addAttribute("wrap", "virtual");
			textareaTag.addAttribute("style", "width: ".concat(width).concat("; height: ").concat(height));
		}
		strEditor.append("</div>");
		return strEditor.toString();
	}

	public String createHtml() {
		return toString();
	}

	private String createInputForVariable(String id, String name, String value, String validate) {
		XHtmlTagTool tag = new XHtmlTagTool("input");
		tag.addAttribute("id", id);
		if (name != null && !name.trim().equals(""))
			tag.addAttribute("name", name);
		tag.addAttribute("value", value);
		tag.addAttribute("type", "hidden");
		if (validate != null && !validate.trim().equals(""))
			tag.addAttribute("validate", validate);
		return tag.toString();
	}

	private FCKeditorConfig fckConfig;
	private String instanceName;
	private String inputName;
	private HttpServletRequest request;
	private String value;
	private String toolbarSet;
	private String width;
	private String height;
	private String basePath;
	private String validate;
}
