package com.mall.butler.account.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.mall.butler.dao.BaseDao;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;
import com.mall.butler.account.model.CouponUseRecord;


@Repository
public class CouponUseRecordDao extends BaseDao<CouponUseRecord, Long>{
	public List<CouponUseRecord> find(CouponUseRecord couponUseRecord) {
		return this.queryEntitys("ALL", couponUseRecord);
	}
	
	public Page<CouponUseRecord> page(PageRequest<CouponUseRecord> request){
		Page<CouponUseRecord> result=this.pageQuery(request,"PAGE");
		return result;
	}
}
