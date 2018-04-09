package com.mall.butler.sys.m.service._impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.butler.sys.dao.SysTextLibraryDao;
import com.mall.butler.sys.m.service.MSysTextLibrayService;
import com.mall.butler.sys.model.SysTextLibrary;

/**
 * 项目名称：ebiz3_manage
 * 类名称：SysTextLibrayServiceImpl
 * 类描述： 大文本
 * 创建人：zhaoxs
 * 创建时间：2011-12-27 下午05:46:18
 * 修改人：zhaoxs
 * 修改时间：2011-12-27 下午05:46:18
 * 修改备注：
 * 
 * @version
 */
public class MSysTextLibrayServiceImpl extends BaseServiceImpl implements MSysTextLibrayService {

	@Autowired
	private SysTextLibraryDao sysTextLibraryDao;

	@Override
	public void saveObj(SysTextLibrary text) {
		Long newObjId = this.sysTextLibraryDao.getNewId();
		text.setId(newObjId);
		this.sysTextLibraryDao.insert(text);
	}

	@Override
	public void updateObj(SysTextLibrary text) {
		SysTextLibrary oldObj = this.sysTextLibraryDao.getById(text.getId());
		if (oldObj != null) {
			if (text.getTitle() != null)
				oldObj.setTitle(text.getTitle());
			if (text.getContext() != null)
				oldObj.setContext(text.getContext());
		}
	}

}
