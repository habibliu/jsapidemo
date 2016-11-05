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
 * 3.2.2.2	支付结果通知协议
 * 描述:支付完成通知捷慧通云平台订单的支付结果。
 * 参数params:JSON格式字符串
 * {
 *  "serviceId":"3c.pay.notifyorderresult",
 *	"requestType":"DATA",
 *	"attributes":{
 *		"orderNo": "",
 *      "tradeStatus": "",
 *		"isCallBack": "",
 *      "notifyUrl": "" 
 *		}
 * }
 * @author 刘淦潮
 */
public class NotifyOrderResult extends APIService {

	@Override
	protected String buildRequestParam() {
		// TODO Auto-generated method stub
		Properties prop = ConfigHelper.getProperties(baseDir+"notifyorderresult");

		// 构造请求参数对象
		JsonObject jsonParam = new JsonObject();
		jsonParam.addProperty("serviceId", prop.getProperty("serviceId"));
		jsonParam.addProperty("requestType", prop.getProperty("requestType"));
		JsonObject attributes = new JsonObject();
		attributes.addProperty("orderNo", prop.getProperty("orderNo"));
		attributes.addProperty("tradeStatus", prop.getProperty("tradeStatus"));
		attributes.addProperty("isCallBack", prop.getProperty("isCallBack"));
		attributes.addProperty("notifyUrl", prop.getProperty("notifyUrl"));
		jsonParam.add("attributes", attributes);

		return jsonParam.toString();
	}

	@Override
	protected void extractResult(CloseableHttpResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == HttpStatus.SC_OK) {//成功调用
			System.out.println("notifyorderresult-SUCCESS:支付结果通知成功!");
			String results = EntityUtils.toString(response.getEntity());
			JsonObject json=new JsonParser().parse(results).getAsJsonObject();
			int resultCode=json.get("resultCode").getAsInt();
			if(resultCode==0){
				JsonElement dataItems=json.get("dataItems");
				JsonArray items=dataItems.getAsJsonArray();
				JsonObject notify=items.get(0).getAsJsonObject().get("attributes").getAsJsonObject();
				System.out.println("notifyorderresult-SUCCESS:订单["+notify.get("orderNo").getAsString()+"]支付"+(notify.get("retCode").getAsString().equals("0")?"成功!":"失败!"));
				
			}else{
				System.out.println("notifyorderresult-ERROR:调用异常!"+"\tresultCode:"+resultCode+"\tmessage:"+json.get("message").getAsString());
			}
		} else {
			System.out.println("notifyorderresult-ERROR:执行失败！"+"\tstatusCode:"+statusCode);

		}
	}

}
