package com.topaiebiz.promotion.mgmt.dto;

import java.util.Date;

/**
 * 
 * Description 营销活动商家报名表
 * 
 * 
 * Author Joe 
 *    
 * Date 2017年12月6日 下午8:42:09 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class PromotionEntryDto {

	/**
	 * ID
	 */
	private Long id;

	/**
	 * 营销活动ID
	 */
	private Long promotionId;

	/**
	 * 商家ID
	 */
	private Long storeId;

	/**
	 * 状态
	 */
	private Integer state;

	/**
	 * 备注
	 */
	private String memo;
	
	/**
	 * 店铺名称
	 */
	private String storeName;
	
	/**
	 * 店铺等级名称
	 */
	private String storeGradeName;
	
	/**
	 * 店铺等级id
	 */
	private Long storeGradeId;
	
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
	 * 报名商品数量
	 */
	private Integer enrolGoodsNum;

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

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreGradeName() {
		return storeGradeName;
	}

	public void setStoreGradeName(String storeGradeName) {
		this.storeGradeName = storeGradeName;
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

	public Long getStoreGradeId() {
		return storeGradeId;
	}

	public void setStoreGradeId(Long storeGradeId) {
		this.storeGradeId = storeGradeId;
	}

	public Integer getEnrolGoodsNum() {
		return enrolGoodsNum;
	}

	public void setEnrolGoodsNum(Integer enrolGoodsNum) {
		this.enrolGoodsNum = enrolGoodsNum;
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
	
}
