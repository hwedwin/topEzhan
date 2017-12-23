package com.topaiebiz.settlement.member.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * Description：会员结算Entity。  
 * 
 * Author   Harry
 *    
 * Date 2017年10月31日 下午4:37:59 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_set_member_settlement")
public class MemberSettlementEntity  extends BaseBizEntity<Long> implements Serializable{
	
	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = -3938475767100443300L;
	
	/**会员编号。*/
	private Long memberId;
	
	/**可提现余额。*/
	private Double allowWithdrawalSum;
	
	/**不可体现余额。*/
	private Double notWithdrawalSum;
	
	/**已提现余额。*/
	private Double yetWithdrawalSum;
	
	/**总获得分销金额。*/
	private Double totalSum;
	
	/**账户状态。1 已出账     2 申请提现  3 未出账*/
	private Integer status;
	
	/**备注*/
	private String memo;
	
	/**平台佣金*/
	private Double platformCommission;
	
	/**支付渠道佣金*/
	private Double paymentChannelCommission;
	
	public Double getPlatformCommission() {
		return platformCommission;
	}

	public void setPlatformCommission(Double platformCommission) {
		this.platformCommission = platformCommission;
	}

	public Double getPaymentChannelCommission() {
		return paymentChannelCommission;
	}

	public void setPaymentChannelCommission(Double paymentChannelCommission) {
		this.paymentChannelCommission = paymentChannelCommission;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Double getAllowWithdrawalSum() {
		return allowWithdrawalSum;
	}

	public void setAllowWithdrawalSum(Double allowWithdrawalSum) {
		this.allowWithdrawalSum = allowWithdrawalSum;
	}

	public Double getNotWithdrawalSum() {
		return notWithdrawalSum;
	}

	public void setNotWithdrawalSum(Double notWithdrawalSum) {
		this.notWithdrawalSum = notWithdrawalSum;
	}

	public Double getYetWithdrawalSum() {
		return yetWithdrawalSum;
	}

	public void setYetWithdrawalSum(Double yetWithdrawalSum) {
		this.yetWithdrawalSum = yetWithdrawalSum;
	}

	public Double getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(Double totalSum) {
		this.totalSum = totalSum;
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
