package com.jieshun.api.test;

import org.junit.Test;

import com.jieshun.api.test.service.BookParkSpace;
import com.jieshun.api.test.service.CancelInvite;
import com.jieshun.api.test.service.CardDelay;
import com.jieshun.api.test.service.CreateOrderByCard;
import com.jieshun.api.test.service.CreateOrderByCarno;
import com.jieshun.api.test.service.DelayParkspaceBook;
import com.jieshun.api.test.service.Invite;
import com.jieshun.api.test.service.NotifyOrderResult;
import com.jieshun.api.test.service.OpenDoor;
import com.jieshun.api.test.service.Passpopedom;
import com.jieshun.api.test.service.QueryCarByCarno;
import com.jieshun.api.test.service.QueryCarParkingSpot;
import com.jieshun.api.test.service.QueryCardDealyList;
import com.jieshun.api.test.service.QueryCurrentParkTraffic;
import com.jieshun.api.test.service.QueryDoors;
import com.jieshun.api.test.service.QueryHistroyInvite;
import com.jieshun.api.test.service.QueryOrder;
import com.jieshun.api.test.service.QueryParkBookParam;
import com.jieshun.api.test.service.QueryParkPlaceList;
import com.jieshun.api.test.service.QueryParkSpace;
import com.jieshun.api.test.service.QueryParkspaceBookList;
import com.jieshun.api.test.service.QueryPersonsByCar;
import com.jieshun.api.test.service.QueryPersonsByTel;

/**
* @ClassName: TestDemo
* @Description: TODO(测试用例)
* @author huozhuangning
* @date 2016年4月28日 下午2:46:29
* 
*/

public class TestDemo {
	
	private APIService bookParkSpace = new BookParkSpace();
	private APIService cardDelay = new CardDelay();
	private APIService createOrderByCard = new CreateOrderByCard();
	private APIService createOrderByCarno = new CreateOrderByCarno();
	private APIService notifyOrderResult = new NotifyOrderResult();
	private APIService openDoor = new OpenDoor();
	private APIService queryCarByCarno = new QueryCarByCarno();
	private APIService queryCardDealyList = new QueryCardDealyList();
	private APIService queryCarParkingSpot = new QueryCarParkingSpot();
	private APIService queryCurrentParkTraffic = new QueryCurrentParkTraffic();
	private APIService querydoors = new QueryDoors();
	private APIService queryOrder = new QueryOrder();
	private APIService queryParkBookParam = new QueryParkBookParam();
	private APIService queryParkPlaceList = new QueryParkPlaceList();
	private APIService queryParkSpace = new QueryParkSpace();
	private APIService queryPersonsByCar = new QueryPersonsByCar();
	private APIService queryPersonsByTel = new QueryPersonsByTel();
	
	private APIService queryParkspaceBookList=new QueryParkspaceBookList();
	private APIService delayparkspacebook=new DelayParkspaceBook();
	private APIService invite =new Invite();
	private APIService queryhistroyinvite=new QueryHistroyInvite();
	private APIService cancelinvite=new CancelInvite();
	private APIService passpopedom = new Passpopedom();
	
	
	
	@Test
	public void testbookParkSpace(){
		bookParkSpace.execute();
	}
	
	@Test
	public void testcardDelay(){
		cardDelay.execute();
	}
	
	@Test
	public void testcreateOrderByCard(){
		while(true){
//			Date startdate =new Date();
			createOrderByCard.execute();
//			Date enddate=new Date();
//			System.out.println("耗时："+"starttime"+startdate+"endtime"+enddate+"\t"+(enddate.getTime()-startdate.getTime()));
			//logger.info("耗时："+"starttime"+startdate+"endtime"+enddate+(enddate.getTime()-startdate.getTime()));
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testcreateOrderByCard1() {
		createOrderByCard.execute();
	}

	@Test
	public void testcreateOrderByCarno(){
		createOrderByCarno.execute();
	}
	
	@Test
	public void testnotifyOrderResult(){
		notifyOrderResult.execute();
	}
	
	@Test
	public void testopenDoor(){
		openDoor.execute();
	}
	
	@Test
	public void testqueryCarByCarno(){
		queryCarByCarno.execute();
	}
	
	@Test
	public void testqueryCardDealyList(){
		queryCardDealyList.execute();
	}
	
	@Test
	public void testqueryCarParkingSpot(){
		queryCarParkingSpot.execute();
	}
	
	@Test
	public void testqueryCurrentParkTraffic(){
		queryCurrentParkTraffic.execute();
	}
	
	@Test
	public void testquerydoors(){
		querydoors.execute();
	}
	
	@Test
	public void testqueryOrder(){
		queryOrder.execute();
	}
	
	@Test
	public void testqueryParkBookParam(){
		queryParkBookParam.execute();
	}
	
	@Test
	public void testqueryParkPlaceList(){
		queryParkPlaceList.execute();
	}
	
	@Test
	public void testqueryParkSpace(){
		queryParkSpace.execute();
	}
	
	@Test
	public void testqueryPersonsByCar(){
		queryPersonsByCar.execute();
	}
	
	@Test
	public void testqueryPersonsByTel(){
		queryPersonsByTel.execute();
	}
	
	/** 
	* @Title: testqueryParkspaceBookList 
	* @Description: TODO(预定记录历史查询)
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/ 
	@Test
	public void testqueryParkspaceBookList(){
		queryParkspaceBookList.execute();
	}
	
	/** 
	* @Title: testdelayparkspacebook 
	* @Description: TODO(车位预定延期) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/ 
	@Test
	public void testdelayparkspacebook(){
		delayparkspacebook.execute();
	}
	
	/** 
	* @Title: testinvite 
	* @Description: TODO(发起访客邀请) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/ 
	@Test
	public void testinvite(){
		invite.execute();
	}
	
	/** 
	* @Title: testqueryhistroyinvite 
	* @Description: TODO(查询邀访历史记录) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/ 
	@Test
	public void testqueryhistroyinvite(){
		queryhistroyinvite.execute();
	}
	
	/** 
	* @Title: testcancelinvite 
	* @Description: TODO(取消邀访) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/ 
	@Test
	public void testcancelinvite(){
		cancelinvite.execute();
	}
	
	/** 
	* @Title: testpasspopedom 
	* @Description: TODO(社区住户通过调用该接口获取二维码通行权限) 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/ 
	@Test
	public void testpasspopedom(){
		passpopedom.execute();
	}
	
}
