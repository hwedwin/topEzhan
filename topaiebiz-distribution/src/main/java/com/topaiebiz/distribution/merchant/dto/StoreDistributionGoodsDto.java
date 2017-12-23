package com.topaiebiz.distribution.merchant.dto;

import javax.validation.constraints.NotNull;

/**
 * Description： 店铺分销商品Dto。
 * 
 * 
 * Author Harry
 *    
 * Date 2017年10月28日 下午8:23:12 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

public class StoreDistributionGoodsDto   {
	
	/** 全局唯一主键标识符。支持泛型，具体类型由传入的类型指定。 */
	private Long id;
	
	/**商品ID*/
	@NotNull(message = "{validation.storeDistributionGoods.itemId}")
	private Long itemId;
	
	/**店铺ID*/
	private Long store;
	
	/**备注*/
	private String memo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getStore() {
		return store;
	}

	public void setStore(Long store) {
		this.store = store;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
	
	

}
