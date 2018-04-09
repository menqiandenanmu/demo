package com.mall.butler;

public class ManageContext {
	// 日期格式
	public final static String DATE_FORMAT = "yyyy-MM-dd";
	// 时间格式
	public final static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	// 验证码session值
	public final static String SESSION_CHECKCODE = "manage_session_checkcode";
	// 登录操作员session值
	public final static String SESSION_LOGINID = "manage_session_loginid";
	// url 栈session值
	public final static String SESSION_URLSTACKID = "manage_session_urlstackid";
	// wap用户OPENID
	public final static String WXSESSION_OPEN_ID = "";
	//wap用户点击购买某个商品时保存的链接，以便授权之后返回该页面
	public final static String WXSESSION_PAY_URL = "wxsite_session_payUrl";
	// 系统账户ID
	public final static Long ADMIN_ACCOUNT_ID = 1L;
	public static Integer POINTS_VALUE = 0; // 积分
	// 系统管理员ID
	public final static Long ADMIN_LOGIN_ID = 1L;
	// 会员默认等级
	public final static Long COUNSUMER_LEVEL = 1L;
	// 保存存文件路径
	public static String UPLOAD_PATH = "/usr/apps/www.taojn.com/upload";
	// 保存文件虚拟路径
	public static String UPLOAD_VPATH = "/upload";
	// 动态系统参数
	// 列表分页每页大小
	public static String LIST_PAGE_CODE = "0001"; // 编号
	public static Integer LIST_PAGE_NUM = 20; // 值

	// 系统日志
	public static String LOG_LEVEL_CODE = "0002"; // 编号
	public static String LOG_LEVEL_VALUE = "generic"; // 值

	// 系统默认验证码过期时间
	public static String CHECK_CODE = "0004"; // 过期时间 编号
	public static Integer CHECK_CODE_VALUE = 30; // 时间 以分钟为主
	// 静态信息数据字典编号
	public static String DICT_STATIC_CLASS = "002";
	// 颜色分类数据字典编号
	public static String DICT_COLOR_CLASS = "004";
	// 商品款式数据字典编号
	public static String DICT_STYLE_CLASS = "003";
	// 商品尺寸数据字典编号
	public static String DICT_SIZE_CLASS = "005";
	public static Long ACCOUNT_MAIN_ID = 1L;//主账户ID
	public static Integer LOG_SYS_TYPE=0;//系统日志
	public static Integer LOG_ACC_TYPE=1;//用户日志
	public static Integer LOG_OPT_TYPE=0;//操作类型默认0
	public static Integer LOG_OPT_TYPE_ACC=1;//
	public static Integer LOG_OPT_TYPE_POINT=2;//积分
	public static Integer LOG_OPT_TYPE_LOGIN=1;//操作类型默认0
	public static Long CRM_KEYN=1L;//CRM获取到的keyn值
	public static Long CRM_KEYE=2L;//CRM获取到的keye值
	public static Long CRM_WORKGUID=3L;//CRM获取到的workGuid值
	public static Long CRM_WORKKEYID=4L;//签到生成的随机数
	
	
	public static String INDEX_CLASS = "100001";
	public static Long ACC_REOURCE_CODE=2220L;//默认用户来源
	public static String ACCOUNT_TJN = "淘江南商城";
	public static String ACCOUNT_CRM = "CRM系统";
	public static String POINT_RULE_CRM = "002001";
	public static String POINT_RULE_SYS = "002000";
	public static String POINT_RULE_TJN = "002002";
	public static Long ACCOUNT_CRM_ID = 1l;
	public static Long ACCOUNT_TJN_ID = 4l;
}
