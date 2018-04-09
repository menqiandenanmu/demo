package com.mall.butler.sys.m.service;

import com.mall.butler.service.BaseService;
import com.mall.butler.sys.model.SysTextLibrary;

/**
 * 类描述：
 * 类名称：MSysTextLibrayService
 * 创建人：caedmon
 * 创建时间：2013-5-8 下午10:16:24
 * 修改人：caedmon
 * 修改时间：2013-5-8 下午10:16:24
 * 修改备注：
 * 
 * @version
 */
public interface MSysTextLibrayService extends BaseService {

	public void saveObj(SysTextLibrary text);

	public void updateObj(SysTextLibrary text);

}
