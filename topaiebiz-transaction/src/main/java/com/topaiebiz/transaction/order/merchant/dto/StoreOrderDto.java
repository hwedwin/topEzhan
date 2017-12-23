package com.topaiebiz.transaction.order.merchant.dto;

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

	/** 订单号。 */
	private Long id;
	
	private Long goodsId;

	/** 订单类型。1为平台订单， 2为店铺订单 */
	private Short orderType;

	/** 会员编号 */
	private Long memberId;

	/** 店铺id */
	private Long storeId;

	/** 订单时间 */
	private Date orderTime;

	/** 订单状态 */
	private Integer orderState;

	/** 售后状态 */
	private Integer refundState;

	/** 售后审核时间 */
	private Date refundCheckTime;

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

	/** 运费营销活动。 */
	private Long freightPromotion;

	/** 实际物流费用 */
	private Double actualFreight;

	/** 订单总金额 */
	private Double orderTotal;

	/** 所使用的营销活动。 */
	private Long usedPromotion;

	/** 店铺优惠金额 */
	private Double storeDeduction;

	/** 优惠后金额（实际金额） */
	private Double salePrice;

	/** 优惠后金额 */
	private Double lastPrice;

	/** 支付订单号。 */
	private Long toalOrderNo;

	/** 备注。 */
	private String memo;

	/** 订单商品详情。 */
	private List<OrderDetailsDto> detailList;

	/** 订单发票信息。 */
	private OrderInvoiceDto orderInvoiceDto;

	/** 订单收货地址。 */
	private OrderAddressDto orderAddressDto;

	/** 店铺订单详情使用营销活动 */
	private Long detailUsedPromotion;

	/**商品的总数量*/
	private Long goodsNum;

	private Date lastModifiedTime;

	private Boolean showRefundBtn;

	/** 使用的平台优惠*/
	private Long usedPlatformPromotion;

	/** 平台优惠使用的金额*/
	private Double platformDeduction;

	public Long getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Long goodsNum) {
		this.goodsNum = goodsNum;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

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

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
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

	public Double getTotalFreight() {
		return totalFreight;
	}
 
	public void setTotalFreight(Double totalFreight) {
		this.totalFreight = totalFreight;
	}

	public Long getFreightPromotion() {
		return freightPromotion;
	}

	public void setFreightPromotion(Long freightPromotion) {
		this.freightPromotion = freightPromotion;
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

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Long getToalOrderNo() {
		return toalOrderNo;
	}

	public void setToalOrderNo(Long toalOrderNo) {
		this.toalOrderNo = toalOrderNo;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<OrderDetailsDto> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<OrderDetailsDto> detailList) {
		this.detailList = detailList;
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

	public Long getDetailUsedPromotion() {
		return detailUsedPromotion;
	}

	public void setDetailUsedPromotion(Long detailUsedPromotion) {
		this.detailUsedPromotion = detailUsedPromotion;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public Integer getRefundState() {
		return refundState;
	}

	public void setRefundState(Integer refundState) {
		this.refundState = refundState;
	}

	public Date getRefundCheckTime() {
		return refundCheckTime;
	}

	public void setRefundCheckTime(Date refundCheckTime) {
		this.refundCheckTime = refundCheckTime;
	}

	public Boolean getShowRefundBtn() {
		return showRefundBtn;
	}

	public void setShowRefundBtn(Boolean showRefundBtn) {
		this.showRefundBtn = showRefundBtn;
	}

	public Double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(Double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public Long getUsedPlatformPromotion() {
		return usedPlatformPromotion;
	}

	public void setUsedPlatformPromotion(Long usedPlatformPromotion) {
		this.usedPlatformPromotion = usedPlatformPromotion;
	}

	public Double getPlatformDeduction() {
		return platformDeduction;
	}

	public void setPlatformDeduction(Double platformDeduction) {
		this.platformDeduction = platformDeduction;
	}
}
