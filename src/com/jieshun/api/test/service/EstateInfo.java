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
 * 3.25.2.1	物业人事资料查询
 * @author yt
 * @date 2016-08-25
 *	
 */
public class EstateInfo extends APIService {

	@Override
	protected String buildRequestParam() {
		Properties prop = ConfigHelper.getProperties(baseDir + "/estate/info/infosearch");
		JsonObject jsonParam = new JsonObject();
		JsonObject attributes = new JsonObject();
		// jsonParam
		jsonParam.addProperty("serviceId", prop.getProperty("serviceId"));
		jsonParam.addProperty("requestType", prop.getProperty("requestType"));
		// attributes
		attributes.addProperty("areaCode", prop.getProperty("areaCode"));
		attributes.addProperty("paramType", prop.getProperty("paramType"));
		attributes.addProperty("paramValue", prop.getProperty("paramValue"));
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
				System.out.println("EstateInfo-SUCCESS:成功!\n信息：" + dataItems.toString());
			} else if (resultCode == 2) {
				System.out.println("EstateInfo:失败!\t参数不正确" + json.get("message").getAsString());
			} else {
				System.out.println("EstateInfo:失败!\t" + "resultCode:" + resultCode);
			}
		} else {
			System.out.println("EstateInfo-ERROR:执行失败！" + "\tstatusCode:" + statusCode);
		}
	}

}
