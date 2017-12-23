package com.topaiebiz.transaction.order.merchant.dto;

import java.util.Date;
import java.util.List;

/**
 * Description APP 用户订单列表查询结果DTO
 * <p>
 * Author hxpeng
 * <p>
 * Date 2017/11/29 14:37
 * <p>
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * <p>
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class APPOrderListResultDto {

	// 订单ID
	private Long orderId;

	// 订单状态
	private Integer orderState;

	private Integer refundState;

	// 店铺ID
	private Long storeId;

	// 实际运费
	private Double actualFreight;

	// 优惠后价格
	private Double lastPrice;

	// 商品数量
	private Integer goodsNum;

	// 下单时间
	private Date orderTime;

	// 总订单ID
	private Long totalOrderNo;

	// 最后修改时间
	private Date lastModifiedTime;

	private List<OrderDetailsDto> orderDetailsDtoList;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderState() {
		return orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

	public Integer getRefundState() {
		return refundState;
	}

	public void setRefundState(Integer refundState) {
		this.refundState = refundState;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Double getActualFreight() {
		return actualFreight;
	}

	public void setActualFreight(Double actualFreight) {
		this.actualFreight = actualFreight;
	}

	public Double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(Double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Long getTotalOrderNo() {
		return totalOrderNo;
	}

	public void setTotalOrderNo(Long totalOrderNo) {
		this.totalOrderNo = totalOrderNo;
	}

	public List<OrderDetailsDto> getOrderDetailsDtoList() {
		return orderDetailsDtoList;
	}

	public void setOrderDetailsDtoList(List<OrderDetailsDto> orderDetailsDtoList) {
		this.orderDetailsDtoList = orderDetailsDtoList;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}
}
