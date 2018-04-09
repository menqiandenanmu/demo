package com.mall.butler.sys.m.service._impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.ManageContext;
import com.mall.butler.service.ApplicationLogService;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.butler.sys.dao.SysDataFilterDao;
import com.mall.butler.sys.m.service.MSysDataFilterService;
import com.mall.butler.sys.model.SysDataFilter;
import com.mall.util.common.TxtUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * @author caedmon
 * @version 创建时间：2013-1-31 上午11:00:05
 */
public class MSysDataFilterServiceImpl extends BaseServiceImpl implements MSysDataFilterService {

	@Autowired
	private SysDataFilterDao sysDataFilterDao;
	@Autowired
	private ApplicationLogService applicationLogService;

	@Override
	public void doAddDataFilter(SysDataFilter dataFilter) {
		// 校验
		checkUser(dataFilter);
		SysDataFilter filter = new SysDataFilter();
		filter.setFilterType(dataFilter.getFilterType());
		filter.setFilterValue(dataFilter.getFilterValue().trim());
		List<SysDataFilter> dataFiltersList = sysDataFilterDao.find(filter);
		if (dataFiltersList != null && dataFiltersList.size() > 0)
			throw new RuntimeException("对象已经存在请重新输入！");
		dataFilter.setId(sysDataFilterDao.getNewId());
		dataFilter.setCreateTime(dataFilter.getCreateTime());
		dataFilter.setModifiedTime(dataFilter.getModifiedTime());
		dataFilter.setRemark(dataFilter.getRemark());
		dataFilter.setFilterType(dataFilter.getFilterType());
		dataFilter.setFilterValue(dataFilter.getFilterValue());
		sysDataFilterDao.insert(dataFilter);
		applicationLogService.log("新增黑名单【" + dataFilter.getFilterValue() + "】成功", "增加黑名单",
				ApplicationLogService.GENERIC,ManageContext.LOG_SYS_TYPE,ManageContext.LOG_OPT_TYPE,null);

	}

	@Override
	public void doDelDataFilter(SysDataFilter sysDataFilter) {
		sysDataFilterDao.delete(sysDataFilter, true);
		applicationLogService.log("删除黑名单【" + sysDataFilter.getFilterValue() + "】成功", "删除黑名单",
				ApplicationLogService.GENERIC,ManageContext.LOG_SYS_TYPE,ManageContext.LOG_OPT_TYPE,null);

	}

	@Override
	public void doUpdateDataFilter(SysDataFilter sysDataFilter) {
		// 只修改过滤对象，备注和修改时间
		SysDataFilter dataFilter = sysDataFilterDao.getById(sysDataFilter.getId());
		// 校验
		if (!dataFilter.getFilterValue().trim().equals(sysDataFilter.getFilterValue().trim())) {
			checkUser(sysDataFilter);
		}
		dataFilter.setFilterValue(sysDataFilter.getFilterValue());
		dataFilter.setRemark(sysDataFilter.getRemark());
		sysDataFilterDao.update(dataFilter);
		applicationLogService.log("更新黑名单【" + sysDataFilter.getFilterValue() + "】成功", "更新黑名单",
				ApplicationLogService.GENERIC,ManageContext.LOG_SYS_TYPE,ManageContext.LOG_OPT_TYPE,null);
	}

	@Override
	public List<SysDataFilter> queryAll(SysDataFilter sysDataFilter) {
		SysDataFilter filter = new SysDataFilter();
		if (sysDataFilter.getFilterType() != null) {
			filter.setFilterValue(sysDataFilter.getFilterType().toString());
		}
		if (!TxtUtil.isEmpty(sysDataFilter.getFilterValue())) {
			filter.setFilterValue(sysDataFilter.getFilterValue().trim());
		}

		return sysDataFilterDao.find(filter);
	}

	@Override
	public Page<SysDataFilter> queryDataFilterPage(PageRequest<SysDataFilter> pageRequest) {
		// 分页查询
		return sysDataFilterDao.pageQuery(pageRequest, "PAGE_M");
	}

	public void checkUser(SysDataFilter sysDataFilter) {
		if (TxtUtil.isEmpty(sysDataFilter.getFilterValue())) {
			throw new RuntimeException("过滤对象为空");
		}
		if (sysDataFilter.getFilterType() == 1) {
			if (!TxtUtil.isIDNumber(sysDataFilter.getFilterValue().trim())) {
				throw new RuntimeException("身份证号码格式不正确！");
			}
		} else if (sysDataFilter.getFilterType() == 0) {
			if (!TxtUtil.isMobile(sysDataFilter.getFilterValue().trim())) {
				throw new RuntimeException("手机号码格式不正确！");
			}
		} else if (sysDataFilter.getFilterType() == 2) {
			if (!TxtUtil.isIPAddress(sysDataFilter.getFilterValue().trim())) {
				throw new RuntimeException("IP地址格式不正确！");
			}
		}
	}
}
