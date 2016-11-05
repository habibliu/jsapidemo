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

public class QueryCurrentParkTraffic extends APIService {

	/**
	 * "serviceId":"3c.park.querycurrentparktraffic",
		"requestType":"DATA",
		"attributes":{
			"parkCode": "parkCode2074",
		}

	 */
	protected String buildRequestParam() {
		Properties prop = ConfigHelper.getProperties(baseDir+"querycurrentparktraffic");
		JsonObject jsonParam = new JsonObject();
		jsonParam.addProperty("serviceId", prop.getProperty("serviceId"));
		jsonParam.addProperty("requestType", prop.getProperty("requestType"));
		JsonObject attributes = new JsonObject();
		attributes.addProperty("parkCode", prop.getProperty("parkCode"));
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
				System.out.println("querycurrentparktraffic-SUCCESS:获取停车场流量成功!\n\t返回信息如下："+dataItems.toString());
			} else if (resultCode == 2010) {
				System.out.println("querycurrentparktraffic-SUCCESS:获取列表失败!\n\t停车场不存在！");
			} else {
				System.out.println("querycurrentparktraffic-SUCCESS:获取列表失败!\n\t未知错误！");
			}
		} else {
			System.out.println("querycurrentparktraffic-ERROR:执行失败！" + "\tstatusCode:" + statusCode);
		}
	}

}
