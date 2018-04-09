package com.mall.butler.sys.m.service._impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.butler.sys.dao.SysFunctionsDao;
import com.mall.butler.sys.m.service.MSysFunctionService;
import com.mall.butler.sys.model.SysFunctions;
import com.mall.util.common.TxtUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class MSysFunctionServiceImpl extends BaseServiceImpl implements MSysFunctionService {

	@Autowired
	private SysFunctionsDao sysFunctionsDao;

	@Override
	public Page<SysFunctions> queryMFunctionPage(PageRequest<SysFunctions> pageRequest) {
		// 分页查询
		return sysFunctionsDao.page(pageRequest);
	}

	@Override
	@Transactional
	public void doDelSysFunction(SysFunctions sysFunctions) {
		// 当功能模块是组类型时候，同事删除组里的功能连接
		sysFunctions = sysFunctionsDao.getById(sysFunctions.getId());
		if (sysFunctions == null) {
			throw new RuntimeException("错误信息");
		} else {
			SysFunctions param = new SysFunctions();
			param.setParentId(sysFunctions.getId());
			List<SysFunctions> list = sysFunctionsDao.find(param);
			sysFunctionsDao.delete(sysFunctions);
			if (!list.isEmpty() && list.size() > 0) {
				Map<String, Long[]> map = new Hashtable<String, Long[]>();
				Long[] funids = new Long[list.size()];
				int i = 0;
				for (SysFunctions functions : list) {
					funids[i] = functions.getId();
					i++;
				}
				map.put("funIds", funids);
				sysFunctionsDao.delete("SYSFUNCTIONS", map, "BY_FUN_PK");
			}
		}
	}

	@Override
	public List<SysFunctions> findFunctions(SysFunctions sysFunctions) {
		return sysFunctionsDao.find(sysFunctions);
	}

	@Override
	public void doEditSysFunctions(SysFunctions sysFunctions) {
		// 验证数据
		if (TxtUtil.isEmpty(sysFunctions.getFunName())) {
			throw new RuntimeException("名称为空");
		} else if (sysFunctions.getSort() == null) {
			throw new RuntimeException("排序为空");
		}
		SysFunctions param = sysFunctionsDao.getById(sysFunctions.getId());
		if (param == null) {
			throw new RuntimeException("信息异常");
		} else {
			param.setFunName(sysFunctions.getFunName().trim());
			param.setFunType(sysFunctions.getFunType());
			param.setFunUrl(sysFunctions.getFunUrl().trim());
			param.setSort(sysFunctions.getSort());
			if (param.getFunType() == 0) {
				param.setParentId(sysFunctions.getId());
			} else {
				param.setParentId(sysFunctions.getParentId());
			}
			sysFunctionsDao.update(param);
		}
	}

	@Override
	public void doAddSysFunions(SysFunctions sysFunctions) {
		// 数据验证
		if (TxtUtil.isEmpty(sysFunctions.getFunName())) {
			throw new RuntimeException("名称为空");
		} else if (sysFunctions.getSort() == null) {
			throw new RuntimeException("排序为空");
		}
		SysFunctions param = new SysFunctions();
		param.setId(sysFunctionsDao.getNewId());
		param.setFunAccType(sysFunctions.getFunAccType());
		param.setFunName(sysFunctions.getFunName().trim());
		param.setFunType(sysFunctions.getFunType());
		param.setFunUrl(sysFunctions.getFunUrl().trim());
		param.setSort(sysFunctions.getSort());
		if (param.getFunType() == 0) {
			param.setParentId(param.getId());
		}
		sysFunctionsDao.insert(param);
	}

	@Override
	public List<SysFunctions> findNFunctions(SysFunctions sysFunctions) {

		return sysFunctionsDao.queryEntitys("NEW_FUNCTION", sysFunctions);
	}

	@Override
	public void doUpdateSubFunctions(Long fId, Long[] cIds) {
		SysFunctions param = new SysFunctions();
		param.setParentId(fId);
		param.setFunType(1);
		List<SysFunctions> list = findFunctions(param);
		// 去除父模块所有子模块
		for (SysFunctions sysFunctions : list) {
			sysFunctions.setParentId(null);
			sysFunctionsDao.update(sysFunctions);
		}

		// 添加子模块
		for (int i = 0; i < cIds.length; i++) {
			SysFunctions sysFunctions = sysFunctionsDao.getById(cIds[i]);
			sysFunctions.setParentId(fId);
			sysFunctionsDao.update(sysFunctions);
		}
	}
}
