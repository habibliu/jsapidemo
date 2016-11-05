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
 * 3.28.2.1	月卡延期（按车牌号）
 * @author yt
 * @date 2016-08-25
 *	
 */
public class DelayByCar extends APIService {

	@Override
	protected String buildRequestParam() {
		Properties prop = ConfigHelper.getProperties(baseDir + "/card/delay/delaybycar");
		JsonObject jsonParam = new JsonObject();
		JsonObject attributes = new JsonObject();
		// jsonParam
		jsonParam.addProperty("serviceId", prop.getProperty("serviceId"));
		jsonParam.addProperty("requestType", prop.getProperty("requestType"));
		// attributes
		attributes.addProperty("parkCode", prop.getProperty("parkCode"));
		attributes.addProperty("carNo", prop.getProperty("carNo"));
		attributes.addProperty("month", prop.getProperty("month"));
		attributes.addProperty("money", prop.getProperty("money"));
		attributes.addProperty("newBeginDate", prop.getProperty("newBeginDate"));
		attributes.addProperty("newEndDate", prop.getProperty("newEndDate"));
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
				System.out.println("DelayByCar-SUCCESS:成功!\n信息：" + dataItems.toString());
			} else if (resultCode == 2) {
				System.out.println("DelayByCar:失败!\t参数不正确");
			} else if (resultCode == 2083) {
				System.out.println("DelayByCar:失败!\t卡不存在");
			} else if (resultCode == 3003) {
				System.out.println("DelayByCar:失败!\t该卡或车存在延期不成功的记录，不能再次进行延期");
			} else {
				System.out.println("DelayByCar:失败!\t" + "resultCode:" + resultCode);
			}
		} else {
			System.out.println("DelayByCar-ERROR:执行失败！" + "\tstatusCode:" + statusCode);
		}
	}

}
