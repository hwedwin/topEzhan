package com.topaiebiz.timetask.settlement;
/**
 * Description： 结算管理的定时任务.  
 * 
 * Author Harry 
 *    
 * Date 2017年11月24日 上午10:20:33 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.settlement.member.service.MemeberSettlementService;
import com.topaiebiz.settlement.store.service.StoreSettlementService;

@Service
public class SettlementTimeTask {
	
	@Autowired 
	private StoreSettlementService  storeSettlementService;
	@Autowired
	private MemeberSettlementService memeberSettlementService;

	
	/**
	 * Description：   定时计算商家的结算。
	 * 
	 * Author Harry  
	 * 
	 * @return
	 * @throws GlobalException
	 */
	@Scheduled(cron = "0 08 13 ? * * ")
	public void timingTask() {
		storeSettlementService.saveStoreSettlement();
	}
	
	/**
	 * Description： 定时计算会员结算
	 * 
	 * Author Harry
	 * 
	 * @return
	 * @throws GlobalException
	 */
	@Scheduled(cron = "0 46 20 ? * * ")
	public void addMemeberSettlement() throws GlobalException {
		memeberSettlementService.saveMemeberSettlement();
	}
	

}
