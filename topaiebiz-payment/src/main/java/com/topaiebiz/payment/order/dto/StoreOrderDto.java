package com.topaiebiz.payment.order.dto;

import com.topaiebiz.transaction.order.merchant.dto.OrderDetailsDto;

import java.util.Date;
import java.util.List;

/**
 * Description 商家订单Dto
 * 
 * Author Aaron.Xue
 * 
 * Date 2017年10月11日 上午11:10:16
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class StoreOrderDto {

	/** 订单ID。 */
	private Long id;

	/** 订单编号。 */
	private String orderCode;

	/** 订单类型。1为平台订单， 2为店铺订单 */
	private Short orderType;

	/** 会员编号 */
	private String memberId;

	/** 店铺id */
	private Long storeId;

	/** 订单时间 */
	private Date orderTime;

	/** 订单状态 */
	private Integer orderState;

	/** 发票状态 */
	private Integer invoiceState;

	/** 结账日期 */
	private Date checkTime;

	/** 配送方式（1：配送2：自提） */
	private Short deliveryType;

	/** 商品总价格 */
	private Double productTotal;

	/** 总运费 */
	private Double totalFreight;

	/** 实际物流费用 */
	private Double actualFreight;

	/** 订单总金额 */
	private Double orderTotal;

	/** 所使用的营销活动。 */
	private Long usedPromotion;

	/** 店铺优惠金额 */
	private Double storeDeduction;

	/** 优惠后金额（实际金额） */
	private Double lastPrice;

	/** 支付订单号。 */
	private Long totalOrderNo;

	/** 订单商品详情。 */
	private List<OrderDetailsDto> detailList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Short getOrderType() {
		return orderType;
	}

	public void setOrderType(Short orderType) {
		this.orderType = orderType;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Integer getOrderState() {
		return orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Short getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(Short deliveryType) {
		this.deliveryType = deliveryType;
	}

	public Double getProductTotal() {
		return productTotal;
	}

	public void setProductTotal(Double productTotal) {
		this.productTotal = productTotal;
	}

	public Double getTotalFreight() {
		return totalFreight;
	}

	public void setTotalFreight(Double totalFreight) {
		this.totalFreight = totalFreight;
	}

	public Double getActualFreight() {
		return actualFreight;
	}

	public void setActualFreight(Double actualFreight) {
		this.actualFreight = actualFreight;
	}

	public Double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(Double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public Long getUsedPromotion() {
		return usedPromotion;
	}

	public void setUsedPromotion(Long usedPromotion) {
		this.usedPromotion = usedPromotion;
	}

	public Double getStoreDeduction() {
		return storeDeduction;
	}

	public void setStoreDeduction(Double storeDeduction) {
		this.storeDeduction = storeDeduction;
	}

	public Double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(Double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public Long getTotalOrderNo() {
		return totalOrderNo;
	}

	public void setTotalOrderNo(Long totalOrderNo) {
		this.totalOrderNo = totalOrderNo;
	}

	public List<OrderDetailsDto> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<OrderDetailsDto> detailList) {
		this.detailList = detailList;
	}

	public Integer getInvoiceState() {
		return invoiceState;
	}

	public void setInvoiceState(Integer invoiceState) {
		this.invoiceState = invoiceState;
	}

}
