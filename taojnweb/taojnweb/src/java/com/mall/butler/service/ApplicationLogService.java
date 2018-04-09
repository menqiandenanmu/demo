package com.mall.butler.service;

/**
 * 系统日志操作
 * 
 * @author caedmon
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
	public void log(String info, String title, String level,Integer logType,Integer optType,Long loginId);

	/**
	 * 普通日志
	 * 
	 * @param info
	 * @param opType
	 * @param level
	 * @param logType 0 系统 1用户
	 */
	public void generic(String info, String title, String level,Integer logType,Integer optType,Long loginId);

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
