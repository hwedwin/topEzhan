package com.topaiebiz.goods.sku.dto;


import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.topaiebiz.goods.comment.dto.GoodsSkuCommentDto;

/**
 * Description 商品基本信息表，存储商品的信息。
 * 
 * Author Hedda
 * 
 * Date 2017年8月23日 下午5:23:11
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class ItemAppDto{

	/** 全局唯一主键标识符 （本字段是业务无关性的，仅用于关联）。 */
	private Long id;
	
	/** 唯一编码 (本字段是从业务角度考虑的，相当于全局的唯一业务主键)。 */
	@NotNull(message = "{validation.item.itemCode}")
	private String itemCode;

	/** 商品名称(标题显示的名称)。 */
	@NotNull(message = "{validation.item.name}")
	private String name;

	/** 引用SPU商品。 */
	private Long spuId;

	/** 默认价格（页面刚打开的价格）。 */
	@NotNull(message = "{validation.item.defaultPrice}")
	private double defaultPrice;

	/** 所属店铺。 */
	private Long belongStore;
	
	/** 店铺名称。*/
	private String storeName;
	
	/** 所属品牌。 */
	private Long belongBrand;
	
	/** 品牌名称。*/
	private String brandName;
	
	/** 适用年龄段。*/
	private Long ageId;

	/** 所属类目。 */
	private Long belongCategory;
	
	/** 前台类目。*/
	private Long frontendCategory;
	
	/** 图片所属类目属性。*/
	private Long imageField;

	/** 商品状态（1 新录入 2 已上架 3 下架 4 违规下架）。 */
	private Integer status;

	/** 选用物流模版。 */
	private Long logisticsId;
	
	/** 物流模版的体积、重量（体积默认为m3，重量默认为kg）。*/
	private Double weightBulk;
	
	/** 营销id。*/
	private Long promotionId;

	/** 商品描述。 */
	private String description;
	
	/** 快递费。*/
	private Double firstPrice;
	
	/** 结束时间。*/
	private Date endTime;
	
	/** 好评率。*/
	private Double feedback;
	
	/** 好评，差评，中评*/
	private Integer type;
	
	/** 销量百分比。*/
	private Double salesRatio;
	
	/** 收藏id。*/
	private Long favId;
	
	/** 商品图片集合。*/
	private List<ItemPictureDto> itemPictureDtos;
	
	/** 商品属性集合。*/
	private List<GoodsSkuDto> goodsSkuDtos;
	
	/** 评价列表。*/
	private List<GoodsSkuCommentDto> goodsSkuCommentDtos; 
	
	/** 销售属性集合。key中的id和值*/
	private List<GoodsSkuSaleDto> goodsSkuSaleKeyDtos;
	
	/** 销售属性集合。key与value*/
	private List<GoodsSkuSaleDto> goodsSkuSaleDtos;
	
	/** 属性值，加图片。*/
	private GoodsSkuSaleKeyAndValueDto goodsSkuSaleKeyAndValueDto;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSpuId() {
		return spuId;
	}

	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}

	public double getDefaultPrice() {
		return defaultPrice;
	}

	public void setDefaultPrice(double defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	public Long getBelongStore() {
		return belongStore;
	}

	public void setBelongStore(Long belongStore) {
		this.belongStore = belongStore;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Long getBelongBrand() {
		return belongBrand;
	}

	public void setBelongBrand(Long belongBrand) {
		this.belongBrand = belongBrand;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Long getAgeId() {
		return ageId;
	}

	public void setAgeId(Long ageId) {
		this.ageId = ageId;
	}

	public Long getBelongCategory() {
		return belongCategory;
	}

	public void setBelongCategory(Long belongCategory) {
		this.belongCategory = belongCategory;
	}

	public Long getFrontendCategory() {
		return frontendCategory;
	}

	public void setFrontendCategory(Long frontendCategory) {
		this.frontendCategory = frontendCategory;
	}

	public Long getImageField() {
		return imageField;
	}

	public void setImageField(Long imageField) {
		this.imageField = imageField;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getLogisticsId() {
		return logisticsId;
	}

	public void setLogisticsId(Long logisticsId) {
		this.logisticsId = logisticsId;
	}

	public Double getWeightBulk() {
		return weightBulk;
	}

	public void setWeightBulk(Double weightBulk) {
		this.weightBulk = weightBulk;
	}

	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getFirstPrice() {
		return firstPrice;
	}

	public void setFirstPrice(Double firstPrice) {
		this.firstPrice = firstPrice;
	}
	
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Double getFeedback() {
		return feedback;
	}

	public void setFeedback(Double feedback) {
		this.feedback = feedback;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Double getSalesRatio() {
		return salesRatio;
	}

	public void setSalesRatio(Double salesRatio) {
		this.salesRatio = salesRatio;
	}
	
	public Long getFavId() {
		return favId;
	}

	public void setFavId(Long favId) {
		this.favId = favId;
	}

	public List<ItemPictureDto> getItemPictureDtos() {
		return itemPictureDtos;
	}

	public void setItemPictureDtos(List<ItemPictureDto> itemPictureDtos) {
		this.itemPictureDtos = itemPictureDtos;
	}

	public List<GoodsSkuDto> getGoodsSkuDtos() {
		return goodsSkuDtos;
	}

	public void setGoodsSkuDtos(List<GoodsSkuDto> goodsSkuDtos) {
		this.goodsSkuDtos = goodsSkuDtos;
	}

	public List<GoodsSkuCommentDto> getGoodsSkuCommentDtos() {
		return goodsSkuCommentDtos;
	}

	public void setGoodsSkuCommentDtos(List<GoodsSkuCommentDto> goodsSkuCommentDtos) {
		this.goodsSkuCommentDtos = goodsSkuCommentDtos;
	}

	public List<GoodsSkuSaleDto> getGoodsSkuSaleDtos() {
		return goodsSkuSaleDtos;
	}

	public void setGoodsSkuSaleDtos(List<GoodsSkuSaleDto> goodsSkuSaleDtos) {
		this.goodsSkuSaleDtos = goodsSkuSaleDtos;
	}
	
	public List<GoodsSkuSaleDto> getGoodsSkuSaleKeyDtos() {
		return goodsSkuSaleKeyDtos;
	}

	public void setGoodsSkuSaleKeyDtos(List<GoodsSkuSaleDto> goodsSkuSaleKeyDtos) {
		this.goodsSkuSaleKeyDtos = goodsSkuSaleKeyDtos;
	}

	public GoodsSkuSaleKeyAndValueDto getGoodsSkuSaleKeyAndValueDto() {
		return goodsSkuSaleKeyAndValueDto;
	}

	public void setGoodsSkuSaleKeyAndValueDto(GoodsSkuSaleKeyAndValueDto goodsSkuSaleKeyAndValueDto) {
		this.goodsSkuSaleKeyAndValueDto = goodsSkuSaleKeyAndValueDto;
	}

}
