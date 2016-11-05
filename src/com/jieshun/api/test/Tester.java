package com.jieshun.api.test;

import com.jieshun.api.test.service.BookParkSpace;
import com.jieshun.api.test.service.CardDelay;
import com.jieshun.api.test.service.CreateOrderByCard;
import com.jieshun.api.test.service.CreateOrderByCarno;
import com.jieshun.api.test.service.NotifyOrderResult;
import com.jieshun.api.test.service.OpenDoor;
import com.jieshun.api.test.service.QueryCarByCarno;
import com.jieshun.api.test.service.QueryCardDealyList;
import com.jieshun.api.test.service.QueryDoors;
import com.jieshun.api.test.service.QueryOrder;
import com.jieshun.api.test.service.EstateInfo;
import com.jieshun.api.test.service.Invite;
import com.jieshun.api.test.service.InviteExtend;
import com.jieshun.api.test.service.OpenDoorByUniqueId;
import com.jieshun.api.test.service.QueryParkBookParam;
import com.jieshun.api.test.service.QueryParkIn;
import com.jieshun.api.test.service.QueryParkInfo;
import com.jieshun.api.test.service.QueryParkOut;
import com.jieshun.api.test.service.QueryParkSpace;
import com.jieshun.api.test.service.QueryParkStandard;
import com.jieshun.api.test.service.QueryReportInfo;
import com.jieshun.api.test.service.ReplacePayFee;
import com.jieshun.api.test.service.QueryPersonsByCar;
import com.jieshun.api.test.service.QueryPersonsByTel;
import com.jieshun.api.test.service.ReportInfoSubmit;

/**
 * 测试类
 * @author 刘淦潮
 *
 */
public class Tester {
	public static void main(String[] args) {
		//3.1.2.1 查询人员门禁设备协议
//		QueryDoors querydoors = new QueryDoors();
//		querydoors.execute();
		
		//3.1.2.2	门禁开门协议
//		OpenDoor openDoor=new OpenDoor();
//		openDoor.execute();
		
		//3.2.2.1	生成订单协议
//		CreateOrderByCard createorderbycard=new CreateOrderByCard();
//		createorderbycard.execute();
		
		//3.2.2.2  支付结果通知协议
//		NotifyOrderResult notifyorderresult=new NotifyOrderResult();
//		notifyorderresult.execute();
		
		//3.3.2.1	查询相似车辆协议
//		QueryCarByCarno querycarbycarno=new QueryCarByCarno();
//		querycarbycarno.execute();
		
		//3.3.2.2	生成订单协议
//		CreateOrderByCarno createorderbycarno=new CreateOrderByCarno();
//		createorderbycarno.execute();
		
		//3.4	订单结果查询
//		QueryOrder queryorder=new QueryOrder();
//		queryorder.execute();
		
		//3.5	停车场空车位数查询
//		QueryParkSpace queryparkspace=new QueryParkSpace();
//		queryparkspace.execute();
		
		//3.6.2.1	查询人员卡信息-byTel
//		QueryPersonsByTel querypersonsbytel=new QueryPersonsByTel();
//		querypersonsbytel.execute();
		
		//3.7.2.1	查询人员卡信息
		QueryPersonsByCar querypersonsbycar=new QueryPersonsByCar();
		querypersonsbycar.execute();
		
		//3.8.2.1	月卡延期
//		CardDelay carddelay=new CardDelay();
//		carddelay.execute();
		
		//3.9.2.1	月卡缴费明细查询
//		QueryCardDealyList querycarddealylist=new QueryCardDealyList();
//		querycarddealylist.execute();
		
		//3.17.2.1	访客邀请
//		Invite invite = new Invite();
//		invite.execute();
		
		//3.20.2.1	访客超时续时协议
//		InviteExtend inviteextend = new InviteExtend();
//		inviteextend.execute();
		
		//3.21.2.1	代客多车缴费协议
//		ReplacePayFee replacepayfee = new ReplacePayFee();  超时
//		replacepayfee.execute();
		
		//3.23.2.1	 停车场信息查询
//		QueryParkInfo queryparkinfo = new QueryParkInfo();
//		queryparkinfo.execute();
		
		//3.24.2.1	停车场收费标准查询
//		QueryParkStandard queryparkstandard = new QueryParkStandard();
//		queryparkstandard.execute();
		
		//3.25.2.1	物业人事资料查询
//		EstateInfo estateinfo = new EstateInfo();
//		estateinfo.execute();
		
		//3.26.2.1	场内车辆信息查询
//		QueryParkIn queryparkin = new QueryParkIn();
//		queryparkin.execute();
		
		//3.28.2.1	车辆出场信息查询
//		QueryParkOut queryparkout = new QueryParkOut();
//		queryparkout.execute();
		
		//3.29.2.1	蓝牙开门（云端鉴权）
//		OpenDoorByUniqueId opendoorbyuniqueid = new OpenDoorByUniqueId();
//		opendoorbyuniqueid.execute();
		
		//3.30.2.1	报修信息上传
//		ReportInfoSubmit reportinfosubmit = new ReportInfoSubmit();
//		reportinfosubmit.execute();
		
		//3.31.2.1	报修信息查询
//		QueryReportInfo queryreportinfo = new QueryReportInfo();
//		queryreportinfo.execute();
		
		//
//		QueryParkBookParam queryparkbookparam = new QueryParkBookParam();
//		queryparkbookparam.execute();
		
		//3.14.2.1	车位预定
//		BookParkSpace bookparkspace = new BookParkSpace();
//		bookparkspace.execute();
		
	}
}
