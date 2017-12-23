package com.topaiebiz.transaction.refundOrder.dto;

import java.util.List;

import com.topaiebiz.transaction.order.merchant.dto.OrderDetailsDto;

/**
 * 
 * Description 退货申请DTO
 * 
 * 
 * Author Joe
 * 
 * Date 2017年10月30日 下午1:23:29
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class RefundDto {

	/**
	 * 退款类型
	 */
	private String refundType;
	
	/**
	 * 退货订单表
	 */
	private RefundOrderDto refundOrderDto;

	/**
	 * 退货订单详情表
	 */
	private List<RefundOrderDetailDto> refundOrderDetailList;
	
	/**
	 * 商品详情表
	 */
	private List<OrderDetailsDto> orderDetailsList;

	/**
	 * token
	 */
	private String token;

	public String getRefundType() {
		return refundType;
	}

	public void setRefundType(String refundType) {
		this.refundType = refundType;
	}

	public RefundOrderDto getRefundOrderDto() {
		return refundOrderDto;
	}

	public void setRefundOrderDto(RefundOrderDto refundOrderDto) {
		this.refundOrderDto = refundOrderDto;
	}


	public List<RefundOrderDetailDto> getRefundOrderDetailList() {
		return refundOrderDetailList;
	}

	public void setRefundOrderDetailList(List<RefundOrderDetailDto> refundOrderDetailList) {
		this.refundOrderDetailList = refundOrderDetailList;
	}

	public List<OrderDetailsDto> getOrderDetailsList() {
		return orderDetailsList;
	}

	public void setOrderDetailsList(List<OrderDetailsDto> orderDetailsList) {
		this.orderDetailsList = orderDetailsList;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
