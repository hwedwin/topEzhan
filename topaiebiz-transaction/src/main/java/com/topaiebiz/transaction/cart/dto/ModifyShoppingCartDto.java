package com.topaiebiz.transaction.cart.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 
 * Description (修改购物车中数量的dto)  
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年10月13日 下午4:26:36 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class ModifyShoppingCartDto {
	
	@NotNull
	private Long id;
	
	/** 数量*/
	@NotNull(message = "{validation.goodsCart.add.goodsNum}")
	@Min(1)
	@Max(99)
	private Long goodsNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Long goodsNum) {
		this.goodsNum = goodsNum;
	}

}
