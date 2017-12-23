package com.topaiebiz.giftcard.manage.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.giftcard.manage.dto.GiftCardOrderDto;
import com.topaiebiz.giftcard.manage.dto.OrderAddressDto;
import com.topaiebiz.giftcard.manage.entity.CardAddressEntity;
import com.topaiebiz.giftcard.manage.entity.CardOrderEntity;
import com.topaiebiz.giftcard.manage.entity.CardOrderInvoiceEntity;
import com.topaiebiz.transaction.order.merchant.entity.OrderInvoiceEntity;

public interface GiftCardOrderService {

	/**
	 * Description 查询礼卡订单。
	 * 
	 * Author Murray.Li
	 * 
	 * return
	 */
	List<CardOrderEntity> getCardOreder();

	/**
	 * Description 订单查询 条件+分页。
	 * 
	 * Author Murray.Li
	 * 
	 * param page param cardOrderDto return throws Exception
	 */
	Page<GiftCardOrderDto> getOrderOfPage(Page<GiftCardOrderDto> page, GiftCardOrderDto cardOrderDto)
			throws GlobalException;

	/**
	 * Description 根据礼卡订单编号查看礼卡详细信息
	 * 
	 * Author Murray.Li
	 * 
	 * param giftCardOrderDto return
	 * 
	 * @throws GlobalException
	 */
	Map<String, Object> getOrderByOrderId(Long orderId) throws GlobalException;

	/**
	 * 
	 * Description： 删除订单。
	 * 
	 * Author Murray.Li
	 * 
	 * @param list
	 * @throws GlobalException
	 */
	void removeOrderById(Long[] list) throws GlobalException;

	/**
	 * 
	 * Description： 查看发票详情。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderId
	 * @return
	 * @throws GlobalException    
	 */ 
	CardOrderInvoiceEntity getOrderInvoiceByOrderId(Long orderId) throws GlobalException;

	/***
	 * 
	 * Description： 根据订单编号查询订单的配送地址信息。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderId
	 * @return 
	 * @throws GlobalException 
	 */
	CardAddressEntity getOrderAddressByOrderId(Long orderId) throws GlobalException;

	/**
	 * 
	 * Description： 根据订单编号修改配送地址信息。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderAddressDto
	 * @throws GlobalException 
	 */ 
	void updateAddressByorderId(OrderAddressDto orderAddressDto) throws GlobalException;

	/**
	 * 
	 * Description：根据订单编号查询标记发货的信息。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderId
	 * @return
	 * @throws GlobalException 
	 */ 
	OrderAddressDto getDelivery(Long orderId) throws GlobalException;

	/**
	 * 
	 * Description：标记订单发货，修改状态为代发货。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderId
	 * @param logisticsNo
	 * @param logisticsType
	 * @throws GlobalException 
	 */
	void markDeliveryByorderId(Long orderId, String logisticsNo, String logisticsType) throws GlobalException;

	/** 
	 * 
	 * Description： 去支付，生成订单。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param addressId
	 * @param cardId
	 * @param number
	 * @param memberId
	 * @throws GlobalException 
	 */ 
	Map<String,Object> generateOrder(OrderInvoiceEntity orderInvoiceEntity) throws GlobalException;
 
	/**
	 * 
	 * Description： 取消订单。  
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderId
	 * @throws GlobalException 
	 */
	void cancelOrder(Long orderId) throws GlobalException;

	/**
	 * 
	 * Description： 查看美礼卡订单详情。  
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderId
	 * @return
	 * @throws GlobalException 
	 */ 
	Map<String, Object> getCardOrderByOrderId(Long orderId) throws GlobalException;

	/***
	 * 
	 * Description： 完成订单支付，将订单状态改为代发货。 
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderId
	 * @param memberId 
	 * @throws GlobalException 
	 */
	void payCardOrder(Long orderId, Long memberId) throws GlobalException; 

	/**
	 * 
	 * Description： 根据 订单编号删除订单。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderId
	 * @param memberId 
	 * @throws GlobalException 
	 */
	void removeOrderByorderId(Long orderId, Long memberId) throws GlobalException;

	/***
	 * 
	 * Description： 延长收货。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderId
	 * @param memberId 
	 * @throws GlobalException 
	 */ 
	void extendTheReceiving(Long orderId, Long memberId) throws GlobalException;

	/**
	 * 
	 * Description：确认收货。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderId
	 * @param memberId 
	 * @throws GlobalException 
	 */  
	void confirmReceipt(Long orderId, Long memberId) throws GlobalException;   


}
