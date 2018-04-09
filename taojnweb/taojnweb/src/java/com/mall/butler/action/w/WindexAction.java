package com.mall.butler.action.w;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.ManageContext;
import com.mall.butler.account.model.TradeAccount;
import com.mall.butler.account.service.TradeAccountService;
import com.mall.butler.point.model.PointAccountInfo;
import com.mall.butler.point.w.service.WPointAccountInfoService;
import com.mall.butler.web.model.PageAreaDetail;
import com.mall.butler.web.w.service.WPageService;


/**
 * 前台首页
 * 
 * @author caedmon 2013-2-28 上午09:16:21
 */
public class WindexAction extends WebBaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private TradeAccountService tradeAccountService;
	@Autowired
	private WPointAccountInfoService wPointAccountInfoService;
	@Autowired
	private WPageService wPageService;
	private TradeAccount tradeAccount;
	private PointAccountInfo pointAccountInfo;
	private PageAreaDetail pageAreaDetail;
	public PageAreaDetail getPageAreaDetail() {
		return pageAreaDetail;
	}
	public void setPageAreaDetail(PageAreaDetail pageAreaDetail) {
		this.pageAreaDetail = pageAreaDetail;
	}
	public String execute() {
		
		//查找账户余额
		tradeAccount=tradeAccountService.getTradeAccountById(super.getAccount().getId());
		pointAccountInfo=wPointAccountInfoService.findById(super.getAccount().getId());
		List<PageAreaDetail> pageAreaDetails= wPageService.findByCode(ManageContext.INDEX_CLASS,1);
		if(null!=pageAreaDetails&&pageAreaDetails.size()>0){
			pageAreaDetail=pageAreaDetails.get(0);
		}
		return SUCCESS;
	}
	public PointAccountInfo getPointAccountInfo() {
		return pointAccountInfo;
	}
	public void setPointAccountInfo(PointAccountInfo pointAccountInfo) {
		this.pointAccountInfo = pointAccountInfo;
	}
	public TradeAccount getTradeAccount() {
		return tradeAccount;
	}
	public void setTradeAccount(TradeAccount tradeAccount) {
		this.tradeAccount = tradeAccount;
	}

}
