package com.topaiebiz.goods.sku.dto;

import java.util.List;

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
public class GoodsSkuDto{

	/** 全局唯一主键标识符 （本字段是业务无关性的，仅用于关联）。 */
	private Long id;

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
	
	/** 销售价格,最多两位小数。 */
	private double price;
	
	/** 库存数量。 */
	private long stockNumber;
	
	/** 货号。 */
	private String articleNumber;
	
	/** 商品条形码。 */
	private String barCode;
	
	/** 商品状态（1 新录入 2 已上架 3 下架 4 违规下架）。 */
	private Integer status;
	
	/** 原有库存。*/
	private Long repertoryNum;
	
	/** 活动库存数量。*/
	private Long promotionNum;
	
	/** 活动价格。*/
	private Double promotionPrice;
	
	/** 营销活动id。*/
	private Long promotionId;
	
	/** 营销商品id。*/
	private Long promotionGoodsId;
	
	/** ID限购。*/
	private Integer confineNum;
	
	/** 营销状态。*/
	private Integer state;
	
	/**
	 * 订单商品数量属性-----仅创建订单调用查询店铺营销活动时使用
	 */
	private Long orderGoodsNum;
	
	private List<GoodsSkuBaseDto> goodsSkuBaseDto;
	
	private List<GoodsSkuSaleDto> goodsSkuSaleDto;
	
	private List<GoodsSkuSaleValueDto> goodsSkuSaleValueDto;
	
	private List<GoodsSkuSaleKeyDto> goodsSkuSaleKeyDto;
	
	private List<GoodsSkuSaleKeyAndValueDto> goodsSkuSaleKeyAndValueDtos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getRepertoryNum() {
		return repertoryNum;
	}

	public void setRepertoryNum(Long repertoryNum) {
		this.repertoryNum = repertoryNum;
	}

	public Long getPromotionNum() {
		return promotionNum;
	}

	public void setPromotionNum(Long promotionNum) {
		this.promotionNum = promotionNum;
	}

	public Double getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(Double promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	public Long getPromotionGoodsId() {
		return promotionGoodsId;
	}

	public void setPromotionGoodsId(Long promotionGoodsId) {
		this.promotionGoodsId = promotionGoodsId;
	}

	public Integer getConfineNum() {
		return confineNum;
	}

	public void setConfineNum(Integer confineNum) {
		this.confineNum = confineNum;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Long getOrderGoodsNum() {
		return orderGoodsNum;
	}

	public void setOrderGoodsNum(Long orderGoodsNum) {
		this.orderGoodsNum = orderGoodsNum;
	}

	public List<GoodsSkuBaseDto> getGoodsSkuBaseDto() {
		return goodsSkuBaseDto;
	}

	public void setGoodsSkuBaseDto(List<GoodsSkuBaseDto> goodsSkuBaseDto) {
		this.goodsSkuBaseDto = goodsSkuBaseDto;
	}

	public List<GoodsSkuSaleDto> getGoodsSkuSaleDto() {
		return goodsSkuSaleDto;
	}

	public void setGoodsSkuSaleDto(List<GoodsSkuSaleDto> goodsSkuSaleDto) {
		this.goodsSkuSaleDto = goodsSkuSaleDto;
	}

	public List<GoodsSkuSaleValueDto> getGoodsSkuSaleValueDto() {
		return goodsSkuSaleValueDto;
	}

	public void setGoodsSkuSaleValueDto(List<GoodsSkuSaleValueDto> goodsSkuSaleValueDto) {
		this.goodsSkuSaleValueDto = goodsSkuSaleValueDto;
	}

	public List<GoodsSkuSaleKeyDto> getGoodsSkuSaleKeyDto() {
		return goodsSkuSaleKeyDto;
	}

	public void setGoodsSkuSaleKeyDto(List<GoodsSkuSaleKeyDto> goodsSkuSaleKeyDto) {
		this.goodsSkuSaleKeyDto = goodsSkuSaleKeyDto;
	}

	public List<GoodsSkuSaleKeyAndValueDto> getGoodsSkuSaleKeyAndValueDtos() {
		return goodsSkuSaleKeyAndValueDtos;
	}

	public void setGoodsSkuSaleKeyAndValueDtos(List<GoodsSkuSaleKeyAndValueDto> goodsSkuSaleKeyAndValueDtos) {
		this.goodsSkuSaleKeyAndValueDtos = goodsSkuSaleKeyAndValueDtos;
	}
	
	
}
