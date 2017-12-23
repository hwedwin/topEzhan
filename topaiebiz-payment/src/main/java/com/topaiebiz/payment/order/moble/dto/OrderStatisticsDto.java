/**
 * 
 */
package com.topaiebiz.payment.order.moble.dto;

import OrderStatisticsDto.OrderStatisticsChartDto;

/**
 * Description：订单统计结果DTO
 * 
 * 
 * Author hxpeng
 * 
 * Date 2017年10月29日 下午3:18:05
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

public class OrderStatisticsDto {

	/**
	 * 查询条件：前多少天
	 */
	private Integer beforDays;

	/**
	 * 查询条件：在beforDate之后
	 */
	private String beforDate;

	/**
	 * 查询条件：在afterDate之前
	 */
	private String afterDate;

	/**
	 * 下单人数
	 */
	private Integer orderPeopleNumber;

	/**
	 * 订单数
	 */
	private Integer orderNumber;

	/**
	 * 下单件数
	 */
	private Integer orderProductNumber;

	/**
	 * 下单金额
	 */
	private double orderTotalPrice;

	/**
	 * 退款金额
	 */
	private double orderRefundPrice;

	/**
	 * 付款人数
	 */
	private Integer payPeopleNumber;

	/**
	 * 付款订单数
	 */
	private Integer payOrderNumber;

	/**
	 * 付款件数
	 */
	private Integer alreadyPayOrderNumber;

	/**
	 * 付款总金额
	 */
	private double payTotalPrice;

	/**
	 * 下单转化率
	 */
	private String toOrderTransformationRate;

	/**
	 * 付款转化率
	 */
	private String toPayTransformationRate;

	/**
	 * 成交转化率
	 */
	private String toDealTransformationRate;

	/**
	 * 交易分析 图表结果数组
	 */
	private OrderStatisticsChartDto[] orderStatisticsChartList;

	public Integer getBeforDays() {
		return beforDays;
	}

	public void setBeforDays(Integer beforDays) {
		this.beforDays = beforDays;
	}

	public String getBeforDate() {
		return beforDate;
	}

	public void setBeforDate(String beforDate) {
		this.beforDate = beforDate;
	}

	public String getAfterDate() {
		return afterDate;
	}

	public void setAfterDate(String afterDate) {
		this.afterDate = afterDate;
	}

	public Integer getOrderPeopleNumber() {
		return orderPeopleNumber;
	}

	public void setOrderPeopleNumber(Integer orderPeopleNumber) {
		this.orderPeopleNumber = orderPeopleNumber;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getOrderProductNumber() {
		return orderProductNumber;
	}

	public void setOrderProductNumber(Integer orderProductNumber) {
		this.orderProductNumber = orderProductNumber;
	}

	public double getOrderTotalPrice() {
		return orderTotalPrice;
	}

	public void setOrderTotalPrice(double orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	public double getOrderRefundPrice() {
		return orderRefundPrice;
	}

	public void setOrderRefundPrice(double orderRefundPrice) {
		this.orderRefundPrice = orderRefundPrice;
	}

	public Integer getPayPeopleNumber() {
		return payPeopleNumber;
	}

	public void setPayPeopleNumber(Integer payPeopleNumber) {
		this.payPeopleNumber = payPeopleNumber;
	}

	public Integer getPayOrderNumber() {
		return payOrderNumber;
	}

	public void setPayOrderNumber(Integer payOrderNumber) {
		this.payOrderNumber = payOrderNumber;
	}

	public Integer getAlreadyPayOrderNumber() {
		return alreadyPayOrderNumber;
	}

	public void setAlreadyPayOrderNumber(Integer alreadyPayOrderNumber) {
		this.alreadyPayOrderNumber = alreadyPayOrderNumber;
	}

	public double getPayTotalPrice() {
		return payTotalPrice;
	}

	public void setPayTotalPrice(double payTotalPrice) {
		this.payTotalPrice = payTotalPrice;
	}

	public String getToOrderTransformationRate() {
		return toOrderTransformationRate;
	}

	public void setToOrderTransformationRate(String toOrderTransformationRate) {
		this.toOrderTransformationRate = toOrderTransformationRate;
	}

	public String getToPayTransformationRate() {
		return toPayTransformationRate;
	}

	public void setToPayTransformationRate(String toPayTransformationRate) {
		this.toPayTransformationRate = toPayTransformationRate;
	}

	public String getToDealTransformationRate() {
		return toDealTransformationRate;
	}

	public void setToDealTransformationRate(String toDealTransformationRate) {
		this.toDealTransformationRate = toDealTransformationRate;
	}

	public OrderStatisticsChartDto[] getOrderStatisticsChartList() {
		return orderStatisticsChartList;
	}

	public void setOrderStatisticsChartList(OrderStatisticsChartDto[] orderStatisticsChartList) {
		this.orderStatisticsChartList = orderStatisticsChartList;
	}

	
}
