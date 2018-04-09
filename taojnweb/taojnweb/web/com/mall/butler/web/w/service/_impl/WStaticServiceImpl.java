package com.mall.butler.web.w.service._impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.butler.web.dao.StaticInfoDao;
import com.mall.butler.web.model.StaticInfo;
import com.mall.butler.web.w.service.WStaticService;

/**
 * @author caedmon
 * @version 创建时间：2013-3-26 下午03:06:01
 */
public class WStaticServiceImpl extends BaseServiceImpl implements WStaticService {

	@Autowired
	private StaticInfoDao staticInfodao;

	@Override
	public StaticInfo getByCode(String classCode) {

		StaticInfo staticInfo = new StaticInfo();
		staticInfo.setClassCode(classCode);
		List<StaticInfo> staticInfos = staticInfodao.find(staticInfo);
		if (staticInfos != null && staticInfos.size() > 0) {
			return staticInfos.get(0);
		} else {
			return null;
		}
	}

}
