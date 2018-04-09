package com.mall.butler.service._impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.dao.GeneralDao;
import com.mall.butler.model.BaseEntity;
import com.mall.butler.service.BaseService;

public class BaseServiceImpl implements BaseService {

	@Autowired
	private GeneralDao generalDao;

	@Override
	public void delete(BaseEntity e) {
		generalDao.remove(e);
	}

	@Override
	public <T extends BaseEntity> T getEntityById(Class<T> clazz, Long pk) {
		return generalDao.getEntityById(clazz, pk);
	}

	@Override
	public void update(BaseEntity entity) {
		generalDao.save(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findByPath(String path, Object param) {
		return generalDao.queryObjectsWithPath(path, param);
	}

}
