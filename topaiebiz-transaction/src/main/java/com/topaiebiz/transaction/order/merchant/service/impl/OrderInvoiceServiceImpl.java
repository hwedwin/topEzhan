package com.topaiebiz.transaction.order.merchant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.transaction.order.merchant.dao.OrderInvoiceDao;
import com.topaiebiz.transaction.order.merchant.dao.StoreOrderDao;
import com.topaiebiz.transaction.order.merchant.entity.OrderInvoiceEntity;
import com.topaiebiz.transaction.order.merchant.entity.StoreOrderEntity;
import com.topaiebiz.transaction.order.merchant.exception.StoreOrderExceptionEnum;
import com.topaiebiz.transaction.order.merchant.service.OrderInvoiceService;

/**
 * 
 * Description 订单发票的接口实现
 * 
 * 
 * Author zhushuyong
 * 
 * Date 2017年9月4日 下午3:34:21
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
@Transactional
public class OrderInvoiceServiceImpl implements OrderInvoiceService {

	@Autowired
	private OrderInvoiceDao orderInvoiceDao;

	@Autowired
	private StoreOrderDao storeOrderDao;

	@Override
	public OrderInvoiceEntity queryOrderInvoice(Long orderId) throws GlobalException {
		StoreOrderEntity storeOrderEntity = storeOrderDao.selectById(orderId);
		if (storeOrderEntity == null) {
			throw new GlobalException(StoreOrderExceptionEnum.STOREORDER_ID_NOT_EXIST);
		}
		return orderInvoiceDao.selectOrderInvoice(orderId);
	}

}
