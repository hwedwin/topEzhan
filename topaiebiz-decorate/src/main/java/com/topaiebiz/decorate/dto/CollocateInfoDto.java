package com.topaiebiz.decorate.dto;

import javax.validation.constraints.NotNull;

public class CollocateInfoDto {
	
	/**商品、类目、品牌名称*/
	private String name;

	/** 主键ID。 */
	private Long id;

	/** '所属模版。用得哪个模版' */
	@NotNull(message = "{validation.collocate.templateId}")
	private Long templateId;

	/** '装饰类型。' */
	@NotNull(message = "{validation.collocate.decType}")
	private String decType;

	/** '模版类型。' */
	private Long collocateType;

	/** '图片地址。' */
	@NotNull(message = "{validation.collocate.image}")
	private String image;

	/** '链接类型。1商品 2类目 3品牌 4 自定义url' */
	@NotNull(message = "{validation.collocate.jumpType}")
	private Integer jumpType;

	/** '跳转ID。' */
	private Long jumpId;

	/** '跳转URL。' */
	private String jumpUrl;

	/** '排序号。' */
	@NotNull(message = "{validation.collocate.sortNo}")
	private Integer sortNo;

	/** 备注。 */
	private String memo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getCollocateType() {
		return collocateType;
	}

	public void setCollocateType(Long collocateType) {
		this.collocateType = collocateType;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getJumpType() {
		return jumpType;
	}

	public void setJumpType(Integer jumpType) {
		this.jumpType = jumpType;
	}

	public Long getJumpId() {
		return jumpId;
	}

	public void setJumpId(Long jumpId) {
		this.jumpId = jumpId;
	}

	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
