package com.topaiebiz.member.grade.dto;

import java.util.Date;

/**
 * 
 * Description： 会员等级特权表,存储会员等级对应的特权。
 * 
 * 
 * Author Scott.Yang
 *    
 * Date 2017年9月25日 下午9:03:24 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class MemberGradePrivilegeDto {
	
	/** 会员等级特权的全局唯一主键标识符*/
	private Long id;
	
	/*** 会员等级。*/
	private Long gradeId;
	
	/*** 特权类型 （1 打折，2 赠送 ）。*/
	private Integer privilegeType;
	
	/*** 是否参与结算(1 参与，0 不参与)。*/
	private Integer isSettle;
	
	/*** 打折，优惠，需要填写。*/
	private double privilegeValue;
	
	/** 所属店铺。*/
	private Long storeId;
	
	/*** 结算比例，以小数形式表示。*/
	private Double settleRatio;
	
	/*** 结算比例，以小数形式表示。*/
	private String memo;
	
	/*** 结算比例，以小数形式表示。*/
	private Date createdTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGradeId() {
		return gradeId;
	}

	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}

	public Integer getPrivilegeType() {
		return privilegeType;
	}

	public void setPrivilegeType(Integer privilegeType) {
		this.privilegeType = privilegeType;
	}
	
	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Integer getIsSettle() {
		return isSettle;
	}

	public void setIsSettle(Integer isSettle) {
		this.isSettle = isSettle;
	}

	public double getPrivilegeValue() {
		return privilegeValue;
	}

	public void setPrivilegeValue(double privilegeValue) {
		this.privilegeValue = privilegeValue;
	}

	public Double getSettleRatio() {
		return settleRatio;
	}

	public void setSettleRatio(Double settleRatio) {
		this.settleRatio = settleRatio;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

}
