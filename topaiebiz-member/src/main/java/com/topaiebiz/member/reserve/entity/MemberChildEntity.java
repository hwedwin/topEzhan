package com.topaiebiz.member.reserve.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * 
 * 
 * Description 会员孩子信息表
 * 
 * 
 * Author Scott
 * 
 * Date 2017年8月23日 下午7:51:00
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@TableName("t_mem_member_child")
public class MemberChildEntity extends BaseBizEntity<Long> {


	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = 1404272029621201866L;

	/*** 会员编号。 */
	private Long memberId;

	/*** 宝宝生日。 */
	private Date babyBirthday;

	/*** 宝宝性别 */
	private Integer babyGender;

	/*** 与宝宝关系（枚举值1 父子，2父女 3 母子 4 母女）。 */
	private Integer babyRelations;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Date getBabyBirthday() {
		return babyBirthday;
	}

	public void setBabyBirthday(Date babyBirthday) {
		this.babyBirthday = babyBirthday;
	}

	public Integer getBabyGender() {
		return babyGender;
	}

	public void setBabyGender(Integer babyGender) {
		this.babyGender = babyGender;
	}

	public Integer getBabyRelations() {
		return babyRelations;
	}

	public void setBabyRelations(Integer babyRelations) {
		this.babyRelations = babyRelations;
	}

	
}
