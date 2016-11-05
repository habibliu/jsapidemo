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
 * 3.4.2 查询订单信息接口协议
 * 描述: 根据订单号查询支付订单信息。
 * 参数params:JSON格式字符串
 * {
 *	"serviceId":"3c.pay.queryorder",
 *	"requestType":"DATA",
 *	"attributes":{
 *		"orderNo": ""
 *		}
 * }
 * @author 刘淦潮
 *
 */
public class QueryOrder extends APIService{

	@Override
	protected String buildRequestParam() {
		// TODO Auto-generated method stub
		Properties prop = ConfigHelper.getProperties(baseDir+"queryorder");

		// 构造请求参数对象
		JsonObject jsonParam = new JsonObject();
		jsonParam.addProperty("serviceId", prop.getProperty("serviceId"));
		jsonParam.addProperty("requestType", prop.getProperty("requestType"));
		JsonObject attributes = new JsonObject();
		attributes.addProperty("orderNo", prop.getProperty("orderNo"));
		jsonParam.add("attributes", attributes);
		return jsonParam.toString();
	}

	@Override
	protected void extractResult(CloseableHttpResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == HttpStatus.SC_OK) {//成功调用
			String results = EntityUtils.toString(response.getEntity());
			JsonObject json=new JsonParser().parse(results).getAsJsonObject();
			int resultCode=json.get("resultCode").getAsInt();
			if(resultCode==0){
				JsonElement dataItems=json.get("dataItems");
				System.out.println("queryorder-SUCCESS:获取订单成功!\n\t订单信息："+dataItems.toString());
				
			}else{
				System.out.println("queryorder-ERROR:调用异常!"+"\tresultCode:"+resultCode+"\tmessage:"+json.get("message").getAsString());
			}
		} else {
			System.out.println("querydoors-ERROR:执行失败！"+"\tstatusCode:"+statusCode);

		}
	}

}
