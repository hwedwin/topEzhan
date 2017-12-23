package com.topaiebiz.transaction.cart.dto;

import java.util.List;

/**
 * 
 * Description TODO (购物车下一步汇总的店铺集合)  
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月21日 下午8:08:12 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class CardStoreListDto {
	
	/** 会员id*/
	private Long memberId;
	
	/** 所属店铺 */
	private Long belongStore;
	
	/** 所属商家id*/
	private Long merchantId;
	
	/** 店铺名称*/
	private String storeName;
	
	/**对应店铺下的商品集合*/
	private List<ShoppingCartDto> productCartDtoList;
	
	/**对应店铺的商品总价*/
	private Double productTotal;
	
	/**每个店铺下商品的总运费*/
	private Double itemTotalFirght;

	public Double getProductTotal() {
		return productTotal;
	}

	public void setProductTotal(Double productTotal) {
		this.productTotal = productTotal;
	}

	public Double getItemTotalFirght() {
		return itemTotalFirght;
	}

	public void setItemTotalFirght(Double itemTotalFirght) {
		this.itemTotalFirght = itemTotalFirght;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getBelongStore() {
		return belongStore;
	}

	public void setBelongStore(Long belongStore) {
		this.belongStore = belongStore;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public List<ShoppingCartDto> getProductCartDtoList() {
		return productCartDtoList;
	}

	public void setProductCartDtoList(List<ShoppingCartDto> productCartDtoList) {
		this.productCartDtoList = productCartDtoList;
	}
	
}
