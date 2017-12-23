package com.topaiebiz.member.point.dto;

import java.util.Date;

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
public class MemberPointLogDto {
	
	/** 会员等级的全局唯一主键标识符。本字段是业务无关性的，仅用于关联。*/
	private Long id;

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
	
	/** 创建时间。默认取值为系统的当前时间。 */
	private Date createdTime;
	
	/** 积分名称*/
	private String name;
	
	/** 退还积分值*/
	private Integer integral;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	
}
