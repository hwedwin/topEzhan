package com.topaiebiz.transaction.order.merchant.dto;

import com.topaiebiz.transaction.order.merchant.entity.OrderInvoiceEntity;

import java.util.Date;
import java.util.List;

/**
 * Description 店铺订单的详情页面参数DTO
 * <p>
 * Author hxpeng
 * <p>
 * Date 2017/11/28 11:24
 * <p>
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * <p>
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class OrderDetailResultDto {

	//订单ID
	private Long id;

	/** 订单类型。1为平台订单， 2为店铺订单 */
	private Short orderType;

	/** 会员编号 */
	private String memberId;

	/** 店铺id */
	private Long storeId;

	/** 店铺名称 **/
	private String storeName;

	/** 订单时间 */
	private Date orderTime;

	/** 订单状态 */
	private Integer orderState;

	// 订单收货地址实体
	private OrderAddressDto orderAddressDto;

	// 订单发票实体
	private OrderInvoiceEntity orderInvoiceEntity;

	/** 发票状态 */
	private Integer invoiceState;

	/** 结账日期 */
	private Date checkTime;

	/** 配送方式（1：配送2：自提） */
	private Short deliveryType;

	/** 商品总价格 */
	private Double productTotal;

	/** 实际物流费用 */
	private Double actualFreight;

	/** 订单总金额 */
	private Double orderTotal;

	/** 店铺优惠金额 */
	private Double storeDeduction;

	/** 平台优惠金额 */
	private Double platformDeduction;

	/** 优惠后金额（实际金额） */
	private Double lastPrice;

	/** 支付订单号。 */
	private Long totalOrderNo;

	private String payCallBackNo;

	/** 备注。 */
	private String memo;

	// 订单详情集合
	private List<OrderDetailsDto> orderDetailListResultDtos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
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

	public OrderAddressDto getOrderAddressDto() {
		return orderAddressDto;
	}

	public void setOrderAddressDto(OrderAddressDto orderAddressDto) {
		this.orderAddressDto = orderAddressDto;
	}

	public OrderInvoiceEntity getOrderInvoiceEntity() {
		return orderInvoiceEntity;
	}

	public void setOrderInvoiceEntity(OrderInvoiceEntity orderInvoiceEntity) {
		this.orderInvoiceEntity = orderInvoiceEntity;
	}

	public Integer getInvoiceState() {
		return invoiceState;
	}

	public void setInvoiceState(Integer invoiceState) {
		this.invoiceState = invoiceState;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<OrderDetailsDto> getOrderDetailListResultDtos() {
		return orderDetailListResultDtos;
	}

	public void setOrderDetailListResultDtos(List<OrderDetailsDto> orderDetailListResultDtos) {
		this.orderDetailListResultDtos = orderDetailListResultDtos;
	}

	public String getPayCallBackNo() {
		return payCallBackNo;
	}

	public void setPayCallBackNo(String payCallBackNo) {
		this.payCallBackNo = payCallBackNo;
	}

	public Double getPlatformDeduction() {
		return platformDeduction;
	}

	public void setPlatformDeduction(Double platformDeduction) {
		this.platformDeduction = platformDeduction;
	}
}
