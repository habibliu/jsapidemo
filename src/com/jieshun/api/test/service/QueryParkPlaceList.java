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

public class QueryParkPlaceList extends APIService {

	/**
	 *  "serviceId":"3c.park.queryparkplacelist",
		"requestType":"DATA",
		"attributes":{
			"parkCode": "g3v3_1",
			"placeType": "SALE",
			"areaName": "A区",
			"floor": "2",
			"status": 1
		}
	 * @return
	 */
	protected String buildRequestParam() {
		Properties prop = ConfigHelper.getProperties(baseDir+"queryparkplacelist");
		JsonObject jsonParam = new JsonObject();
		jsonParam.addProperty("serviceId", prop.getProperty("serviceId"));
		jsonParam.addProperty("requestType", prop.getProperty("requestType"));
		JsonObject attributes = new JsonObject();
		attributes.addProperty("parkCode", prop.getProperty("parkCode"));
		attributes.addProperty("placeType", prop.getProperty("placeType"));
		attributes.addProperty("areaName", prop.getProperty("areaName"));
		attributes.addProperty("floor", prop.getProperty("floor"));
		attributes.addProperty("status", prop.getProperty("status"));
		jsonParam.add("attributes", attributes);
		return jsonParam.toString();
	}

	protected void extractResult(CloseableHttpResponse response)
			throws Exception {
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == HttpStatus.SC_OK) {
			String results = EntityUtils.toString(response.getEntity());
			JsonObject json = new JsonParser().parse(results).getAsJsonObject();
			int resultCode = json.get("resultCode").getAsInt();
			if (resultCode == 0) {
				JsonElement dataItems = json.get("dataItems");
				System.out.println("queryparkplacelist-SUCCESS:获取列表成功!\n\t返回信息如下："+dataItems.toString());
			} else if (resultCode == 2) {
				System.out.println("queryparkplacelist-SUCCESS:获取列表失败!\n\t参数不正确！");
			} else if (resultCode == 2010) {
				System.out.println("queryparkplacelist-SUCCESS:获取列表失败!\n\t停车场不存在！");
			} else {
				System.out.println("queryparkplacelist-SUCCESS:获取列表失败!\n\t未知错误！");
			}
		} else {
			System.out.println("queryparkplacelist-ERROR:执行失败！" + "\tstatusCode:" + statusCode);
		}
	}

}
