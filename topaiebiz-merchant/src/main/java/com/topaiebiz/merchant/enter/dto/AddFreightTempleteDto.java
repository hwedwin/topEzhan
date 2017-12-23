package com.topaiebiz.merchant.enter.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.topaiebiz.merchant.enter.entity.FreightTempleteDetailEntity;

/**
 * Description: 添加运费模板所需字段
 * 
 * Author : Anthony
 * 
 * Date :2017年11月2日 下午4:21:13
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class AddFreightTempleteDto {
	
	private Long id;
	

	/** 店铺ID */
	private Long storeId;

	/** 模板名称 */
	@NotNull(message = "{validation.AddFreightTemplete.freightName}")
	private String freightName;

	/** 是否默认模板 */
	private Integer isDefault;

	/** 计价方式。1 件数 2体积 3重量 */
	@NotNull(message = "{validation.AddFreightTemplete.pricing}")
	private Integer pricing;

	/** 是否仅配送特定地区。（1 为是 ，0为否） */
	private Integer onlyThis;
	
	/**运费模板详情*/
	private List<FreightTempleteDetailEntity> freightTempleteDetails;

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getFreightName() {
		return freightName;
	}

	public void setFreightName(String freightName) {
		this.freightName = freightName;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public Integer getPricing() {
		return pricing;
	}

	public void setPricing(Integer pricing) {
		this.pricing = pricing;
	}

	public Integer getOnlyThis() {
		return onlyThis;
	}

	public void setOnlyThis(Integer onlyThis) {
		this.onlyThis = onlyThis;
	}

	public List<FreightTempleteDetailEntity> getFreightTempleteDetails() {
		return freightTempleteDetails;
	}

	public void setFreightTempleteDetails(List<FreightTempleteDetailEntity> freightTempleteDetails) {
		this.freightTempleteDetails = freightTempleteDetails;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
