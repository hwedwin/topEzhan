package com.topaiebiz.merchant.enter.dto;

import java.util.List;

import com.topaiebiz.goods.category.backend.dto.BackendCategorysDto;

/**
 * Description: 经营类目信息所需dto
 * 
 * Author : Anthony
 * 
 * Date :2017年11月3日 下午1:27:23
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class MerchantManageDto {

	/** 商家入驻信息的全局唯一主键标识符。本字段是业务无关性的，仅用于关联。 */
	private Long id;

	/** 店铺名称 */
	private String name;

	/** 选择店铺类型 */
	private String merchantType;

	/** 类目的List */
	private List<BackendCategorysDto> backendCategorysDtos;

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

	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	public List<BackendCategorysDto> getBackendCategorysDtos() {
		return backendCategorysDtos;
	}

	public void setBackendCategorysDtos(List<BackendCategorysDto> backendCategorysDtos) {
		this.backendCategorysDtos = backendCategorysDtos;
	}

}
