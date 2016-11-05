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
 * 3.1.2.1 查询人员门禁设备协议  
 * 参数params:JSON格式字符串 
 * {"serviceId":"3c.door.querydoors", 
 * 	"requestType":"DATA", 
 * 	"attributes": {
 * 		"areaCode": "", 
 * 		"personCode": "" 
 *  	} 
 * }
 * @author 刘淦潮
 */
public class QueryDoors extends APIService{
	
	
	@Override
	protected String buildRequestParam() {
		// TODO Auto-generated method stub
		Properties prop = ConfigHelper.getProperties(baseDir+"app/querydoors");

		// 构造请求参数对象
		JsonObject jsonParam = new JsonObject();
		jsonParam.addProperty("serviceId", prop.getProperty("serviceId"));
		jsonParam.addProperty("requestType", prop.getProperty("requestType"));
		JsonObject attributes = new JsonObject();
		attributes.addProperty("areaCode", prop.getProperty("areaCode"));
		attributes.addProperty("personCode", prop.getProperty("personCode"));
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
				JsonElement dataItems=json.get("dataItems");
				if(dataItems==null||(dataItems.isJsonArray() && dataItems.getAsJsonArray().size()==0)){
					System.out.println("querydoors-SUCCESS:您在当前小区没有被授权！");
				}else{
					System.out.println("querydoors-SUCCESS:您在当前小区具有以下门的开门权限：");
					JsonArray items=dataItems.getAsJsonArray();
					for(int i=0;i<items.size();i++){
						JsonObject door=items.get(i).getAsJsonObject().get("attributes").getAsJsonObject();
						System.out.println("\t<"+(i+1)+">doorId:"+door.get("doorId").getAsString()+"\tcardId:"+items.get(i).getAsJsonObject().get("subItems").getAsJsonArray().get(0).getAsJsonObject().get("attributes").getAsJsonObject().get("cardId")+"\tdoorName:"+door.get("doorName").getAsString());
					}
				}
			}else{
				System.out.println("querydoors-ERROR:调用异常!"+"\tresultCode:"+resultCode+"\tmessage:"+json.get("message"));
			}
		} else {
			System.out.println("querydoors-ERROR:执行失败！"+"\tstatusCode:"+statusCode);

		}
	}

}
