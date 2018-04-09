package com.mall.butler.sys.m.service;

import java.util.Map;

import com.mall.butler.service.BaseService;
import com.mall.butler.sys.m.vo.SysOptLogVo;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * @author zhaoxs 2013-1-30 上午09:37:18
 */
public interface MSysOptLogService extends BaseService {

	/**
	 * 系统日志分页查询
	 * 
	 * @param pageRequest
	 */
	public Page<SysOptLogVo> querySysOptLogPage(PageRequest<Map<String, Object>> pageRequest);

}
