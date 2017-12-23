package com.topaiebiz.decorate.dto;

import javax.validation.constraints.NotNull;

/***
 * Description： 模版信息DTO
 * 
 * Author Aaron.Xue 
 *    
 * Date 2017年11月18日 下午2:21:02 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class TemeplateInfoDto {
	
	/**主键ID*/
	@NotNull(message = "{validation.collocate.temeplateInfoId}")
	private Long id;

	/** '模版名称。' */
	private String templateName;

	/** '模版描述。' */
	private String description;

	@NotNull(message = "{validation.collocate.isOpen}")
	/** '是否启用。1启用 0未启用' */
	private String isOpen;

	/** '备注。' */
	private String memo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
