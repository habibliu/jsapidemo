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
 * 3.26.2.1	场内车辆信息查询
 * @author yt
 * @date 2016-08-25
 *	
 */
public class QueryParkIn extends APIService {

	@Override
	protected String buildRequestParam() {
		Properties prop = ConfigHelper.getProperties(baseDir + "/park/flow/queryparkin");
		JsonObject jsonParam = new JsonObject();
		JsonObject attributes = new JsonObject();
		// jsonParam
		jsonParam.addProperty("serviceId", prop.getProperty("serviceId"));
		jsonParam.addProperty("requestType", prop.getProperty("requestType"));
		// attributes
		attributes.addProperty("parkCode", prop.getProperty("parkCode"));
		attributes.addProperty("carNo", prop.getProperty("carNo"));
		attributes.addProperty("cardNo", prop.getProperty("cardNo"));
		attributes.addProperty("beginDate", prop.getProperty("beginDate"));
		attributes.addProperty("endDate", prop.getProperty("endDate"));
		attributes.addProperty("pageIndex", prop.getProperty("pageIndex"));
		attributes.addProperty("pageSize", prop.getProperty("pageSize"));
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
				System.out.println("QueryParkIn-SUCCESS:成功!\n信息：" + dataItems.toString());
			} else if (resultCode == 2) {
				System.out.println("QueryParkIn:失败!\t参数不正确");
			} else {
				System.out.println("QueryParkIn:失败!\t" + "resultCode:" + resultCode);
			}
		} else {
			System.out.println("QueryParkIn-ERROR:执行失败！" + "\tstatusCode:" + statusCode);
		}
	}

}
