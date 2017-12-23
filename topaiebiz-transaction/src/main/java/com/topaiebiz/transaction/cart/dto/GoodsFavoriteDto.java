package com.topaiebiz.transaction.cart.dto;

import javax.validation.constraints.NotNull;

/**
 * 
 * Description： 收藏夹dto 
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月26日 下午3:16:44 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class GoodsFavoriteDto {
	
	private Long id;
	
	/** 会员id*/
	@NotNull(message = "{validation.goodsFavorite.add.member}")
	private Long memberId;
	
	/** 商品id*/
	@NotNull(message = "{validation.goodsFavorite.add.goodsId}")
	private Long goodsId;
	
	@NotNull
	private Long[] ids;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

}
