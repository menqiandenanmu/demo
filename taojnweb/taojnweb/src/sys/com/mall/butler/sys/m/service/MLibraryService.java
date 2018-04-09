package com.mall.butler.sys.m.service;

import com.mall.butler.service.BaseService;
import com.mall.butler.sys.model.SysImageLibrary;
import com.mall.butler.sys.model.SysTextLibrary;

/**
 * 系统文件资源管理 图片，flash等
 * 
 * @author zhaoxs
 */

public interface MLibraryService extends BaseService {
	/**
	 * 保存文件
	 * 
	 * @param filePath
	 *            源文件路径
	 * @param targetId
	 *            目标对象id
	 * @param targetType
	 *            目标对象类型
	 * @return
	 */
	@Deprecated
	public SysImageLibrary doSaveImage(String filePath, Long targetId, Integer targetType);

	/**
	 * 保存文件
	 * 
	 * @return
	 */
	public SysImageLibrary doSaveImage(String filePath);

	/**
	 * 保存文本信息
	 * 
	 * @param text
	 * @return
	 */
	SysTextLibrary doSaveText(SysTextLibrary text);

	/**
	 * 保存文信息
	 * 
	 * @param title
	 * @param content
	 * @return
	 */
	SysTextLibrary doSaveText(String title, String content);
}
