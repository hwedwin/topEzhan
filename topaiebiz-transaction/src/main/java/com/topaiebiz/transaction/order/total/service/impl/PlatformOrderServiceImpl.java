package com.topaiebiz.transaction.order.total.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.transaction.order.total.dao.TotalOrderDao;
import com.topaiebiz.transaction.order.total.dto.PlatformPayOrderLogDto;
import com.topaiebiz.transaction.order.total.exception.PlatformOrderExceptionEnum;
import com.topaiebiz.transaction.order.total.service.PlatformOrderService;

/**
 * 
 * Description 平台订单的业务接口实现  
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月5日 下午4:06:18 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
public class PlatformOrderServiceImpl implements PlatformOrderService {
	
	@Autowired
	private TotalOrderDao platformOrderDao;

	@Override
	public Page<PlatformPayOrderLogDto> queryPlatformPayOrderLog(Page<PlatformPayOrderLogDto> page,
			PlatformPayOrderLogDto platformPayOrderLogDto) {
		page.setRecords(platformOrderDao.selectPlatformOrder(page,platformPayOrderLogDto));
		return page;
	}

	@Override
	public PlatformPayOrderLogDto queryPayOrderLogId(Long id) throws GlobalException {
		PlatformPayOrderLogDto platformPayOrderLogDto=platformOrderDao.selectPlatformOrderId(id);
		if(platformPayOrderLogDto==null) {
			throw new GlobalException(PlatformOrderExceptionEnum.PLATFORMORDER_ID_NOT_EXIST);
		}
		return platformPayOrderLogDto;
	}

}
