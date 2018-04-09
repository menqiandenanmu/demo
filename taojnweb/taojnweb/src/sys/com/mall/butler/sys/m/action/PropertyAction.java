package com.mall.butler.sys.m.action;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.action.m.ManageBaseAction;
import com.mall.butler.helper.PropertiesFileHelper;
import com.mall.util.common.action.MessageDialog;

/**
 * 类描述：配置文件
 * 类名称：PropertyAction
 * 创建人：caedmon
 * 创建时间：2013-5-8 下午10:14:59
 * 修改人：caedmon
 * 修改时间：2013-5-8 下午10:14:59
 * 修改备注：
 * 
 * @version
 */
public class PropertyAction extends ManageBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -151773720402232828L;
	@Autowired
	private PropertiesFileHelper propertiesFileHelper;

	private Properties properties;
	private String key;
	private String value;

	/**
	 * 读取配置文件
	 */
	public String execute() {
		propertiesFileHelper.setFileName("conf/app.properties");
		properties = propertiesFileHelper.getProperties();

		return SUCCESS;
	}

	/**
	 * 编辑
	 * 
	 * @return
	 */
	public String edit() {
		value = propertiesFileHelper.getProperties().getProperty(key);
		return EDIT;
	}

	public String update() {
		propertiesFileHelper.writeDate(key, value);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("修改成功!");
		return JDIALOG;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
