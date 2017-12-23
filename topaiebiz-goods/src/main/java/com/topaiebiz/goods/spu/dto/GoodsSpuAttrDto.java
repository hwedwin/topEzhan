    
package com.topaiebiz.goods.spu.dto;

import java.util.List;

/**
 * Description 商品SPU属性dto 。
 * 
 * Author Hedda 
 *    
 * Date 2017年8月23日 下午5:10:36 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class GoodsSpuAttrDto{

	/** 全局唯一主键标识符 （本字段是业务无关性的，仅用于关联）。 */
	private Long id;
	
	/** SPU商品。*/
	private Long spuId;
	
	/** 属性集合以键值对形式存放。key：value，key1：value1。*/
	private String baseFieldValue;
	
	/** 销售属性集合以键值对形式存放key:value,key1:value1。*/
	private String saleFieldValue;
	
	/** SKU商品图片。*/
	private String saleImage;
	
	/** 销售价格  (最多两位小数)。*/
	private Double price;
	
	/** 商品条形码。*/
	private String barCode;
	
	private List<GoodsSpuAttrBaseDto> goodsSpuAttrBaseDtos = null;
	
	private List<GoodsSpuAttrSaleDto> goodsSpuAttrSaleDtos = null;
	
	private List<GoodsSpuAttrSaleValueDto> goodsSpuAttrSaleValueDto = null;
	
	private List<GoodsSpuAttrSaleKeyDto> goodsSpuAttrSaleKeyDto = null;
	
	private List<GoodsSpuAttrSaleKeyAndValueDto> goodsSpuAttrSaleKeyAndValueDtos = null;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSpuId() {
		return spuId;
	}

	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}

	public String getBaseFieldValue() {
		return baseFieldValue;
	}

	public void setBaseFieldValue(String baseFieldValue) {
		this.baseFieldValue = baseFieldValue;
	}

	public String getSaleFieldValue() {
		return saleFieldValue;
	}

	public void setSaleFieldValue(String saleFieldValue) {
		this.saleFieldValue = saleFieldValue;
	}

	public String getSaleImage() {
		return saleImage;
	}

	public void setSaleImage(String saleImage) {
		this.saleImage = saleImage;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public List<GoodsSpuAttrBaseDto> getGoodsSpuAttrBaseDtos() {
		return goodsSpuAttrBaseDtos;
	}

	public void setGoodsSpuAttrBaseDtos(List<GoodsSpuAttrBaseDto> goodsSpuAttrBaseDtos) {
		this.goodsSpuAttrBaseDtos = goodsSpuAttrBaseDtos;
	}

	public List<GoodsSpuAttrSaleDto> getGoodsSpuAttrSaleDtos() {
		return goodsSpuAttrSaleDtos;
	}

	public void setGoodsSpuAttrSaleDtos(List<GoodsSpuAttrSaleDto> goodsSpuAttrSaleDtos) {
		this.goodsSpuAttrSaleDtos = goodsSpuAttrSaleDtos;
	}

	public List<GoodsSpuAttrSaleValueDto> getGoodsSpuAttrSaleValueDto() {
		return goodsSpuAttrSaleValueDto;
	}

	public void setGoodsSpuAttrSaleValueDto(List<GoodsSpuAttrSaleValueDto> goodsSpuAttrSaleValueDto) {
		this.goodsSpuAttrSaleValueDto = goodsSpuAttrSaleValueDto;
	}

	public List<GoodsSpuAttrSaleKeyDto> getGoodsSpuAttrSaleKeyDto() {
		return goodsSpuAttrSaleKeyDto;
	}

	public void setGoodsSpuAttrSaleKeyDto(List<GoodsSpuAttrSaleKeyDto> goodsSpuAttrSaleKeyDto) {
		this.goodsSpuAttrSaleKeyDto = goodsSpuAttrSaleKeyDto;
	}

	public List<GoodsSpuAttrSaleKeyAndValueDto> getGoodsSpuAttrSaleKeyAndValueDtos() {
		return goodsSpuAttrSaleKeyAndValueDtos;
	}

	public void setGoodsSpuAttrSaleKeyAndValueDtos(List<GoodsSpuAttrSaleKeyAndValueDto> goodsSpuAttrSaleKeyAndValueDtos) {
		this.goodsSpuAttrSaleKeyAndValueDtos = goodsSpuAttrSaleKeyAndValueDtos;
	}
	
	
}
