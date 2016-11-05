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
 * 3.29.2.1	蓝牙开门（云端鉴权）
 * @author yt
 * @date 2016-08-25
 *	
 */
public class OpenDoorByUniqueId extends APIService {

	@Override
	protected String buildRequestParam() {
		Properties prop = ConfigHelper.getProperties(baseDir + "/app/opendoorbyuniqueid");
		JsonObject jsonParam = new JsonObject();
		JsonObject attributes = new JsonObject();
		// jsonParam
		jsonParam.addProperty("serviceId", prop.getProperty("serviceId"));
		jsonParam.addProperty("requestType", prop.getProperty("requestType"));
		// attributes
		attributes.addProperty("areaCode", prop.getProperty("areaCode"));
		attributes.addProperty("personCode", prop.getProperty("personCode"));
		attributes.addProperty("doorUniqueId", prop.getProperty("doorUniqueId"));
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
				System.out.println("OpenDoorByUniqueId-SUCCESS:成功!\n信息：" + dataItems.toString());
			} else if (resultCode == 2) {
				System.out.println("OpenDoorByUniqueId:失败!\t参数不正确");
			} else if (resultCode == 2056) {
				System.out.println("OpenDoorByUniqueId:失败!\t设备不存在");
			} else if (resultCode == 3112) {
				System.out.println("OpenDoorByUniqueId:失败!\t该人员没有该设备的权限");
			} else {
				System.out.println("OpenDoorByUniqueId:失败!\t" + "resultCode:" + resultCode);
			}
		} else {
			System.out.println("OpenDoorByUniqueId-ERROR:执行失败！" + "\tstatusCode:" + statusCode);
		}
	}

}
