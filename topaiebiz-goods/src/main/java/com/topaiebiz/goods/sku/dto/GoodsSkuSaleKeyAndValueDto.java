package com.topaiebiz.goods.sku.dto;

import java.util.List;

/**  
 * Description 商品属性表中的销售属性。  
 * 
 * Author Hedda 
 *    
 * Date 2017年8月23日 下午5:24:37 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class GoodsSkuSaleKeyAndValueDto{

	/** 销售属性的键。*/
	private String saleId;
	
	/** 是非显示sku图片。*/
	private Long imageField;
	
	/** 销售属性见得值。*/
	private String saleName;
	
	/** 销售属性的值。*/
	private List<GoodsSkuSaleValueDto> goodsSkuSaleValueDto;

	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public Long getImageField() {
		return imageField;
	}

	public void setImageField(Long imageField) {
		this.imageField = imageField;
	}
	
	public String getSaleName() {
		return saleName;
	}

	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}

	public List<GoodsSkuSaleValueDto> getGoodsSkuSaleValueDto() {
		return goodsSkuSaleValueDto;
	}

	public void setGoodsSkuSaleValueDto(List<GoodsSkuSaleValueDto> goodsSkuSaleValueDto) {
		this.goodsSkuSaleValueDto = goodsSkuSaleValueDto;
	}
	
}
