package com.topaiebiz.merchant.enter.dto;

import java.util.List;

import com.topaiebiz.merchant.enter.entity.MerchantAuditDetailEntity;

/**
 * Description: 商家入驻审核记录Dto
 * 
 * Author : Anthony
 * 
 * Date :2017年10月13日 上午9:21:08
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class MerchantauditLogDto {

	/** 全局唯一标识符 */
	private Long id;

	/** 商家信息 */
	private Long merchantId;

	/** 审核结果 */
	private Integer auditResult;

	/** 不通过原因 */
	private String noPassReason;

	/** 不通过字段 */
	//@NotNull(message = "{validation.MerchantAuditDetail.noPassField}")
	private String noPassField;
	
	/** 审核记录Id */
//	@NotNull(message = "{validation.MerchantAuditDetail.auditLogId}")
	private Long auditLogId;
	
	private List<MerchantAuditDetailEntity> detailList;

	public Long getAuditLogId() {
		return auditLogId;
	}

	public void setAuditLogId(Long auditLogId) {
		this.auditLogId = auditLogId;
	}

	public Long getId() {
		return id;
	}

	public String getNoPassField() {
		return noPassField;
	}

	public void setNoPassField(String noPassField) {
		this.noPassField = noPassField;
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

	public Integer getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(Integer auditResult) {
		this.auditResult = auditResult;
	}

	public String getNoPassReason() {
		return noPassReason;
	}

	public void setNoPassReason(String noPassReason) {
		this.noPassReason = noPassReason;
	}

	public List<MerchantAuditDetailEntity> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<MerchantAuditDetailEntity> detailList) {
		this.detailList = detailList;
	}

}
