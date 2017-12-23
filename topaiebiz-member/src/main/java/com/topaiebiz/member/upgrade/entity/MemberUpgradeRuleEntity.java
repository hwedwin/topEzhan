package com.topaiebiz.member.upgrade.entity;


import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * 
 * Description 会员的成长规则,一个规则相当于一类任务,有达成条件,有得分等.
 * 
 * 
 * Author Scott
 *    
 * Date 2017年8月23日 下午7:49:37 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@TableName("t_mem_member_upgrade_rule")
public class MemberUpgradeRuleEntity extends BaseBizEntity<Long> {
	
	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = 7609392085603197269L;

	/*** 会员等级成长规则名称。*/
	private String name;
	
	/** 所属店铺。*/
	private Long storeId;
	
	/*** 获取方式类型（1，购买**钱，2，拉取多少会员 ）。*/
	private Integer type;
	
	/*** 获取需要完成的值。例如花多少前，拉多少人。*/
	private Long needValue;
	
	/*** 获得成长值分.完成一次获多少分。*/
	private Long upgradeScore;
	
	/*** 是否可以重复完成。是完成一次 还是可以重复完成(1 重复  0 不可重复)。*/
	private Integer repeatState;
	
	/*** 可以完成这个的会员类型。*/
	private Long memberTypeId;
	
	/*** 可以完成这个的会员等级。*/
	private Long memberGradeId;
	
	/** 备注*/
	private String memo;

	@TableField(exist=false)
	/** 最后修改人编号。取值为最后修改人的全局唯一主键标识符。 */
	private Long lastModifierId;

	/** 最后修改时间。默认取值为null，当发生修改时取系统的当前时间。 */
	@TableField(exist=false)
	private Date lastModifiedTime;
	
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
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

	public Long getUpgradeScore() {
		return upgradeScore;
	}

	public void setUpgradeScore(Long upgradeScore) {
		this.upgradeScore = upgradeScore;
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



	
	
}
