package com.topaiebiz.merchant.enter.dto;

import javax.validation.constraints.NotNull;

/**
 * Description: 经营信息(营业执照信息、银行账户信息)信息dto
 * 
 * Author : Anthony
 * 
 * Date :2017年10月18日 下午7:31:44
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class MercahntManageInfoDto {

	/** 商家入驻信息的全局唯一主键标识符。本字段是业务无关性的，仅用于关联。 */
	private Long id;

	/** 营业执照号 */
	@NotNull(message = "{validation.qualification.licenseNo}")
	private String licenseNo;

	/** 营业执照电子版 */
	@NotNull(message = "{validation.qualification.licenseImage}")
	private String licenseImage;

	/** 营业执照号所在地 */
	@NotNull(message = "{validation.qualification.licenseLocation}")
	private String licenseLocation;
	
	/** 营业执照号所在地 */
	@NotNull(message = "{validation.qualification.licenseLocation}")
	private Long licenseRegionId;

	/** 营业执照有效期起始 */
	@NotNull(message = "{validation.qualification.licenseBegin}")
	private String licenseBegin;

	/** 营业执照有效期结束 */
	@NotNull(message = "{validation.qualification.licenseEnd}")
	private String licenseEnd;

	/** 所属商家 */
	private Long merchantId;

	/** 银行开户名 */
	@NotNull(message = "${validation.merchantaccount.accountName}")
	private String accountName;

	/** 公司银行帐号 */
	@NotNull(message = "${validation.merchantaccount.account}")
	private String account;

	/** 开户行所在区域 */
	@NotNull(message = "${validation.merchantaccount.districtId}")
	private Long districtId;

	/** 开户银行支行名称 */
	@NotNull(message = "${validation.merchantaccount.bankName}")
	private String bankName;

	/** 支行银联号 */
	@NotNull(message = "${validation.merchantaccount.bankNum}")
	private String bankNum;

	/** 开户银行许可证电子版 */
	@NotNull(message = "${validation.merchantaccount.electronicImage}")
	private String electronicImage;

	/** 法定经营范围 */
	private String manageScope;

	public String getManageScope() {
		return manageScope;
	}

	public void setManageScope(String manageScope) {
		this.manageScope = manageScope;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getLicenseImage() {
		return licenseImage;
	}

	public void setLicenseImage(String licenseImage) {
		this.licenseImage = licenseImage;
	}

	public String getLicenseLocation() {
		return licenseLocation;
	}

	public void setLicenseLocation(String licenseLocation) {
		this.licenseLocation = licenseLocation;
	}

	public String getLicenseBegin() {
		return licenseBegin;
	}

	public void setLicenseBegin(String licenseBegin) {
		this.licenseBegin = licenseBegin;
	}

	public String getLicenseEnd() {
		return licenseEnd;
	}

	public void setLicenseEnd(String licenseEnd) {
		this.licenseEnd = licenseEnd;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankNum() {
		return bankNum;
	}

	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	public String getElectronicImage() {
		return electronicImage;
	}

	public void setElectronicImage(String electronicImage) {
		this.electronicImage = electronicImage;
	}

	public Long getLicenseRegionId() {
		return licenseRegionId;
	}

	public void setLicenseRegionId(Long licenseRegionId) {
		this.licenseRegionId = licenseRegionId;
	}

}
