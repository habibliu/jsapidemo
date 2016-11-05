package com.jieshun.api.test.service;

import java.util.Properties;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jieshun.api.test.APIService;
import com.jieshun.api.test.ConfigHelper;

/**
 * @访客超时续时协议
 * @author yt
 * @date 2016-08-25
 */
public class InviteExtend extends APIService {

	@Override
	protected String buildRequestParam() {
		Properties prop = ConfigHelper.getProperties(baseDir+"/visitor/extend/inviteextend");
		JsonObject jsonParam = new JsonObject();
		JsonObject attributes = new JsonObject();
		//jsonParam
		jsonParam.addProperty("serviceId", prop.getProperty("serviceId"));
		jsonParam.addProperty("requestType", prop.getProperty("requestType"));
		//attributes
		attributes.addProperty("visitorId", prop.getProperty("visitorId"));
		attributes.addProperty("timeDesc", prop.getProperty("timeDesc"));
		attributes.addProperty("personCode", prop.getProperty("personCode"));
		attributes.addProperty("areaCode", prop.getProperty("areaCode"));
		attributes.addProperty("businesserCode", prop.getProperty("businesserCode"));
		attributes.addProperty("parkCode", prop.getProperty("parkCode"));
		attributes.addProperty("oldEndTime", prop.getProperty("oldEndTime"));
		attributes.addProperty("newEndTime", prop.getProperty("newEndTime"));
		jsonParam.add("attributes", attributes);
		return jsonParam.toString();
	}

	@Override
	protected void extractResult(CloseableHttpResponse response)
			throws Exception {
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == HttpStatus.SC_OK) {
			String results = EntityUtils.toString(response.getEntity());
			JsonObject json=new JsonParser().parse(results).getAsJsonObject();
			int resultCode=json.get("resultCode").getAsInt();
			if(resultCode==0){
				JsonElement dataItems=json.get("dataItems");
				System.out.println("InviteExtend-SUCCESS:访客成功!\n\t访客成功信息："+dataItems.toString());
				
			}else{
				System.out.println("InviteExtend-ERROR:调用异常!"+"\tresultCode:"+resultCode+"\tmessage:"+json.get("message").getAsString());
			}
		} else {
			System.out.println("InviteExtend-ERROR:执行失败！"+"\tstatusCode:"+statusCode);
		}
	}
	
	

}
