package com.topaiebiz.goods.sku.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**  
 * Description 商品属性表，一条数据对应一个SKU。  
 * 
 * Author Hedda 
 *    
 * Date 2017年8月23日 下午5:24:37 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_goo_goods_sku")
public class GoodsSkuEntity extends BaseBizEntity<Long>{

	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = 4799084762992286907L;

	/** 所属商品。 */
	private Long itemId;
	
	/** 所属商品SPU。 */
	private Long spuId;
	
	/** 属性集合以键值对形式存放 (key:value,key1:value1)。 */
	private String baseFieldValue;
	
	/** 销售属性集合以键值对形式存放  (key:value,key1:value1)。 */
	private String saleFieldValue;
	
	/** SKU商品图片。 */
	private String saleImage;
	
	/** 市场价。*/
	private Double marketPrice;
	
	/** 销售价格,最多两位小数。 */
	private double price;
	
	/** 库存数量。 */
	private long stockNumber;
	
	/** 货号。 */
	private String articleNumber;
	
	/** 商品条形码。 */
	private String barCode;
	
	/** 备注。用于备注其他信息。 */
	private String memo;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
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

	public Double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(long stockNumber) {
		this.stockNumber = stockNumber;
	}

	public String getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
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
