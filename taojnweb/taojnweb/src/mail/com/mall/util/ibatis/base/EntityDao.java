package com.mall.util.ibatis.base;

import java.util.List;
import java.util.Map;

/**
 * dao方法扩展接口，根据不用业务增减
 * @author caedmon
 * Version 2010-6-11
 */
public interface EntityDao{

	public Object getEntityByName(String name);
	
	public Object getEntityById(Long id);
	
	public List<?> getEntityListAll();
	
	public List<?> getEntityListByObject(Map<String, String> params);
	
	public Object update(Object entity);
	
	public Object save(Object entity);
	
	public Object delete(Long id);
	
}
