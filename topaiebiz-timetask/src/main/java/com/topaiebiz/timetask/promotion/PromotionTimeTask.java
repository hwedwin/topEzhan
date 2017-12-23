package com.topaiebiz.timetask.promotion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.topaiebiz.promotion.mgmt.service.PromotionService;

/**
 * 
 * Description 营销活动相关定时任务
 * 
 * 
 * Author Joe 
 *    
 * Date 2017年11月22日 下午9:15:24 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
public class PromotionTimeTask {

	@Autowired
	private PromotionService promotionService;
	
	/**
	 * 
	 * Description 开始活动/结束活动(时间间隔：一小时)
	 * 
	 * Author Joe   
	 *
	 */
	@Scheduled(cron = "0 0/5 * * * ?")
	public void timingTask() {
		promotionService.timingTask();
	}
	
}
