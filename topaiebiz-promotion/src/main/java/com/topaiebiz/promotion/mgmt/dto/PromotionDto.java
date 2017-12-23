package com.topaiebiz.promotion.mgmt.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * 
 * Description： 营销活动信息表DTO
 * 
 * 
 * Author Joe
 * 
 * Date 2017年9月25日 下午7:56:46
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class PromotionDto {

	/**
	 * ID
	 */
	private Long id;

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
	@NotNull(message = "{validation.promotion.name}")
	@Length(max = 15, message = "{validation.promotion.nameLength}")
	private String name;

	/**
	 * 活动开始时间
	 */
	private Date startTime;

	/**
	 * 活动开始时间
	 */
	@NotNull(message = "{validation.promotion.startTime}")
	private String promotionStart;

	/**
	 * 活动结束时间
	 */
	@NotNull(message = "{validation.promotion.endTime}")
	private String promotionEnd;

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
	@NotNull(message = "{validation.promotion.condType}")
	private Integer condType;

	/**
	 * 条件值
	 */
	@NotNull(message = "{validation.promotion.condValue}")
	private Double condValue;

	/**
	 * 优惠类型
	 */
	private Integer discountType;

	/**
	 * 优惠值
	 */
	@NotNull(message = "{validation.promotion.discountValue}")
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

	/**
	 * 备注
	 */
	private String memo;

	/**
	 * 报名标题
	 */
	private String applyTitle;
	
	/**
	 * 报名开始时间
	 */
	private Date applyStartTime;
	
	/**
	 * 报名开始时间
	 */
	private String promotionEnrolStart;

	/**
	 * 报名结束时间
	 */
	private String promotionEnrolEnd;
	
	/**
	 * 报名结束时间
	 */
	private Date applyEndTime;

	/**
	 * 报名说明
	 */
	private String applyDesc;

	/**
	 * 店铺等级要求
	 */
	private Long storeGrade;

	/**
	 * 最少报名商品数
	 */
	private Long mixProductNum;

	/**
	 * 最大报名商品数
	 */
	private Long maxProductNum;

	/**
	 * 报名状态
	 */
	private Integer state;

	/**
	 * 活动状态
	 */
	private Integer marketState;

	/**
	 * 优惠总额
	 */
	private Double totalDiscount;

	/**
	 * 已使用数
	 */
	private Integer usedQuantity;

	/**
	 * 商品skuID
	 */
	private Long goodsSkuId;

	/**
	 * 下单商品数量
	 */
	private Long goodsNum;

	/**
	 * 下单总金额
	 */
	private Double totalAmount;

	/**
	 * 支付买家数
	 */
	private Integer buyersNum;

	/**
	 * 下单量
	 */
	private Long placeOrderNum;

	/**
	 * 客单价
	 */
	private Double perCustomerTransaction;

	/**
	 * 创建时间
	 */
	private Date createdTime;

	/**
	 * 优惠券会员是否已领取(0-未领取,1-已领取)
	 */
	private Integer usedState;
	
	/**
	 * 商家报名数量
	 */
	private Integer storeEnrolNum;
	
	/**
	 * 已审核商家数量
	 */
	private Integer auditStoreNum;
	
	/**
	 * 店铺等级名称
	 */
	private String storeGradeName;
	
	/**
	 * 是否可报名
	 */
	private Integer whetherEnrol;
	
	/**
	 * 报名店铺
	 */
	private Long storeId;
	
	/**
	 * 报名店铺审核状态
	 */
	private Integer auditState;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getApplyStartTime() {
		return applyStartTime;
	}

	public void setApplyStartTime(Date applyStartTime) {
		this.applyStartTime = applyStartTime;
	}

	public Date getApplyEndTime() {
		return applyEndTime;
	}

	public void setApplyEndTime(Date applyEndTime) {
		this.applyEndTime = applyEndTime;
	}

	public String getApplyDesc() {
		return applyDesc;
	}

	public void setApplyDesc(String applyDesc) {
		this.applyDesc = applyDesc;
	}

	public Long getStoreGrade() {
		return storeGrade;
	}

	public void setStoreGrade(Long storeGrade) {
		this.storeGrade = storeGrade;
	}

	public Long getMixProductNum() {
		return mixProductNum;
	}

	public void setMixProductNum(Long mixProductNum) {
		this.mixProductNum = mixProductNum;
	}

	public Long getMaxProductNum() {
		return maxProductNum;
	}

	public void setMaxProductNum(Long maxProductNum) {
		this.maxProductNum = maxProductNum;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getMarketState() {
		return marketState;
	}

	public void setMarketState(Integer marketState) {
		this.marketState = marketState;
	}

	public Double getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(Double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public Integer getUsedQuantity() {
		return usedQuantity;
	}

	public void setUsedQuantity(Integer usedQuantity) {
		this.usedQuantity = usedQuantity;
	}

	public Long getGoodsSkuId() {
		return goodsSkuId;
	}

	public void setGoodsSkuId(Long goodsSkuId) {
		this.goodsSkuId = goodsSkuId;
	}

	public Long getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Long goodsNum) {
		this.goodsNum = goodsNum;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getBuyersNum() {
		return buyersNum;
	}

	public void setBuyersNum(Integer buyersNum) {
		this.buyersNum = buyersNum;
	}

	public Long getPlaceOrderNum() {
		return placeOrderNum;
	}

	public void setPlaceOrderNum(Long placeOrderNum) {
		this.placeOrderNum = placeOrderNum;
	}

	public Double getPerCustomerTransaction() {
		return perCustomerTransaction;
	}

	public void setPerCustomerTransaction(Double perCustomerTransaction) {
		this.perCustomerTransaction = perCustomerTransaction;
	}

	public Integer getIsGoodsArea() {
		return isGoodsArea;
	}

	public void setIsGoodsArea(Integer isGoodsArea) {
		this.isGoodsArea = isGoodsArea;
	}

	public String getPromotionStart() {
		return promotionStart;
	}

	public void setPromotionStart(String promotionStart) {
		this.promotionStart = promotionStart;
	}

	public String getPromotionEnd() {
		return promotionEnd;
	}

	public void setPromotionEnd(String promotionEnd) {
		this.promotionEnd = promotionEnd;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getUsedState() {
		return usedState;
	}

	public void setUsedState(Integer usedState) {
		this.usedState = usedState;
	}

	public Integer getStoreEnrolNum() {
		return storeEnrolNum;
	}

	public void setStoreEnrolNum(Integer storeEnrolNum) {
		this.storeEnrolNum = storeEnrolNum;
	}

	public Integer getAuditStoreNum() {
		return auditStoreNum;
	}

	public void setAuditStoreNum(Integer auditStoreNum) {
		this.auditStoreNum = auditStoreNum;
	}

	public String getStoreGradeName() {
		return storeGradeName;
	}

	public void setStoreGradeName(String storeGradeName) {
		this.storeGradeName = storeGradeName;
	}

	public String getApplyTitle() {
		return applyTitle;
	}

	public void setApplyTitle(String applyTitle) {
		this.applyTitle = applyTitle;
	}

	public Integer getWhetherEnrol() {
		return whetherEnrol;
	}

	public void setWhetherEnrol(Integer whetherEnrol) {
		this.whetherEnrol = whetherEnrol;
	}

	public String getPromotionEnrolStart() {
		return promotionEnrolStart;
	}

	public void setPromotionEnrolStart(String promotionEnrolStart) {
		this.promotionEnrolStart = promotionEnrolStart;
	}

	public String getPromotionEnrolEnd() {
		return promotionEnrolEnd;
	}

	public void setPromotionEnrolEnd(String promotionEnrolEnd) {
		this.promotionEnrolEnd = promotionEnrolEnd;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Integer getAuditState() {
		return auditState;
	}

	public void setAuditState(Integer auditState) {
		this.auditState = auditState;
	}

}
