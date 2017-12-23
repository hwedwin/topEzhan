package com.topaiebiz.decorate.dto;

import java.util.List;


public class CollocateDetailDto {

	/** '店铺编号。' */
	private Long storeId;

	/** '所属模版。用得哪个模版' */
	private Long templateId;

	/** '装饰类型。' */
	private String decType;

	/** '标题。' */
	private String title;

	/**图片详情*/
	List<CollocateInfoDto> infoList;
	
	/** 图片标图。 */
	private String iconImage;

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

	public List<CollocateInfoDto> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<CollocateInfoDto> infoList) {
		this.infoList = infoList;
	}

	public String getIconImage() {
		return iconImage;
	}

	public void setIconImage(String iconImage) {
		this.iconImage = iconImage;
	}
	
}
