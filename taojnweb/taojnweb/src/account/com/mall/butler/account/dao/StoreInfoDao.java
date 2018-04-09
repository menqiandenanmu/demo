package com.mall.butler.account.dao;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Repository;

import com.mall.butler.account.model.StoreInfo;
import com.mall.butler.dao.BaseDao;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;


@Repository
public class StoreInfoDao extends BaseDao<StoreInfo, Long>{
	public List<StoreInfo> find(StoreInfo storeInfo) {
		return this.queryEntitys("ALL", storeInfo);
	}
	
	public Page<StoreInfo> page(PageRequest<StoreInfo> request){
		Page<StoreInfo> result=this.pageQuery(request,"PAGE");
		return result;
	}
	/**
	 * 长度12位+8位随机数
	 * @param consumerCoupons
	 * @return
	 */
	public String createQrCode(Long newId) {
		Long n =newId % 100000;
		Random random = new Random();
		String rands = "";
		for (int i = 0; i < 8; i++) {
			rands = rands + random.nextInt(10);
		}
		rands = rands + String.format("%1$04d", n);
		return rands;
	}
}
