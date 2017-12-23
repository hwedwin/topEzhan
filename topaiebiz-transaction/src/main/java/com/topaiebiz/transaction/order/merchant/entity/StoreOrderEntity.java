package com.topaiebiz.transaction.order.merchant.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

import java.util.Date;

/**
 * Description： 店铺订单详情
 * 
 * 
 * Author hxpeng
 * 
 * Date 2017年10月24日 下午5:09:00
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@TableName("t_tsa_store_order")
public class StoreOrderEntity extends BaseBizEntity<Long> {

	private static final long serialVersionUID = -8599221882857186555L;

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

	/** 售后状态 */
	private Integer refundState;

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

	/** 使用的平台优惠*/
	private Long usedPlatformPromotion;

	/** 平台优惠使用的金额*/
	private Double platformDeduction;

	/** 优惠后金额（实际金额） */
	private Double lastPrice;

	/** 支付订单号。 */
	private Long totalOrderNo;

	/** 备注。 */
	private String memo;

	/**
	 * 上次售后审核时间
	 */
	private Date refundCheckTime;

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

	public Integer getRefundState() {
		return refundState;
	}

	public void setRefundState(Integer refundState) {
		this.refundState = refundState;
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

	public Date getRefundCheckTime() {
		return refundCheckTime;
	}

	public void setRefundCheckTime(Date refundCheckTime) {
		this.refundCheckTime = refundCheckTime;
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
