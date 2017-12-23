package com.topaiebiz.timetask.distribution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.distribution.member.service.MemberDistributionAllocationService;
import com.topaiebiz.distribution.merchant.service.GoodsDistributionAllocationService;

/*
 * Description： 分销管理定时任务. 
 * 
 * Author Harry
 *    
 * Date 2017年11月24日 上午10:37:37 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
public class DistributionTimeTask {
	
	@Autowired
	private MemberDistributionAllocationService memberDistributionAllocationService;
	@Autowired
	private GoodsDistributionAllocationService goodsDistributionAllocationService;
	
	
	@Scheduled(cron = "0 10 10 ? * * ")
	public void addMemberDistributionLog() throws GlobalException {
		memberDistributionAllocationService.saveMemberDistributionLog();
	}
	
	
	@Scheduled(cron = "00 15 10 ? * * ")
	public void addGoodsDistributionLog() throws GlobalException {
		goodsDistributionAllocationService.saveGoodsDistributionLog();
	}
	

}
