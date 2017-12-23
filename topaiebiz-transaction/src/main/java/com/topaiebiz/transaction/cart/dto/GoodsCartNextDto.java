package com.topaiebiz.transaction.cart.dto;

import java.util.List;

/**
 * 
 * Description TODO (购物车的下一步汇总)  
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月21日 下午7:58:21 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class GoodsCartNextDto {
	
	/** 美礼卡是否可以使用（1可以，0不可以）。*/
	private Boolean usedCardState;
	
	/**总运费*/
	private Double firght;
	
	/**所有商品总金额*/
	private Double productAmount;
	
	/**可以使用的美礼卡金额*/
	private Double usedCardPrice;
	
	/**订单实际支付金额*/
	private Double actualAmount;
	
	/**购物车下的店铺集合集合*/
	private List<CardStoreListDto> cardStoreList;

	public Boolean getUsedCardState() {
		return usedCardState;
	}

	public void setUsedCardState(Boolean usedCardState) {
		this.usedCardState = usedCardState;
	}

	public Double getFirght() {
		return firght;
	}

	public void setFirght(Double firght) {
		this.firght = firght;
	}

	public Double getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(Double productAmount) {
		this.productAmount = productAmount;
	}

	public Double getUsedCardPrice() {
		return usedCardPrice;
	}

	public void setUsedCardPrice(Double usedCardPrice) {
		this.usedCardPrice = usedCardPrice;
	}

	public Double getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(Double actualAmount) {
		this.actualAmount = actualAmount;
	}

	public List<CardStoreListDto> getCardStoreList() {
		return cardStoreList;
	}

	public void setCardStoreList(List<CardStoreListDto> cardStoreList) {
		this.cardStoreList = cardStoreList;
	}

}
