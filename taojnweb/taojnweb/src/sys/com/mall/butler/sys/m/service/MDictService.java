package com.mall.butler.sys.m.service;

import java.util.List;

import com.mall.butler.service.BaseService;
import com.mall.butler.sys.model.SysDict;
import com.mall.butler.sys.model.SysDictDetail;

/**
 * 数据字典管理
 * 
 * @author zhaoxs
 */
public interface MDictService extends BaseService {
	/**
	 * 得到所有数据字典项
	 * 
	 * @return
	 */
	public List<SysDict> queryAllDict();

	/**
	 * 得到数据字典的子项
	 * 
	 * @param dict
	 * @return
	 */
	public List<SysDictDetail> queryDetail(SysDict dict);

	/**
	 * 添加明细信息
	 * 
	 * @param detail
	 */
	public void doAddDetail(SysDictDetail detail);

	/**
	 * 删除明细信息
	 * 
	 * @param detail
	 */
	public void doDelDetail(SysDictDetail detail);

	/**
	 * 修改明细信息
	 * 
	 * @param detail
	 */
	public void doUpdateDetail(SysDictDetail detail);

	/**
	 * 得到数据字典项
	 * 
	 * @param code
	 * @return
	 */
	public SysDict getDict(String code);

	/**
	 * 从字典编号得到内容列表
	 * 
	 * @param dictCode
	 * @return
	 */
	List<SysDictDetail> queryDetail(String dictCode);

	/**
	 * 从明细编号得到明细
	 * 
	 * @param code
	 * @return
	 */
	SysDictDetail getDetail(String code);
}
