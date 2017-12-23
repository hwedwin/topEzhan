package com.topaiebiz.promotion.mgmt.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * 
 * Description 秒杀活动DTO
 * 
 * 
 * Author Joe
 * 
 * Date 2017年9月30日 下午1:33:51
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class PromotionSingleDto {

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
	 * 活动开始时间（接收参数）
	 */
	@NotNull(message = "{validation.promotion.startTime}")
	private String promotionStart;

	/**
	 * 活动结束时间（接收参数）
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
	@NotNull(message = "{validation.promotion.description}")
	private String description;

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

	/**
	 * 备注
	 */
	private String memo;

	/**
	 * 报名开始时间
	 */
	private Date applyStartTime;

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

}
