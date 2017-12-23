package com.topaiebiz.system.dict.dto;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * Description: 数据字典类型表。
 * 
 * Author : Anthony
 * 
 * Date :2017年9月23日 下午9:57:17
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_sys_data_dict_type")
public class DataDictTypeDto {

	/** 数据字典类型的全局唯一主键标识符。本字段是业务无关性的，仅用于关联 。 */
	private Long id;

	@NotNull(message = "{validation.dictionaryType.typeCode}")
	/** 数据字典类型的唯一编码。本字段是从业务角度考虑的，相当于全局的唯一业务主键。 */
	private String typeCode;
    
	@NotNull(message = "{validation.dictionaryType.typeName}")
	/** 数据字典类型的名称 。 */
	private String typeName;

	/** 数据字典类型的说明 。 */
/*	@NotNull(message = "{validation.dictionaryType.description}")*/
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
