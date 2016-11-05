package com.jieshun.api.test.service;

import java.util.Properties;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jieshun.api.test.APIService;
import com.jieshun.api.test.ConfigHelper;

/**
 * 3.1.2.2	门禁开门协议
 * 用户用移动端打开小区的门禁设备
 * 参数params:JSON格式字符串
 * {
 *  "serviceId":"3c.door.opendoor",
 *  "requestType":"ACTION",
 *  "attributes":{
 *		"cardId": "",
 *      "doorId": ""
 *	}
 * }

 */
public class OpenDoor extends APIService{

	@Override
	protected String buildRequestParam() {
		// TODO Auto-generated method stub
		Properties prop = ConfigHelper.getProperties(baseDir+"opendoor");

		// 构造请求参数对象
		JsonObject jsonParam = new JsonObject();
		jsonParam.addProperty("serviceId", prop.getProperty("serviceId"));
		jsonParam.addProperty("requestType", prop.getProperty("requestType"));
		JsonObject attributes = new JsonObject();
		attributes.addProperty("cardId", prop.getProperty("cardId"));
		attributes.addProperty("doorId", prop.getProperty("doorId"));
		jsonParam.add("attributes", attributes);
		return jsonParam.toString();
	}

	@Override
	protected void extractResult(CloseableHttpResponse response) throws Exception {
		// TODO Auto-generated method stub
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == HttpStatus.SC_OK) {//成功调用
			String results = EntityUtils.toString(response.getEntity());
			JsonObject json=new JsonParser().parse(results).getAsJsonObject();
			int resultCode=json.get("resultCode").getAsInt();
			if(resultCode==0){
				
				System.out.println("opendoor-SUCCESS:开门成功!：");
					
			}else{
				System.out.println("opendoor-ERROR:调用异常!"+"\tresultCode:"+resultCode+"\tmessage:"+json.get("message"));
			}
		} else {
			System.out.println("opendoor-ERROR:执行失败！"+"\tstatusCode:"+statusCode);
		}
	}

}
