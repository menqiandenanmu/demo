package com.mall.butler.point.m.service._impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mall.butler.account.dao.AccountLoginDao;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.point.dao.PointAccountInfoDao;
import com.mall.butler.point.dao.PointChangeDetalDao;
import com.mall.butler.point.m.model.PointChangeDetalExtendDetail;
import com.mall.butler.point.m.service.MUserPointService;
import com.mall.butler.point.model.PointAccountInfo;
import com.mall.butler.point.model.PointChangeDetal;
import com.mall.butler.port.service.MsgPortService;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class MUserPointServiceImpl extends BaseServiceImpl implements MUserPointService{

	@Autowired
	protected PointAccountInfoDao pointAccountInfoDao;
	@Autowired
	protected PointChangeDetalDao pointChangeDetalDao;
	@Autowired
	private AccountLoginDao accountLoginDao;
	@Autowired
	private MsgPortService msgService;
	@SuppressWarnings("unchecked")
	@Override
	public Page<PointAccountInfo> queryUserPoint(PageRequest<Map> filter) {
		return (Page<PointAccountInfo>) pointAccountInfoDao.pageQueryObj(filter, "BY_USERPOINT_PAGE");
	}
	@Override
	public List<PointAccountInfo> getPointAccountList(Map<String, Object> params) {
		return (List<PointAccountInfo>) pointAccountInfoDao.queryEntitys("USERPOINT_PAGE",params);
	}
	
	@Override
	public List<PointAccountInfo> hssPointAccountList(Map<String, Object> params) {
		return (List<PointAccountInfo>) pointAccountInfoDao.queryEntitys("USERHASPOINT_PAGE",params);
	}
	@Override
	public List<PointAccountInfo> noPointAccountList(Map<String, Object> params) {
		return (List<PointAccountInfo>) pointAccountInfoDao.queryEntitys("USERNOPOINT_PAGE",params);
	}
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void doSavePoint(AccountLogin accountLogin, PointAccountInfo pointAccountInfo, PointChangeDetal pointChangeDetal) {
		//更新用户积分
		PointAccountInfo pointAccountInfo2=pointAccountInfoDao.getById(pointAccountInfo.getId());
		pointAccountInfo2.setAccName(pointAccountInfo.getAccName());
		pointAccountInfo2.setCountPoint(pointAccountInfo.getPoint()+pointChangeDetal.getPoint());
		pointAccountInfo2.setPoint(pointAccountInfo.getPoint()+pointChangeDetal.getPoint());
		pointAccountInfoDao.update(pointAccountInfo2);
		//插入订单清单
		PointChangeDetal pointChangeDetal2=new PointChangeDetal();
		long pointId= pointChangeDetalDao.getNewId();
		pointChangeDetal2.setId(pointId);
		pointChangeDetal2.setAccountId(pointAccountInfo.getId());
		pointChangeDetal2.setOpType(1);
		pointChangeDetal2.setOrderNo(String.format("%1$010d",pointId));
		pointChangeDetal2.setPoint(pointChangeDetal.getPoint());
		pointChangeDetal2.setLeftPoint(pointAccountInfo.getPoint()+pointChangeDetal.getPoint());
		pointChangeDetal2.setRemark(pointChangeDetal.getRemark().equals("")?"后台积分赠送":pointChangeDetal.getRemark());
		pointChangeDetal2.setLoginId(accountLogin.getId());
		pointChangeDetal2.setLoginName(accountLogin.getLoginName());
		pointChangeDetalDao.insert(pointChangeDetal2);
		AccountLogin login=accountLoginDao.getById(pointAccountInfo.getId());
		//记录操作的内容
		String msg="尊敬的"+login.getRealname()+"，您好,系统赠送您积分"+pointChangeDetal.getPoint()+"，欢迎使用江南钱包！";
		msgService.sendMsg(login.getMobile(), msg);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Page<PointChangeDetalExtendDetail> queryPointDetail(PageRequest<Map> filter) {
		return (Page<PointChangeDetalExtendDetail>) pointChangeDetalDao.pageQueryObj(filter, "BY_POINTDETAIL_PAGE");
	}
	@SuppressWarnings("unchecked")
	@Override
	public Page<PointChangeDetalExtendDetail> countPointDetail(PageRequest<Map> filter) {
		return (Page<PointChangeDetalExtendDetail>) pointChangeDetalDao.pageQueryObj(filter, "BY_COUNTDETAL_PAGE");
	}
	@SuppressWarnings("unchecked")
	@Override
	public Page<PointAccountInfo> userHasPoint(PageRequest<Map> filter) {
		return (Page<PointAccountInfo>) pointAccountInfoDao.pageQueryObj(filter, "BY_USERHASPOINT_PAGE");
	}
	@SuppressWarnings("unchecked")
	@Override
	public Page<PointAccountInfo> userNoPoint(PageRequest<Map> filter) {
		return (Page<PointAccountInfo>) pointAccountInfoDao.pageQueryObj(filter, "BY_USERNOPOINT_PAGE");
	}
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> querySum(Map<String, Object> params) {
		return (Map<String, Object>) pointChangeDetalDao.getObject("POINT_TOTAL", params);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<PointChangeDetalExtendDetail> pointCountList(Map<String, Object> params) {
		return pointChangeDetalDao.queryObjects("COUNTDETAL_LIST", params);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<PointChangeDetalExtendDetail> pointDetailList(Map<String, Object> params) {
		return pointChangeDetalDao.queryObjects("POINTDETAIL_PAGE", params);
		
	}
	
	

}
