package com.topaiebiz.merchant.enter.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.topaiebiz.goods.category.backend.dto.BackendCategorysDto;

/**
 * Description: 商家资质Dto类
 * 
 * Author : Anthony
 * 
 * Date :2017年10月9日 下午1:40:47
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class MerchantQualificationDto {

	/** 商家入驻信息的全局唯一主键标识符。本字段是业务无关性的，仅用于关联。 */
	private Long id;

	/** 商家信息 */
	private Long merchantId;

	/** 公司名称 */
	@NotNull(message = "{validation.merchantInfo.name}")
	private String name;

	/** 公司所在地 */
	@NotNull(message = "{validation.qualification.districtId}")
	private String districtId;

	/** 公司所在地 */
	private String serialName;

	public String getSerialName() {
		return serialName;
	}

	public void setSerialName(String serialName) {
		this.serialName = serialName;
	}

	/** 公司详细地址 */
	@NotNull(message = "{validation.qualification.address}")
	private String address;

	/** 公司电话 */
	@NotNull(message = "{validation.qualification.telephone}")
	private String telephone;

	/** 员工总数 */
	@NotNull(message = "{validation.qualification.staffNo}")
	private Long staffNo;

	/** 注册资金，单位万元 */
	@NotNull(message = "{validation.qualification.capital}")
	private Double capital;

	/** 类目的List */
	private List<BackendCategorysDto> backendCategorysDtos;

	// private List<MerchantauditLogDto> logList;

	private StateDto StateDto;

	/*
	 * public List<MerchantauditLogDto> getLogList() { return logList; }
	 * 
	 * public void setLogList(List<MerchantauditLogDto> logList) { this.logList
	 * = logList; }
	 */

	public StateDto getStateDto() {
		return StateDto;
	}

	public void setStateDto(StateDto stateDto) {
		StateDto = stateDto;
	}

	public List<BackendCategorysDto> getBackendCategorysDtos() {
		return backendCategorysDtos;
	}

	public void setBackendCategorysDtos(List<BackendCategorysDto> backendCategorysDtos) {
		this.backendCategorysDtos = backendCategorysDtos;
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

	public Integer getIsSettle() {
		return isSettle;
	}

	public void setIsSettle(Integer isSettle) {
		this.isSettle = isSettle;
	}

	public String getElectronicImage() {
		return electronicImage;
	}

	public void setElectronicImage(String electronicImage) {
		this.electronicImage = electronicImage;
	}

	/** 联系人姓名 */
	@NotNull(message = "{validation.merchantInfo.contactName}")
	private String contactName;

	/** 联系人电话 */
	@NotNull(message = "{validation.merchantInfo.contactTele}")
	@Length(min = 11, max = 11)
	private String contactTele;

	/** 联系人身份证号 */
	@NotNull(message = "{validation.qualification.idCard}")
	private String idCard;

	/** 联系人身份证号电子版 */
	@NotNull(message = "{validation.qualification.idCardImage}")
	private String idCardImage;

	/** 电子邮箱 */
	@NotNull(message = "{validation.qualification.email}")
	@Email
	private String email;

	/** 营业执照号 */
	@NotNull(message = "{validation.qualification.licenseNo}")
	private String licenseNo;

	/** 营业执照号所在地 */
	@NotNull(message = "{validation.qualification.licenseLocation}")
	private String licenseLocation;

	/** 营业执照有效期起始 */
	@NotNull(message = "{validation.qualification.licenseBegin}")
	private Date licenseBegin;

	/** 营业执照有效期结束 */
	@NotNull(message = "{validation.qualification.licenseEnd}")
	private Date licenseEnd;

	/** 法定经营范围 */
	@NotNull(message = "{validation.qualification.manageScope}")
	private String manageScope;

	/** 营业执照电子版 */
	@NotNull(message = "{validation.qualification.licenseImage}")
	private String licenseImage;

	/** 组织机构代码 */
	@NotNull(message = "{validation.qualification.organCode}")
	private String organCode;

	/** 一般纳税人证明 */
	private String taxpayerImage;

	/** 税务登记证号 */
	@NotNull(message = "{validation.qualification.taxRegistNo}")
	private String taxRegistNo;

	/** 纳税人识别号 */
	@NotNull(message = "{validation.qualification.taxpayerNo}")
	private String taxpayerNo;

	/** 税务登记证号电子版 */
	@NotNull(message = "{validation.qualification.taxpayerNoImage}")
	private String taxpayerNoImage;

	/** 1申请，2审核通过 3 审核不通过 4待付款 5已完成 */
	private Integer state;

	/** 需要支付的金额 */
	private Double PaymentPrice;

	/** 支付凭证图片 */
	private String payImage;

	/** 支付时间 */
	private Date payTime;

	/** 公司名称 */
	// private String name;

	/** 连锁店、直营店等暂定 */
	@NotNull(message = "{validation.MerchantInfo.merchantType}")
	private Integer merchantType;

	/** 上级商户 */
	private Long parentMerchant;

	/** 入驻状态。1申请，2审核通过 3 审核不通过 4待付款 5已完成 */
	// private Integer state;

	/** 商家联系人姓名 */
	// private String contactName;

	/** 联系人手机号 */
	// private String contactTele;

	/** 店铺的积分。和后期奖惩有关系 */
	private Long integral;

	/** 商家等级积分 */
	private Long gradeIntegral;

	/** 商家等级 */
	private Long merchantGradeId;

	/** 所属商家 */
	// private Long merchantId;

	/** 银行开户名 */
	@NotNull(message = "${validation.merchantaccount.accountName}")
	private String accountName;

	/** 公司银行帐号 */
	@NotNull(message = "${validation.merchantaccount.account}")
	private String account;

	/** 开户银行支行名称 */
	@NotNull(message = "${validation.merchantaccount.bankName}")
	private String bankName;

	/** 支行银联号 */
	@NotNull(message = "${validation.merchantaccount.bankNum}")
	private String bankNum;

	/** 开户行所在区域 */
	@NotNull(message = "${validation.merchantaccount.districtId}")
	// private Long districtId;

	/** 是否为结算账号 */
	private Integer isSettle;

	/** 开户银行许可证电子版 */
	@NotNull(message = "${validation.merchantaccount.electronicImage}")
	private String electronicImage;

	/** 预留电话 */
	// private String telephone;

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

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
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

	public String getIdCardImage() {
		return idCardImage;
	}

	public void setIdCardImage(String idCardImage) {
		this.idCardImage = idCardImage;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getLicenseLocation() {
		return licenseLocation;
	}

	public void setLicenseLocation(String licenseLocation) {
		this.licenseLocation = licenseLocation;
	}

	public Date getLicenseBegin() {
		return licenseBegin;
	}

	public void setLicenseBegin(Date licenseBegin) {
		this.licenseBegin = licenseBegin;
	}

	public Date getLicenseEnd() {
		return licenseEnd;
	}

	public void setLicenseEnd(Date licenseEnd) {
		this.licenseEnd = licenseEnd;
	}

	public String getManageScope() {
		return manageScope;
	}

	public void setManageScope(String manageScope) {
		this.manageScope = manageScope;
	}

	public String getLicenseImage() {
		return licenseImage;
	}

	public void setLicenseImage(String licenseImage) {
		this.licenseImage = licenseImage;
	}

	public String getOrganCode() {
		return organCode;
	}

	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}

	public String getTaxpayerImage() {
		return taxpayerImage;
	}

	public void setTaxpayerImage(String taxpayerImage) {
		this.taxpayerImage = taxpayerImage;
	}

	public String getTaxRegistNo() {
		return taxRegistNo;
	}

	public void setTaxRegistNo(String taxRegistNo) {
		this.taxRegistNo = taxRegistNo;
	}

	public String getTaxpayerNo() {
		return taxpayerNo;
	}

	public void setTaxpayerNo(String taxpayerNo) {
		this.taxpayerNo = taxpayerNo;
	}

	public String getTaxpayerNoImage() {
		return taxpayerNoImage;
	}

	public void setTaxpayerNoImage(String taxpayerNoImage) {
		this.taxpayerNoImage = taxpayerNoImage;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Double getPaymentPrice() {
		return PaymentPrice;
	}

	public void setPaymentPrice(Double paymentPrice) {
		PaymentPrice = paymentPrice;
	}

	public String getPayImage() {
		return payImage;
	}

	public void setPayImage(String payImage) {
		this.payImage = payImage;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Integer getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(Integer merchantType) {
		this.merchantType = merchantType;
	}

	public Long getParentMerchant() {
		return parentMerchant;
	}

	public void setParentMerchant(Long parentMerchant) {
		this.parentMerchant = parentMerchant;
	}

	public Long getIntegral() {
		return integral;
	}

	public void setIntegral(Long integral) {
		this.integral = integral;
	}

	public Long getGradeIntegral() {
		return gradeIntegral;
	}

	public void setGradeIntegral(Long gradeIntegral) {
		this.gradeIntegral = gradeIntegral;
	}

	public Long getMerchantGradeId() {
		return merchantGradeId;
	}

	public void setMerchantGradeId(Long merchantGradeId) {
		this.merchantGradeId = merchantGradeId;
	}

}
