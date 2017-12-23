
package com.topaiebiz.goods.spu.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.topaiebiz.goods.category.backend.dto.BackendCategoryDto;

/**
 * Description 商品SPU信息。
 * 
 * Author Hedda
 * 
 * Date 2017年8月23日 下午5:12:53
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class GoodsSpuDto{
	
	/** 全局唯一主键标识符 （本字段是业务无关性的，仅用于关联）。 */
	private Long id;

	/** 唯一编码 (本字段是从业务角度考虑的，相当于全局的唯一业务主键)。 */
	@NotNull(message = "{validation.goodsspu.spuCode}")
	private String spuCode;

	/** 商品名称(标题显示的名称)。 */
	@NotNull(message = "{validation.goodsspu.name}")
	private String name;

	/** 商品详情。 */
	@NotNull(message = "{validation.goodsspu.description}")
	private String description;

	/** 默认价格（页面刚打开的价格）。 */
	@NotNull(message = "{validation.goodsspu.defaultPrice}")
	private Double defaultPrice;

	/** 所属店铺。 */
	private Long belongStore;

	/** 所属品牌。 */
	@NotNull(message = "{validation.goodsspu.belongBrand}")
	private Long belongBrand;
	
	/** 品牌名称。*/
	private String brandName;
	
	/** 适用年龄段。*/
	@NotNull(message = "{validation.goodsspu.ageId}")
	private Long ageId;

	/** 所属类目。 */
	@NotNull(message = "{validation.goodsspu.belongCategory}")
	private Long belongCategory;
	
	/** 类目名称。*/
	private String categoryName;

	/** 创建时间。默认取值为系统的当前时间。 */
	private Date createdTime = new Date();
	
	/** 图片所属类目属性。*/
	private Long imageField;

	/** 商品spu属性集合。*/
	private List<GoodsSpuAttrDto> goodsSpuAttrDtos;
	
	/** 商品spu图片集合。*/
	private List<GoodsSpuPictureDto> goodsSpuPictureDtos;
	
	/**商品后台类目。*/
	private List<BackendCategoryDto> backendCategoryDtos;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSpuCode() {
		return spuCode;
	}

	public void setSpuCode(String spuCode) {
		this.spuCode = spuCode;
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

	public Double getDefaultPrice() {
		return defaultPrice;
	}

	public void setDefaultPrice(Double defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	public Long getBelongStore() {
		return belongStore;
	}

	public void setBelongStore(Long belongStore) {
		this.belongStore = belongStore;
	}

	public Long getBelongBrand() {
		return belongBrand;
	}

	public void setBelongBrand(Long belongBrand) {
		this.belongBrand = belongBrand;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Long getAgeId() {
		return ageId;
	}

	public void setAgeId(Long ageId) {
		this.ageId = ageId;
	}

	public Long getBelongCategory() {
		return belongCategory;
	}

	public void setBelongCategory(Long belongCategory) {
		this.belongCategory = belongCategory;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Long getImageField() {
		return imageField;
	}

	public void setImageField(Long imageField) {
		this.imageField = imageField;
	}

	public List<GoodsSpuAttrDto> getGoodsSpuAttrDtos() {
		return goodsSpuAttrDtos;
	}

	public void setGoodsSpuAttrDtos(List<GoodsSpuAttrDto> goodsSpuAttrDtos) {
		this.goodsSpuAttrDtos = goodsSpuAttrDtos;
	}

	public List<GoodsSpuPictureDto> getGoodsSpuPictureDtos() {
		return goodsSpuPictureDtos;
	}

	public void setGoodsSpuPictureDtos(List<GoodsSpuPictureDto> goodsSpuPictureDtos) {
		this.goodsSpuPictureDtos = goodsSpuPictureDtos;
	}

	public List<BackendCategoryDto> getBackendCategoryDtos() {
		return backendCategoryDtos;
	}

	public void setBackendCategoryDtos(List<BackendCategoryDto> backendCategoryDtos) {
		this.backendCategoryDtos = backendCategoryDtos;
	}

}
