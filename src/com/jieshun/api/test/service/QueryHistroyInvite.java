package com.jieshun.api.test.service;

import java.util.Properties;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jieshun.api.test.APIService;
import com.jieshun.api.test.ConfigHelper;

/**
* @ClassName: QueryParkspaceBookList
* @Description: TODO(查询邀访记录记录协议)
* @author huozhuangning
* @date 2016年4月28日 下午2:47:56
* 
*/

public class QueryHistroyInvite extends APIService {

	protected String buildRequestParam() {
		Properties prop = ConfigHelper.getProperties(baseDir+"queryhistroyinvite");
		JsonObject jsonParam = new JsonObject();
		jsonParam.addProperty("serviceId", prop.getProperty("serviceId"));
		jsonParam.addProperty("requestType", prop.getProperty("requestType"));
		JsonObject attributes = new JsonObject();
		attributes.addProperty("visitorId", prop.getProperty("visitorId"));
		attributes.addProperty("visiterType", prop.getProperty("visiterType"));
		attributes.addProperty("personCode", prop.getProperty("personCode"));
		attributes.addProperty("areaCode", prop.getProperty("areaCode"));
		attributes.addProperty("status", prop.getProperty("status"));
		attributes.addProperty("beginTime", prop.getProperty("beginTime"));
		attributes.addProperty("endTime", prop.getProperty("endTime"));
		attributes.addProperty("pageSize", prop.getProperty("pageSize"));
		attributes.addProperty("pageIndex", prop.getProperty("pageIndex"));
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
				
				System.out.println("opendoor-SUCCESS:开门成功!：");
					
			}else{
				System.out.println("opendoor-ERROR:调用异常!"+"\tresultCode:"+resultCode+"\tmessage:"+json.get("message"));
			}
		} else {
			System.out.println("opendoor-ERROR:执行失败！"+"\tstatusCode:"+statusCode);
		}
	}

}
