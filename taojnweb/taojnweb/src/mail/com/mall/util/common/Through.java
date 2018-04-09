package com.mall.util.common;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

public class Through {
	private static String URL_MY = "http://my.ziuu.com/cyotoo.php";
	private static String URL_BBS = "http://bbs.ziuu.com/cyotoo.php";
	private String url;
	private OPERATION op;
	private String data;

	public enum OPERATION {
		add, edit, delete, image
	};

	public enum FIELD {
		login_id, loginname, password, password2, last_ip, last_time, account_id, nickname, desc, type, email, image, info_id, name, sex, birthday, inhabit, hometown, addr, post, jobcorp, calling, job, income, education
	};

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public OPERATION getOp() {
		return op;
	}

	public void setOp(OPERATION op) {
		this.op = op;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public static boolean isNullOrEmpty(String aString) {
		if (aString != null) {
			return (aString.length() == 0);
		}
		return true;
	}

	public static String decodeString(String aStr) {
		String result = "";

		if (!isNullOrEmpty(aStr)) {
			try {
				result = aStr.replace('@', '%');
				result = URLDecoder.decode(result, "UTF8");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static String encodeString(String aStr) {
		String result = "";

		if (!isNullOrEmpty(aStr)) {
			try {
				result = URLEncoder.encode(aStr, "UTF8");
				result = result.replace('%', '@');
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public String getEmail(String aId) {
		String result = "";

		if (!isNullOrEmpty(aId)) {
			if (aId.indexOf("@") > 0)
				result = aId;
			else {
				Date now = new Date();
				result = now.getTime() + "@ziuu.com";
			}
		}
		return result;
	}

	public void addParameter(FIELD aKey, String aValue) {
		try {
			if (aKey != null && !isNullOrEmpty(aValue)) {
				if (isNullOrEmpty(this.data))
					this.data = "";
				else
					this.data += "&";
				this.data += aKey.name() + "=" + encodeString(aValue);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public int sendData() {
		int result = 0;
		try {
			long lngStart = System.currentTimeMillis();

			if (isNullOrEmpty(this.url))
				this.url = URL_MY;
			String strUrl = this.url + "?op=" + this.op + "&" + this.data;
			URL urlWeb = new URL(strUrl);
			HttpURLConnection conn = (HttpURLConnection) urlWeb
					.openConnection();
			conn.setConnectTimeout(2000);
			conn.connect();
			result = conn.getResponseCode();
			// HTTP/1.0 200 OK
			// HTTP/1.0 401 Unauthorized

			long lngEnd = System.currentTimeMillis();

			System.out.println("sendData code=" + result + " url=" + strUrl
					+ " cost=" + String.valueOf(lngEnd - lngStart));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int sendUUData() {
		int result = 0;
		try {
			// long lngStart = System.currentTimeMillis();

			if (isNullOrEmpty(this.url))
				this.url = URL_BBS;
			String strUrl = this.url + "?op=" + this.op + "&" + this.data;
			URL urlWeb = new URL(strUrl);
			HttpURLConnection conn = (HttpURLConnection) urlWeb
					.openConnection();
			conn.setConnectTimeout(2000);
			conn.connect();
			result = conn.getResponseCode();
			// HTTP/1.0 200 OK
			// HTTP/1.0 401 Unauthorized

			// long lngEnd = System.currentTimeMillis();

			// System.out.println("sendData code=" + result + " url=" + strUrl +
			// " cost=" + String.valueOf(lngEnd - lngStart));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
