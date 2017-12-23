package com.topaiebiz.system.dict.dto;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * Description: 数据字典信息Dto
 * 
 * Author : Anthony
 * 
 * Date :2017年9月25日 下午8:49:38
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_sys_data_dict")
public class DataDictDto {

	/** 数据字典类型的全局唯一主键标识符。本字段是业务无关性的，仅用于关联 。 */
	private Long id;

	@NotNull(message = "{validation.dictionary.typeId}")
	/** 数据字典类型 。 */
	private Long typeId;

	@NotNull(message = "{validation.dictionary.dictCode}")
	/** 数据字典的编码 。 */
	private String dictCode;

	@NotNull(message = "{validation.dictionary.dictValue}")
	/** 数据字典的值。 */
	private String dictValue;
    
	/*@NotNull(message = "{validation.dictionary.memo}")*/
	/** 数据字典的说明 */
	private String memo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
