package com.mall.butler.sys.service._impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.ManageContext;
import com.mall.butler.RequestContext;
import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.service.ApplicationLogService;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.butler.sys.dao.SysOptLogDao;
import com.mall.butler.sys.model.SysOptLog;

public class ApplicationLogServiceImpl extends BaseServiceImpl implements ApplicationLogService {

	@Autowired
	private SysOptLogDao sysOptLogDao;

	@Override
	public void debug(String info, String title, String level) {
		SysOptLog log = new SysOptLog();
		log.setId(sysOptLogDao.getNewId());
		log.setAccountId(RequestContext.getAccount().getId());
		log.setLoginId(RequestContext.getLogin().getId());
		log.setContent(info);
		log.setLogLevel(level);
		log.setLogType(0);
		log.setAccountType(RequestContext.getAccount().getAccType());

		sysOptLogDao.insert(log);
	}

	@Override
	public void error(String info, String title, String level) {
		SysOptLog log = new SysOptLog();
		log.setId(sysOptLogDao.getNewId());
		log.setAccountId(RequestContext.getAccount().getId());
		log.setLoginId(RequestContext.getLogin().getId());
		log.setContent(info);
		log.setLogLevel(level);
		log.setLogType(0);
		log.setAccountType(RequestContext.getAccount().getAccType());

		sysOptLogDao.insert(log);
	}

	@Override
	public void log(String info, String title, String level,Integer logType,Integer optType,Long loginId) {

		// 等级“，”分隔 如all 或者generic,debug
		String setlevel = ManageContext.LOG_LEVEL_VALUE;

		// 全部日志
		if (setlevel.indexOf(ApplicationLogService.ALL) >= 0) {
			if (ApplicationLogService.DEBUG.equals(level))
				error(info, title, level);
			// 调试日志
			if (ApplicationLogService.ERROR.equals(level))
				debug(info, title, level);
			// 调试日志
			if (ApplicationLogService.GENERIC.equals(level))
				generic(info, title, level,logType,optType,loginId);
		} else {
			// 其他
			if (ApplicationLogService.DEBUG.equals(level) && setlevel.indexOf(level) >= 0)
				error(info, title, level);
			// 调试日志
			if (ApplicationLogService.ERROR.equals(level) && setlevel.indexOf(level) >= 0)
				debug(info, title, level);
			// 调试日志
			if (ApplicationLogService.GENERIC.equals(level) && setlevel.indexOf(level) >= 0)
				generic(info, title, level,logType,optType,loginId);
		}

	}

	@Override
	public void generic(String info, String title, String level,Integer logType,Integer optType,Long loginId) {
		SysOptLog log = new SysOptLog();
		log.setId(sysOptLogDao.getNewId());
		if(null==RequestContext.getLogin())
		{
			AccountLogin accountLogin=super.getEntityById(AccountLogin.class, loginId);
			if(accountLogin != null){
				log.setLoginId(accountLogin.getId());
				log.setAccountId(accountLogin.getAccountId());
				AccountInfo	account = super.getEntityById(AccountInfo.class, accountLogin.getAccountId());
				if(account != null)
					log.setAccountType(account.getAccType());
			}
		}else {
			log.setAccountId(RequestContext.getAccount().getId());
			log.setLoginId(RequestContext.getLogin().getId());
			log.setAccountType(RequestContext.getAccount().getAccType());
		}
		
		log.setContent(info);
		log.setLogLevel(level);
		log.setLogType(logType);
		log.setOptType(optType);
		log.setTitle(title);
		log.setOptIp(RequestContext.getRemotIp());
		sysOptLogDao.insert(log);
	}

}
