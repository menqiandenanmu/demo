package com.mall.butler.sys.m.service._impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.butler.sys.dao.SysOptLogDao;
import com.mall.butler.sys.m.service.MSysOptLogService;
import com.mall.butler.sys.m.vo.SysOptLogVo;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * @author zhaoxs 2013-1-30 上午09:39:06
 */
public class MSysOptLogServiceImpl extends BaseServiceImpl implements MSysOptLogService {

	@Autowired
	private SysOptLogDao sysOptLogDao;

	@SuppressWarnings("unchecked")
	@Override
	public Page<SysOptLogVo> querySysOptLogPage(PageRequest<Map<String, Object>> pageRequest) {
		return (Page<SysOptLogVo>) sysOptLogDao.pageQueryObj(pageRequest, "PAGE_M");
	}

}
