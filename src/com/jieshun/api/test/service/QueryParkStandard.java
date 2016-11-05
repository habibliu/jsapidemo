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
 * 3.24.2.1	停车场收费标准查询
 * @author yt
 * @date 2016-08-25
 *	
 */
public class QueryParkStandard extends APIService {

	@Override
	protected String buildRequestParam() {
		Properties prop = ConfigHelper.getProperties(baseDir + "/park/standard/queryparkstandard");
		JsonObject jsonParam = new JsonObject();
		JsonObject attributes = new JsonObject();
		// jsonParam
		jsonParam.addProperty("serviceId", prop.getProperty("serviceId"));
		jsonParam.addProperty("requestType", prop.getProperty("requestType"));
		// attributes
		attributes.addProperty("parkCode", prop.getProperty("parkCode"));
		jsonParam.add("attributes", attributes);
		return jsonParam.toString();
	}

	@Override
	protected void extractResult(CloseableHttpResponse response)
			throws Exception {
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == HttpStatus.SC_OK) {
			String results = EntityUtils.toString(response.getEntity());
			JsonObject json = new JsonParser().parse(results).getAsJsonObject();
			int resultCode = json.get("resultCode").getAsInt();
			JsonElement dataItems = json.get("dataItems");
			System.out.println(dataItems);
			if (resultCode == 0) {
				System.out.println("QueryParkStandard-SUCCESS:成功!\n信息：" + dataItems.toString());
			} else if (resultCode == 2) {
				System.out.println("QueryParkStandard:失败!\t参数不正确" + json.get("message").getAsString());
			} else {
				System.out.println("QueryParkStandard:失败!\t" + "resultCode:" + resultCode);
			}
		} else {
			System.out.println("QueryParkStandard-ERROR:执行失败！" + "\tstatusCode:" + statusCode);
		}
	}

}
