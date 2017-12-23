package com.topaiebiz.goods.category.backend.dto;

import javax.validation.constraints.NotNull;

/**
 * Description 商品后台类目dto 。
 * 
 * Author Hedda
 * 
 * Date 2017年9月24日 下午3:10:58
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
public class BackendCategoryDto {

	/** 全局唯一主键标识符 （本字段是业务无关性的，仅用于关联）。 */
	private Long id;

	/** 类目类型。1为商家自定义类目  0或者空为平台类目。暂时不用管 */
	private Integer cateType;

	/** 类目名称。 */
	@NotNull(message = "{validation.backendCategory.name}")
	private String name;

	/** 类目描述。 */
	private String description;

	/** 类目等级 (1 一级 2 二级 3 三级)。 */
	@NotNull(message = "{validation.backendCategory.level}")
	private Integer level;

	/** 父类目。 */
	private Long parentId;
	
	/** 类目id。*/
	private Long categoryId;
	
	/** 绑定类目id。*/
	private Long frontBackId;
	
	/** 类目名称。*/
	private String categoryName;
	
	/** 类目个数 。*/
	private Integer count;

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

	public Integer getCateType() {
		return cateType;
	}

	public void setCateType(Integer cateType) {
		this.cateType = cateType;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	public Long getFrontBackId() {
		return frontBackId;
	}

	public void setFrontBackId(Long frontBackId) {
		this.frontBackId = frontBackId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
}
