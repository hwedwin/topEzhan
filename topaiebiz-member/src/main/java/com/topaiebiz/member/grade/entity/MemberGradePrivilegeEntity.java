package com.topaiebiz.member.grade.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * Description 会员等级特权表
 * 
 * 
 * Author Scott
 *    
 * Date 2017年8月23日 下午7:49:04 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@TableName("t_mem_member_grade_privilege")
public class MemberGradePrivilegeEntity extends BaseBizEntity<Long> {
	
	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = -7218867780298682018L;

	/*** 会员等级。*/
	private Long gradeId;
	
	/*** 特权类型 （1 打折，2 赠送 ）。*/
	private Integer privilegeType;
	
	/** 所属店铺。*/
	private Long storeId;
	
	/*** 是否参与结算(1 参与，0 不参与)。*/
	private Integer isSettle;
	
	/*** 打折，优惠，需要填写。*/
	private double privilegeValue;
	
	/*** 结算比例，以小数形式表示。*/
	private Double settleRatio;

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

}