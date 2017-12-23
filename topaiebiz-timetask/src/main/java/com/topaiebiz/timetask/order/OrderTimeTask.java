package com.topaiebiz.timetask.order;

import com.topaiebiz.transaction.order.merchant.service.StoreOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Description 订单相关定时任务
 * <p>
 * Author hxpeng
 * <p>
 * Date 2017/11/22 15:56
 * <p>
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * <p>
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Component
public class OrderTimeTask {

	@Autowired
	private StoreOrderService storeOrderService;

	@Scheduled(cron = "0 0 0 * * ?")
	private void changeOrderFinish(){
		storeOrderService.changeOrderFinish();
	}


}
