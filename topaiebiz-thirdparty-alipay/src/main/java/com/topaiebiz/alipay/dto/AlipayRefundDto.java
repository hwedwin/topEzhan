package com.topaiebiz.alipay.dto;

/**
 * Description 支付宝退款DTO
 * <p>
 * Author hxpeng
 * <p>
 * Date 2017/11/18 9:49
 * <p>
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * <p>
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class AlipayRefundDto {

	/**
	 * 不能和 tradeNo同时为空
	 * 订单支付时传入的商户订单号
	 */
	private String outTradeNo;

	/**
	 * 和商户订单号不能同时为空
	 * 支付宝交易号
	 */
	private String tradeNo;

	/**
	 * 非空
	 * 总订单表（退款类型）主键ID
	 */
	private String refundTotalOrderId;

	/**
	 * 非空
	 * 退款金额
	 */
	private Double refundAmount;

	/**
	 * 可空
	 * 退款原因
	 */
	private String refundReason;

	/**
	 * 可空
	 * 商户的操作员编号
	 */
	private String operatorId;

	/**
	 * 可空
	 * 商店的门店编号
	 */
	private String storeId;

	/**
	 * 可空
	 * 商户的终端编号
	 */
	private String terminalId;

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getRefundTotalOrderId() {
		return refundTotalOrderId;
	}

	public void setRefundTotalOrderId(String refundTotalOrderId) {
		this.refundTotalOrderId = refundTotalOrderId;
	}

	public Double getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(Double refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
}
