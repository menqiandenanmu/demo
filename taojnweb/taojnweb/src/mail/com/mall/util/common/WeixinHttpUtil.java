package com.mall.util.common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

public class WeixinHttpUtil {
	public static String doGet(String uriStr, Map headerMap, String proxyUrl, int proxyPort) throws Exception {
		BasicHttpParams httpParams = new BasicHttpParams();
		httpParams.setParameter(HttpProtocolParams.HTTP_CONTENT_CHARSET, "utf-8");
		httpParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
		httpParams.setParameter(CoreConnectionPNames.SO_TIMEOUT, new Integer(3000));
		HttpClient httpClient = new DefaultHttpClient(httpParams);

		URL url = new URL(uriStr);
		URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
		HttpGet httpGet = new HttpGet(uri);

		try {
			HttpResponse responseBody = httpClient.execute(httpGet);
			return EntityUtils.toString(responseBody.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("请求异常");
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}

	public static String doPost(String reqUrl, Map<String, String> headers, String charset) throws Exception {
		HttpURLConnection url_con = null;
		String responseContent = null;
		try {
			StringBuffer params = new StringBuffer();
			for (Iterator iter = headers.entrySet().iterator(); iter.hasNext(); params.append("&")) {
				java.util.Map.Entry element = (java.util.Map.Entry) iter.next();
				params.append(((String) element.getKey()).toString());
				params.append("=");
				params.append(URLEncoder.encode(((String) element.getValue()).toString(), charset));
			}

			if (params.length() > 0)
				params = params.deleteCharAt(params.length() - 1);
			URL url = new URL(reqUrl);
			url_con = (HttpURLConnection) url.openConnection();
			url_con.setRequestMethod("POST");
			url_con.setConnectTimeout(60 * 1000);
			url_con.setReadTimeout(60 * 1000);
			url_con.setDoOutput(true);
			byte b[] = params.toString().getBytes();
			url_con.getOutputStream().write(b, 0, b.length);
			url_con.getOutputStream().flush();
			url_con.getOutputStream().close();
			InputStream in = url_con.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in, charset));
			String tempLine = rd.readLine();
			StringBuffer tempStr = new StringBuffer();
			String crlf = System.getProperty("line.separator");
			for (; tempLine != null; tempLine = rd.readLine()) {
				tempStr.append(tempLine);
				tempStr.append(crlf);
			}

			responseContent = tempStr.toString();
			rd.close();
			in.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return responseContent;
	}

	public static String doPost1(String reqUrl, Map<String, String> headers, String content, String charset) throws Exception {

		BasicHttpParams httpParams = new BasicHttpParams();
		httpParams.setParameter(HttpProtocolParams.HTTP_CONTENT_CHARSET, charset);
		httpParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
		httpParams.setParameter(CoreConnectionPNames.SO_TIMEOUT, new Integer(3000));
		HttpClient httpClient = new DefaultHttpClient(httpParams);

		URL url = new URL(reqUrl);
		URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
		HttpPost httpPost = new HttpPost(uri);

		try {
			HttpEntity httpEntity = new StringEntity(content, charset);
			httpPost.setEntity(httpEntity);
			if (headers != null) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					httpPost.addHeader(entry.getKey(), entry.getValue());
				}
			}
			HttpResponse responseBody = httpClient.execute(httpPost);
			return EntityUtils.toString(responseBody.getEntity(), charset);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("请求异常");
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}

	/**
	 * 获取post中的内容(适用于无key的post内容)
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String getPostContent(HttpServletRequest request) throws IOException {
		String result = null;
		StringBuffer info = new java.lang.StringBuffer();
		InputStream in = request.getInputStream();
		BufferedInputStream buf = new BufferedInputStream(in);
		byte[] buffer = new byte[1024];
		int iRead;
		while ((iRead = buf.read(buffer)) != -1) {
			info.append(new String(buffer, 0, iRead, "UTF-8"));
		}
		if (!StringUtils.isBlank(info))
			result = info.toString();

		return result;
	}
}
