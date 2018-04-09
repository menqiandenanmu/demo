package com.mall.butler.web.m.service._impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.butler.sys.dao.SysTextLibraryDao;
import com.mall.butler.sys.m.service.MDictService;
import com.mall.butler.sys.m.service.MLibraryService;
import com.mall.butler.sys.model.SysDictDetail;
import com.mall.butler.sys.model.SysTextLibrary;
import com.mall.butler.web.dao.StaticInfoDao;
import com.mall.butler.web.m.service.MStaticService;
import com.mall.butler.web.model.StaticInfo;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 静态信息业务实现
 * 
 * @author zhaoxs
 */
public class MStaticServiceImpl extends BaseServiceImpl implements MStaticService {
	@Autowired
	private StaticInfoDao staticInfoDao;
	@Autowired
	private SysTextLibraryDao sysTextLibraryDao;
	@Autowired
	private MLibraryService libraryService;
	@Autowired
	private MDictService dictService;

	@Override
	public void doSaveStatic(StaticInfo info, String content) {
		// 关键字判断
		StaticInfo filter = new StaticInfo();
		filter.setKeystr(info.getKeystr());
		if (staticInfoDao.find(filter).size() > 0)
			throw new RuntimeException("关键字不可以重复!");
		// 数据字典
		SysDictDetail dict = dictService.getDetail(info.getClassCode());
		if (dict == null)
			throw new RuntimeException("无效的分类信息!");
		SysTextLibrary text = libraryService.doSaveText(info.getName(), content);
		info.setContentId(text.getId());
		info.setClassCode(info.getClassCode());
		info.setClassName(dict.getDictDetailValue());
		info.setId(staticInfoDao.getNewId());
		staticInfoDao.insert(info);
	}

	@Override
	public void doUpdateStatic(StaticInfo info, String content) {
		StaticInfo staic = staticInfoDao.getById(info.getId());
		SysTextLibrary text = sysTextLibraryDao.getById(staic.getContentId());
		if (text == null) {
			text = libraryService.doSaveText(info.getName(), content);
		} else {
			text.setContext(content);
			sysTextLibraryDao.update(text);
		}
	}

	@Override
	public Page<StaticInfo> queryStatic(PageRequest<StaticInfo> filter) {
		return staticInfoDao.page(filter);
	}

	@Override
	public void doDelStatic(StaticInfo info) {
		staticInfoDao.delete(info);
	}
}
