package com.topaiebiz.goods.sku.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.topaiebiz.goods.category.backend.dto.BackendCategoryDto;
import com.topaiebiz.goods.category.frontend.dto.FrontendCategoryDto;
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
public class ItemDto implements Comparable<ItemDto> {

	/** 全局唯一主键标识符 （本字段是业务无关性的，仅用于关联）。 */
	private Long id;
	
	private List<Long> ids;

	/** 唯一编码 (本字段是从业务角度考虑的，相当于全局的唯一业务主键)。 */
	@NotNull(message = "{validation.item.itemCode}")
	private String itemCode;

	/** 商品名称(标题显示的名称)。 */
	@NotNull(message = "{validation.item.name}")
	private String name;

	/** 佣金比例。小数形式。平台收取商家的佣金。 */
	private Double brokerageRatio;

	/** 引用SPU商品。 */
	private Long spuId;

	/** 默认价格（页面刚打开的价格）。 */
	@NotNull(message = "{validation.item.defaultPrice}")
	private Double defaultPrice;

	/** 具体一件商品sku价格。 */
	private Double skuPrice;

	/** 销售属性。 */
	private String saleFieldValue;

	/** 基本属性。 */
	private String baseFieldValue;

	/** 价格区间1。 */
	private String priceRangeLeft;

	/** 价格区间2。 */
	private String priceRangeRigth;

	/** 所属店铺。 */
	private Long belongStore;

	/** 店铺名称。 */
	private String storeName;

	/** 所属品牌。 */
	@NotNull(message = "{validation.item.belongBrand}")
	private Long belongBrand;

	/** 类目名 */
	private String brandName;

	/** 适用年龄段。 */
	@NotNull(message = "{validation.item.ageId}")
	private Long ageId;

	/** 年龄段。 */
	private String ageGroup;

	/** 所属后台类目。 */
	@NotNull(message = "{validation.item.belongCategory}")
	private Long belongCategory;

	/** 所属前台类目。 */
	private Long frontendCategory;

	/** 所属类目属性id。 */
	private Long categoryAttrId;

	/** 所属后台类目。 */
	private List<Long> belongCategoryIds;

	/** 图片所属类目属性。 */
	private Long imageField;

	/** 类目名 */
	private String categoryName;

	/** 库存数量。 */
	private long stockNumber;

	/** 累计销量。 */
	private Long salesVolome;

	/** 购买商品数量。 */
	private Long buyNumber;

	/** 商品状态（1 新录入 2 已上架 3 下架 4 违规下架）。 */
	private Integer status;

	/** 选用物流模版。 */
	@NotNull(message = "{validation.item.logisticsId}")
	private Long logisticsId;

	/** 物流模版的体积、重量（体积默认为m3，重量默认为kg）。 */
	private Double weightBulk;

	/** 商品详情。 */
	@NotNull(message = "{validation.item.description}")
	private String description;

	/** 创建时间。默认取值为系统的当前时间。 */
	private Date createdTime;

	/** 开始时间 */
	private Date bTime;

	/** 结束时间 */
	private Date eTime;

	/** 开始时间 */
	private String beganTime;

	/** 结束时间 */
	private String endTime;

	/** 时间。 */
	private String createdTimes;

	/** 图片名称。 */
	private String pictureName;

	/** 根据销售量进行排序。 */
	private Integer sales;

	/** 根据价格进行排序。 */
	private Integer price;

	/** 营销活动的id。 */
	private Long promotionId;

	/** 会员id。 */
	private Long memberId;

	/** 购物车id。 */
	private Long cattId;

	/** 收藏夹id。 */
	private Long favId;

	/** 具体skuId。 */
	private Long skuId;

	/** 商家id。 */
	private Long merchantId;

	/** 商品图片集合。 */
	private List<ItemPictureDto> itemPictureDtos;

	/** 商品属性集合。 */
	private List<GoodsSkuDto> goodsSkuDtos;

	/** 商品后台类目集合。 */
	private List<BackendCategoryDto> backendCategoryDtos;

	/** 商品前台第三级类目。 */
	private List<FrontendCategoryDto> frontendCategoryDtos;

	/** 评价列表。 */
	private List<GoodsSkuCommentDto> goodsSkuCommentDtos;

	/** 所有营销活动 */
	private List<PromoTionGoodsDto> promoTionGoodsDtos = null;

	/** 店铺级单品营销活动。 */
	private List<PromoTionGoodsDto> storePromoTionGoodsDtos = null;

	/** 平台级单品营销活动。 */
	private List<PromoTionGoodsDto> adminPromoTionGoodsDtos = null;

	/** 店铺优惠券。 */
	private List<PromoTionGoodsDto> storeCouponPromoTionGoodsDtos = null;

	/** 平台优惠券。 */
	private List<PromoTionGoodsDto> adminCouponPromoTionGoodsDtos = null;

	/** 店铺包邮。 */
	private List<PromoTionGoodsDto> storePinkagePromoTionGoodsDtos = null;

	/** 平台级活动（满减）。 */
	private List<PromoTionGoodsDto> adminMoneyOffPromoTionGoodsDtos = null;

	/** 店铺级活动（满减）。 */
	private List<PromoTionGoodsDto> storeMoneyOffPromoTionGoodsDtos = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
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

	public Double getBrokerageRatio() {
		return brokerageRatio;
	}

	public void setBrokerageRatio(Double brokerageRatio) {
		this.brokerageRatio = brokerageRatio;
	}

	public Long getSpuId() {
		return spuId;
	}

	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}

	public Double getDefaultPrice() {
		return defaultPrice;
	}

	public void setDefaultPrice(Double defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	public Double getSkuPrice() {
		return skuPrice;
	}

	public void setSkuPrice(Double skuPrice) {
		this.skuPrice = skuPrice;
	}

	public String getSaleFieldValue() {
		return saleFieldValue;
	}

	public void setSaleFieldValue(String saleFieldValue) {
		this.saleFieldValue = saleFieldValue;
	}

	public String getBaseFieldValue() {
		return baseFieldValue;
	}

	public void setBaseFieldValue(String baseFieldValue) {
		this.baseFieldValue = baseFieldValue;
	}

	public String getPriceRangeLeft() {
		return priceRangeLeft;
	}

	public void setPriceRangeLeft(String priceRangeLeft) {
		this.priceRangeLeft = priceRangeLeft;
	}

	public String getPriceRangeRigth() {
		return priceRangeRigth;
	}

	public void setPriceRangeRigth(String priceRangeRigth) {
		this.priceRangeRigth = priceRangeRigth;
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

	public String getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
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

	public Long getCategoryAttrId() {
		return categoryAttrId;
	}

	public void setCategoryAttrId(Long categoryAttrId) {
		this.categoryAttrId = categoryAttrId;
	}

	public List<Long> getBelongCategoryIds() {
		return belongCategoryIds;
	}

	public void setBelongCategoryIds(List<Long> belongCategoryIds) {
		this.belongCategoryIds = belongCategoryIds;
	}

	public Long getImageField() {
		return imageField;
	}

	public void setImageField(Long imageField) {
		this.imageField = imageField;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public long getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(long stockNumber) {
		this.stockNumber = stockNumber;
	}

	public Long getSalesVolome() {
		return salesVolome;
	}

	public void setSalesVolome(Long salesVolome) {
		this.salesVolome = salesVolome;
	}

	public Long getBuyNumber() {
		return buyNumber;
	}

	public void setBuyNumber(Long buyNumber) {
		this.buyNumber = buyNumber;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getbTime() {
		return bTime;
	}

	public void setbTime(Date bTime) {
		this.bTime = bTime;
	}

	public Date geteTime() {
		return eTime;
	}

	public void seteTime(Date eTime) {
		this.eTime = eTime;
	}

	public String getBeganTime() {
		return beganTime;
	}

	public void setBeganTime(String beganTime) {
		this.beganTime = beganTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCreatedTimes() {
		return createdTimes;
	}

	public void setCreatedTimes(String createdTimes) {
		this.createdTimes = createdTimes;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getCattId() {
		return cattId;
	}

	public void setCattId(Long cattId) {
		this.cattId = cattId;
	}

	public Long getFavId() {
		return favId;
	}

	public void setFavId(Long favId) {
		this.favId = favId;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
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

	public List<BackendCategoryDto> getBackendCategoryDtos() {
		return backendCategoryDtos;
	}

	public void setBackendCategoryDtos(List<BackendCategoryDto> backendCategoryDtos) {
		this.backendCategoryDtos = backendCategoryDtos;
	}

	public List<FrontendCategoryDto> getFrontendCategoryDtos() {
		return frontendCategoryDtos;
	}

	public void setFrontendCategoryDtos(List<FrontendCategoryDto> frontendCategoryDtos) {
		this.frontendCategoryDtos = frontendCategoryDtos;
	}

	public List<GoodsSkuCommentDto> getGoodsSkuCommentDtos() {
		return goodsSkuCommentDtos;
	}

	public void setGoodsSkuCommentDtos(List<GoodsSkuCommentDto> goodsSkuCommentDtos) {
		this.goodsSkuCommentDtos = goodsSkuCommentDtos;
	}

	public List<PromoTionGoodsDto> getPromoTionGoodsDtos() {
		return promoTionGoodsDtos;
	}

	public void setPromoTionGoodsDtos(List<PromoTionGoodsDto> promoTionGoodsDtos) {
		this.promoTionGoodsDtos = promoTionGoodsDtos;
	}

	public List<PromoTionGoodsDto> getStorePromoTionGoodsDtos() {
		return storePromoTionGoodsDtos;
	}

	public void setStorePromoTionGoodsDtos(List<PromoTionGoodsDto> storePromoTionGoodsDtos) {
		this.storePromoTionGoodsDtos = storePromoTionGoodsDtos;
	}

	public List<PromoTionGoodsDto> getAdminPromoTionGoodsDtos() {
		return adminPromoTionGoodsDtos;
	}

	public void setAdminPromoTionGoodsDtos(List<PromoTionGoodsDto> adminPromoTionGoodsDtos) {
		this.adminPromoTionGoodsDtos = adminPromoTionGoodsDtos;
	}

	public List<PromoTionGoodsDto> getStoreCouponPromoTionGoodsDtos() {
		return storeCouponPromoTionGoodsDtos;
	}

	public void setStoreCouponPromoTionGoodsDtos(List<PromoTionGoodsDto> storeCouponPromoTionGoodsDtos) {
		this.storeCouponPromoTionGoodsDtos = storeCouponPromoTionGoodsDtos;
	}

	public List<PromoTionGoodsDto> getAdminCouponPromoTionGoodsDtos() {
		return adminCouponPromoTionGoodsDtos;
	}

	public void setAdminCouponPromoTionGoodsDtos(List<PromoTionGoodsDto> adminCouponPromoTionGoodsDtos) {
		this.adminCouponPromoTionGoodsDtos = adminCouponPromoTionGoodsDtos;
	}

	public List<PromoTionGoodsDto> getStorePinkagePromoTionGoodsDtos() {
		return storePinkagePromoTionGoodsDtos;
	}

	public void setStorePinkagePromoTionGoodsDtos(List<PromoTionGoodsDto> storePinkagePromoTionGoodsDtos) {
		this.storePinkagePromoTionGoodsDtos = storePinkagePromoTionGoodsDtos;
	}

	public List<PromoTionGoodsDto> getAdminMoneyOffPromoTionGoodsDtos() {
		return adminMoneyOffPromoTionGoodsDtos;
	}

	public void setAdminMoneyOffPromoTionGoodsDtos(List<PromoTionGoodsDto> adminMoneyOffPromoTionGoodsDtos) {
		this.adminMoneyOffPromoTionGoodsDtos = adminMoneyOffPromoTionGoodsDtos;
	}

	public List<PromoTionGoodsDto> getStoreMoneyOffPromoTionGoodsDtos() {
		return storeMoneyOffPromoTionGoodsDtos;
	}

	public void setStoreMoneyOffPromoTionGoodsDtos(List<PromoTionGoodsDto> storeMoneyOffPromoTionGoodsDtos) {
		this.storeMoneyOffPromoTionGoodsDtos = storeMoneyOffPromoTionGoodsDtos;
	}

	public int compareTo(ItemDto i) {
		if (i.salesVolome != null) {
			if (this.salesVolome > i.salesVolome) {
				return 1;
			} else if (this.salesVolome < i.salesVolome) {
				return -1;
			}
		}
		return 0;
	}

}
