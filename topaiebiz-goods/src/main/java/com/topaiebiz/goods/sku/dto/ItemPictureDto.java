package com.topaiebiz.goods.sku.dto;

/**  
 * Description 商品图片，存储商品的图片信息  。
 * 
 * Author Hedda 
 *    
 * Date 2017年8月23日 下午5:23:47 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class ItemPictureDto{
	
	/** 全局唯一主键标识符 （本字段是业务无关性的，仅用于关联）。 */
	private Long id;

	/** 所属商品。*/
	private Long itemId;

	/** 图片名称。*/
	private String name;
	
	/** 图片类型（1为显示的5张主图，2 为详情图）。*/
	private Integer type;
	
	/** 是否为主图（1是 ,0否，空也为否）。*/
	private Integer isMain;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
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

	public Integer getIsMain() {
		return isMain;
	}

	public void setIsMain(Integer isMain) {
		this.isMain = isMain;
	}

}
