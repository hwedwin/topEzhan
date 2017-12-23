package com.topaiebiz.merchant.enter.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * Description: 商家入驻审核记录实体类
 * 
 * Author : Anthony
 * 
 * Date :2017年10月13日 上午9:15:51
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_mer_merchant_audit_log")
public class MerchantauditLogEntity extends BaseBizEntity<Long> {

	/** 序列化版本号 */
	private static final long serialVersionUID = -1101244490260477378L;

	/** 商家信息 */
	private Long merchantId;

	/** 审核结果 */
	private Integer auditResult;

	/** 不通过原因 */
	private String noPassReason;

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

}
