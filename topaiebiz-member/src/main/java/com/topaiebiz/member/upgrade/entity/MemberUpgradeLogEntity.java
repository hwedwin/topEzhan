package com.topaiebiz.member.upgrade.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * 
 * Description  会员成长分完成记录表 
 * 
 * 
 * Author Scott
 *    
 * Date 2017年8月23日 下午7:49:46 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@TableName("t_mem_member_upgrade_log")
public class MemberUpgradeLogEntity extends BaseBizEntity<Long> {
	
	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = 3884188393659565449L;
	
	/*** 会员编号。*/
	private Long memberId;
	
	/*** 对应营销活动。*/
	private Long promotionId;
	
	/*** 会员等级成长分规则。*/
	private Long upgradeRuleId;
	
	/*** 获取成长分。*/
	private Long upgradeScore;
	
	/*** 完成时间。*/
	private Date finishTime;
	
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

	public Long getMemberId() {
		return memberId;
	}
	
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	public Long getUpgradeRuleId() {
		return upgradeRuleId;
	}

	public void setUpgradeRuleId(Long upgradeRuleId) {
		this.upgradeRuleId = upgradeRuleId;
	}

	public Long getUpgradeScore() {
		return upgradeScore;
	}

	public void setUpgradeScore(Long upgradeScore) {
		this.upgradeScore = upgradeScore;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

}
