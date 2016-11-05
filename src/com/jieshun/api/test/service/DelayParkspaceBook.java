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
* @Description: TODO(车位预定延期)
* @author huozhuangning
* @date 2016年4月28日 下午2:47:56
* 
*/

public class DelayParkspaceBook extends APIService {

	protected String buildRequestParam() {
		Properties prop = ConfigHelper.getProperties(baseDir+"delayparkspacebook");
		JsonObject jsonParam = new JsonObject();
		jsonParam.addProperty("serviceId", prop.getProperty("serviceId"));
		jsonParam.addProperty("requestType", prop.getProperty("requestType"));
		JsonObject attributes = new JsonObject();
		attributes.addProperty("firstBookId", prop.getProperty("firstBookId"));
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
				
				System.out.println("opendoor-SUCCESS:开门成功!：");
					
			}else{
				System.out.println("opendoor-ERROR:调用异常!"+"\tresultCode:"+resultCode+"\tmessage:"+json.get("message"));
			}
		} else {
			System.out.println("opendoor-ERROR:执行失败！"+"\tstatusCode:"+statusCode);
		}
	}

}
