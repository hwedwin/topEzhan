package com.topaiebiz.member.point.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * 
 * Description： 会员积分获取记录  
 * 
 * 
 * Author Scott.Yang
 *    
 * Date 2017年9月28日 下午3:10:56 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_mem_member_point_log")
public class MemberPointLogEntity extends BaseBizEntity<Long>{
	
	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = -5719053409251769064L;

	/** 会员编号*/
	private Long memberId;
	
	/** 获取类型*/
	private Long gainType;
	
	/** 订单编号*/
	private Long orderId;
	
	/** 消费金额。*/
	private Double costMoney;
	
	/** 获取积分。*/
	private Long gainScore;
	
	/** 拉取的时候记录。*/
	private Long scoreSource;

	/** 备注*/
	private String memo;

	@TableField(exist=false)
	/** 最后修改人编号。取值为最后修改人的全局唯一主键标识符。 */
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

	public Long getGainType() {
		return gainType;
	}

	public void setGainType(Long gainType) {
		this.gainType = gainType;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Double getCostMoney() {
		return costMoney;
	}

	public void setCostMoney(Double costMoney) {
		this.costMoney = costMoney;
	}

	public Long getGainScore() {
		return gainScore;
	}

	public void setGainScore(Long gainScore) {
		this.gainScore = gainScore;
	}

	public Long getScoreSource() {
		return scoreSource;
	}

	public void setScoreSource(Long scoreSource) {
		this.scoreSource = scoreSource;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
