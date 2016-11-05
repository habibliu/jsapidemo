package com.jieshun.api.test.service;

import java.util.Properties;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jieshun.api.test.APIService;
import com.jieshun.api.test.ConfigHelper;

/**
* @ClassName: QueryParkspaceBookList
* @Description: TODO(车位预定延期)
* @author huozhuangning
* @date 2016年4月28日 下午2:47:56
* 
*/

public class Invite extends APIService {

	protected String buildRequestParam() {
		Properties prop = ConfigHelper.getProperties(baseDir+"visitor/invite");
		JsonObject jsonParam = new JsonObject();
		jsonParam.addProperty("serviceId", prop.getProperty("serviceId"));
		jsonParam.addProperty("requestType", prop.getProperty("requestType"));
		JsonObject attributes = new JsonObject();
		attributes.addProperty("personCode", prop.getProperty("personCode"));
		attributes.addProperty("areaCode", prop.getProperty("areaCode"));
		attributes.addProperty("visitorType", prop.getProperty("visitorType"));
		attributes.addProperty("businesserCode", prop.getProperty("businesserCode"));
		attributes.addProperty("parkCode", prop.getProperty("parkCode"));
		attributes.addProperty("visitorContent", prop.getProperty("visitorContent"));
		attributes.addProperty("timeDesc", prop.getProperty("timeDesc"));
		jsonParam.add("attributes", attributes);
		
		JsonArray dataItems =new JsonArray();
		
		JsonObject jsonObject=new JsonObject();
		
		JsonObject att = new JsonObject();
		att.addProperty("visitorName", prop.getProperty("visitorName"));
		att.addProperty("cardNo", prop.getProperty("cardNo"));
		att.addProperty("visitorTel", prop.getProperty("visitorTel"));
		att.addProperty("carNo", prop.getProperty("carNo"));
		
		jsonObject.add("attributes", att);
		jsonObject.addProperty("objectId", prop.getProperty("objectId"));
		jsonObject.addProperty("operateType", prop.getProperty("operateType"));
		jsonObject.add("subItems", new JsonArray());
		
		dataItems.add(jsonObject);
		
		jsonParam.add("dataItems", dataItems);
		
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
				JsonElement dataItems=json.get("dataItems");
				System.out.println("queryorder-SUCCESS:访客成功!\n\t访客成功信息："+dataItems.toString());
				
			}else{
				System.out.println("queryorder-ERROR:调用异常!"+"\tresultCode:"+resultCode+"\tmessage:"+json.get("message").getAsString());
			}
		} else {
			System.out.println("opendoor-ERROR:执行失败！"+"\tstatusCode:"+statusCode);
		}
	}

}
