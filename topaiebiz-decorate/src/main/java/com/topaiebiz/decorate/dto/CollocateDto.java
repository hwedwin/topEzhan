package com.topaiebiz.decorate.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.topaiebiz.decorate.entity.CollocateGoodsEntity;

public class CollocateDto {

	/** 主键ID。 */
	private Long id;

	/** '店铺编号。' */
	private Long storeId;

	/** '所属模版。用得哪个模版' */
	@NotNull(message = "{validation.collocate.templateId}")
	private Long templateId;

	/** '装饰类型。' */
	@NotNull(message = "{validation.collocate.decType}")
	private String decType;

	/** '标题。' */
	private String title;

	/** 图标图片。 */
	private String iconImage;

	/** '模版类型。' */
	private Long collocateType;

	/** 模版详情list。 */
	List<CollocateInfoDto> infoList;

	/** 配置商品详情list。 */
	List<CollocateGoodsEntity> goodsList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public String getDecType() {
		return decType;
	}

	public void setDecType(String decType) {
		this.decType = decType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIconImage() {
		return iconImage;
	}

	public void setIconImage(String iconImage) {
		this.iconImage = iconImage;
	}

	public Long getCollocateType() {
		return collocateType;
	}

	public void setCollocateType(Long collocateType) {
		this.collocateType = collocateType;
	}

	public List<CollocateInfoDto> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<CollocateInfoDto> infoList) {
		this.infoList = infoList;
	}

	public List<CollocateGoodsEntity> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<CollocateGoodsEntity> goodsList) {
		this.goodsList = goodsList;
	}

}
