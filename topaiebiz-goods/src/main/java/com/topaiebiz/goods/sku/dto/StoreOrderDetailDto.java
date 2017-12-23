package com.topaiebiz.goods.sku.dto;
/**
 * Description 店铺详情dto
 * 
 * Author Hedda 
 *    
 * Date 2017年11月28日 下午8:26:16 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
public class StoreOrderDetailDto {
	
	private Long id;
	
	private Long goodsId;
	
	private String name;
	
	private String fieldValue;
	
	private String saleValue;
	
	private Double salePrice ;
	
	private String goodsImage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	
	public String getSaleValue() {
		return saleValue;
	}

	public void setSaleValue(String saleValue) {
		this.saleValue = saleValue;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public String getGoodsImage() {
		return goodsImage;
	}

	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}
	
}
