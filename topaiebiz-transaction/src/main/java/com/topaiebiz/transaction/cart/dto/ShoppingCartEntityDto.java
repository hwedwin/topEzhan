package com.topaiebiz.transaction.cart.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 
 * Description： 购物车添加的Dto 
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月25日 下午10:00:53 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class ShoppingCartEntityDto {
	
	private Long id;
	
	/** 会员id*/
	private Long memberId;
	
	/** 商品sku表的id*/
	@NotNull(message = "{validation.goodsCart.add.goodsId}")
	private Long goodsId;
	
	/** 数量*/
	@NotNull(message = "{validation.goodsCart.add.goodsNum}")
	@Min(1)
	@Max(99)
	private Long goodsNum;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Long getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Long goodsNum) {
		this.goodsNum = goodsNum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
