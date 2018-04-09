package com.mall.butler.sys.m.service._impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.ManageContext;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.exception.ManageException;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.butler.sys.dao.SysParamDao;
import com.mall.butler.sys.m.service.MSysParamService;
import com.mall.butler.sys.model.SysParam;
import com.mall.util.common.TxtUtil;

/**
 * 系统参数管理
 * 
 * @author zhaoxs
 */
public class MSysParamServiceImpl extends BaseServiceImpl implements MSysParamService {

	@Autowired
	private SysParamDao sysParamDao;

	@Override
	public void doUpdate(SysParam param) {
		SysParam t = sysParamDao.getById(param.getId());
		// 值正则式有效性判断
		if (TxtUtil.isEmpty(t.getParamRegex())) {
			if (!TxtUtil.match(param.getParamValue(), t.getParamRegex(), true))
				throw new ManageException("无效的值!");
		}
		t.setParamValue(param.getParamValue());
		sysParamDao.update(t);
	}

	@Override
	public List<SysParam> queryAll(SysParam sysParam) {
		SysParam filter = new SysParam();
		if (!TxtUtil.isEmpty(sysParam.getParamName())) {
			filter.setParamName(sysParam.getParamName().trim());
		}
		if (!TxtUtil.isEmpty(sysParam.getParamCode())) {
			filter.setParamCode(sysParam.getParamCode().trim());
		}
		return sysParamDao.find(filter);
	}

	@Override
	public void refresnParam() {
		SysParam sysParam = new SysParam();
		List<SysParam> spL = this.queryAll(sysParam);
		for (SysParam index : spL) {
			// 分页大小
			if (index.getParamCode().equals(ManageContext.LIST_PAGE_CODE))
				ManageContext.LIST_PAGE_NUM = Integer.parseInt(index.getParamValue());
		}

	}

	@Override
	public void doAddParam(SysParam sysParam, AccountLogin accountLogin) {
		// 判断param值是否为空
		validate(sysParam);
		SysParam param = new SysParam();
		param.setParamCode(sysParam.getParamCode());
		if (sysParamDao.queryEntitys("ALL", param).size() > 0) {
			throw new RuntimeException("系统参数编码重复");
		}
		param = new SysParam();
		param.setParamName(sysParam.getParamName());
		if (sysParamDao.queryEntitys("ALL", param).size() > 0) {
			throw new RuntimeException("系统参数名称重复");
		}
		sysParam.setId(sysParamDao.getNewId());
		sysParam.setOperatorId(accountLogin.getId());
		sysParamDao.insert(sysParam);
	}

	/**
	 * 验证数据
	 * 
	 * @param param
	 */
	private void validate(SysParam param) {
		if (TxtUtil.isEmpty(param.getParamCode())) {
			throw new RuntimeException("系统参数编码为空");
		} else if (TxtUtil.isEmpty(param.getParamName())) {
			throw new RuntimeException("系统参数名称为空");
		} else if (TxtUtil.isEmpty(param.getParamValue())) {
			throw new RuntimeException("系统参数值为空");
		} else {
			param.setParamCode(param.getParamCode().trim());
			param.setParamName(param.getParamName().trim());
			param.setParamValue(param.getParamValue().trim());
			param.setParamRegex(param.getParamRegex().trim());
		}
	}

	@Override
	public void doDelParam(SysParam sysParam) {
		//
		sysParam = sysParamDao.getById(sysParam.getId());
		if (sysParam == null) {
			throw new RuntimeException("参数不存在");
		} else {
			sysParamDao.delete(sysParam, true);
		}
	}
}
