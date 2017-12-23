package com.topaiebiz.promotion.mgmt.dto;

import java.util.List;

/**
 * 
 * Description 根据商品与营销活动查询价格Dto
 * 
 * 
 * Author Joe
 * 
 * Date 2017年10月13日 下午8:30:15
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class PromotionPriceDto {

	/**
	 * 营销活动ID
	 */
	private Long promotionId;

	/**
	 * 原价
	 */
	private Double originalPrice;

	/**
	 * 活动商品总价
	 */
	private Double promotionGoodsProce;

	/**
	 * 现价
	 */
	private Double presentPrice;

	/**
	 * 优惠金额
	 */
	private Double preferentialAmount;

	/**
	 * 是否包邮
	 */
	private boolean freeShipping;
	
	/**
	 * 参与活动商品
	 */
	private List<PromotionGoodsDto> promotionGoodsList;

	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	public Double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Double getPresentPrice() {
		return presentPrice;
	}

	public void setPresentPrice(Double presentPrice) {
		this.presentPrice = presentPrice;
	}

	public Double getPreferentialAmount() {
		return preferentialAmount;
	}

	public void setPreferentialAmount(Double preferentialAmount) {
		this.preferentialAmount = preferentialAmount;
	}

	public boolean isFreeShipping() {
		return freeShipping;
	}

	public void setFreeShipping(boolean freeShipping) {
		this.freeShipping = freeShipping;
	}

	public Double getPromotionGoodsProce() {
		return promotionGoodsProce;
	}

	public List<PromotionGoodsDto> getPromotionGoodsList() {
		return promotionGoodsList;
	}

	public void setPromotionGoodsList(List<PromotionGoodsDto> promotionGoodsList) {
		this.promotionGoodsList = promotionGoodsList;
	}

	public void setPromotionGoodsProce(Double promotionGoodsProce) {
		this.promotionGoodsProce = promotionGoodsProce;
	}

}
