package com.topaiebiz.transaction.order.merchant.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * 
 * Description 订单配送地址的实体类
 * 
 * 
 * Author:zhushuyong
 * 
 * Date 2017年8月30日 下午8:53:07
 * 
 * Copyright:Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_tsa_order_address")
public class OrderAddressEntity extends BaseBizEntity<Long> {

	/** 序列化版本号 */
	@TableField(exist = false)
	private static final long serialVersionUID = 4455910672930257562L;

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
	
	/**所在省的名称*/
	@TableField(exist=false) 
	private Long provinceId;
	
	/**所在市的名称*/
	@TableField(exist=false) 
	private Long cityId;
	
	
	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public OrderAddressEntity() {
		super();
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
