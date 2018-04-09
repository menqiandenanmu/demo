package com.mall.butler.helper;

/**
 * 项目名称：mall_common 类名称：SessionHelper 类描述： 管理项目session 创建人：caedmon
 * 675053447@gmail.com; 创建时间：2011-10-24 上午10:38:49 修改人：caedmon 修改时间：2011-10-24
 * 上午10:38:49 修改备注：
 * 
 * @version
 * 
 */
public interface SessionHelper {
	/**
	 * 设置值
	 * 
	 * @Title: set
	 * @param key
	 * @param value
	 * @return void 返回类型
	 * @throws
	 */
	public void set(String key, Object value);

	/**
	 * 得到值
	 * 
	 * @Title: get
	 * @param key
	 * @return Object 返回类型
	 * @throws
	 */
	public Object get(String key);

	/**
	 * 清除信息
	 * 
	 * @Title: clear
	 * @return void 返回类型
	 * @throws
	 */
	public void clear();
}
