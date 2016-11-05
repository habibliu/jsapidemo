package com.jieshun.api.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

/**
 * 捷顺业务接口演示代码
 * 
 * @author 刘淦潮
 * 
 */
public abstract class APIService {

	private static Properties prop = ConfigHelper.getProperties("config");

	public static String baseDir = ConfigHelper.getProperties("config").getProperty("config");

	private static String token = "";

	/**
	 * 构造HTTP请求实体
	 * 
	 * @param param
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	protected HttpEntity constructHttpEntity(String param)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Properties pp = ConfigHelper.getProperties(baseDir + "public");
		if (token.isEmpty()) {
			token = Login.getToken(baseDir);
		}

		String signKey = (pp.getProperty("signKey") == null) ? "" : pp
				.getProperty("signKey");
		System.out.println("当前signKey---->" + signKey);

		// 生成MD5签名
		MessageDigest md5Tool = MessageDigest.getInstance("MD5");
		byte[] md5Data = md5Tool.digest((param + signKey).getBytes("UTF-8"));
		String sn = Util.toHexString(md5Data);
		Properties prop = ConfigHelper.getProperties(baseDir + "public");
		ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("cid", prop.getProperty("cid")));
		list.add(new BasicNameValuePair("v", prop.getProperty("v")));
		list.add(new BasicNameValuePair("p", param));
		list.add(new BasicNameValuePair("sn", sn));// MD5特征码
		list.add(new BasicNameValuePair("tn", token));// 取token

		HttpEntity en = new UrlEncodedFormEntity(list, "UTF-8");
//		try {
//			System.out.println("HttpEntity========="+EntityUtils.toString(en));
//		} catch (ParseException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println("调用参数:" + param);
		return en;
	}

	/**
	 * API执行方法，此方法是一个模板方法，子类无需实现
	 */
	final public void execute() {
		String url = prop.getProperty("funcurl");

		// TODO Auto-generated method stub
		BasicCookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		CloseableHttpResponse response = null;

		try {
			// 发起请求
			long startTime = System.currentTimeMillis();
			HttpUriRequest requst = RequestBuilder.post().setUri(new URI(url))
					.setEntity(constructHttpEntity(buildRequestParam()))
					.build();
//			System.out.println("======"+constructHttpEntity(buildRequestParam()));
			response = httpclient.execute(requst);
			
			long endTime = System.currentTimeMillis();
			System.out.println("本次API调用耗时----->" + (endTime - startTime) / 1000
					+ "." + (endTime - startTime) % 1000 + "秒");
			extractResult(response);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (response != null) {
					response.close();
				}
				httpclient.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	/**
	 * 构造服务请求的参数
	 * 
	 * @return
	 */
	protected abstract String buildRequestParam();

	/**
	 * 解释请求结果
	 * 
	 * @param response
	 * @throws Exception
	 */
	protected abstract void extractResult(CloseableHttpResponse response)
			throws Exception;
}
