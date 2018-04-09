package com.mall.butler.sys.service;

import com.mall.butler.service.BaseService;

/**
 * 系统日志操作
 * 
 * @author zhaoxs
 */
public interface ApplicationLogService extends BaseService {
	public static final String DEBUG = "debug";
	public static final String GENERIC = "generic";
	public static final String ERROR = "error";
	public static final String ALL = "all";

	/**
	 * 日志
	 * 
	 * @param info
	 */
	public void log(String info, String title, String level);

	/**
	 * 普通日志
	 * 
	 * @param info
	 * @param opType
	 * @param level
	 */
	public void generic(String info, String title, String level);

	/**
	 * 错误日志
	 * 
	 * @param info
	 */
	public void error(String info, String title, String level);

	/**
	 * 调试日志
	 * 
	 * @param info
	 */
	public void debug(String info, String title, String level);
}
