package com.topaiebiz.payment.order.dto;

/**
 * Description 后台管理 个人工作台 数据展示DTO
 * <p>
 * Author hxpeng
 * <p>
 * Date 2017/11/22 21:00
 * <p>
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * <p>
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class AdminOrderDataDto {

	/**
	 * 下单人数
	 */
	private Integer orderPeopleNumber;

	/**
	 * 订单总金额
	 */
	private Double orderTotalPrice;

	/**
	 * 订单数量
	 */
	private Integer orderNumber;

	/**
	 * 待付款订单数量
	 */
	private Integer waitToPayOrderNumber;

	/**
	 * 待发货订单数量
	 */
	private Integer waitToDeliverOrderNumber;

	/**
	 * 待收货订单数量
	 */
	private Integer waitToReceiptOrderNumber;

	/**
	 * 退款订单数
	 */
	private Integer refundOrderNumber;

	/**
	 * 等待处理退款订单数
	 */
	private Integer waitToDispose;

	/**
	 * 已处理退款订单数
	 */
	private Integer alreadyProcessed;

	/**
	 * 已拒绝退款订单数
	 */
	private Integer alreadyRejected;

	public Integer getOrderPeopleNumber() {
		return orderPeopleNumber;
	}

	public void setOrderPeopleNumber(Integer orderPeopleNumber) {
		this.orderPeopleNumber = orderPeopleNumber;
	}

	public Double getOrderTotalPrice() {
		return orderTotalPrice;
	}

	public void setOrderTotalPrice(Double orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getWaitToPayOrderNumber() {
		return waitToPayOrderNumber;
	}

	public void setWaitToPayOrderNumber(Integer waitToPayOrderNumber) {
		this.waitToPayOrderNumber = waitToPayOrderNumber;
	}

	public Integer getWaitToDeliverOrderNumber() {
		return waitToDeliverOrderNumber;
	}

	public void setWaitToDeliverOrderNumber(Integer waitToDeliverOrderNumber) {
		this.waitToDeliverOrderNumber = waitToDeliverOrderNumber;
	}

	public Integer getWaitToReceiptOrderNumber() {
		return waitToReceiptOrderNumber;
	}

	public void setWaitToReceiptOrderNumber(Integer waitToReceiptOrderNumber) {
		this.waitToReceiptOrderNumber = waitToReceiptOrderNumber;
	}

	public Integer getRefundOrderNumber() {
		return refundOrderNumber;
	}

	public void setRefundOrderNumber(Integer refundOrderNumber) {
		this.refundOrderNumber = refundOrderNumber;
	}

	public Integer getWaitToDispose() {
		return waitToDispose;
	}

	public void setWaitToDispose(Integer waitToDispose) {
		this.waitToDispose = waitToDispose;
	}

	public Integer getAlreadyProcessed() {
		return alreadyProcessed;
	}

	public void setAlreadyProcessed(Integer alreadyProcessed) {
		this.alreadyProcessed = alreadyProcessed;
	}

	public Integer getAlreadyRejected() {
		return alreadyRejected;
	}

	public void setAlreadyRejected(Integer alreadyRejected) {
		this.alreadyRejected = alreadyRejected;
	}
}
