package com.topaiebiz.payment.order.dto;

/**
 * Description 订单收货地址DTO
 * 
 * Author Aaron.Xue
 * 
 * Date 2017年10月14日 上午9:41:23
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class OrderAddressDto {

	/** 收货地址ID */
	private Long id;

	/** 订单编号 */
	private Long orderId;

	/** 收货人姓名 */
	private String name;

	/** 地址区域 */
	private Long districtId;

	/** 收货人地址 */
	private String address;

	/** 收货人邮编 */
	private String zipCode;

	/** 收货人手机号 */
	private String telephone;

	/** 收货人座机号 */
	private String landline;

	/** 紧急联系人，备用电话 */
	private String otherTelephone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getLandline() {
		return landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getOtherTelephone() {
		return otherTelephone;
	}

	public void setOtherTelephone(String otherTelephone) {
		this.otherTelephone = otherTelephone;
	}

}
