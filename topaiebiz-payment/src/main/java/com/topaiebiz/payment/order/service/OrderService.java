package com.topaiebiz.payment.order.service;

import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.payment.order.dto.AdminOrderDataDto;
import com.topaiebiz.payment.order.dto.OrderDto;
import com.topaiebiz.payment.order.dto.TotalOrderDto;

public interface OrderService {
	
	/**
	 * Description 提交订单
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param orderDto
	 * 		返回总订单（支付订单）的信息
	 * @return
	 * @throws GlobalException 
	 */
	TotalOrderDto submitOrder(OrderDto orderDto) throws GlobalException;

	/**
	 * Description 支付订单
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param totalOrderDto
	 * @return
	 */
	Boolean payOrder(TotalOrderDto totalOrderDto);

	/**
	 * Description 取消订单
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param id
	 * @return
	 * @throws GlobalException
	 */
	Boolean cancelOrder(Long id) throws GlobalException;


	/**
	*
	* Description: 获取今天订单单数情况
	*
	* Author: hxpeng
	* createTime: 2017/11/22
	*
	* @param:
	**/
	AdminOrderDataDto getTodayOrderSituation();
}
