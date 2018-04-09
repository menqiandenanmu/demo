package com.mall.butler.account.w.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.WebsiteContext;
import com.mall.butler.account.m.service.MAgentService;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.account.model.AgentInfo;
import com.mall.butler.account.w.service.WAccountService;
import com.mall.butler.action.w.WebBaseAction;
import com.mall.butler.helper.SessionHelper;
import com.mall.butler.port.service.MsgPortService;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.common.lang.DateUtil;

/**
 * 类名称：AuthAction 类描述： 前台用户登录注册平台 创建人：caedmon 创建时间：2013-4-26 下午01:56:22
 * 修改人：caedmon 修改时间：2013-4-26 下午01:56:22 修改备注：
 * 
 * @version
 */
public class AuthAction extends WebBaseAction {

	private static final long serialVersionUID = 1584927878119906872L;
	@Autowired
	private SessionHelper sessionHelper;
	@Autowired
	private WAccountService wAccountService;
	private AccountLogin accountLogin;// 注册用户信息

	private String loginName; // 登录名

	private String loginPass;// 密码
	private String loginPass1;// 确认密码

	private String checkCode;// 验证码

	private Long lgId;// 找回密码时，所有 登录ID

	private Integer opType;// 操作类型 0 手机接收 1邮箱接收

	private Integer checkFlag;// 服务选中 0 未选 1选中
	@Autowired
	private MAgentService mAgentService;
	private AgentInfo agentInfo;

	@Autowired
	private MsgPortService msgService;
	private String returnUrl; // 登录完成跳转url

	public String execute() {
		Long id = this.getLoginId();
		if (id == null)
			return INPUT;
		else
			return SUCCESS;
	}

	/**
	 * @Title:去注册
	 * @param @return
	 * @date 2013-4-26 下午01:58:03
	 * @return
	 */
	public String toCodeReg() {
		return "reg";
	}

	/**
	 * @Title:去注册
	 * @param @return
	 * @date 2013-4-26 下午01:58:03
	 * @return
	 */
	public String toDoReg() {
		// 判断是否已经输入过验证码
		
		return "reg";
	}

	/**
	 * @Title:获取验证码
	 * @param @return
	 * @date 2013-4-26 下午01:58:03
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void sendCheckCode() {
		String message = "";
		String flag = ERROR;
		Object t = sessionHelper.get(WebsiteContext.SESSION_CHECKCODE);
		// 开取测试不验证验证码
		if ((null == t || !t.toString().equals(checkCode.toUpperCase()))) {
			message = "输入的验证码有误，或验证码已经过时，请更换验证码";
			msgInfo.setFlag(flag);
			msgInfo.addMessage(message);
			this.write(msgInfo);
			return;
		}else {
			//清除旧的
			sessionHelper.set(WebsiteContext.SESSION_CHECKCODE,"");
		}
		
		if (loginName == null || loginName == "") {
			message = "请输入手机号！";
			msgInfo.setFlag(flag);
			msgInfo.addMessage(message);
			this.write(msgInfo);
			return;
		}
		if (!TxtUtil.isMobile(loginName)) {
			message = "请输入正确的手机号码！";
			msgInfo.setFlag(flag);
			msgInfo.addMessage(message);
			this.write(msgInfo);
			return;
		}
		accountLogin = wAccountService.findByLoginName(loginName);
		if (null!=accountLogin){
			message = "该手机号已被注册";
			msgInfo.setFlag(flag);
			msgInfo.addMessage(message);
			this.write(msgInfo);
			return;
		}
		// 初始化map
		if (sessionHelper.get(WebsiteContext.MOBILE_CHECK_CODE_MAP) == null) {
			Map<String, String> param = new HashMap<String, String>();
			sessionHelper.set(WebsiteContext.MOBILE_CHECK_CODE_MAP, param);
		}

		// 第一次直接发送验证码
		Map<String, String> mobileCheckCodeMap = (Map<String, String>) sessionHelper
				.get(WebsiteContext.MOBILE_CHECK_CODE_MAP);
		String mobile = mobileCheckCodeMap.get("mobile");
		String checkCodeTime = mobileCheckCodeMap.get("checkCodeTime");
		if (mobile == null || checkCodeTime == null) {
			String randNum = pucCheckCode();
			String msg = "尊敬的用户：" + loginName + "您好，您本次验证码为" + randNum
					+ "(验证码1分钟有效)";
			String status = msgService.sendMsg(loginName, msg);
			if (null != status && status.equals("0")) {
				sendSaveCode(loginName, randNum);
				message = "验证码已经发送到手机[" + loginName + "]，请查收！";
				flag = SUCCESS;
				msgInfo.setFlag(flag);
				msgInfo.addMessage(message);
				this.write(msgInfo);
				return;
			} else {
				message = "发送失败，请稍后重试";
				msgInfo.setFlag(flag);
				msgInfo.addMessage(message);
				this.write(msgInfo);
				return;
			}

		}

		// 获取两次发送验证码的时间之差
		Date prev = DateUtil.toDate(checkCodeTime);
		Date now = new Date();
		long diff = now.getTime() - prev.getTime();
		long second = diff / (1000);

		if (second >= 60) {
			String randNum = pucCheckCode();
			String msg = "尊敬的用户：" + mobile + "您好，您本次验证码为" + randNum
					+ "(验证码1分钟有效)";
			String status = msgService.sendMsg(mobile, msg);
			if (null != status && status.equals("0")) {
				sendSaveCode(loginName, randNum);
				System.out.println("发送成功");
			} else {
				message = "发送失败，请稍后重试";
				msgInfo.setFlag(flag);
				msgInfo.addMessage(message);
				this.write(msgInfo);
				return;
			}
			message = "验证码已经发送到手机" + loginName + "，请查收！";
			flag = SUCCESS;
			msgInfo.setFlag(flag);
			msgInfo.addMessage(message);
			this.write(msgInfo);
			return;
		} else {
			long leftSecond = 60 - second;
			message = "两次发送短信的间隔不得小于一分钟，请" + leftSecond + "秒后重试";
			msgInfo.setFlag(flag);
			msgInfo.addMessage(message);
			this.write(msgInfo);
			return;
		}
	}

	// 发送验证码并且把验证码保存到session中
	public void sendSaveCode(String mobile, String randNum) {

		String checkCodeTime = DateUtil.format(new Date(),
				"yyyy-MM-dd HH:mm:ss");
		String checkCode = randNum;
		Map<String, String> codeMap = new HashMap<String, String>();
		codeMap.put("mobile", mobile);
		codeMap.put("checkCodeTime", checkCodeTime);
		codeMap.put("checkCode", checkCode);
		sessionHelper.set(WebsiteContext.MOBILE_CHECK_CODE_MAP, codeMap);
	}

	
	/**
	 * @Title:获取验证码
	 * @param @return
	 * @date 2013-4-26 下午01:58:03
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void sendPassCode() {
		String message = "";
		String flag = ERROR;
		accountLogin = wAccountService.getEntityById(AccountLogin.class,
				super.getLoginId());
		// 初始化map
		if (sessionHelper.get(WebsiteContext.MOBILE_CHECK_CODE_MAP) == null) {
			Map<String, String> param = new HashMap<String, String>();
			sessionHelper.set(WebsiteContext.MOBILE_CHECK_CODE_MAP, param);
		}

		// 第一次直接发送验证码
		Map<String, String> mobileCheckCodeMap = (Map<String, String>) sessionHelper
				.get(WebsiteContext.MOBILE_CHECK_CODE_MAP);
		String mobile = mobileCheckCodeMap.get("mobile");
		String checkCodeTime = mobileCheckCodeMap.get("checkCodeTime");
		if (mobile == null || checkCodeTime == null) {
			String randNum = pucCheckCode();
			String msg = "尊敬的用户：" + accountLogin.getLoginName() + "您好，您本次验证码为" + randNum
					+ "(验证码1分钟有效)";
			String status = msgService.sendMsg(accountLogin.getLoginName(), msg);
			if (null != status && status.equals("0")) {
				sendSaveCode(accountLogin.getLoginName(), randNum);
				message = "验证码已经发送到手机[" + accountLogin.getLoginName() + "]，请查收！";
				flag = SUCCESS;
				msgInfo.setFlag(flag);
				msgInfo.addMessage(message);
				this.write(msgInfo);
				return;
			} else {
				message = "发送失败，请稍后重试";
				msgInfo.setFlag(flag);
				msgInfo.addMessage(message);
				this.write(msgInfo);
				return;
			}

		}

		// 获取两次发送验证码的时间之差
		Date prev = DateUtil.toDate(checkCodeTime);
		Date now = new Date();
		long diff = now.getTime() - prev.getTime();
		long second = diff / (1000);

		if (second >= 60) {
			String randNum = pucCheckCode();
			String msg = "尊敬的用户：" + mobile + "您好，您本次验证码为" + randNum
					+ "(验证码1分钟有效)";
			String status = msgService.sendMsg(mobile, msg);
			if (null != status && status.equals("0")) {
				sendSaveCode(accountLogin.getLoginName(), randNum);
				System.out.println("发送成功");
			} else {
				message = "发送失败，请稍后重试";
				msgInfo.setFlag(flag);
				msgInfo.addMessage(message);
				this.write(msgInfo);
				return;
			}
			message = "验证码已经发送到手机" + accountLogin.getLoginName() + "，请查收！";
			flag = SUCCESS;
			msgInfo.setFlag(flag);
			msgInfo.addMessage(message);
			this.write(msgInfo);
			return;
		} else {
			long leftSecond = 60 - second;
			message = "两次发送短信的间隔不得小于一分钟，请" + leftSecond + "秒后重试";
			msgInfo.setFlag(flag);
			msgInfo.addMessage(message);
			this.write(msgInfo);
			return;
		}
	}
	/**
	 * @Title:获取验证码
	 * @param @return
	 * @date 2013-4-26 下午01:58:03
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void sendTransPassCode() {
		String message = "";
		String flag = ERROR;
		accountLogin = wAccountService.getEntityById(AccountLogin.class,
				super.getLoginId());
		// 初始化map
		if (sessionHelper.get(WebsiteContext.MOBILE_CHECK_CODE_MAP) == null) {
			Map<String, String> param = new HashMap<String, String>();
			sessionHelper.set(WebsiteContext.MOBILE_CHECK_CODE_MAP, param);
		}

		// 第一次直接发送验证码
		Map<String, String> mobileCheckCodeMap = (Map<String, String>) sessionHelper
				.get(WebsiteContext.MOBILE_CHECK_CODE_MAP);
		String mobile = mobileCheckCodeMap.get("mobile");
		String checkCodeTime = mobileCheckCodeMap.get("checkCodeTime");
		if (mobile == null || checkCodeTime == null) {
			String randNum = pucCheckCode();
			String msg = "尊敬的用户：" + accountLogin.getLoginName() + "您好，您本次验证码为" + randNum
					+ "(验证码1分钟有效)";
			String status = msgService.sendMsg(accountLogin.getLoginName(), msg);
			if (null != status && status.equals("0")) {
				sendSaveCode(accountLogin.getLoginName(), randNum);
				message = "验证码已经发送到手机[" + accountLogin.getLoginName() + "]，请查收！";
				flag = SUCCESS;
				msgInfo.setFlag(flag);
				msgInfo.addMessage(message);
				this.write(msgInfo);
				return;
			} else {
				message = "发送失败，请稍后重试";
				msgInfo.setFlag(flag);
				msgInfo.addMessage(message);
				this.write(msgInfo);
				return;
			}

		}

		// 获取两次发送验证码的时间之差
		Date prev = DateUtil.toDate(checkCodeTime);
		Date now = new Date();
		long diff = now.getTime() - prev.getTime();
		long second = diff / (1000);

		if (second >= 60) {
			String randNum = pucCheckCode();
			String msg = "尊敬的用户：" + mobile + "您好，您本次验证码为" + randNum
					+ "(验证码1分钟有效)";
			String status = msgService.sendMsg(mobile, msg);
			if (null != status && status.equals("0")) {
				sendSaveCode(accountLogin.getLoginName(), randNum);
				System.out.println("发送成功");
			} else {
				message = "发送失败，请稍后重试";
				msgInfo.setFlag(flag);
				msgInfo.addMessage(message);
				this.write(msgInfo);
				return;
			}
			message = "验证码已经发送到手机" + accountLogin.getLoginName() + "，请查收！";
			flag = SUCCESS;
			msgInfo.setFlag(flag);
			msgInfo.addMessage(message);
			this.write(msgInfo);
			return;
		} else {
			long leftSecond = 60 - second;
			message = "两次发送短信的间隔不得小于一分钟，请" + leftSecond + "秒后重试";
			msgInfo.setFlag(flag);
			msgInfo.addMessage(message);
			this.write(msgInfo);
			return;
		}
	}
	/**
	 * 登录
	 * 
	 * @return
	 */
	public String registerNext() {
		String message = validateMobileCheckCode();
		if (SUCCESS.equals(message)) {
			return "register";
		} else {
			this.addActionError(message);
			return "reg";
		}
	}

	// 验证手机号是否为本人手机号
	@SuppressWarnings("all")
	public String validateMobileCheckCode() {
		String message = "";
		if (loginPass == null || TxtUtil.isEmpty(loginPass)) {
			return message = "手机验证码为空！";
		}
		if (loginName == null || TxtUtil.isEmpty(loginName)) {
			return message = "请输入手机号！";
		}
		if (!TxtUtil.isMobile(loginName)) {
			return message = "请输入正确的手机号码！";
		}

		if (sessionHelper.get(WebsiteContext.MOBILE_CHECK_CODE_MAP) == null) {
			return message = "请先填写手机号，并且点击发送验证码！";
		}

		// 第一次直接发送验证码
		Map<String, String> mobileCheckCodeMap = (Map<String, String>) sessionHelper
				.get(WebsiteContext.MOBILE_CHECK_CODE_MAP);
		String mobile = mobileCheckCodeMap.get("mobile");
		String checkCodeTime = mobileCheckCodeMap.get("checkCodeTime");
		String checkCode = mobileCheckCodeMap.get("checkCode");
		if (mobile == null || checkCodeTime == null || checkCode == null) {
			return message = "请先填写手机号，并且点击发送验证码！";
		}
		if (!loginName.equals(mobile)) {
			return message = "手机号码必须为接收短信的手机号";
		}
		// 获取两次发送验证码的时间之差
		Date prev = DateUtil.toDate(checkCodeTime);
		Date now = new Date();
		long diff = now.getTime() - prev.getTime();
		long second = diff / (1000);

		// 验证码的有效期是30分钟
		if (second >= 1800) {
			return message = "距离上次发送的验证码已经超过30分钟，请重新发送验证码！";
		} else {
			if (checkCode.equals(loginPass)) {
				return message = SUCCESS;
			} else {
				return message = "输入的手机验证码错误！";
			}
		}
	}

	private String pucCheckCode() {
		Random random = new Random();
		String rands = "";
		for (int i = 0; i < 4; i++) {
			rands = rands + random.nextInt(10);
		}
		return rands;
	}

	/**
	 * @Title:注册
	 * @param @return
	 * @date 2013-4-26 下午01:56:46
	 * @return
	 */
	public String doReg() {
		accountLogin.setCreateLoginId(getLoginId());
		accountLogin.setLoginPass(TxtUtil.digest(accountLogin.getLoginPass()));
		Long loginId = mAgentService.doAddAgent(agentInfo, accountLogin);
		if (loginId != null) {
			this.putLoginId(loginId);
			msgInfo.setFlag(MessageDialog.SUCCESS);
			msgInfo.addMessage("注册成功!");
			return JDIALOG;
		} else {
			throw new RuntimeException("注册过程中出错了，请重新注册!");
		}
		// }
	}

	/**
	 * @Title:登陆
	 * @param @return
	 * @date 2013-4-26 下午01:57:09
	 * @return
	 */
	public String login() {
		AccountLogin accountLogin = new AccountLogin();
		Object t = sessionHelper.get(WebsiteContext.SESSION_CHECKCODE);
		// 开取测试不验证验证码
		if ((null == t || !t.toString().equals(checkCode))) {
			this.addActionError("输入的验证码有误!");
			return INPUT;
		} else {
			accountLogin.setLoginName(loginName);
			accountLogin.setLoginPass(TxtUtil.digest(loginPass));
			System.out.println("输入值================="+loginPass);
			accountLogin.setLastIp(this.getClientIp());
			// 执行登录
			accountLogin = wAccountService.doOperatorLogin(accountLogin);

			if (accountLogin != null) {
				// 保存登录信息
				this.putLoginId(accountLogin.getId());
				Object temp = sessionHelper.get(WebsiteContext.BEFORE_LOGIN_URL);
				if (temp != null) {
					returnUrl = (String) temp;
					// 删除登录后跳转url
					sessionHelper.set(WebsiteContext.BEFORE_LOGIN_URL, null);
					return "returnUrl";
				}
				return SUCCESS;
			} else {
				this.addActionError("输入的用户名密码有误!");
			}
		}
		return INPUT;
	}

	/**
	 * @Title: 页面登录 json返回
	 * @param @return
	 * @date 2013-4-26 下午01:57:26
	 * @return
	 */
	public String loginJson() {
		AccountLogin accountLogin = new AccountLogin();
		if (loginName == null || loginName.isEmpty()) {
			msgInfo.setFlag(MessageDialog.ERROR);
			msgInfo.addMessage("请输入登录名!");
			return JDIALOG;
		}
		if (loginPass == null || loginPass.isEmpty()) {
			msgInfo.setFlag(MessageDialog.ERROR);
			msgInfo.addMessage("请输入密码!");
			return JDIALOG;
		}
		if (checkCode == null || checkCode.isEmpty()) {
			msgInfo.setFlag(MessageDialog.ERROR);
			msgInfo.addMessage("请输入验证码!");
			return JDIALOG;
		}
		Object t = sessionHelper.get(WebsiteContext.SESSION_CHECKCODE);
		// 开取测试不验证验证码
		if ((null == t || !t.toString().equals(checkCode))) {
			msgInfo.setFlag(MessageDialog.ERROR);
			msgInfo.addMessage("输入的验证码有误!");
			return JDIALOG;
		} else {
			accountLogin.setLoginName(loginName);
			accountLogin.setLoginPass(TxtUtil.digest(loginPass));
			accountLogin.setLastIp(this.getClientIp());
			// 执行登录
			accountLogin = wAccountService.doOperatorLogin(accountLogin);

			if (accountLogin != null) {
				// 保存登录信息
				this.putLoginId(accountLogin.getId());
				msgInfo.setFlag(MessageDialog.SUCCESS);
				msgInfo.addMessage("登录成功!");
				return JDIALOG;
			} else {
				msgInfo.setFlag(MessageDialog.ERROR);
				msgInfo.addMessage("输入的用户名密码有误!");
				return JDIALOG;
			}
		}

	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	/**
	 * @Title:注销用户
	 * @param @return
	 * @date 2013-4-26 下午01:57:39
	 * @return
	 */
	public String logout() {
		this.putLoginId(null);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("注销成功");
		return JDIALOG;
	}

	/**
	 * @Title:去找回密码页面
	 * @param @return
	 * @date 2013-4-26 下午01:57:49
	 * @return
	 */
	public String toFindPass() {
		return SUCCESS;
	}

	/**
	 * 手机找回密码页面
	 * 
	 * @return
	 */
	public String toFindByMobile() {
		return SUCCESS;
	}

	/**
	 * 输入手机号，找回相关相关的信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void findpassCheckCode() {
		String message = "";
		String flag = ERROR;
		if (loginName == null || loginName == "") {
			message = "请输入手机号！";
			msgInfo.setFlag(flag);
			msgInfo.addMessage(message);
			this.write(msgInfo);
			return;
		}
		if (!TxtUtil.isMobile(loginName)) {
			message = "请输入正确的手机号码！";
			msgInfo.setFlag(flag);
			msgInfo.addMessage(message);
			this.write(msgInfo);
			return;
		}
		accountLogin = wAccountService.findByLoginName(loginName);
		if (null == accountLogin) {
			message = "请输入正确的手机号码！";
			msgInfo.setFlag(flag);
			msgInfo.addMessage(message);
			this.write(msgInfo);
			return;
		}
		// 初始化map
		if (sessionHelper.get(WebsiteContext.MOBILE_CHECK_CODE_MAP) == null) {
			Map<String, String> param = new HashMap<String, String>();
			sessionHelper.set(WebsiteContext.MOBILE_CHECK_CODE_MAP, param);
		}

		// 第一次直接发送验证码
		Map<String, String> mobileCheckCodeMap = (Map<String, String>) sessionHelper
				.get(WebsiteContext.MOBILE_CHECK_CODE_MAP);
		String mobile = mobileCheckCodeMap.get("mobile");
		String checkCodeTime = mobileCheckCodeMap.get("checkCodeTime");
		if (mobile == null || checkCodeTime == null) {
			String randNum = pucCheckCode();
			String msg = "尊敬的用户：" + loginName + "您好，您本次验证码为" + randNum
					+ "(验证码1分钟有效)";
			String status = msgService.sendMsg(loginName, msg);
			if (null != status && status.equals("0")) {
				sendSaveCode(loginName, randNum);
				message = "验证码已经发送到手机[" + loginName + "]，请查收！";
				flag = SUCCESS;
				msgInfo.setFlag(flag);
				msgInfo.addMessage(message);
				this.write(msgInfo);
				return;
			} else {
				message = "发送失败，请稍后重试";
				msgInfo.setFlag(flag);
				msgInfo.addMessage(message);
				this.write(msgInfo);
				return;
			}

		}

		// 获取两次发送验证码的时间之差
		Date prev = DateUtil.toDate(checkCodeTime);
		Date now = new Date();
		long diff = now.getTime() - prev.getTime();
		long second = diff / (1000);

		if (second >= 60) {
			String randNum = pucCheckCode();
			String msg = "尊敬的用户：" + mobile + "您好，您本次验证码为" + randNum
					+ "(验证码1分钟有效)";
			String status = msgService.sendMsg(mobile, msg);
			if (null != status && status.equals("0")) {
				sendSaveCode(loginName, randNum);
				System.out.println("发送成功");
			} else {
				message = "发送失败，请稍后重试";
				msgInfo.setFlag(flag);
				msgInfo.addMessage(message);
				this.write(msgInfo);
				return;
			}
			message = "验证码已经发送到手机" + loginName + "，请查收！";
			flag = SUCCESS;
			msgInfo.setFlag(flag);
			msgInfo.addMessage(message);
			this.write(msgInfo);
			return;
		} else {
			long leftSecond = 60 - second;
			message = "两次发送短信的间隔不得小于一分钟，请" + leftSecond + "秒后重试";
			msgInfo.setFlag(flag);
			msgInfo.addMessage(message);
			this.write(msgInfo);
			return;
		}
	}

	/**
	 * 去找回密码
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String mobileResetPass() {
		if (TxtUtil.isEmpty(loginName))
			throw new RuntimeException("请先填写手机号，并且点击发送验证码！");
		if (!TxtUtil.isMobile(loginName))
			throw new RuntimeException("手机号码格式有误，请重新输入！");
		// 第一次直接发送验证码
		Map<String, String> mobileCheckCodeMap = (Map<String, String>) sessionHelper
				.get(WebsiteContext.MOBILE_CHECK_CODE_MAP);
		String mobile = mobileCheckCodeMap.get("mobile");
		String checkCodeTime = mobileCheckCodeMap.get("checkCodeTime");
		String checkCode = mobileCheckCodeMap.get("checkCode");
		if (!loginName.equals(mobile))
			throw new RuntimeException("请输入接收短信的手机号码");
		if (mobile == null || checkCodeTime == null || checkCode == null) {
			throw new RuntimeException("请先填写手机号，并且点击发送验证码！");
		}
		if (!checkCode.equals(loginPass)) {
			throw new RuntimeException("输入的手机验证码错误！");
		}
		if (TxtUtil.isEmpty(loginPass1))
			throw new RuntimeException("确认密码不能为空");
		if (TxtUtil.isEmpty(accountLogin.getLoginPass()))
			throw new RuntimeException("登录密码为空");
		if (!accountLogin.getLoginPass().equals(loginPass1))
			throw new RuntimeException("两次密码不相等");
		accountLogin = wAccountService.findByLoginName(loginName);
		if (accountLogin == null)
			throw new RuntimeException("账户出现异常，请刷新!");
		accountLogin.setLoginPass(TxtUtil.digest(loginPass1));
		wAccountService.update(accountLogin);
		msgInfo.setFlag(MessageDialog.SUCCESS);
		msgInfo.addMessage("操作成功!");
		return JDIALOG;
	}

	public String getLoginPass() {
		return loginPass;
	}

	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public AccountLogin getAccountLogin() {
		return accountLogin;
	}

	public void setAccountLogin(AccountLogin accountLogin) {
		this.accountLogin = accountLogin;
	}

	public Long getLgId() {
		return lgId;
	}

	public void setLgId(Long lgId) {
		this.lgId = lgId;
	}

	public Integer getOpType() {
		return opType;
	}

	public void setOpType(Integer opType) {
		this.opType = opType;
	}

	public Integer getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(Integer checkFlag) {
		this.checkFlag = checkFlag;
	}

	public AgentInfo getAgentInfo() {
		return agentInfo;
	}

	public void setAgentInfo(AgentInfo agentInfo) {
		this.agentInfo = agentInfo;
	}

	public String getLoginPass1() {
		return loginPass1;
	}

	public void setLoginPass1(String loginPass1) {
		this.loginPass1 = loginPass1;
	}

}
