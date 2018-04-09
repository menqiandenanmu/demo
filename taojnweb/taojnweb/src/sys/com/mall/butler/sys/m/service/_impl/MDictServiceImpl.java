package com.mall.butler.sys.m.service._impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.butler.sys.dao.SysDictDao;
import com.mall.butler.sys.dao.SysDictDetailDao;
import com.mall.butler.sys.m.service.MDictService;
import com.mall.butler.sys.model.SysDict;
import com.mall.butler.sys.model.SysDictDetail;

public class MDictServiceImpl extends BaseServiceImpl implements MDictService {
	@Autowired
	private SysDictDetailDao sysDictDetailDao;
	@Autowired
	private SysDictDao sysDictDao;

	@Override
	public void doAddDetail(SysDictDetail detail) {
		// 编号唯一验证
		SysDictDetail filter = new SysDictDetail();
		filter.setDictDetailCode(detail.getDictDetailCode());
		List<SysDictDetail> sddList = sysDictDetailDao.find(filter);
		if (sddList.size() > 0)
			throw new RuntimeException("编号重复!" + detail.getDictDetailCode());
		// 名字字典下唯一验证
		filter = new SysDictDetail();
		filter.setDictDetailName(detail.getDictDetailName());
		filter.setDictId(detail.getDictId());
		sddList = sysDictDetailDao.find(filter);
		if (sddList.size() > 0)
			throw new RuntimeException("名称不能重复!" + detail.getDictDetailName());

		detail.setId(sysDictDetailDao.getNewId());
		sysDictDetailDao.insert(detail);
	}

	@Override
	public void doDelDetail(SysDictDetail detail) {
		SysDictDetail dictDetail = sysDictDetailDao.getById(detail.getId());
		sysDictDetailDao.delete(dictDetail);
	}

	@Override
	public void doUpdateDetail(SysDictDetail detail) {
		// 只修改名字值和备注三项信息
		SysDictDetail dictDetail = sysDictDetailDao.getById(detail.getId());
		dictDetail.setDictDetailName(detail.getDictDetailName());
		dictDetail.setDictDetailValue(detail.getDictDetailValue());
		dictDetail.setRemark(detail.getRemark());
		dictDetail.setListSort(detail.getListSort());
		sysDictDetailDao.update(dictDetail);
	}

	@Override
	public List<SysDict> queryAllDict() {
		return sysDictDao.find(null);
	}

	@Override
	public List<SysDictDetail> queryDetail(SysDict dict) {
		SysDictDetail filter = new SysDictDetail();
		filter.setDictId(dict.getId());
		return sysDictDetailDao.find(filter);
	}

	@Override
	public List<SysDictDetail> queryDetail(String dictCode) {
		SysDict dict = this.getDict(dictCode);
		if (dict == null)
			return null;
		SysDictDetail filter = new SysDictDetail();
		filter.setDictId(dict.getId());
		return sysDictDetailDao.find(filter);
	}

	@Override
	public SysDict getDict(String code) {
		SysDict filter = new SysDict();
		filter.setDictCode(code);
		List<SysDict> sdL = sysDictDao.find(filter);
		return sdL.size() > 0 ? sdL.get(0) : null;
	}

	@Override
	public SysDictDetail getDetail(String code) {
		SysDictDetail filter = new SysDictDetail();
		filter.setDictDetailCode(code);
		List<SysDictDetail> sddL = sysDictDetailDao.find(filter);
		return sddL.size() > 0 ? sddL.get(0) : null;
	}

}
