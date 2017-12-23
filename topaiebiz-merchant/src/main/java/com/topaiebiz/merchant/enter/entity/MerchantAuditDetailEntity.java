package com.topaiebiz.merchant.enter.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * Description: 商家入驻审核详情实体类
 * 
 * Author : Anthony
 * 
 * Date :2017年10月12日 下午2:44:56
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_mer_merchant_audit_detail")
public class MerchantAuditDetailEntity extends BaseBizEntity<Long> {

	/** 版本序列化 */
	private static final long serialVersionUID = -4158460879531457123L;

	/** 审核记录Id */
	private Long auditLogId;

	/** 商家信息 */
	private Long merchantId;

	/** 不通过字段 */
	private String noPassField;

	/** 不通过原因 */
	private String noPassReason;

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
