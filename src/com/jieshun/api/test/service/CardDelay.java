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
 * 3.8.2.1	月卡延期协议
 * 描述:根据延期时间和延期金额等信息进行延期。
 * 参数params:JSON格式字符串
 * {
 *	"serviceId":"3c.card.carddelay",
 *	"requestType":"DATA",
 *	"attributes":{
 *		"cardId": "",
 *		"month": "",
 *		"money": "",
 *		"newBeginDate": "",
 *		"newEndDate": ""
 *		}
 * }
 * @author 刘淦潮
 */
public class CardDelay extends APIService {

	@Override
	protected String buildRequestParam() {
		Properties prop = ConfigHelper.getProperties(baseDir+"carddelay");

		// 构造请求参数对象
		JsonObject jsonParam = new JsonObject();
		jsonParam.addProperty("serviceId", prop.getProperty("serviceId"));
		jsonParam.addProperty("requestType", prop.getProperty("requestType"));
		JsonObject attributes = new JsonObject();
		attributes.addProperty("cardId", prop.getProperty("cardId"));
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
		// TODO Auto-generated method stub
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == HttpStatus.SC_OK) {//成功调用
			String results = EntityUtils.toString(response.getEntity());
			JsonObject json=new JsonParser().parse(results).getAsJsonObject();
			int resultCode=json.get("resultCode").getAsInt();
			if(resultCode==0){
				JsonElement dataItems=json.get("dataItems");
				System.out.println("carddelay-SUCCESS:月卡延期成功!\n\t返回信息如下："+dataItems.toString());
				
			}else{
				System.out.println("carddelay-ERROR:调用异常!"+"\tresultCode:"+resultCode+"\tmessage:"+json.get("message").getAsString());
			}
		} else {
			System.out.println("carddelay-ERROR:执行失败！"+"\tstatusCode:"+statusCode);

		}
	}

}
