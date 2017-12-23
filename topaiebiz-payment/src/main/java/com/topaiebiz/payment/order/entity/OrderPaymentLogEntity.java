package com.topaiebiz.payment.order.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * Description 订单支付记录
 * <p>
 * Author hxpeng
 * <p>
 * Date 2017/11/11 15:37
 * <p>
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * <p>
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@TableName("t_tsa_order_payment_log")
public class OrderPaymentLogEntity extends BaseBizEntity<Long> {

	private static final long serialVersionUID = 1295014956963862901L;

	/** 支付订单号。*/
	private String payOrderId;

	/** 支付类型编码。*/
	private String payType;

	/** 是否成功。*/
	private Integer successState;

	/** 金额。*/
	private double money;

	/** 对方帐号。*/
	private String otherAccount;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getPayOrderId() {
		return payOrderId;
	}

	public void setPayOrderId(String payOrderId) {
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

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getOtherAccount() {
		return otherAccount;
	}

	public void setOtherAccount(String otherAccount) {
		this.otherAccount = otherAccount;
	}
}
