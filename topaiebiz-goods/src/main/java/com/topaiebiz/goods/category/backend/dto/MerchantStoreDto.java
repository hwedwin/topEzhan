package com.topaiebiz.goods.category.backend.dto;
/**
 * Description 商家店铺id
 * 
 * Author Hedda 
 *    
 * Date 2017年10月31日 上午10:29:56 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
public class MerchantStoreDto {

	/** 店铺id。*/
	private Long storeId;
	
	/** 商家id。*/
	private Long merchantId;

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	
	
}
