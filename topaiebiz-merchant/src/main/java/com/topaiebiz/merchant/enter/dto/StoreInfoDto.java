package com.topaiebiz.merchant.enter.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.topaiebiz.goods.category.backend.dto.BackendCategorysDto;

/**
 * Description: 店铺信息实体类
 * 
 * Author : Anthony
 * 
 * Date :2017年10月2日 下午7:30:25
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class StoreInfoDto {

	/** 商家入驻信息的全局唯一主键标识符。本字段是业务无关性的，仅用于关联。 */
	private Long id;

	/** 所属商家ID */
	private Long merchantId;

	/** 店铺名称 */
	@NotNull(message = "{validation.storeInfo.name}")
	private String name;

	/** 店铺类型 */
	private String merchantType;

	/** 实体店所在区域 */
	@NotNull(message = "{validation.storeInfo.districtId}")
	private Long districtId;

	/** 实体店位置 */
	@NotNull(message = "{validation.storeInfo.storeAddress}")
	private String storeAddress;

	/** 商家联系人姓名 */
	@NotNull(message = "{validation.storeInfo.contactName}")
	private String contactName;

	/** 联系人手机号 */
	@NotNull(message = "{validation.storeInfo.contactTele}")
	private String contactTele;

	/** 门店电话 */
	@NotNull(message = "{validation.storeInfo.storeTele}")
	private String storeTele;

	/** 商家介绍 */
	private String description;

	/** 地理位置 */
	private String position;

	/** 门店照片多张 */
	@NotNull(message = "{validation.storeInfo.images}")
	private String images;

	/** 店铺等级。和商家保持一致 */
	private Long merchantGradeId;

	/** 类目的List */
	private List<BackendCategorysDto> backendCategorysDtos;

	public List<BackendCategorysDto> getBackendCategorysDtos() {
		return backendCategorysDtos;
	}

	public void setBackendCategorysDtos(List<BackendCategorysDto> backendCategorysDtos) {
		this.backendCategorysDtos = backendCategorysDtos;
	}

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

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
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

	public String getStoreTele() {
		return storeTele;
	}

	public void setStoreTele(String storeTele) {
		this.storeTele = storeTele;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Long getMerchantGradeId() {
		return merchantGradeId;
	}

	public void setMerchantGradeId(Long merchantGradeId) {
		this.merchantGradeId = merchantGradeId;
	}

	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
