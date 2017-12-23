package com.topaiebiz.promotion.mgmt.dto;

import java.util.Date;

/**
 * 
 * Description 营销活动商品 DTO
 * 
 * 
 * Author Joe
 * 
 * Date 2017年10月9日 下午4:08:20
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class PromotionGoodsDto implements Comparable<PromotionGoodsDto> {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 营销活动
	 */
	private Long promotionId;

	/**
	 * 所属店铺
	 */
	private Long storeId;

	/**
	 * 所属商品
	 */
	private Long itemId;

	/**
	 * 商品SKU
	 */
	private Long goodsSkuId;

	/**
	 * 原有库存
	 */
	private Long repertoryNum;

	/**
	 * 活动数量
	 */
	private Long promotionNum;

	/**
	 * 活动价格
	 */
	private Double promotionPrice;

	/**
	 * ID限购
	 */
	private Long confineNum;

	/**
	 * 优惠类型
	 */
	private Integer discountType;

	/**
	 * 优惠值
	 */
	private Double discountValue;

	/**
	 * 优惠赠品
	 */
	private Long giveProduct;

	/**
	 * 平台补贴
	 */
	private Double platformPrice;

	/**
	 * 状态
	 */
	private Integer state;

	/**
	 * 备注
	 */
	private String memo;

	/**
	 * 优惠金额
	 */
	private Double preferentialAmount;

	/**
	 * 商品原价
	 */
	private Double goodsPrice;

	/**
	 * 营销活动状态
	 */
	private Integer marketState;

	/**
	 * 商品图片
	 */
	private String saleImage;

	/**
	 * item编码
	 */
	private String itemCode;

	/**
	 * item名称
	 */
	private String goodsName;

	/**
	 * 营销活动结束时间
	 */
	private Date promotionEndTime;

	/**
	 * 买货比例
	 */
	private Double sellGoodsQuantity;

	/**
	 * 每件商品的销量
	 */
	private Long salesVolome;

	/**
	 * 商品所属活动名称
	 */
	private String promotionName;

	/**
	 * 商品所属活动级别
	 */
	private Long gradeId;

	/**
	 * 商品所属活动类型
	 */
	private Long typeId;

	/**
	 * 销售属性集合
	 */
	private String saleFieldValue;
	
	/**
	 * 所属活动类型
	 */
	private Long promotionTypeId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getGoodsSkuId() {
		return goodsSkuId;
	}

	public void setGoodsSkuId(Long goodsSkuId) {
		this.goodsSkuId = goodsSkuId;
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

	public Long getConfineNum() {
		return confineNum;
	}

	public void setConfineNum(Long confineNum) {
		this.confineNum = confineNum;
	}

	public Integer getDiscountType() {
		return discountType;
	}

	public void setDiscountType(Integer discountType) {
		this.discountType = discountType;
	}

	public Double getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(Double discountValue) {
		this.discountValue = discountValue;
	}

	public Long getGiveProduct() {
		return giveProduct;
	}

	public void setGiveProduct(Long giveProduct) {
		this.giveProduct = giveProduct;
	}

	public Double getPlatformPrice() {
		return platformPrice;
	}

	public void setPlatformPrice(Double platformPrice) {
		this.platformPrice = platformPrice;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Double getPreferentialAmount() {
		return preferentialAmount;
	}

	public void setPreferentialAmount(Double preferentialAmount) {
		this.preferentialAmount = preferentialAmount;
	}

	public Double getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public Integer getMarketState() {
		return marketState;
	}

	public void setMarketState(Integer marketState) {
		this.marketState = marketState;
	}

	public String getSaleImage() {
		return saleImage;
	}

	public void setSaleImage(String saleImage) {
		this.saleImage = saleImage;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Date getPromotionEndTime() {
		return promotionEndTime;
	}

	public void setPromotionEndTime(Date promotionEndTime) {
		this.promotionEndTime = promotionEndTime;
	}

	public Double getSellGoodsQuantity() {
		return sellGoodsQuantity;
	}

	public void setSellGoodsQuantity(Double sellGoodsQuantity) {
		this.sellGoodsQuantity = sellGoodsQuantity;
	}

	public Long getSalesVolome() {
		return salesVolome;
	}

	public void setSalesVolome(Long salesVolome) {
		this.salesVolome = salesVolome;
	}

	public String getPromotionName() {
		return promotionName;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	public Long getGradeId() {
		return gradeId;
	}

	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getSaleFieldValue() {
		return saleFieldValue;
	}

	public void setSaleFieldValue(String saleFieldValue) {
		this.saleFieldValue = saleFieldValue;
	}

	public Long getPromotionTypeId() {
		return promotionTypeId;
	}

	public void setPromotionTypeId(Long promotionTypeId) {
		this.promotionTypeId = promotionTypeId;
	}

	@Override
	public int compareTo(PromotionGoodsDto i) {
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
