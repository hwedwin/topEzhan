package com.topaiebiz.transaction.payment.log.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * 
 * Description 订单三方支付记录实体类 
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年8月31日 下午1:16:37 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_tsa_pay_order_log")
public class PayOrderLogEntity extends BaseBizEntity<Long> {
	
	/** 序列化版本号*/
	@TableField(exist = false)
	private static final long serialVersionUID = 4146349330403903042L;
	
	/** 支付订单号*/
	private Long payOrderId;
	
	/** 1微信 2支付宝 3等 可以写到数据字典里*/
	private String payType;
	
	/** 是否成功*/
	private Integer successState;
	
	/** 金额*/
	private Double money;
	
	/** 对方账号*/
	private String otherAccount;

	public PayOrderLogEntity() {
		super();
	}

	public Long getPayOrderId() {
		return payOrderId;
	}

	public void setPayOrderId(Long payOrderId) {
		this.payOrderId = payOrderId;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Integer getSuccessState() {
		return successState;
	}

	public void setSuccessState(Integer successState) {
		this.successState = successState;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getOtherAccount() {
		return otherAccount;
	}

	public void setOtherAccount(String otherAccount) {
		this.otherAccount = otherAccount;
	}
	
}
