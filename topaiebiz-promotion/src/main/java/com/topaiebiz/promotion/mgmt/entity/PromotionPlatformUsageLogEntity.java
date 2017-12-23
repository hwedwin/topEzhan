package com.topaiebiz.promotion.mgmt.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseEntity;

/**
 * 
 * Description 平台活动使用记录表
 * 
 * 
 * Author Joe
 * 
 * Date 2017年9月27日 下午4:01:40
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_pro_promotion_platform_usage_log")
public class PromotionPlatformUsageLogEntity extends BaseEntity<Long> {

	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = -2929579912946831737L;

	/**
	 * ID
	 */
	private Long id;

	/**
	 * 平台订单编号
	 */
	private Long orderId;

	/**
	 * 会员编号
	 */
	private Long memberId;

	/**
	 * 营销活动
	 */
	private Long marketId;

	/**
	 * 优惠码编号
	 */
	private Long couponId;

	/**
	 * 优惠金额
	 */
	private Double price;

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

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getMarketId() {
		return marketId;
	}

	public void setMarketId(Long marketId) {
		this.marketId = marketId;
	}

	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
