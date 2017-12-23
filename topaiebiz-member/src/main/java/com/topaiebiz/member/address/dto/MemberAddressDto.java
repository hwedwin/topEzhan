package com.topaiebiz.member.address.dto;


import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * Description：会员收货地址,存储会员的收货地址。    
 * 
 * Author Scott.Yang
 *    
 * Date 2017年10月13日 下午7:59:05 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class MemberAddressDto {
	
	/** 会员等级的全局唯一主键标识符。本字段是业务无关性的，仅用于关联。*/
	private Long id;
	
	/** 会员编号。*/
	private Long memberId;
	
	/** 收货人。*/
	@NotBlank(message = "{validation.address.name}")
	@Length(min = 2,max = 100)
	private String name;
	
	/** 收货手机号。*/
	@NotNull(message = "{validation.address.telephone}")
	@Length(min = 11,max = 11)
	private String telephone;
	
	/** 地址区域。*/
	@NotNull(message = "{validation.address.districtId}")
	private Long districtId;
	
	/** 具体区域 */
	private String districtValue;
	
	/**省名称*/
	private String provinceName;
	
	/**省id*/
	private Long provinceId;
	
	/**市名称*/
	private String cityName;
	
	/**市id*/
	private Long cityId;
	
	/**区名称*/
	private String districtName;

	/** 详细地址。*/
	@NotBlank(message = "{validation.address.address}")
	@Length(min = 5,max = 1000)
	private String address;
	
	/** 是否默认地址（1 是，0不是）。*/
	private Integer isDefault;
	
	
	
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	
	public String getDistrictValue() {
		return districtValue;
	}

	public void setDistrictValue(String districtValue) {
		this.districtValue = districtValue;
	}

}
