package com.topaiebiz.transaction.order.merchant.dto;

import java.util.List;

import com.topaiebiz.transaction.order.total.dto.TotalOrderDto;

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

	/** 店铺订单集合。 */
	private List<StoreOrderDto> storeOrderList;

	public TotalOrderDto getTotalOrderDto() {
		return totalOrderDto;
	}

	public void setTotalOrderDto(TotalOrderDto totalOrderDto) {
		this.totalOrderDto = totalOrderDto;
	}

	public List<StoreOrderDto> getStoreOrderList() {
		return storeOrderList;
	}

	public void setStoreOrderList(List<StoreOrderDto> storeOrderList) {
		this.storeOrderList = storeOrderList;
	}

}
