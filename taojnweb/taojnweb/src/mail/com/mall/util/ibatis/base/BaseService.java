package com.mall.util.ibatis.base;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;
/**
 * service扩展接口
 * @author caedmon
 * Version 2010-6-11
 */
@Transactional
public abstract class BaseService <E,PK extends Serializable>{
	
	protected Log log = LogFactory.getLog(getClass());


}
