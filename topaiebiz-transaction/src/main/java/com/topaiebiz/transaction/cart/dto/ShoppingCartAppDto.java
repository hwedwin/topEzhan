package com.topaiebiz.transaction.cart.dto;

import java.util.List;

import com.topaiebiz.goods.sku.dto.ItemDto;

/**
 * Description 购物车展示  
 * 
 * Author Hedda 
 *    
 * Date 2017年10月30日 下午3:16:54 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
public class ShoppingCartAppDto {

	/** 商家商品。*/
	private List<ItemDto> itemStoreDtos;
	
	/** 平台商品。*/
	private List<ItemDto> itemAdminDtos;
	
	/** 失效商品。*/
	private List<ItemDto> itemLosaDtos;
	
	public List<ItemDto> getItemStoreDtos() {
		return itemStoreDtos;
	}

	public void setItemStoreDtos(List<ItemDto> itemStoreDtos) {
		this.itemStoreDtos = itemStoreDtos;
	}

	public List<ItemDto> getItemAdminDtos() {
		return itemAdminDtos;
	}

	public void setItemAdminDtos(List<ItemDto> itemAdminDtos) {
		this.itemAdminDtos = itemAdminDtos;
	}

	public List<ItemDto> getItemLosaDtos() {
		return itemLosaDtos;
	}

	public void setItemLosaDtos(List<ItemDto> itemLosaDtos) {
		this.itemLosaDtos = itemLosaDtos;
	}

}
