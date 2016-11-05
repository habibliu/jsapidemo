package com.jieshun.api.test;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;






import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 用户登录类
 * @author 刘淦潮
 *
 */
public class Login {
	
	private static Properties prop=ConfigHelper.getProperties("config");

	private static String token=null;
	/**
	 * 用户登录
	 * @param cid 客户编号
	 * @param usr 用户帐户
	 * @param psw 用户密码
	 * @return
	 */
	public static String login(String cid, String usr, String psw) {
		String url = prop.getProperty("loginurl");
		
		System.out.println("cid:"+cid+"\tusr:"+usr+"\tpsw:"+psw);
		BasicCookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient httpclient = HttpClients.custom()
				.setDefaultCookieStore(cookieStore).build();
		CloseableHttpResponse response = null;
		try {

			ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("cid", cid));
			list.add(new BasicNameValuePair("usr", usr));
			list.add(new BasicNameValuePair("psw", psw));
			HttpEntity en = new UrlEncodedFormEntity(list, "UTF-8");

			HttpUriRequest login = RequestBuilder.post().setUri(new URI(url))
					.setEntity(en).build();
			response = httpclient.execute(login);
			int statusCode=response.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				
				String results = EntityUtils.toString(response.getEntity());
				JsonObject json=new JsonParser().parse(results).getAsJsonObject();
				int resultCode=json.get("resultCode").getAsInt();
				if(resultCode==0){
					System.out.println("登录成功！");
					return json.get("token").getAsString();
				}else{
					throw new Exception("登录失败!，错误信息：\tresultCode:"+resultCode+"\tmessage:"+json.get("message").getAsString());
				}
			} else {
				throw new Exception("调用失败，错误代码："+statusCode);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				httpclient.close();
			} catch (IOException ioe) {
				System.out.println(ioe);
			}
		}

		return null;
	}
	
	/**
	 * 从登录返回信息中提取token
	 * @param loginMsg
	 * @return
	 */
	public static String getToken(String baseDir){
		if(token!=null){
			return token;
		}
		//取公共配置信息
//		Properties prop=ConfigHelper.getProperties("public");
//		String config = prop.getProperty("config");
		Properties pp = ConfigHelper.getProperties(baseDir+"public");
		token=login(pp.getProperty("cid"),pp.getProperty("usr"),pp.getProperty("psw"));
		
		System.out.println("当前token---->"+token);

		//System.out.println("当前signKey---->"+(token.split(",").length==2?token.split(",")[1]:"").toString());

		
		return token;
	}
	
}
