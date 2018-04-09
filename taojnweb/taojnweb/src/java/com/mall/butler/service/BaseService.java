package com.mall.butler.service;

import java.util.List;

import com.mall.butler.model.BaseEntity;

public interface BaseService {

	/**
	 * 删除对象
	 * 
	 * @author caedmon
	 * @date 2010-10-15 下午03:37:40
	 * @param e
	 */
	void delete(BaseEntity e);

	/**
	 * 执行查询语句
	 * 
	 * @author caedmon
	 * @date 2010-10-15 下午03:37:48
	 * @param path
	 * @param param
	 * @return
	 */
	List<Object> findByPath(String path, Object param);

	/**
	 * 更新对象
	 * 
	 * @author caedmon
	 * @date 2010-10-15 下午03:38:10
	 * @param entity
	 */
	void update(BaseEntity entity);

	/**
	 * 查找对象
	 * 
	 * @author caedmon
	 * @date 2010-10-15 下午03:38:27
	 * @param <T>
	 * @param clazz
	 * @param pk
	 * @return
	 */
	<T extends BaseEntity> T getEntityById(Class<T> clazz, Long pk);

}
