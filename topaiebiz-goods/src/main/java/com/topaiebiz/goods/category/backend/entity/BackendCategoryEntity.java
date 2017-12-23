package com.topaiebiz.goods.category.backend.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * Description 商品类目 。
 * 
 * Author Hedda
 * 
 * Date 2017年8月23日 下午4:56:48
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_goo_backend_category")
public class BackendCategoryEntity extends BaseBizEntity<Long>{

	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = -1501890616022477798L;

	/** 类目类型。1为商家自定义类目 0或者空为平台类目。暂时不用管 */
	private Integer cateType;

	/** 类目名称。 */
	private String name;

	/** 类目描述。 */
	private String description;

	/** 类目等级 (1 一级 2 二级 3 三级)。 */
	private Integer level;
	
	/** 类目排序号。*/
	private Integer sortNo;

	/** 父类目。 */
	private Long parentId;

	/** 备注。用于备注其他信息。 */
	private String memo;

	public Integer getCateType() {
		return cateType;
	}

	public void setCateType(Integer cateType) {
		this.cateType = cateType;
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

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
