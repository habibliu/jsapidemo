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
 * 3.9.2.1	月卡缴费明细查询协议
 * 描述:根据卡信息查询卡延期明细记录。
 * 参数params:JSON格式字符串
 * {
 *	"serviceId":"3c.card.querycarddealylist",
 *	"requestType":"DATA",
 *	"attributes":{
 *		"cardId": "",
 *		"beginDate": "",
 *		"endDate": "",
 *		"pageSize": "",
 *		"pageIndex": ""
 *		}
 * }
 * @author 刘淦潮
 *
 */
public class QueryCardDealyList extends APIService {

	@Override
	protected String buildRequestParam() {
		// TODO Auto-generated method stub
		Properties prop = ConfigHelper.getProperties(baseDir+"querycarddealylist");

		// 构造请求参数对象
		JsonObject jsonParam = new JsonObject();
		jsonParam.addProperty("serviceId", prop.getProperty("serviceId"));
		jsonParam.addProperty("requestType", prop.getProperty("requestType"));
		JsonObject attributes = new JsonObject();
		attributes.addProperty("cardId", prop.getProperty("cardId"));
		attributes.addProperty("beginDate", prop.getProperty("beginDate"));
		attributes.addProperty("endDate", prop.getProperty("endDate"));
		attributes.addProperty("pageSize", prop.getProperty("pageSize"));
		attributes.addProperty("pageIndex", prop.getProperty("pageIndex"));
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
				System.out.println("querycarddealylist-SUCCESS:月卡缴费明细查询成功!\n\t信息如下："+dataItems.toString());
				
			}else{
				System.out.println("querycarddealylist-ERROR:调用异常!"+"\tresultCode:"+resultCode+"\tmessage:"+json.get("message").getAsString());
			}
		} else {
			System.out.println("querycarddealylist-ERROR:执行失败！"+"\tstatusCode:"+statusCode);

		}
	}

}
