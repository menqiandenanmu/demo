package com.mall.butler.dao;

import org.springframework.stereotype.Repository;

import com.mall.butler.model.BaseEntity;
import com.mall.util.ibatis.IbatisBaseDaoImpl;

@Repository
public class GeneralDao extends IbatisBaseDaoImpl<BaseEntity, Long> {

	@SuppressWarnings("unchecked")
	public <T extends BaseEntity> T getEntityById(Class<T> clazz, Long pk) {
		String namespace = clazz.getSimpleName().toUpperCase();
		try {
			T c = clazz.newInstance();
			c.setId(pk);
			Object o = this.getObjectWithPath(namespace + "."
					+ BaseDao.SELECT_BY_PK, c);
			return (T) o;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 保存对像
	 * 
	 * @Title: remove
	 * @param entity
	 * @return void 返回类型
	 * @throws
	 */
	public void remove(BaseEntity entity) {
		String namespace = entity.getClass().getSimpleName().toUpperCase();
		entity.setDeleted(true);
		this.updateWithPath(namespace + "." + BaseDao.UPDATE_BY_PK, entity);
	}

	public void remove(BaseEntity entity, boolean flag) {
		String namespace = entity.getClass().getSimpleName().toUpperCase();
		if (flag)
			this.deleteWithPath(namespace + "." + BaseDao.DELETE_BY_PK, entity);
		else {
			entity.setDeleted(true);
			this.updateWithPath(namespace + "." + BaseDao.UPDATE_BY_PK, entity);
		}
	}

	public void save(BaseEntity entity) {
		String namespace = entity.getClass().getSimpleName().toUpperCase();
		this.updateWithPath(namespace + "." + BaseDao.UPDATE_BY_PK, entity);
	}
}
