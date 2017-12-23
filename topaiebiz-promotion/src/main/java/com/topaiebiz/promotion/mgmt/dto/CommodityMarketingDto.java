package com.topaiebiz.promotion.mgmt.dto;

import java.util.List;

/**
 * 
 * Description 商品对应营销活动
 * 
 * 
 * Author Joe
 * 
 * Date 2017年10月13日 上午9:49:03
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class CommodityMarketingDto {

	private Long goodsSkuId;

	private List<PromotionDto> promotionList;

	private List<PromotionGoodsDto> promotionSingleList;

	public Long getGoodsSkuId() {
		return goodsSkuId;
	}

	public void setGoodsSkuId(Long goodsSkuId) {
		this.goodsSkuId = goodsSkuId;
	}

	public List<PromotionDto> getPromotionList() {
		return promotionList;
	}

	public void setPromotionList(List<PromotionDto> promotionList) {
		this.promotionList = promotionList;
	}

	public List<PromotionGoodsDto> getPromotionSingleList() {
		return promotionSingleList;
	}

	public void setPromotionSingleList(List<PromotionGoodsDto> promotionSingleList) {
		this.promotionSingleList = promotionSingleList;
	}

	public CommodityMarketingDto() {
		// TODO Auto-generated constructor stub
	}

}
