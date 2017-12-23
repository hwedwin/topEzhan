package com.topaiebiz.thirdparty.dto;

/**
 * Description 微信退款参数DTO
 * <p>
 * Author hxpeng
 * <p>
 * Date 2017/11/18 13:35
 * <p>
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * <p>
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class WechatRefundDto {

	/**
	 * 微信订单号和商户订单号 ，二选一， 不能同时为空
	 * 微信订单号
	 */
	private String transactionId;

	/**
	 * 微信订单号和商户订单号 ，二选一， 不能同时为空
	 * 商户支付订单号
	 */
	private String outTradeNo;

	/**
	 * 商户退款总订单号 非空
	 * 总订单表（退款类型）主键ID
	 */
	private String outRefundNo;

	/**
	 * 非空
	 * 支付订单总金额
	 */
	private Double totalFee;

	/**
	 * 非空
	 * 退款订单总金额
	 */
	private Double refundFee;

	/**
	 * 退款原因
	 */
	private String refundDesc;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getOutRefundNo() {
		return outRefundNo;
	}

	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}

	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	public Double getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(Double refundFee) {
		this.refundFee = refundFee;
	}

	public String getRefundDesc() {
		return refundDesc;
	}

	public void setRefundDesc(String refundDesc) {
		this.refundDesc = refundDesc;
	}
}
