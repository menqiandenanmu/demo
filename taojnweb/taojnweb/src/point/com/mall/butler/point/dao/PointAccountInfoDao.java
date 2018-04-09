package com.mall.butler.point.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.butler.dao.BaseDao;
import com.mall.butler.point.model.PointAccountInfo;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

@Repository
public class PointAccountInfoDao extends BaseDao<PointAccountInfo, Long>{
	public List<PointAccountInfo> find(PointAccountInfo pointAccountInfo) {
		return this.queryEntitys("ALL", pointAccountInfo);
	}
	
	public Page<PointAccountInfo> page(PageRequest<PointAccountInfo> request){
		Page<PointAccountInfo> result=this.pageQuery(request,"PAGE");
		return result;
	}
}
