
package com.topaiebiz.goods.category.frontend.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * Description 前台类目 。
 * 
 * Author Hedda 
 *    
 * Date 2017年8月23日 下午5:08:21 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_goo_frontend_category")
public class FrontendCategoryEntity extends BaseBizEntity<Long>{

	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = -4713213711744452207L;
	
	/** 所属店铺。*/
	private Long belongStore;
	
	/** 类目名称。*/
	private String name;
	
	/** 类目描述。*/
	private String description;
	
	/** 类目等级。*/
	private Integer level;
	
	/** 类目排序号。*/
	private Integer sortNo;
	
	/** 父类目。*/
	private Long parentId;
	
	/** 类目图片。*/
	private String image;
	
	/** 备注。用于备注其他信息。 */
	private String memo;

	public Long getBelongStore() {
		return belongStore;
	}

	public void setBelongStore(Long belongStore) {
		this.belongStore = belongStore;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
