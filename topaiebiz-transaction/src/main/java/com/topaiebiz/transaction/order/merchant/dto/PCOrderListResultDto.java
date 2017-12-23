package com.topaiebiz.transaction.order.merchant.dto;

import java.util.Date;
import java.util.List;

/**
 * Description PC 商家/平台 订单列表查询结果DTO
 * <p>
 * Author hxpeng
 * <p>
 * Date 2017/11/28 11:12
 * <p>
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * <p>
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class PCOrderListResultDto {

	// 订单ID
	private long orderId;

	// 订单提交时间
	private Date orderTime;

	// 店铺ID
	private Long storeId;

	// 商家ID
	private Long merchantId;

	// 商家名称
	private String merchantName;

	// 店铺名称
	private String storeName;

	// 收货人名称
	private String customerName;

	// 收货人手机号
	private String customerPhone;

	// 店铺订单状态
	private int orderState;

	// 店铺优惠后金额
	private double lastPrice;

	private double actualFreight;

	private int orderType;

	private List<OrderDetailsDto> orderDetailsDtoList;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public int getOrderState() {
		return orderState;
	}

	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}

	public double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public double getActualFreight() {
		return actualFreight;
	}

	public void setActualFreight(double actualFreight) {
		this.actualFreight = actualFreight;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public List<OrderDetailsDto> getOrderDetailsDtoList() {
		return orderDetailsDtoList;
	}

	public void setOrderDetailsDtoList(List<OrderDetailsDto> orderDetailsDtoList) {
		this.orderDetailsDtoList = orderDetailsDtoList;
	}
}
