package com.topaiebiz.goods.category.backend.dto;

/**
 * Description 商家可用后台类目
 * 
 * Author Hedda
 * 
 * Date 2017年11月8日 下午7:43:20
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
public class BackendMerchantCategoryDto {

	/** 后台类目ID */
	private Long id;

	/** 所属商家。 */
	private Long merchantId;

	/** 所属店铺。 */
	private Long storeId;

	/** 平台类目ID。 */
	private Long categoryId;

	/** 类目名称。 */
	private String name;

	/** 类目描述。 */
	private String description;

	/** 类目等级 (1 一级 2 二级 3 三级)。 */
	private Integer level;

	/** 父类目。 */
	private Long parentId;

	/** 备注。用于备注其他信息。 */
	private String memo;

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
