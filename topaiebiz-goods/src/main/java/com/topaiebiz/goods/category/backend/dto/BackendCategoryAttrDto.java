package com.topaiebiz.goods.category.backend.dto;

import javax.validation.constraints.NotNull;

/**
 * Description 商品后台类目属性dto
 * 
 * Author Hedda
 * 
 * Date 2017年9月25日 下午8:29:27
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
public class BackendCategoryAttrDto {

	/** 全局唯一主键标识符 （本字段是业务无关性的，仅用于关联）。 */
	private Long id;
	
	/** 所属类目。*/
	@NotNull(message = "{validation.backendCategoryAttr.belongCategory}")
	private Long belongCategory;
	
	/** 是否为商家填的属性。（1为是，null为不是）*/
	private String storeCustom;

	/** 类目属性名字。 */
	@NotNull(message = "{validation.backendCategoryAttr.name}")
	private String name;

	/** 属性类型。(1.文本2.日期3.数字)。 */
	private Integer type;

	/** 默认单位。 */
	private String defaultUnit;

	/** 是否为销售属性 (1是，0不是)。 */
	private Integer isSale;

	/** 是否为必填项 (1是，0不是)。 */
	private Integer isMust;

	/** 是否可以自定义 (1是，0不是)。 */
	private Integer isCustom;

	/** 属性值集合，用逗号隔开。 */
	private String valueList;
	
	/** 排序号。*/
	private Integer sortNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBelongCategory() {
		return belongCategory;
	}

	public void setBelongCategory(Long belongCategory) {
		this.belongCategory = belongCategory;
	}

	public String getStoreCustom() {
		return storeCustom;
	}

	public void setStoreCustom(String storeCustom) {
		this.storeCustom = storeCustom;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDefaultUnit() {
		return defaultUnit;
	}

	public void setDefaultUnit(String defaultUnit) {
		this.defaultUnit = defaultUnit;
	}

	public Integer getIsSale() {
		return isSale;
	}

	public void setIsSale(Integer isSale) {
		this.isSale = isSale;
	}

	public Integer getIsMust() {
		return isMust;
	}

	public void setIsMust(Integer isMust) {
		this.isMust = isMust;
	}

	public Integer getIsCustom() {
		return isCustom;
	}

	public void setIsCustom(Integer isCustom) {
		this.isCustom = isCustom;
	}

	public String getValueList() {
		return valueList;
	}

	public void setValueList(String valueList) {
		this.valueList = valueList;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
	
}
