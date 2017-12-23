package com.topaiebiz.payment.order.dto;

import java.util.List;

/**
 * 
 * Description 订单的DTO，其中包含了商家订单list,商家订单发票信息，平台订单
 * 
 * Author Aaron.Xue
 * 
 * Date 2017年10月11日 下午8:10:17
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class OrderDto {

	/** 总支付订单。 */
	private TotalOrderDto totalOrderDto;

	/** 订单发票信息。 */
	private OrderInvoiceDto orderInvoiceDto;

	/** 订单收货地址。 */
	private OrderAddressDto orderAddressDto;

	/** 店铺订单集合。 */
	private List<StoreOrderDto> storeOrderList;

	public TotalOrderDto getTotalOrderDto() {
		return totalOrderDto;
	}

	public void setTotalOrderDto(TotalOrderDto totalOrderDto) {
		this.totalOrderDto = totalOrderDto;
	}

	public OrderInvoiceDto getOrderInvoiceDto() {
		return orderInvoiceDto;
	}

	public void setOrderInvoiceDto(OrderInvoiceDto orderInvoiceDto) {
		this.orderInvoiceDto = orderInvoiceDto;
	}

	public OrderAddressDto getOrderAddressDto() {
		return orderAddressDto;
	}

	public void setOrderAddressDto(OrderAddressDto orderAddressDto) {
		this.orderAddressDto = orderAddressDto;
	}

	public List<StoreOrderDto> getStoreOrderList() {
		return storeOrderList;
	}

	public void setStoreOrderList(List<StoreOrderDto> storeOrderList) {
		this.storeOrderList = storeOrderList;
	}

}
