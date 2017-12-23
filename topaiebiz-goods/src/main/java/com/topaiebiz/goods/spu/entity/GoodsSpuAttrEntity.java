
package com.topaiebiz.goods.spu.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * Description 商品SPU属性 。
 * 
 * Author Hedda 
 *    
 * Date 2017年8月23日 下午5:10:36 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_goo_goods_spu_attr")
public class GoodsSpuAttrEntity extends BaseBizEntity<Long>{

	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = -4057401632721956251L;
	
	/** SPU商品。*/
	private Long spuId;
	
	/** 属性集合以键值对形式存放。key：value，key1：value1。*/
	private String baseFieldValue;
	
	/** 销售属性集合以键值对形式存放key:value,key1:value1。*/
	private String saleFieldValue;
	
	/** SKU商品图片。*/
	private String saleImage;
	
	/** 市场价。*/
	private Double marketPrice;
	
	/** 销售价格  (最多两位小数)。*/
	private Double price;
	
	/** 商品条形码。*/
	private String barCode;
	
	/** 备注。用于备注其他信息。 */
	private String memo;

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

	public Double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
