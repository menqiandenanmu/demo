package com.mall.butler.order.service._impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mall.butler.account.model.TradeOrder;
import com.mall.butler.order.dao.TransInfoDao;
import com.mall.butler.order.dao.TransLogsDao;
import com.mall.butler.order.model.OrderInfo;
import com.mall.butler.order.model.TransInfo;
import com.mall.butler.order.model.TransLogs;
import com.mall.butler.order.service.TransService;
import com.mall.butler.service._impl.BaseServiceImpl;

/**
 * 交易记录操作
 * 
 * @author juror
 */
public class TransServiceImpl extends BaseServiceImpl implements TransService {
	@Autowired
	private TransInfoDao transInfoDao;
	@Autowired
	private TransLogsDao transLogsDao;
	@Override
	public String createNo(String gateway) {
		return transInfoDao.createTransNo(gateway);
	}
	

	@Transactional
	@Override
	public TransInfo alipayTrans(TradeOrder order, int gateway) {
		TransInfo filter = new TransInfo();
		filter.setGateway(gateway);
		filter.setBusinessId(order.getId());
		filter.setTransType(0);
		List<TransInfo> transL = transInfoDao.find(filter);
		if (transL.size() > 0)
			return transL.get(0);
		else {
			TransInfo trans = new TransInfo();
			trans.setId(transInfoDao.getNewId());
			trans.setAccountId(order.getAccountId());
			trans.setAmount(order.getOpValue());
			//trans.setBusinessType(order.getOrderType());
			trans.setBusinessNo(order.getOrderNo());
			trans.setBusinessId(order.getId());
			trans.setTransNo(order.getNetpayNo());
			trans.setStatus(0);
			trans.setLoginId(order.getOpLoginId());
			trans.setGateway(gateway);
			trans.setTransType(0);
			transInfoDao.insert(trans);
			return trans;
		}
	}
	@Transactional
	@Override
	public TransInfo alipayTrans(OrderInfo order) {
		TransInfo filter = new TransInfo();
		filter.setGateway(2);
		filter.setBusinessId(order.getId());
		filter.setTransType(0);
		List<TransInfo> transL = transInfoDao.find(filter);
		if (transL.size() > 0)
			return transL.get(0);
		else {
			TransInfo trans = new TransInfo();
			trans.setId(transInfoDao.getNewId());
			trans.setAccountId(order.getBuyerId());
			trans.setAmount(order.getFinalSum());
			//trans.setBusinessType(order.getOrderType());
			trans.setBusinessNo(order.getOrderNo());
			trans.setBusinessId(order.getId());
			trans.setTransNo(transInfoDao.createTransNo(trans));
			trans.setStatus(0);
			trans.setLoginId(0L);
			trans.setGateway(2);
			trans.setTransType(0);
			transInfoDao.insert(trans);
			return trans;
		}
	}
	
	
	
	@Transactional
	@Override
	public TransInfo weixinTrans(OrderInfo order) {
		TransInfo filter = new TransInfo();
		filter.setGateway(6);
		filter.setBusinessId(order.getId());
		filter.setTransType(0);
		List<TransInfo> transL = transInfoDao.find(filter);
		if (transL.size() > 0)
			return transL.get(0);
		else {
			TransInfo trans = new TransInfo();
			trans.setId(transInfoDao.getNewId());
			trans.setAccountId(order.getBuyerId());
			trans.setAmount(order.getFinalSum());
			//trans.setBusinessType(order.getOrderType());
			trans.setBusinessNo(order.getOrderNo());
			trans.setBusinessId(order.getId());
			trans.setTransNo(transInfoDao.createTransNo(trans));
			trans.setStatus(0);
			trans.setLoginId(0L);
			trans.setGateway(6);
			trans.setTransType(0);
			transInfoDao.insert(trans);
			return trans;
		}
	}
	
	
	
	@Transactional
	@Override
	public TransInfo netpayTrans(OrderInfo order) {
		TransInfo filter = new TransInfo();
		filter.setGateway(4);
		filter.setBusinessId(order.getId());
		filter.setTransType(0);
		List<TransInfo> transL = transInfoDao.find(filter);
		if (transL.size() > 0)
			return transL.get(0);
		else {
			TransInfo trans = new TransInfo();
			trans.setId(transInfoDao.getNewId());
			trans.setAccountId(order.getBuyerId());
			trans.setAmount(order.getFinalSum());
			//trans.setBusinessType(order.getOrderType());
			trans.setBusinessNo(order.getOrderNo());
			trans.setBusinessId(order.getId());
			trans.setTransNo(transInfoDao.createTransNo(trans));
			trans.setStatus(0);
			trans.setLoginId(0L);
			trans.setGateway(4);
			trans.setTransType(0);
			transInfoDao.insert(trans);
			return trans;
		}
	}

	@Override
	public void doInserTransLogs(TransLogs logs) {
		logs.setId(transLogsDao.getNewId());
		transLogsDao.insert(logs);
	}

	@Override
	public void doUpdateTrans(TransInfo trans) {
		TransInfo oldTrans = transInfoDao.getById(trans.getId());
		oldTrans.setStatus(trans.getStatus());
		oldTrans.setReportInfo(trans.getReportInfo());
		oldTrans.setReportTime(trans.getReportTime());
		oldTrans.setGatewayTransNo(trans.getGatewayTransNo());
		transInfoDao.update(oldTrans);
	}

	@Override
	public TransInfo getAlipayTransByNo(String transNo) {
		TransInfo filter = new TransInfo();
		filter.setGateway(5);
		filter.setTransNo(transNo);
		filter.setTransType(0);
		List<TransInfo> transL = transInfoDao.find(filter);
		if (transL.size() > 0)
			return transL.get(0);
		return null;
	}
	
	
	@Override
	public TransInfo getWeixinTransByNo(String transNo) {
		TransInfo filter = new TransInfo();
		filter.setTransNo(transNo);
		filter.setTransType(0);
		List<TransInfo> transL = transInfoDao.find(filter);
		if (transL.size() > 0)
			return transL.get(0);
		return null;
	}

	@Override
	public TransInfo getNetpayTransByNo(String transNo) {
		TransInfo filter = new TransInfo();
		filter.setGateway(4);
		filter.setTransNo(transNo);
		filter.setTransType(0);
		List<TransInfo> transL = transInfoDao.find(filter);
		if (transL.size() > 0)
			return transL.get(0);
		return null;
	}

	@Override
	public TransInfo accpayTrans(OrderInfo order) {
		TransInfo filter = new TransInfo();
		filter.setGateway(3);
		filter.setBusinessId(order.getId());
		filter.setTransType(0);
		List<TransInfo> transL = transInfoDao.find(filter);
		if (transL.size() > 0)
			return transL.get(0);
		else {
			TransInfo trans = new TransInfo();
			trans.setId(transInfoDao.getNewId());
			trans.setAccountId(order.getBuyerId());
			trans.setAmount(order.getFinalSum());
			//trans.setBusinessType(order.getOrderType());
			trans.setBusinessNo(order.getOrderNo());
			trans.setBusinessId(order.getId());
			String no = String.format("%1$011d", trans.getId());
			trans.setTransNo(transInfoDao.createTransNo(trans));
			trans.setStatus(0);
			trans.setLoginId(0L);
			trans.setGateway(3);
			trans.setTransType(0);
			transInfoDao.insert(trans);
			return trans;
		}
	}

	@Override
	public TransInfo getAlipayTransByOrderId(Long orderId) {
		TransInfo filter = new TransInfo();
		filter.setGateway(2);
		filter.setBusinessId(orderId);
		filter.setTransType(0);
		List<TransInfo> transL = transInfoDao.find(filter);
		if (transL.size() > 0) {
			return transL.get(0);
		}
		return null;
	}

	@Override
	public TransInfo wxTrans(OrderInfo order) {
		TransInfo filter = new TransInfo();
		filter.setGateway(10);
		filter.setBusinessId(order.getId());
		List<TransInfo> transL = transInfoDao.find(filter);
		if (transL.size() > 0)
			return transL.get(0);
		else {
			TransInfo trans = new TransInfo();
			trans.setId(transInfoDao.getNewId());
			trans.setAccountId(order.getBuyerId());
			trans.setAmount(order.getFinalSum());
			//trans.setBusinessType(order.getOrderType());
			trans.setBusinessNo(order.getOrderNo());
			trans.setBusinessId(order.getId());
			trans.setTransNo(order.getOrderNo() + "SX");
			trans.setStatus(0);
			trans.setLoginId(0L);
			trans.setGateway(10);
			transInfoDao.insert(trans);
			return trans;
		}
	} 
	
	@Override
	public TransInfo getWxTransByOrderNo(String orderNo) {
		TransInfo filter = new TransInfo();
		filter.setGateway(10);
		filter.setBusinessNo(orderNo);
		List<TransInfo> transL = transInfoDao.find(filter);
		if (transL.size() > 0)
			return transL.get(0);
		return null;
	}
}
