    
package com.topaiebiz.goods.spu.dto;


/**
 * Description 商品SPU销售属性dto 。
 * 
 * Author Hedda 
 *    
 * Date 2017年8月23日 下午5:10:36 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class GoodsSpuAttrSaleDto{
	
	/** 商品销售属性key。*/
	private String id;
	
	/** 商品销售属性value。*/
	private String saleName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSaleName() {
		return saleName;
	}

	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}

}
