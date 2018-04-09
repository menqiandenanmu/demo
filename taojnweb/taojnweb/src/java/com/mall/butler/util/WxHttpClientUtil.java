package com.mall.butler.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

/**
 * @author Archer
 * @date 2014-11-27
 * 
 */
@SuppressWarnings({ "unchecked", "deprecation" })
public class WxHttpClientUtil {
	
	public static String postNameValue(String uri, Map<String, String> headers, Map<String, String[]> nameValue, String charset) {
		BasicHttpParams httpParams = new BasicHttpParams();
		httpParams.setParameter("http.protocol.content-charset", charset);
		httpParams.setParameter("http.connection.timeout", Integer.valueOf(20000));
		httpParams.setParameter("http.socket.timeout", new Integer(20000));
		DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);
		HttpPost httpPost = new HttpPost(uri);
		List parameters = new ArrayList();
		if (nameValue != null) {
			for (Map.Entry entry : nameValue.entrySet()) {
				for (String value : (String[]) entry.getValue())
					parameters.add(new BasicNameValuePair((String) entry.getKey(), value));
			}
		}
		try {
			HttpEntity httpEntity = new UrlEncodedFormEntity(parameters, charset);
			httpPost.setEntity(httpEntity);
			if (headers != null) {
				for (Map.Entry entry : headers.entrySet()) {
					httpPost.addHeader((String) entry.getKey(), (String) entry.getValue());
				}
			}
			HttpResponse responseBody = null;
			try {
				responseBody = httpClient.execute(httpPost);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return EntityUtils.toString(responseBody.getEntity(), charset);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return "exception";
	}

	public static String postXmlRequest(String requestUrl, String xmlData, String contentType, String charset) throws IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(requestUrl);
		StringEntity myEntity = new StringEntity(xmlData, charset);
		httppost.addHeader("Content-Type", "text/xml");
		httppost.setEntity(myEntity);
		HttpResponse response = null;
		try {
			try {
				response = httpclient.execute(httppost);
				HttpEntity resEntity = response.getEntity();
				InputStreamReader reader = new InputStreamReader(resEntity.getContent(), "UTF-8");
				char[] buff = new char[1024];
				int length = 0;
				while ((length = reader.read(buff)) != -1) {
					System.out.println(new String(buff, 0, length));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return EntityUtils.toString(response.getEntity(), charset);

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			httpclient.getConnectionManager().shutdown();
		}
		return "exception";
	}

	public static String doPost(String urlStr, Map<String, String> headers, String content, String charset) throws Exception {
		BasicHttpParams httpParams = new BasicHttpParams();
		httpParams.setParameter(HttpProtocolParams.HTTP_CONTENT_CHARSET, charset);
		httpParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
		httpParams.setParameter(CoreConnectionPNames.SO_TIMEOUT, new Integer(3000));
		HttpClient httpClient = new DefaultHttpClient(httpParams);

		URL url = new URL(urlStr);
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
			throw new RuntimeException(e);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}

	public static String clientSSLRes(String reqUrl, Map<String, String> headers, String content, String Partner, String Certificate) throws Exception {
		// 指定读取证书格式为PKCS12
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		// 读取本机存放的PKCS12证书文件
		FileInputStream instream = new FileInputStream(new File(Certificate));
		try {
			// 指定PKCS12的密码(商户ID)
			keyStore.load(instream, Partner.toCharArray());
		} finally {
			instream.close();
		}
		SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, Partner.toCharArray()).build();
		// 指定TLS版本
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		try {

			HttpPost httpPost = new HttpPost(reqUrl);
			HttpEntity httpEntity = new StringEntity(content, "UTF-8");
			httpPost.setEntity(httpEntity);
			if (headers != null) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					httpPost.addHeader(entry.getKey(), entry.getValue());
				}
			}
			// 设置httpclient的SSLSocketFactory
			CloseableHttpResponse response = httpClient.execute(httpPost);
			try {
				HttpEntity entity = response.getEntity();
				return EntityUtils.toString(entity, "UTF-8");
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				response.close();
			}
		} finally {
			httpClient.close();
		}
	}

}
