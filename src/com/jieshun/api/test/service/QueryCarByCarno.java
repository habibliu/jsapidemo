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
 * 3.3.2.1	查询相似车辆协议
 * 描述: 查询停车场中与用户车牌相似的车辆，供用户确认进行支付。
 * 参数params:JSON格式字符串
 * {
 *	"serviceId":"3c.pay.querycarbycarno",
 *	"requestType":"DATA",
 *	"attributes":{
 *		"parkCode": "",
 *      "carNo": "",
 *		"isCallBack": "",
 *      "notifyUrl": ""
 *	}
 * }
 * @author 刘淦潮
 */
public class QueryCarByCarno  extends APIService{

	@Override
	protected String buildRequestParam() {
		// TODO Auto-generated method stub
		Properties prop = ConfigHelper.getProperties(baseDir+"payment/carno/querycarbycarno");

		// 构造请求参数对象
		JsonObject jsonParam = new JsonObject();
		jsonParam.addProperty("serviceId", prop.getProperty("serviceId"));
		jsonParam.addProperty("requestType", prop.getProperty("requestType"));
		JsonObject attributes = new JsonObject();
		attributes.addProperty("parkCode", prop.getProperty("parkCode"));
		attributes.addProperty("carNo", prop.getProperty("carNo"));
		jsonParam.add("attributes", attributes);

		return jsonParam.toString();
	}

	@Override
	protected void extractResult(CloseableHttpResponse response) throws Exception {
		// TODO Auto-generated method stub
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == HttpStatus.SC_OK) {//成功调用
			String results = EntityUtils.toString(response.getEntity());
			JsonObject json=new JsonParser().parse(results).getAsJsonObject();
			int resultCode=json.get("resultCode").getAsInt();
			if(resultCode==0){
				System.out.println("querycarbycarno-SUCCESS:查询相似车辆成功!");
				JsonElement dataItems=json.get("dataItems");
				if(dataItems==null||(dataItems.isJsonArray() && dataItems.getAsJsonArray().size()==0)){
					System.out.println("querycarbycarno-SUCCESS:没有匹配的车辆！");
				}else{
					System.out.println("querycarbycarno-SUCCESS:匹配到的车辆如下：");
					JsonArray items=dataItems.getAsJsonArray();
					for(int i=0;i<items.size();i++){
						JsonObject door=items.get(i).getAsJsonObject().get("attributes").getAsJsonObject();
						System.out.println("\t<"+(i+1)+">carNo:"+door.get("carNo").getAsString()+"\tenterTime:"+door.get("enterTime").getAsString());
					}
				}
			}else{
				System.out.println("querycarbycarno-ERROR:调用异常!"+"\tresultCode:"+resultCode+"\tmessage:"+json.get("message"));
			}
		} else {
			System.out.println("querydoors-ERROR:执行失败！"+"\tstatusCode:"+statusCode);

		}
	}

}
