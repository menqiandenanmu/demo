package com.mall.butler.dao;

import java.io.Serializable;

import com.mall.util.ibatis.IbatisBaseDaoImpl;
import com.mall.util.ibatis.base.BaseEntity;

public abstract class BaseDao<E extends BaseEntity<PK>, PK extends Serializable>
		extends IbatisBaseDaoImpl<E, PK> {

}
