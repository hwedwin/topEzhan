package com.topaiebiz.transaction.payment.log.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.transaction.payment.log.dao.PayOrderLogDao;
import com.topaiebiz.transaction.payment.log.entity.PayOrderLogEntity;
import com.topaiebiz.transaction.payment.log.exception.PayOrderLogExceptionEnum;
import com.topaiebiz.transaction.payment.log.service.PayOrderLogService;

/**
 * 
 * Description 第三方支付接口的实现层  
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月7日 下午5:07:16 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
public class PayOrderLogServiceImpl implements PayOrderLogService {
	
	@Autowired
	private PayOrderLogDao payOrderLogDao;

	@Override
	public Page<PayOrderLogEntity> queryPayOrderLogPage(Page<PayOrderLogEntity> page,PayOrderLogEntity payOrderLog) {
		page.setRecords(payOrderLogDao.selectPayOrderLogPage(page,payOrderLog));
		return page;
	}

	@Override
	public PayOrderLogEntity queryPayOrderLogView(Long id)
			throws GlobalException {
		PayOrderLogEntity entity=payOrderLogDao.selectById(id);
		if(entity==null) {
			throw new GlobalException(PayOrderLogExceptionEnum.PAORDERLOG_ID_NOT_EXIST);
		}
		return entity;
	}

}
