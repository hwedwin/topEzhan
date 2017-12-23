package com.topaiebiz.promotion.mgmt.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * 
 * Description： 活动优惠码表
 * 
 * 
 * Author Joe
 * 
 * Date 2017年9月22日 下午1:10:00
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_pro_promotion_code")
public class PromotionCodeEntity extends BaseBizEntity<Long> {

	private static final long serialVersionUID = 992035467082644325L;

	/**
	 * id
	 */
	private Long id;

	/**
	 * 所属营销活动
	 */
	private Long promotionId;

	/**
	 * 优惠码
	 */
	private String promotionCode;

	/**
	 * 使用时间
	 */
	private String usageTime;

	/**
	 * 使用会员
	 */
	private Long memberId;

	/**
	 * 备注
	 */
	private String memo;

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

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public String getUsageTime() {
		return usageTime;
	}

	public void setUsageTime(String usageTime) {
		this.usageTime = usageTime;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
