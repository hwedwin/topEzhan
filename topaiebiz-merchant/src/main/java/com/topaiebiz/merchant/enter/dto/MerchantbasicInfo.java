package com.topaiebiz.merchant.enter.dto;

/**
 * Description:商家基本信息
 * 
 * Author : Anthony
 * 
 * Date :2017年11月2日 下午11:52:14
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class MerchantbasicInfo {

	/** 商家入驻信息的全局唯一主键标识符。本字段是业务无关性的，仅用于关联。 */
	private Long id;

	/** 商家信息 */
	private Long merchantId;

	/** 公司名称 */
	private String name;

	/** 公司所在地 */
	private String districtId;

	/** 公司详细地址 */
	private String address;

	/** 门店数量 */
	private Long storeNumber;

	/** 公司电话 */
	private String telephone;

	/** 员工总数 */
	private Long staffNo;

	/** 注册资金，单位万元 */
	private Double capital;

	/** 联系人电话 */
	private String contactTele;

	/** 联系人身份证号 */
	private String idCard;

	/** 电子邮箱 */
	private String email;

	/** 联系人身份证号电子版 */
	private String idCardImage;

	/** 营业执照号 */
	private String licenseNo;

	/** 营业执照电子版 */
	private String licenseImage;

	/** 营业执照号所在地 */
	private String licenseLocation;

	/** 营业执照有效期起始 */
	private String licenseBegin;

	/** 营业执照有效期结束 */
	private String licenseEnd;

	/** 银行开户名 */
	private String accountName;

	/** 公司银行帐号 */
	private String account;

	/** 开户银行支行名称 */
	private String bankName;

	/** 支行银联号 */
	private String bankNum;

	/** 开户银行许可证电子版 */
	private String electronicImage;

	/** 法定经营范围 */
	private String manageScope;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getStoreNumber() {
		return storeNumber;
	}

	public void setStoreNumber(Long storeNumber) {
		this.storeNumber = storeNumber;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Long getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(Long staffNo) {
		this.staffNo = staffNo;
	}

	public Double getCapital() {
		return capital;
	}

	public void setCapital(Double capital) {
		this.capital = capital;
	}

	public String getContactTele() {
		return contactTele;
	}

	public void setContactTele(String contactTele) {
		this.contactTele = contactTele;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdCardImage() {
		return idCardImage;
	}

	public void setIdCardImage(String idCardImage) {
		this.idCardImage = idCardImage;
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

	public String getManageScope() {
		return manageScope;
	}

	public void setManageScope(String manageScope) {
		this.manageScope = manageScope;
	}

}
