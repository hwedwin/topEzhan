package com.topaiebiz.giftcard.manage.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * Description 描述配货地址的实体类。
 * 
 * 
 * Author Murray.Li
 * 
 * Date 2017年9月8日 下午4:02:53
 * 
 * Copyright:Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_tsa_order_address")
public class CardAddressEntity extends BaseBizEntity<Long> {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 2440970210766515700L;

	/** 全局唯一标识符 */
	@TableId("id")
	private Long id;

	/** 订单编号 */
	private Long orderId;

	/** 收货人姓名 */
	private String name;

	/** 地址区域。 */
	private Long districtId;
	
	/**所在省的名称*/
	@TableField(exist=false) 
	private Long provinceId;
	
	/**所在市的名称*/
	@TableField(exist=false) 
	private Long cityId;
	
	/** 收货人地址 */
	private String address;

	/** 收货人邮编 */
	private String zipCode;
	

	/** 收货人手机号 */
	@NotBlank
	@Length(min = 11, max = 11)
	private String telephone;

	/** 收货人座机号 */
	private String landline;

	/** 紧急联系电话，备用电话。 */
	private String otherTelephone;

	/** 创建人编号。取值为创建人的全局唯一主键标识符。 */
	private Long creatorId;

	/**
	 * Description 配货地址实体类的所有属性的set，get方法。
	 * 
	 * Author Murray.Li
	 * 
	 * return
	 */
	public Long getId() {
		return id;
	}

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

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	@Override
	public String toString() {
		return "CardAddress [id=" + id + ", orderId=" + orderId + ", name=" + name + ", districtId=" + districtId
				+ ", address=" + address + ", zipCode=" + zipCode + ", telephone=" + telephone + ", landline="
				+ landline + ", otherTelephone=" + otherTelephone + ", creatorId=" + creatorId + "]";
	}

}
