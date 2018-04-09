package com.mall.butler.account.m.service._impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mall.butler.ManageContext;
import com.mall.butler.account.dao.AccountInfoDao;
import com.mall.butler.account.dao.AccountLoginDao;
import com.mall.butler.account.dao.AgentInfoDao;
import com.mall.butler.account.dao.TradeAccountDao;
import com.mall.butler.account.m.dao.AgentExtendInfoDao;
import com.mall.butler.account.m.model.AgentExtendInfo;
import com.mall.butler.account.m.service.MAgentService;
import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.account.model.AgentInfo;
import com.mall.butler.account.model.TradeAccount;
import com.mall.butler.point.dao.PointAccountInfoDao;
import com.mall.butler.point.dao.PointChangeDetalDao;
import com.mall.butler.point.dao.PointRuleDao;
import com.mall.butler.point.model.PointAccountInfo;
import com.mall.butler.point.model.PointChangeDetal;
import com.mall.butler.point.model.PointRule;
import com.mall.butler.port.service.CrmService;
import com.mall.butler.port.service.MsgPortService;
import com.mall.butler.port.xml.OutputParameter;
import com.mall.butler.service.ApplicationLogService;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.butler.sys.dao.SysDictDetailDao;
import com.mall.butler.sys.m.service.MDictService;
import com.mall.butler.sys.model.SysDictDetail;
import com.mall.butler.util.XmlHelper;
import com.mall.util.common.TxtUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class MAgentServiceImpl extends BaseServiceImpl implements MAgentService {
	private static Logger logger = Logger.getLogger(MAgentServiceImpl.class);
	@Autowired
	private AgentInfoDao agentInfoDao;
	@Autowired
	private AgentExtendInfoDao agentExtendInfoDao;
	@Autowired
	private ApplicationLogService applicationLogService;
	@Autowired
	private MsgPortService msgService;
	@Autowired
	private AccountInfoDao accountInfoDao;
	@Autowired
	private AccountLoginDao accountLoginDao;
	@Autowired
	private TradeAccountDao tradeAccountDao;
	@Autowired
	private PointAccountInfoDao pointAccountInfoDao;
	@Autowired
	private PointChangeDetalDao pointChangeDetalDao;
	@Autowired
	private CrmService crmService;

	/**
	 * 去除数据首尾空格
	 * 
	 * @param agentInfo
	 * @return
	 */
	private AgentInfo clean(AgentInfo agentInfo) {
		if (!TxtUtil.isEmpty(agentInfo.getCorpName())) {
			agentInfo.setCorpName(agentInfo.getCorpName().trim());
		}
		if (!TxtUtil.isEmpty(agentInfo.getCode())) {
			agentInfo.setCode(agentInfo.getCode().trim());
		}
		return agentInfo;
	}

	/**
	 * 去除数据首尾空格
	 * 
	 * @param agentInfo
	 * @return
	 */
	private AgentExtendInfo cleanPage(AgentExtendInfo agentInfo) {
		if (!TxtUtil.isEmpty(agentInfo.getCorpName())) {
			agentInfo.setCorpName(agentInfo.getCorpName().trim());
		}
		if (!TxtUtil.isEmpty(agentInfo.getCode())) {
			agentInfo.setCode(agentInfo.getCode().trim());
		}
		return agentInfo;
	}

	@Override
	public AgentExtendInfo findByPkForM(Long pk) {
		return agentExtendInfoDao.findByPkForM(pk);
	}

	@Override
	public Page<AgentExtendInfo> queryPageAgent(
			PageRequest<AgentExtendInfo> pageRequest) {
		pageRequest.setFilters(cleanPage(pageRequest.getFilters()));
		return agentExtendInfoDao.page(pageRequest);
	}

	@Override
	public List<AgentExtendInfo> findTradeDetailList(Map<String, Object> map) {
		return agentExtendInfoDao.queryEntitys("PAGE_M", map);
	}

	@Autowired
	private SysDictDetailDao sysDictDetailDao;
	@Autowired
	private MDictService mDictService;
	@Override
	@Transactional
	public Long doAddAgent(AgentInfo agentInfo, AccountLogin accountLogin) {
		logger.error("用户注册开始===================="+accountLogin.getMobile());
		// 验证数据
		validate(agentInfo, accountLogin);

		agentInfo = clean(agentInfo);
		// 用户信息
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setId(accountInfoDao.getNewId());
		accountInfo.setAccName(accountLogin.getMobile());
		agentInfo.setId(accountInfo.getId());
		agentInfo.setLinkmanTel(accountLogin.getMobile());
		agentInfo.setCode(agentInfoDao.createCode(agentInfo));
		if(TxtUtil.isEmpty(agentInfo.getUrl())){
			//设置默认值
			//获取默认用户来源
			SysDictDetail dictDetail=sysDictDetailDao.getById(ManageContext.ACC_REOURCE_CODE);
			agentInfo.setUrl(dictDetail.getDictDetailCode());
			agentInfo.setFax(dictDetail.getDictDetailValue());
			
		}else {
			//获取用户来源
			SysDictDetail dictDetail=mDictService.getDetail(agentInfo.getUrl());
			agentInfo.setUrl(dictDetail.getDictDetailCode());
			agentInfo.setFax(dictDetail.getDictDetailValue());
		}
		accountInfo.setAccNo(agentInfo.getCode());
		accountInfo.setAccStatus(1);
		accountInfo.setAccLevelId(0l);
		accountInfo.setAccType(3);
		accountInfo.setAccLevelId(0L);
		accountInfoDao.insert(accountInfo);

		// 保存数据
		accountLogin.setId(accountInfo.getId());
		accountLogin.setLoginPass(accountLogin.getLoginPass());
		accountLogin.setLoginCount(0);
		accountLogin.setAccountId(accountInfo.getId());
		accountLogin.setStatus(1);
		accountLogin.setAdminFlag(true);
		accountLogin.setLoginName(accountLogin.getMobile());
		accountLogin.setMobile(accountLogin.getMobile());
		accountLogin.setRealname(agentInfo.getCorpName());
		accountLogin.setRemark(agentInfo.getRemark());

		// 新增用户账户
		TradeAccount tradeAccount = new TradeAccount();
		tradeAccount.setAccountId(agentInfo.getId());
		tradeAccount.setAccountName(accountLogin.getMobile());
		tradeAccount.setLeftValue(0d);
		tradeAccount.setId(accountInfo.getId());
		tradeAccount.setWarnLine(0d);
		tradeAccount.setCurLeftValue(0d);
		tradeAccount.setTradeAccName(agentInfo.getBankName());// 钱包名称
		tradeAccount.setTradeType(tradeAccountDao.createTransNo(tradeAccount));
		tradeAccount.setStatus(true);
		tradeAccount.setTranPassword(TxtUtil.digest(agentInfo.getAddress()));
		tradeAccountDao.insert(tradeAccount);
		Integer opValue = ManageContext.POINTS_VALUE;

		PointAccountInfo pointAccountInfo = new PointAccountInfo();
		pointAccountInfo.setId(accountInfo.getId());
		pointAccountInfo.setCountPoint(opValue);
		pointAccountInfo.setAccName(accountInfo.getAccName());
		pointAccountInfo.setPoint(opValue);
		pointAccountInfoDao.insert(pointAccountInfo);
		// if (opValue != 0) {
		// 积分变动日志
		PointChangeDetal pointChangeDetal = new PointChangeDetal();
		pointChangeDetal.setId(pointChangeDetalDao.getNewId());
		pointChangeDetal.setAccountId(pointAccountInfo.getId());
		pointChangeDetal.setOpType(1);// 订单赠送
		pointChangeDetal.setOrderNo(pointChangeDetalDao
				.createTransNo(pointChangeDetal));
		pointChangeDetal.setPoint(opValue);
		pointChangeDetal.setLeftPoint(pointAccountInfo.getPoint());
		pointChangeDetal.setRemark("注册 ，赠送积分");
		pointChangeDetal.setLoginId(accountLogin.getId());
		pointChangeDetal.setLoginName(accountLogin.getLoginName());
		pointChangeDetalDao.insert(pointChangeDetal);
		// }

		applicationLogService.generic("新增客户：" + agentInfo.getCorpName()
				+ " 信息成功", "新增客户信息", ApplicationLogService.GENERIC,
				ManageContext.LOG_SYS_TYPE, ManageContext.LOG_OPT_TYPE_ACC,
				null);
		applicationLogService.generic("新增客户账户：【"
				+ tradeAccount.getAccountName() + "】 信息成功", "新增客户账户",
				ApplicationLogService.GENERIC, ManageContext.LOG_SYS_TYPE,
				ManageContext.LOG_OPT_TYPE_ACC, null);
		System.out.println("=======================================查询注册用户================================");
		logger.error("=======================================查询注册用户================================");
		// 推送用户到crm系统，先查询crm有没有次用户如果有此用户这标记为已对接，否则推送用户和积分到crm
		String accountString = crmService.queryAccount(accountLogin, "");
		if (null == accountString) {
			System.out.println("=======================================用户未找到注册用户================================");
			logger.error("=======================================用户未找到注册用户================================");
			String reg = crmService.doRegAccount(accountLogin, "");
			if (null == reg){
				logger.error("用户激活失败"+agentInfo.getCorpName());
				throw new RuntimeException("用户激活失败");
				}
			System.out.println("=======================================用户注册成功============="+reg);
			logger.error("=======================================用户注册成功============="+reg);
			String[] msg = reg.split("</OutputParameter>");
			String[] con = msg[0].split("<OutputParameter>");
			OutputParameter crmRegistXml = XmlHelper.toObj(
					OutputParameter.class,
					"<?xml version=\"1.0\" encoding=\"UTF-8\"?><OutputParameter>"
							+ con[1].toUpperCase() + "</OutputParameter>");
			agentInfo.setBankCode(crmRegistXml.getVIPCARDNO());
			agentInfo.setBankOpenInfo(crmRegistXml.getCARDTYPECODE());
			agentInfo.setParentAgentName(crmRegistXml.getCARDFACENO());
			accountLogin.setBirthday(crmRegistXml.getVIPCARDNO());
			System.out.println("卡号========================="
					+ crmRegistXml.getVIPCARDNO());
			logger.error("卡号================="+ crmRegistXml.getVIPCARDNO());
			String pointstr = crmService.doAddPoint(accountLogin, "",
					pointChangeDetal);
			if (null == pointstr){
				logger.error("用户激活失败");	
				throw new RuntimeException("用户激活失败");
			}
		} else {
			// 激活用户
			System.out.println("=======================================用户查询成功============="+accountString);
			logger.error("=======================================用户查询成功============="+accountString);
			agentInfo.setAgentType(1);
		}
		// 保存用户条形码
		agentInfo.setPartnerKey(agentInfoDao.createKey(agentInfo));
		agentInfoDao.insert(agentInfo);
		accountLoginDao.insert(accountLogin);
		String msg = "尊敬的" + accountLogin.getRealname() + "，您好，欢迎注册江南钱包！";
		System.out.println("=======================================用户注册成功=============");
		logger.error("=======================================用户注册成功============="+accountLogin.getRealname());
		msgService.sendMsg(accountLogin.getMobile(), msg);
		return accountInfo.getId();
	}

	@Autowired
	private PointRuleDao pointRuleDao;
	@Transactional
	@Override
	public void addPoint(AgentInfo agentInfo, Integer payValue,Integer OpType,String source) {
		//根据操作值来判断赠送积分
		PointRule rule=new PointRule();
//		rule.setBnsType(OpType.toString());
		rule.setBnsType("0");//目前只有消费
		//rule.setOpValue(payValue.toString());
		rule.setSource(source);
		rule.setUseStatus(1);
		List<PointRule> rules=pointRuleDao.queryEntitys("FIND_ALL_RULE",rule);
		Integer opValue=0;
		Integer pointValue=0;
		int amountValue=0;
		if(rules!=null&&rules.size()>0){
			//获取第一个
			rule=rules.get(0);
			pointValue=Integer.parseInt(rule.getAmount());
			amountValue=Integer.parseInt(rule.getOpValue());
		
		//根据支付金额计算赠送数量
		int pointNum=payValue/amountValue;//赠送基数
		opValue=pointNum*pointValue;
		//超过值的才新增
		if(opValue>0){
		AccountLogin accountLogin=accountLoginDao.getById(agentInfo.getId());
		PointAccountInfo pointAccountInfo =pointAccountInfoDao.getById(agentInfo.getId());
		pointAccountInfo.setCountPoint(pointAccountInfo.getCountPoint()+opValue);
		pointAccountInfo.setPoint(pointAccountInfo.getPoint()+opValue);
		pointAccountInfoDao.update(pointAccountInfo);
		// if (opValue != 0) {
		// 积分变动日志
		PointChangeDetal pointChangeDetal = new PointChangeDetal();
		pointChangeDetal.setId(pointChangeDetalDao.getNewId());
		pointChangeDetal.setAccountId(pointAccountInfo.getId());
		pointChangeDetal.setOpType(OpType);// 订单赠送
		pointChangeDetal.setOrderNo(pointChangeDetalDao
				.createTransNo(pointChangeDetal));
		pointChangeDetal.setPoint(opValue);
		pointChangeDetal.setLeftPoint(pointAccountInfo.getPoint());
		pointChangeDetal.setRemark("送积分"+opValue);
		pointChangeDetal.setLoginId(accountLogin.getId());
		pointChangeDetal.setLoginName(accountLogin.getLoginName());
		pointChangeDetalDao.insert(pointChangeDetal);
		
		String pointstr = crmService.doAddPoint(accountLogin, "",
				pointChangeDetal);
		if (null == pointstr)
			throw new RuntimeException("积分赠送失败");
		applicationLogService.generic("赠送积分：【" +pointChangeDetal.getPoint()
				+ " 】成功", "消费赠送积分", ApplicationLogService.GENERIC,
				ManageContext.LOG_ACC_TYPE, ManageContext.LOG_OPT_TYPE_POINT,
				null);
		}
		}
		
		
	}

	@Override
	@Transactional
	public void doUpdateAgent(AgentInfo agentInfo) {
		if (agentInfoDao.getById(agentInfo.getId()) == null) {
			throw new RuntimeException("信息出错,请刷新");
		} else {
			AgentInfo param = agentInfoDao.getById(agentInfo.getId());
			param.setCorpName(agentInfo.getCorpName());
			param.setRemark(agentInfo.getRemark());
			param.setBankName(agentInfo.getBankName());
			agentInfoDao.update(param);
			AccountLogin accountLogin = accountLoginDao.getById(agentInfo
					.getId());
			if (null == accountLogin)
				throw new RuntimeException("信息出错,请刷新");
			accountLogin.setRealname(agentInfo.getCorpName());
			accountLoginDao.update(accountLogin);
			TradeAccount tradeAccount = tradeAccountDao.getById(agentInfo
					.getId());
			tradeAccount.setTradeType(agentInfo.getBankName());
			tradeAccountDao.update(tradeAccount);
			applicationLogService.generic("修改客户信息：【" + param.getCorpName()
					+ " 】信息成功", "修改客户信息", ApplicationLogService.GENERIC,
					ManageContext.LOG_SYS_TYPE, ManageContext.LOG_OPT_TYPE_ACC,
					null);

		}

	}

	/**
	 * 保存信息验证
	 * 
	 * @return
	 */
	private void validate(AgentInfo agentInfo, AccountLogin accountLogin) {
		if (TxtUtil.isEmpty(accountLogin.getMobile()))
			throw new RuntimeException("联系方式为空" + accountLogin.getMobile());
		if (TxtUtil.isEmpty(agentInfo.getCorpName()))
			throw new RuntimeException("客户名称为空" + agentInfo.getCorpName());
		if (TxtUtil.isEmpty(agentInfo.getBankName()))
			throw new RuntimeException("钱包名称为空" + agentInfo.getBankName());
		if (TxtUtil.isEmpty(agentInfo.getAddress()))
			throw new RuntimeException("交易密码为空" + agentInfo.getAddress());
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setAccName(accountLogin.getMobile());
		if (accountInfoDao.find(accountInfo).size() > 0) {
			throw new RuntimeException("联系方式重复" + accountLogin.getMobile());
		}
		AccountLogin filter = new AccountLogin();
		filter.setLoginName(accountLogin.getMobile());
		List<AccountLogin> al = accountLoginDao.find(filter);
		if (al != null && al.size() > 0)
			throw new RuntimeException("账户名已经存在" + accountLogin.getLoginName());
	}

	@Override
	@Transactional
	public void doDelAgent(AgentInfo agentInfo) {
		if (agentInfo.getId() == null) {
			throw new RuntimeException("信息出错,请刷新");
		} else {
			agentInfo = agentInfoDao.getById(agentInfo.getId());
			if (agentInfo == null) {
				throw new RuntimeException("信息出错,请刷新");
			}
			agentInfoDao.delete(agentInfo);

			AccountInfo accountInfo = accountInfoDao.getById(agentInfo.getId());
			if (accountInfo == null) {
				throw new RuntimeException("信息出错,请刷新");
			}
			accountInfoDao.delete(accountInfo);

			// 删除操作员
			AccountLogin param = accountLoginDao.getById(agentInfo.getId());
			accountLoginDao.delete(param);
			// 删除用户的资金账户信息
			TradeAccount tradeAccount = tradeAccountDao.getById(agentInfo
					.getId());
			tradeAccountDao.delete(tradeAccount);
			applicationLogService.generic("删除客户：【" + agentInfo.getCorpName()
					+ " 】信息成功", "删除客户信息", ApplicationLogService.GENERIC,
					ManageContext.LOG_SYS_TYPE, ManageContext.LOG_OPT_TYPE_ACC,
					null);

		}

	}

	@Override
	public List<AgentExtendInfo> findAll(AgentExtendInfo info) {
		return agentExtendInfoDao.findAll(info);
	}

	@Override
	public List<AgentInfo> queryAgentByBnsType(String bnsType) {
		return agentInfoDao.queryEntitys("BNSTYPE_M", bnsType);
	}

	@Override
	public List<AgentInfo> queryList(AgentInfo agentInfo) {
		return agentInfoDao.find(agentInfo);
	}

	@Override
	public void updateCode(AgentInfo agentInfo) {
		// 保存用户条形码
		agentInfo.setPartnerKey(agentInfoDao.createKey());
		agentInfoDao.update(agentInfo);

	}
	public String getAgentKey(){
		String barCode=agentInfoDao.createKey();
		if(barCode.substring(0, 2).equals("88"))
			getAgentKey();
		//查询是否有重复的
		AgentInfo filter=new AgentInfo();
		filter.setPartnerKey(barCode);
		List<AgentInfo> list=agentInfoDao.find(filter);
		if(null!=list&&list.size()>0){
			getAgentKey();
		}
		return barCode;
	}
	public Boolean codeFlag(String code) {
		AgentInfo filter = new AgentInfo();
		filter.setPartnerKey(code);
		if (agentInfoDao.find(filter).size() > 0) {
			return true;
		} else {
			return false;
		}
	}
}
