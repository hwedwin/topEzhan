package com.topaiebiz.merchant.enter.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 * Description: 商家入驻流程--基本信息填写（公司及联系人信息）信息的dto
 * 
 * Author : Anthony
 * 
 * Date :2017年10月18日 下午4:40:37
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class MerchantBasicInfoDto {

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

	/** 公司详细地址 */
	@NotNull(message = "{validation.qualification.address}")
	private String address;

	/** 门店数量 */
	@NotNull(message = "{validation.qualification.storeNumber}")
	private Long storeNumber;

	/** 公司电话 */
	@NotNull(message = "{validation.qualification.telephone}")
	private String telephone;

	/** 员工总数 */
	@NotNull(message = "{validation.qualification.staffNo}")
	private Long staffNo;

	/** 注册资金，单位万元 */
	@NotNull(message = "{validation.qualification.capital}")
	private Double capital;

	/** 联系人电话 */
	@NotNull(message = "{validation.merchantInfo.contactTele}")
	@Length(min = 11, max = 11)
	private String contactTele;

	/** 联系人身份证号 */
	@NotNull(message = "{validation.qualification.idCard}")
	private String idCard;

	/** 电子邮箱 */
	@NotNull(message = "{validation.qualification.email}")
	@Email
	private String email;

	/** 联系人身份证号电子版 */
	@NotNull(message = "{validation.qualification.idCardImage}")
	private String idCardImage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
     
}
