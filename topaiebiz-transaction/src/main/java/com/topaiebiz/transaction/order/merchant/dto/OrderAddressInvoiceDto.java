package com.topaiebiz.transaction.order.merchant.dto;

import javax.validation.constraints.NotNull;

/**
 * 
 * Description 订单的收货地址&订单发票Dto 
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月12日 下午3:43:27 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class OrderAddressInvoiceDto {
	
	/** 传入的收货人姓名  */
	@NotNull(message = "{validation.OrderAddressInvoice.consigneeName}")
	private String consigneeName;
	
	/** 传入的收货人地址  */
	@NotNull(message = "{validation.OrderAddressInvoice.address}")
	private String address;
	
	/** 传入的收货人备注  */
	private String addressMemo;
	
	/**收货人手机号*/
	private String telephone;
	
	/**配送地址的区域*/
	@NotNull(message="{validation.OrderAddressInvoice.districtId}")
	private Long districtId;
	
	/** 订单id */
	@NotNull(message = "{validation.OrderAddressInvoice.orderId}")
	private Long orderId;

	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressMemo() {
		return addressMemo;
	}

	public void setAddressMemo(String addressMemo) {
		this.addressMemo = addressMemo;
	}

    
	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

}
