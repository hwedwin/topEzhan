package com.topaiebiz.transaction.order.merchant.service;

import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.transaction.order.merchant.entity.OrderInvoiceEntity;

/**
 * 
 * Description 订单发票  
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月4日 下午3:31:32 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public interface OrderInvoiceService {
	
	/**
	 * 
	 * Description 根据订单id查询订单发票  
	 * 
	 * Author zhushuyong   
	 * 
	 * @param orderInvoice
	 * @return
	 * @throws GlobalException 
	 */
	OrderInvoiceEntity queryOrderInvoice(Long orderId) throws GlobalException;

}
