
package com.topaiebiz.goods.category.backend.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * Description 商品类目属性  。
 * 
 * Author Hedda 
 *    
 * Date 2017年8月23日 下午4:59:29 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_goo_backend_category_attr")
public class BackendCategoryAttrEntity extends BaseBizEntity<Long>{

	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = 3014348620845679844L;

	/** 所属类目。*/
	private Long belongCategory;
	
	/** 是否为商家填的属性。（1为是，null为不是）*/
	private String storeCustom;
	
    /** 类目属性名字。*/
	private String name;
	
	/**属性类型。(1.文本2.日期3.数字)。*/
	private Integer type;
	
	/** 默认单位。*/
	private String defaultUnit;
	
	/** 是否为销售属性         (1是，0不是)。*/
	private Integer isSale;
	
	/** 是否为必填项              (1是，0不是)。*/
	private Integer isMust;
	
	/**是否可以自定义   (1是，0不是)。*/
	private Integer isCustom;
	
	/**属性值集合，用逗号隔开。*/
	private String valueList;
	
	/** 排序号。*/
	private Integer sortNo;
	
	/** 备注。用于备注其他信息。 */
	private String memo;

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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
