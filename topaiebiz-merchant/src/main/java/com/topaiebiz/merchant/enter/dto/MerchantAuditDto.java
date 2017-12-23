package com.topaiebiz.merchant.enter.dto;

/**
 * Description: 商家审核记录回显
 * 
 * Author : Anthony
 * 
 * Date :2017年11月5日 下午9:00:06
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class MerchantAuditDto {

	/** 全局唯一标识符 */
	private Long id;

	/** 商家信息 */
	private Long merchantId;

	/** 不通过原因 */
	private String noPassReason;

	/** 审核记录Id */
	private Long auditLogId;

	/** 不通过字段 */
	private String noPassField;

	/** 商家入驻状态 */
	private Integer state;

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

	public String getNoPassReason() {
		return noPassReason;
	}

	public void setNoPassReason(String noPassReason) {
		this.noPassReason = noPassReason;
	}

	public Long getAuditLogId() {
		return auditLogId;
	}

	public void setAuditLogId(Long auditLogId) {
		this.auditLogId = auditLogId;
	}

	public String getNoPassField() {
		return noPassField;
	}

	public void setNoPassField(String noPassField) {
		this.noPassField = noPassField;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	

}
