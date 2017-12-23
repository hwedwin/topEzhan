package com.topaiebiz.settlement.member.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * Description 会员提现记录dto。 
 * 
 * Author Hedda 
 *    
 * Date 2017年11月13日 下午7:56:15 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
public class MemberWithdrawalLogDto{

	/** 全局唯一主键标识符。支持泛型，具体类型由传入的类型指定。 */
	private Long id;
	
	/** 会员编号。*/
	@NotNull(message = "{validation.memberWithdrawalLog.memberId}")
	private Long memberId;
	
	/** 提现金额。*/
	@NotNull(message = "{validation.memberWithdrawalLog.withdrawalSum}")
	private Double withdrawalSum;
	
	/** 提现金额时间。*/
	private Date withdrawalTime;
	
	/** 提现账户类型。*/
	private Integer accountType;
	
	/** 账户号。*/
	private Integer accountNum;
	
	/** 提现状态。*/
	private Integer status;
	
	/** 备注。*/
	private String memo;

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

	public Double getWithdrawalSum() {
		return withdrawalSum;
	}

	public void setWithdrawalSum(Double withdrawalSum) {
		this.withdrawalSum = withdrawalSum;
	}

	public Date getWithdrawalTime() {
		return withdrawalTime;
	}

	public void setWithdrawalTime(Date withdrawalTime) {
		this.withdrawalTime = withdrawalTime;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Integer getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(Integer accountNum) {
		this.accountNum = accountNum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
