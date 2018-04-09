package com.mall.butler.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mall.butler.weixin.poji.AccessToken;
import com.mall.butler.weixin.poji.Menu;
import com.mall.butler.weixin.poji.QRImg;
import com.mall.butler.weixin.poji.QRcodeInfo;
import com.mall.butler.weixin.poji.RcodeLimitInfo;
import com.mall.butler.weixin.poji.UserAccess;
import com.mall.butler.weixin.poji.WxUserInfo;

/**
 * 公众平台通用接口工具类
 * 
 * @author wangxy 2014-11-26
 */
public class WeixinUtil {
	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);
	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	// 菜单创建（POST） 限100（次/天）
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	// 查询自定义菜单的结构
	public static String menu_get_url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

	// 删除当前自定义菜单的结构
	public static String menu_del_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

	// 跟据TOKEN和用户OPEN＿ID得到用户的基本信息
	public final static String access_openId_url = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	// 操作弹出授权信息，返回CODE用作换取TOKEN值
	public final static String access_code_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=1#wechat_redirect";

	// 跟据返回的CODE值 取得用户的Token值
	public final static String access_tokencode_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

	// 刷新TOKEN值
	public final static String refresh_accessToken_url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";

	// 检验授权凭证（access_token）是否有效
	public final static String check_accessToken_url = "https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID";

	// 跟据ACCESS_TOKEN和用户OPEN＿ID得到用户的基本信息
	public final static String access_token_openId_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	// 跟据ACCESS_TOKEN得到ticket参数及二维码
	public final static String access_qrcode_url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";

	// 跟据TICKET得到二维码图片
	public final static String access_qrcode_img_url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";

	// 上传多媒体文件
	public final static String uplad_file = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

	// 下载多媒体文件
	public final static String download_file = "https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";

	
	// 上传图文消息素材
	public final static String uploadnews = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";

	// 根据分组进行群发
	public final static String sendall = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";

	
	/**
	 * 创建菜单
	 * 
	 * @param menu
	 *            菜单实例
	 * @param accessToken
	 *            有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */
	public static int createMenu(Menu menu, String accessToken) {
		int result = 0;

		// 拼装创建菜单的url
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}

		return result;
	}

	public static int delMenu(String accessToken) {
		int result = 0;
		// 拼装创建菜单的url
		String url = menu_del_url.replace("ACCESS_TOKEN", accessToken);
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", null);

		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				log.error("删除菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}

		return result;
	}

	
	public static void testQueryMenu(String accessToken) throws Exception {
		String url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + accessToken;
		JSONObject jsonObject = httpRequest(url, "POST", null);
		System.out.println(jsonObject.get(""));
	}
	
	public static int getMenu(String accessToken) {
		int result = 0;

		// 拼装取得菜单的url
		String url = menu_get_url.replace("ACCESS_TOKEN", accessToken);
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", null);

		if (null != jsonObject) {

		}

		return result;
	}

	/**
	 * 获取openId
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getCodeUrl(String appid, String redirectUrl, String scope) {
		return access_code_url.replace("APPID", appid).replace("REDIRECT_URI", URLEncoder.encode(redirectUrl)).replace("SCOPE", scope);
	}
	/**
	 * 根据用户授权的返过来的CODE值 ，获妈公众TOKEN、OPenId
	 * 
	 * @param appid
	 * @param secret
	 * @param code
	 */
	public static UserAccess getUserAccessToken(String appid, String secret, String code) {
		UserAccess userAccess = null;

		String requestUrl = access_tokencode_url.replace("APPID", appid).replace("SECRET", secret).replace("CODE", code);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		System.out.println(requestUrl);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				userAccess = new UserAccess();
				userAccess.setAccessToken(jsonObject.getString("access_token"));
				userAccess.setExpiresIn(jsonObject.getInt("expires_in"));
				userAccess.setOpenid(jsonObject.getString("openid"));
				userAccess.setRefreshToken(jsonObject.getString("refresh_token"));
				userAccess.setScope(jsonObject.getString("scope"));
			} catch (JSONException e) {
				userAccess = null;
				// 获取token失败
				System.out.println("获取openId失败 errcode:{} errmsg:{}"+"编号"+jsonObject.getInt("errcode")+"消息内容"+ jsonObject.getString("errmsg"));
				log.error("获取openId失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return userAccess;
	}

	/**
	 * 刷新TOKEN值
	 * 
	 * @param appid
	 * @param accessToken
	 * @return
	 */
	public static UserAccess refreshUserAccessToken(String appid, String accessToken) {
		UserAccess userAccess = null;
		String requestUrl = refresh_accessToken_url.replace("APPID", appid).replace("REFRESH_TOKEN", accessToken);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				userAccess = new UserAccess();
				userAccess.setAccessToken(jsonObject.getString("access_token"));
				userAccess.setExpiresIn(jsonObject.getInt("expires_in"));
				userAccess.setOpenid(jsonObject.getString("openid"));
				userAccess.setRefreshToken(jsonObject.getString("refresh_token"));
				userAccess.setScope(jsonObject.getString("scope"));
			} catch (JSONException e) {
				userAccess = null;
				// 获取token失败
				log.error("刷新获取openId失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return userAccess;
	}

	/**
	 * 根据TOKEN 值 OPENID得到用户的基本信息
	 * 
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	public static WxUserInfo getUserInfo(String accessToken, String openId) {
		WxUserInfo userAccess = null;
		String requestUrl = access_openId_url.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				userAccess = new WxUserInfo();
				userAccess.setOpenId(jsonObject.getString("openid"));
				userAccess.setSex(jsonObject.getString("sex"));
				userAccess.setNickname(jsonObject.getString("nickname"));
				userAccess.setCity(jsonObject.getString("city"));
				userAccess.setProvince(jsonObject.getString("province"));
				userAccess.setPrivilege(jsonObject.getString("privilege"));
			} catch (JSONException e) {
				userAccess = null;
				// 获取token失败
				log.error("获取用户基本信息失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return userAccess;
	}

	/**
	 * 根据ACCESS_TOKEN 值 OPENID得到用户的基本信息
	 * 
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	public static WxUserInfo getUserInfoByAccessTokenAndOpenId(String accessToken, String openId) {
		WxUserInfo userAccess = null;
		String requestUrl = access_token_openId_url.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				userAccess = new WxUserInfo();
				userAccess.setOpenId(jsonObject.getString("openid"));
				userAccess.setSex(jsonObject.getString("sex"));
				userAccess.setNickname(jsonObject.getString("nickname"));
				userAccess.setCity(jsonObject.getString("city"));
				userAccess.setProvince(jsonObject.getString("province"));
				userAccess.setHeadimgurl(jsonObject.getString("headimgurl"));
				return userAccess;
			} catch (JSONException e) {
				userAccess = null;
				// 获取token失败
				log.error("获取用户基本信息失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return userAccess;
	}

	/**
	 * 获取access_token
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public static AccessToken getAccessToken(String appid, String appsecret) {
		AccessToken accessToken = null;

		String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}

	/**
	 * 创建带参数的二维码 临时
	 * 
	 * @author wangxy 2014-12-23
	 * @param accessToken
	 * @param actionInfo
	 * @return
	 */
	public static QRImg getQRCode(String accessToken, QRcodeInfo aRcodeInfo) {
		String requestUrl = access_qrcode_url.replace("TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(aRcodeInfo).toString();
		JSONObject jsonObject = httpRequest(requestUrl, "POST", jsonMenu);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				QRImg qrImg = new QRImg();
				qrImg.setTicket(getQRcodeImg(jsonObject.getString("ticket")));
				qrImg.setUrl(jsonObject.getString("url"));
				qrImg.setExpire_seconds(jsonObject.getString("expire_seconds"));
				return qrImg;
			} catch (JSONException e) {
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return null;
	}
	/**
	 * 创建带参数的二维码 永久
	 * 
	 * @author wangxy 2014-12-23
	 * @param accessToken
	 * @param actionInfo
	 * @return
	 */
	public static QRImg getQRCodeLmimit(String accessToken, RcodeLimitInfo aRcodeInfo) {
		String requestUrl = access_qrcode_url.replace("TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(aRcodeInfo).toString();
		JSONObject jsonObject = httpRequest(requestUrl, "POST", jsonMenu);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				QRImg qrImg = new QRImg();
				qrImg.setTicket(getQRcodeImg(jsonObject.getString("ticket")));
				qrImg.setUrl(jsonObject.getString("url"));
				return qrImg;
			} catch (JSONException e) {
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return null;
	}
	public static String getQRcodeImg(String ticket) {
		try {
			return access_qrcode_img_url.replace("TICKET", URLEncoder.encode(ticket, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 文件上传到微信服务器
	 * 
	 * @param fileType
	 *            文件类型
	 * @param filePath
	 *            文件路径
	 * @return JSONObject
	 * @throws Exception
	 * 
	 *             媒体文件在后台保存时间为3天，即3天后media_id失效
	 */
	public static String uploadFill(String accessToken, String fileType, String filePath) {
		JSONObject jsonObject = null;
		String requestUrl = uplad_file.replace("ACCESS_TOKEN", accessToken).replace("TYPE", fileType);
		try {
			String result = null;
			File file = new File(filePath);
			if (!file.exists() || !file.isFile()) {
				throw new IOException("文件不存在");
			}

			URL urlObj = new URL(requestUrl);
			HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
			con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false); // post方式不能使用缓存
			// 设置请求头信息
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", "UTF-8");
			// 设置边界
			String BOUNDARY = "----------" + System.currentTimeMillis();
			con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
			// 请求正文信息
			// 第一部分：
			StringBuilder sb = new StringBuilder();
			sb.append("--"); // 必须多两道线
			sb.append(BOUNDARY);
			sb.append("\r\n");
			sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
			sb.append("Content-Type:application/octet-stream\r\n\r\n");
			byte[] head = sb.toString().getBytes("utf-8");
			// 获得输出流
			OutputStream out = new DataOutputStream(con.getOutputStream());
			// 输出表头
			out.write(head);
			// 文件正文部分
			// 把文件已流文件的方式 推入到url中
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			in.close();
			// 结尾部分
			byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
			out.write(foot);
			out.flush();
			out.close();
			StringBuffer buffer = new StringBuffer();
			BufferedReader reader = null;
			try {
				// 定义BufferedReader输入流来读取URL的响应
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String line = null;
				while ((line = reader.readLine()) != null) {
					// System.out.println(line);
					buffer.append(line);
				}
				if (result == null) {
					result = buffer.toString();
				}
			} catch (IOException e) {
				System.out.println("发送POST请求出现异常！" + e);
				e.printStackTrace();
				throw new IOException("数据读取异常");
			} finally {
				if (reader != null) {
					reader.close();
				}
			}
			jsonObject = JSONObject.fromObject(buffer.toString());

		} catch (Exception e) {
			return null;
		}
		return jsonObject.getString("media_id");
	}

	/**
	 * 获取媒体文件
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param media_id
	 *            媒体文件id
	 * @param savePath
	 *            文件在服务器上的存储路径
	 * */
	public static String downloadMedia(String accessToken, String mediaId, String savePath) {
		String requestUrl = download_file.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);
		String filePath = null;
		// 拼接请求地址
		System.out.println(requestUrl);
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			// conn.setRequestProperty("Content-Type",
			// "application/x-www-form-urlencoded");
			if (!savePath.endsWith("/")) {
				savePath += "/";
			}
			// 根据内容类型获取扩展名
			String fileExt = conn.getHeaderField("Content-Type");
			// 获取响应状态
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				System.out.println("connect failed!");
				// return false;
			}
			StringBuffer buffer = new StringBuffer();
			BufferedReader reader = null;
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				// System.out.println(line);
				buffer.append(line);
			}
			if (fileExt.equals("application/json; charset=utf-8")) {
				JSONObject jsonObject = JSONObject.fromObject(buffer.toString());
				jsonObject.get("errcode");
				jsonObject.get("errmsg");
				return null;
			}
			// 将mediaId作为文件名
			filePath = savePath + mediaId + fileExt;

			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
			fos.close();
			bis.close();

			conn.disconnect();
			String info = String.format("下载媒体文件成功，filePath=" + filePath);
			System.out.println(info);
		} catch (Exception e) {
			filePath = null;
			String error = String.format("下载媒体文件失败：%s", e);
			System.out.println(error);
		}
		return filePath;
	}

	/**
	 * 检验授权凭证（access_token）是否有效
	 * 
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	public static boolean checkAccessToken(String accessToken, String openId) {
		String requestUrl = check_accessToken_url.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				Integer errorCode = jsonObject.getInt("errcode");
				if (errorCode == 0) {
					return true;
				} else {
					return false;
				}
			} catch (JSONException e) {
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}

		return false;
	}

	
	
	
	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("https request error:{}", e);
		}
		return jsonObject;
	}

	/**
	 * 认证微信Url方法
	 * 
	 * @author wangxy
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void registerWeiXinUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		PrintWriter out = response.getWriter();
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;
	}

}
