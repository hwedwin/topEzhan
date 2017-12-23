package com.topaiebiz.giftcard.manage.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

@TableName("t_gif_giftcard_use_log")
public class CardUseLogEntity extends BaseBizEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5283072040830353338L;
	
	/**主键ID*/
	private Long id;
	
	/**订单主键ID*/
	private Long orderId;
	
	/**礼卡主键*/
	private Long cardId;
	
	/**礼卡类型*/
	private Long meilikaType;
	
	/**抵扣金额*/
	private Double price;
	
	/**会员编号*/
	private Long memberId;
	
	/**备注*/
	private String memo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public Long getMeilikaType() {
		return meilikaType;
	}

	public void setMeilikaType(Long meilikaType) {
		this.meilikaType = meilikaType;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
	
	
	

}
