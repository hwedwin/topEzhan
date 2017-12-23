package com.topaiebiz.promotion.mgmt.dto;

/**
 * 
 * Description 店铺活动使用记录DTO
 * 
 * 
 * Author Administrator
 * 
 * Date 2017年10月16日 下午5:15:11
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class PromotionStoreUsageLogDto {

	/**
	 * ID
	 */
	private Long id;

	/**
	 * 商家订单编号
	 */
	private Long orderId;

	/**
	 * 营销活动
	 */
	private Long promotionId;

	/**
	 * 会员编号
	 */
	private Long memberId;

	/**
	 * 优惠码编号
	 */
	private Long couponId;

	/**
	 * 所属商家
	 */
	private Long businessId;

	/**
	 * 优惠金额
	 */
	private Long price;

	/**
	 * 备注
	 */
	private String memo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
