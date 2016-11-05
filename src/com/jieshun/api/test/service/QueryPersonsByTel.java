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
 * 3.6.2.1	查询人员卡信息协议
 * 描述:根据小区编号和手机号查询人员及相关卡信息。
 * 参数params:JSON格式字符串
 * {
 *	"serviceId":"3c.base.querypersonsbytel",
 *	"requestType":"DATA",
 *	"attributes":{
 *		"areaCode": "",
 *		"telephone": ""
 *		}
 * }
 * @author 刘淦潮
 *
 */
public class QueryPersonsByTel extends APIService{

	@Override
	protected String buildRequestParam() {
		Properties prop = ConfigHelper.getProperties(baseDir+"querypersonsbytel");

		// 构造请求参数对象
		JsonObject jsonParam = new JsonObject();
		jsonParam.addProperty("serviceId", prop.getProperty("serviceId"));
		jsonParam.addProperty("requestType", prop.getProperty("requestType"));
		JsonObject attributes = new JsonObject();
		attributes.addProperty("areaCode", prop.getProperty("areaCode"));
		attributes.addProperty("telephone", prop.getProperty("telephone"));
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
				System.out.println("querypersonsbytel-SUCCESS:获取人员卡信息成功!\n\t信息如下："+dataItems.toString());
				
			}else{
				System.out.println("querypersonsbytel-ERROR:调用异常!"+"\tresultCode:"+resultCode+"\tmessage:"+json.get("message").getAsString());
			}
		} else {
			System.out.println("querypersonsbytel-ERROR:执行失败！"+"\tstatusCode:"+statusCode);

		}
	}

}
