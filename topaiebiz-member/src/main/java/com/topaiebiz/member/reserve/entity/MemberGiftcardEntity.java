package com.topaiebiz.member.reserve.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;
/**
 * 
 * Description 会员美礼卡，存储会员拥有的美礼卡。  
 * 
 * 
 * Author Scott
 *    
 * Date 2017年9月12日 上午10:41:52 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_mem_member_giftcard")
public class MemberGiftcardEntity extends BaseBizEntity<Long> {
	
	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = 6773459809602003563L;

	/** 会员编号。*/
	private Long memberId;
	
	/** 美礼卡余额。*/
	private double cardBalance;
	
	/** 获取时间。*/
	private Date receiverTime;
	
	/** 是否可以使用（1可以，0不可以）。*/
	private Integer state;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public double getCardBalance() {
		return cardBalance;
	}

	public void setCardBalance(double cardBalance) {
		this.cardBalance = cardBalance;
	}

	public Date getReceiverTime() {
		return receiverTime;
	}

	public void setReceiverTime(Date receiverTime) {
		this.receiverTime = receiverTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	
	
}
