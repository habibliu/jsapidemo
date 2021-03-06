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

public class BookParkSpace extends APIService {

	/**
	 * "serviceId":"3c.park.bookparkspace",
		"requestType":"DATA",
		"attributes":{
			"parkCode": "parkCode2074",
			"carNo": "粤-B12345"
		}

	 */
	protected String buildRequestParam() {
		Properties prop = ConfigHelper.getProperties(baseDir+"park/space/reserve/bookparkspace");
		JsonObject jsonParam = new JsonObject();
		jsonParam.addProperty("serviceId", prop.getProperty("serviceId"));
		jsonParam.addProperty("requestType", prop.getProperty("requestType"));
		JsonObject attributes = new JsonObject();
		attributes.addProperty("parkCode", prop.getProperty("parkCode"));
		attributes.addProperty("carNo", prop.getProperty("carNo"));
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
				System.out.println("bookparkspace-SUCCESS:车位预定成功!\n\t返回信息如下："+dataItems.toString());
			} else {
				System.out.println("bookparkspace-ERROR:车位预定失败!\n\t未知错误！");
			}
		} else {
			System.out.println("bookparkspace-ERROR:执行失败！" + "\tstatusCode:" + statusCode);
		}
	}

}
