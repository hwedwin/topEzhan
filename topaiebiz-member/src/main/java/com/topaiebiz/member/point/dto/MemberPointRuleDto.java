package com.topaiebiz.member.point.dto;


/**
 * 
 * Description 会员获取积分的规则表 
 * 
 * 
 * Author Scott
 *    
 * Date 2017年8月29日 上午11:07:03 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

public class MemberPointRuleDto {
	
	/** 会员等级的全局唯一主键标识符。本字段是业务无关性的，仅用于关联。*/
	private Long id;
	
	/*** 规则名称*/
	private String name;
	
	/*** 获取方式类型（1，购买**钱，2，拉取多少会员 暂定,3）*/
	private Integer type;
	
	/*** 获取需要完成的值。例如花多少前，拉多少人*/
	private Long needValue;
	
	/*** 获得积分.完成一次获多少分*/
	private Long value;
	
	/*** 是否可以重复完成。是完成一次 还是可以重复完成(1 重复  0 不可重复)*/
	private Integer repeatState;
	
	/*** 可以完成这个的会员类型*/
	private Long memberTypeId;
	
	/*** 可以完成这个的会员等级*/
	private Long memberGradeId;

	/** 备注*/
	private String memo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getNeedValue() {
		return needValue;
	}

	public void setNeedValue(Long needValue) {
		this.needValue = needValue;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public Integer getRepeatState() {
		return repeatState;
	}

	public void setRepeatState(Integer repeatState) {
		this.repeatState = repeatState;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
