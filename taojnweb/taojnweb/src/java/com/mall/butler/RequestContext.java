package com.mall.butler;

import java.util.Hashtable;
import java.util.Map;

import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.account.model.AccountLogin;

/**
 * 线程运行环境
 * 
 * @author caedmon
 * @date 2010-11-22 上午10:21:18
 */
public class RequestContext {
	public static final String ACCOUNT_LOGIN = "accountLogin";
	public static final String ACCOUNT_INFO = "accountInfo";
	public static final String REMOT_IP = "remotIp";
	private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();
	static {
		threadLocal.set(new Hashtable<String, Object>());
	}

	/**
	 * 取对象
	 * 
	 * @author caedmon
	 * @date 2010-11-22 上午10:25:42
	 * @param key
	 * @return
	 */
	public static Object getObject(String key) {
		Map<String, Object> content = threadLocal.get();
		if (content == null)
			return null;
		return content.get(key);
	}

	/**
	 * 设置对象
	 * 
	 * @author caedmon
	 * @date 2010-11-22 上午10:26:05
	 * @param key
	 * @param obj
	 */
	public static void setObject(String key, Object obj) {
		Map<String, Object> content = threadLocal.get();
		if (content == null) {
			content = new Hashtable<String, Object>();
			threadLocal.set(content);
		}
		content.put(key, obj);
	}

	/**
	 * 当前线程操作员
	 * 
	 * @author caedmon
	 * @date 2010-11-22 上午10:26:24
	 * @return
	 */
	public static AccountLogin getLogin() {
		Object o = getObject(ACCOUNT_LOGIN);
		if (null != o) {
			return (AccountLogin) o;
		} else
			return null;
	}

	public static void setLogin(AccountLogin accountLogin) {
		setObject(ACCOUNT_LOGIN, accountLogin);
	}

	/**
	 * 当前线程用户
	 * 
	 * @author caedmon
	 * @date 2010-11-22 上午10:26:24
	 * @return
	 */
	public static AccountInfo getAccount() {
		Object o = getObject(ACCOUNT_INFO);
		if (null != o) {
			return (AccountInfo) o;
		} else
			return null;
	}

	public static void setAccount(AccountInfo accountInfo) {
		setObject(ACCOUNT_INFO, accountInfo);
	}

	/**
	 * 当前请求ip
	 * 
	 * @return
	 */
	public static String getRemotIp() {
		Object o = getObject(REMOT_IP);
		if (null != o) {
			return (String) o;
		} else
			return null;
	}

	public static void setRemotIp(String remotIp) {
		setObject(REMOT_IP, remotIp);
	}
}
