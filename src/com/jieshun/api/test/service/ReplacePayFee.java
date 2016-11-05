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
 * 3.21.2.1 代客多车缴费协议
 * 
 * @author yt
 * @date 2016-08-25
 */
public class ReplacePayFee extends APIService {

	@Override
	protected String buildRequestParam() {
		Properties prop = ConfigHelper.getProperties(baseDir + "/payment/replace/replacepayfee");
		JsonObject jsonParam = new JsonObject();
		JsonObject attributes = new JsonObject();
		// jsonParam
		jsonParam.addProperty("serviceId", prop.getProperty("serviceId"));
		jsonParam.addProperty("requestType", prop.getProperty("requestType"));
		// attributes
		attributes.addProperty("parkCode", prop.getProperty("parkCode"));
		attributes.addProperty("businesserCode", prop.getProperty("businesserCode"));
		attributes.addProperty("carNo", prop.getProperty("carNo"));
		attributes.addProperty("cardNo", prop.getProperty("cardNo"));
		jsonParam.add("attributes", attributes);
		return jsonParam.toString();
	}

	@Override
	protected void extractResult(CloseableHttpResponse response)
			throws Exception {
		//{"attributes":{},"dataItems":[],"message":"参数不完整:businesserCode为空","resultCode":2,"serviceId":"3c.visitor.replacepayfee"}
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == HttpStatus.SC_OK) {
			String results = EntityUtils.toString(response.getEntity());
			JsonObject json = new JsonParser().parse(results).getAsJsonObject();
			int resultCode = json.get("resultCode").getAsInt();
			JsonElement dataItems = json.get("dataItems");
			System.out.println(dataItems);
			if (resultCode == 0) {
				System.out.println("ReplacePayFee-SUCCESS:成功!\n\t访客成功信息：" + dataItems.toString());
			} else if (resultCode == 2) {
				System.out.println("ReplacePayFee:失败!\t参数不正确" + json.get("message").getAsString());
			} else if (resultCode == 2087) {
				System.out.println("ReplacePayFee:失败!\t商户不存在");
			} else if (resultCode == 2010) {
				System.out.println("ReplacePayFee:失败!\t停车场不存在");
			} else {
				System.out.println("ReplacePayFee:失败!\t");
			}
		} else {
			System.out.println("ReplacePayFee-ERROR:执行失败！" + "\tstatusCode:" + statusCode);
		}
	}

}
