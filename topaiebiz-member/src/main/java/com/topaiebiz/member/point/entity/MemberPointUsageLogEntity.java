package com.topaiebiz.member.point.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;
/**
 * 
 * Description： 会员积分使用记录  
 * 
 * 
 * Author Scott.Yang
 *    
 * Date 2017年10月16日 下午1:30:17 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_mem_member_point_usage_log")
public class MemberPointUsageLogEntity extends BaseBizEntity<Long> {
	
	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = 1139105636389770310L;
	
	/** 会员编号*/
	private Long memberId;
	
	/** 订单编号*/
	private Long orderId;
	
	/** 使用积分*/
	private Long usageScore;
	
	/** 抵扣金额*/
	private Double deductibleAmount;
	
	/** 备注*/
	private String memo;
	
	/** 最后修改人编号。取值为最后修改人的全局唯一主键标识符。 */
	@TableField(exist=false)
	private Long lastModifierId;

	/** 最后修改时间。默认取值为null，当发生修改时取系统的当前时间。 */
	@TableField(exist=false)
	private Date lastModifiedTime;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getUsageScore() {
		return usageScore;
	}

	public void setUsageScore(Long usageScore) {
		this.usageScore = usageScore;
	}

	public Double getDeductibleAmount() {
		return deductibleAmount;
	}

	public void setDeductibleAmount(Double deductibleAmount) {
		this.deductibleAmount = deductibleAmount;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	
}
