package com.topaiebiz.goods.sku.dto;

import java.util.Date;

/**
 * Description 单品折扣活动dto  
 * 
 * Author Hedda 
 *    
 * Date 2017年11月16日 下午9:00:47 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
public class PromoTionGoodsDto {

	/** 主键id。*/
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
	 * 优惠赠品
	 */
	private Long giveProduct;

	/**
	 * 平台补贴
	 */
	private Double platformPrice;

	
	/**
	 * 活动发起者
	 */
	private Long sponsorType;

	/**
	 * 营销级别
	 */
	private Long gradeId;

	/**
	 * 营销类型
	 */
	private Long typeId;

	/**
	 * 活动名称
	 */
	private String name;

	/**
	 * 活动开始时间
	 */
	private Date startTime;

	/**
	 * 活动结束时间
	 */
	private Date endTime;

	/**
	 * 活动说明
	 */
	private String description;
	
	/**
	 * 是否指定商品可用
	 */
	private Integer isGoodsArea;

	/**
	 * 限制会员类型
	 */
	private Long memberTypeId;

	/**
	 * 限制会员级别
	 */
	private Long memberGradeId;

	/**
	 * 条件类型
	 */
	private Integer condType;

	/**
	 * 条件值
	 */
	private Double condValue;

	/**
	 * 优惠类型
	 */
	private Integer discountType;

	/**
	 * 优惠值
	 */
	private Double discountValue;

	/**
	 * 发放数量
	 */
	private Long amount;

	/**
	 * 已领数量
	 */
	private Long usedAmount;

	/**
	 * 限制数额
	 */
	private Long confineAmount;

	/**
	 * 平台补贴比例
	 */
	private Double platformRatio;
	
	/** 打折*/
	private String discount;
	
	/** 优惠券会员是否已领取(0-未领取,1-已领取)。*/
	private Integer usedState;

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

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
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

	public Long getSponsorType() {
		return sponsorType;
	}

	public void setSponsorType(Long sponsorType) {
		this.sponsorType = sponsorType;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIsGoodsArea() {
		return isGoodsArea;
	}

	public void setIsGoodsArea(Integer isGoodsArea) {
		this.isGoodsArea = isGoodsArea;
	}

	public Long getMemberTypeId() {
		return memberTypeId;
	}

	public void setMemberTypeId(Long memberTypeId) {
		this.memberTypeId = memberTypeId;
	}

	public Long getMemberGradeId() {
		return memberGradeId;
	}

	public void setMemberGradeId(Long memberGradeId) {
		this.memberGradeId = memberGradeId;
	}

	public Integer getCondType() {
		return condType;
	}

	public void setCondType(Integer condType) {
		this.condType = condType;
	}

	public Double getCondValue() {
		return condValue;
	}

	public void setCondValue(Double condValue) {
		this.condValue = condValue;
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

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getUsedAmount() {
		return usedAmount;
	}

	public void setUsedAmount(Long usedAmount) {
		this.usedAmount = usedAmount;
	}

	public Long getConfineAmount() {
		return confineAmount;
	}

	public void setConfineAmount(Long confineAmount) {
		this.confineAmount = confineAmount;
	}

	public Double getPlatformRatio() {
		return platformRatio;
	}

	public void setPlatformRatio(Double platformRatio) {
		this.platformRatio = platformRatio;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public Integer getUsedState() {
		return usedState;
	}

	public void setUsedState(Integer usedState) {
		this.usedState = usedState;
	}
	
	

}
