package com.mall.butler;

public class WebsiteContext {
	// 日期格式
	public final static String DATE_FORMAT = "yyyy-MM-dd";
	// 时间格式
	public final static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	// 验证码session值
	public final static String SESSION_CHECKCODE = "website_session_checkcode";
	// 登录操作员session值
	public final static String SESSION_LOGINID = "website_session_loginid";
	// url 栈session值
	public final static String SESSION_URLSTACKID = "website_session_urlstackid";
	// 用户登录前的URL
	public final static String BEFORE_LOGIN_URL = "website_before_login_url";
	// 系统账户ID
	public final static Long ADMIN_ACCOUNT_ID = 1L;
	// 系统管理员ID
	public final static Long ADMIN_LOGIN_ID = 1L;
	// 系统管理员名称
	public final static String ADMIN_LOGIN_NAME = "系统初始管理员";
	// 保存存文件路径
	public static String UPLOAD_PATH = "d:/upload";
	// 保存文件虚拟路径
	public static String UPLOAD_VPATH = "/upload";
	// 动态系统参数
	// 列表分页每页大小
	public static String LIST_PAGE_CODE = "0001"; // 编号
	public static Integer LIST_PAGE_NUM = 20; // 值
	// 线路出游天数
	public static String TOUR_DAYS_CODE = "0002"; // 编号
	public static Integer TOUR_DAYS_NUM = 10; // 值

	// 消息类型
	public static Integer SMS_TYPE = 0; // 短信
	public static Integer MMS_TYPE = 1; // 彩信
	public static Integer EMAIL_TYPE = 2; // 邮件
	//注册时记录验证码、验证吗发送时间、手机号
	public final static String MOBILE_CHECK_CODE_MAP = "mobile_check_code_map";
	// 广告位 编号

	/*** 栏目下的横幅广告 */
	public static String AD_TOP = "001001";
	/** * 左边的全国服务热线 */
	public static String AD_PHONE = "001002";
	/** * 左边的图片 */
	public static String AD_MAP = "001003";
	/** * 右边的积分商品 */
	public static String AD_PRODUCT = "001004";

	// 业务代码 系统验证码
	/** * 001 手机激活 */
	public static String BNS_CODE1 = "001";
	/** * 002 邮件激活 */
	public static String BNS_CODE2 = "002";
	/** *003 手机找回密码 */
	public static String BNS_CODE3 = "003";
	/** * 004邮件找回密码 */
	public static String BNS_CODE4 = "004";

	// 帮助中心
	/** 帮助中心 **/
	public static String HELP_CENTER = "002001";
	/** 常见问题 **/
	public static String HELP_COMMONP_ROBLEM = "002002";
	/** 法律申明 **/
	public static String HELP_LEGAL_NOTICES = "002003";
	/** 条款服务 **/
	public static String HELP_SERVICE = "002004";

}
