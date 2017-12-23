package com.topaiebiz.merchant.enter.dto;

/**
 * Description: 缴费凭证图片页面所需Dto
 * 
 * Author : Anthony
 * 
 * Date :2017年10月26日 下午2:50:15
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class CapitalDto {

	/** 商家入驻信息的全局唯一主键标识符。本字段是业务无关性的，仅用于关联。 */
	private Long id;

	/** 商家id */
	private Long merchantId;

	/** 上传缴费凭证 */
	private String payImage;

	/** 缴纳金额 */
	private Double PaymentPrice;

	/** 备注。用于备注其他信息 */
	private String memo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getPayImage() {
		return payImage;
	}

	public void setPayImage(String payImage) {
		this.payImage = payImage;
	}

	public Double getPaymentPrice() {
		return PaymentPrice;
	}

	public void setPaymentPrice(Double paymentPrice) {
		PaymentPrice = paymentPrice;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
