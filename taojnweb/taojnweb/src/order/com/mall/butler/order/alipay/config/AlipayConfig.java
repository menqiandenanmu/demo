package com.mall.butler.order.alipay.config;


/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088611360370656";
	public static String key = "znereghl57y15b0z9a6x10vvkdk827d4"; 
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串
	public static String seller_id = "jnds@aliyun.com";
	

	//需http://格式的完整路径，不能加?id=123这类自定义参数
	public static String  notify_url = "/pay/pay/alipayNotify.htm";

	//页面跳转同步通知页面路径
	public static String  return_url = "/pay/pay/alipayReturn.htm";


	// 商户的私钥
	public static String private_key = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKzM56wLD2Mc1/UMxmymbeuaFZCyaSyx/g17f5Sia/DBuBU51MxCT8q3Kuz1D/PxCvTpNISMqDHI6ST1RxPOvbc0OkdaZ2tgXTYLYe27nvENk9KE/784AOZvHqRop2EPsz8HWnT99Zs/FAXlZzErwsw+o6wZ3X/RNHdGUKlYzyf7AgMBAAECgYBE65j/Yo3pjwiHYWgGz76JuLQfOTl/rSJSCE7TXpDEvs8IED/SlCQ1CydgyUFqn1oqYpUixOMUsDhlGDsYKF47+x7exDZVtQgahy/tgf8PrOssCkGwrKcXKa7bdfmKE5VGypt5LBQkV7XU2JANqzEQXcdNBwxyHyzYvuOf8H774QJBANKyh3MiWK42p0cDTwQBIoCoQ0DafDG83rDkFy8cZUuW/TzdVTvez7sJYXdmgkeYh+NZpMOt1IjXw1d2I8Eu2JECQQDR9GcwvethSPy4GnLIdMiqIvQoXPq5K9nmii8AFSxkG69vAxr2rbuaMYSqILTkeKkvbRAeR1HFZlm8GTkwkR3LAkAIYnJhcox04lfly4vpbOIinI+QlSE8GXgDMhB6H3coeum48lXh/AvoNYdbaQjmMHu34GleIBzFFVEAugLSPTsBAkBrw0xrwi9cYcAFJt4fcYHFgedcCE1QWeef4vE406VTRLqI+kkOsCGIhT4YrGzsj9oW053gTK716RDJ8RBfkzx9AkAR2W5kGrGbFSQLCoKPEp7KII4BIPIwtFEZO1ivDOAInQedCxLtlcSDGZjeukRhjwxznwaACHkNRvEZAX39H6OI";
	
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "/usr/apps";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "MD5";

}