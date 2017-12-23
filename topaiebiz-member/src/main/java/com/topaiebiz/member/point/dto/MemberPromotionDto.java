package com.topaiebiz.member.point.dto;

import java.util.Date;

/**
 * 
 * Description： 会员优惠券Dto
 * 
 * 
 * Author Scott.Yang
 *    
 * Date 2017年10月31日 下午8:29:21 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class MemberPromotionDto {
	
		/** 营销活动id*/
		private Long promotionId;
		
		/** 活动名称。*/
		private String name;
		
		/** 活动开始时间。*/
		private Date startTime;
		
		/** 活动结束时间。*/
		private Date endTime;
		
		/** 活动状态。*/
		private Integer marketState;
		
		/** 活动说明。*/
		private String description;
		
		/** 条件类型（1.满   2.每满）。*/
		private Integer condType;
		
		/** 条件值。具体多少钱。*/
		private Double condValue;
		
		/** 优惠值。折扣直接写小数。减价写价钱  包邮则不写。*/
		private Double discountValue;

		public Long getPromotionId() {
			return promotionId;
		}

		public void setPromotionId(Long promotionId) {
			this.promotionId = promotionId;
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

		public Integer getMarketState() {
			return marketState;
		}

		public void setMarketState(Integer marketState) {
			this.marketState = marketState;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
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

		public Double getDiscountValue() {
			return discountValue;
		}

		public void setDiscountValue(Double discountValue) {
			this.discountValue = discountValue;
		}
		
		
}
