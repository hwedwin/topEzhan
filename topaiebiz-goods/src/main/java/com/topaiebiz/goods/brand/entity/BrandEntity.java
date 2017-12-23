
package com.topaiebiz.goods.brand.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * Description 商品品牌。
 * 
 * Author Hedda
 * 
 * Date 2017年8月23日 下午4:24:44
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_goo_brand")
public class BrandEntity extends BaseBizEntity<Long>{

	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = 5304153063104117657L;

	/** 唯一编码 （本字段是从业务角度考虑的，相当于全局的唯一业务主键）。 */
	private String brandCode;

	/** 品牌名称。 */
	private String name;
	
	/** 品牌展示排序。*/
	private Integer sortNo;

	/** 英文名称。 */
	private String englishName;

	/** 品牌图片。 */
	private String brandImage;
	
	/** 品牌故事。*/
	private String brandStory;
	
	/** 备注。用于备注其他信息。 */
	private String memo;

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getBrandImage() {
		return brandImage;
	}

	public void setBrandImage(String brandImage) {
		this.brandImage = brandImage;
	}

	public String getBrandStory() {
		return brandStory;
	}

	public void setBrandStory(String brandStory) {
		this.brandStory = brandStory;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
