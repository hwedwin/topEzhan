package com.topaiebiz.merchant.enter.dto;

import javax.validation.constraints.NotNull;

/**
 * Description: 商家入驻审核详情Dto
 * 
 * Author : Anthony
 * 
 * Date :2017年10月12日 下午3:09:16
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class MerchantAuditDetailDto {

	/** 全局唯一标识符 */
	private Long id;

	/** 审核记录Id */
//	@NotNull(message = "{validation.MerchantAuditDetail.auditLogId}")
	private Long auditLogId;

	/** 商家信息 */
	@NotNull(message = "{validation.MerchantAuditDetail.merchantId}")
	private Long merchantId;

	/** 不通过字段 */
//	@NotNull(message = "{validation.MerchantAuditDetail.noPassField}")
	private String noPassField;

	/** 不通过原因 */
	//@NotNull(message = "{validation.MerchantAuditDetail.noPassReason}")
	private String noPassReason;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAuditLogId() {
		return auditLogId;
	}

	public void setAuditLogId(Long auditLogId) {
		this.auditLogId = auditLogId;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getNoPassField() {
		return noPassField;
	}

	public void setNoPassField(String noPassField) {
		this.noPassField = noPassField;
	}

	public String getNoPassReason() {
		return noPassReason;
	}

	public void setNoPassReason(String noPassReason) {
		this.noPassReason = noPassReason;
	}

}
