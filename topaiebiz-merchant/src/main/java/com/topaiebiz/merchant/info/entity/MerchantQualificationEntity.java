package com.topaiebiz.merchant.info.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * Description: 商家资质实体类
 * 
 * Author : Anthony
 * 
 * Date :2017年10月2日 下午7:40:32
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_mer_merchant_qualification")
public class MerchantQualificationEntity extends BaseBizEntity<Long> {

	/** 版本序列化 */
	private static final long serialVersionUID = 5766432792237052428L;

	private Long id;

	/** 商家信息 */
	private Long merchantId;

	/** 公司名称 */
	private String name;

	/** 公司所在地 */
	private String districtId;

	/** 公司详细地址 */
	private String address;

	/** 公司电话 */
	private String telephone;

	/** 员工总数 */
	private Long staffNo;

	/** 注册资金，单位万元 */
	private double capital;

	/** 联系人姓名 */
	private String contactName;

	/** 联系人电话 */
	private String contactTele;

	/** 联系人身份证号 */
	private String idCard;

	/** 联系人身份证号电子版 */
	private String idCardImage;

	/** 电子邮箱 */
	private String email;

	/** 营业执照号 */
	private String licenseNo;
	
	/**营业执照所在区域。*/
	private Long licenseRegionId;

	/** 营业执照号所在地 */
	private String licenseLocation;

	/** 营业执照有效期起始 */
	private Date licenseBegin;

	/** 营业执照有效期结束 */
	private Date licenseEnd;

	/** 法定经营范围 */
	private String manageScope;

	/** 营业执照电子版 */
	private String licenseImage;

	/** 组织机构代码 */
	private String organCode;

	/** 一般纳税人证明 */
	private String taxpayerImage;

	/** 税务登记证号 */
	private String taxRegistNo;

	/** 纳税人识别号 */
	private String taxpayerNo;

	/** 税务登记证号电子版 */
	private String taxpayerNoImage;

	/** 1申请，2审核通过 3 审核不通过 4待付款 5已完成 */
	private Integer state;

	/** 需要支付的金额 */
	private Double PaymentPrice;

	/** 支付凭证图片 */
	private String payImage;

	/** 支付时间 */
	private Date payTime;

	/** 门店数量 */
	private Long storeNumber;

	private String memo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public double getCapital() {
		return capital;
	}

	public void setCapital(double capital) {
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

	public Long getStoreNumber() {
		return storeNumber;
	}

	public void setStoreNumber(Long storeNumber) {
		this.storeNumber = storeNumber;
	}

	public Long getLicenseRegionId() {
		return licenseRegionId;
	}

	public void setLicenseRegionId(Long licenseRegionId) {
		this.licenseRegionId = licenseRegionId;
	}
	
}
