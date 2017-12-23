/**
 * 
 */
package com.topaiebiz.system.district.dto;

import javax.validation.constraints.NotNull;


/**  
 * Description： 区域 数据Dto
 * 
 * 
 * Author hxpeng 
 *    
 * Date 2017年10月19日 下午2:50:40 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

public class DistrictTypeDto {

	private Long id;
	
	/**
	 * 区域类型信息的全局唯一主键标识符。
	 */
	@NotNull(message = "{validation.district.type.code}")
	private String code;

	/**
	 * 区域类型的名称。
	 */
	private String name;

	/**
	 * 区域类型的父类型的编号。注意：取值自父类型的全局唯一主键。
	 */
	private Long parentTypeId;

	/**
	 * 区域类型的父类型的名称。注意：取值自父类型的名称。冗余字段，方便显示。
	 */
	private String parentTypeName;
	
	/**
	 * 区域类型的序列号。由父类型序列号+“.”+当前类型的全局唯一主键构成。冗余字段，方便查询。
	 */
	private String serialNo;
	
	/**
	 * 区域类型的序列名。由父类型序列名+“ -> ”+当前类型的名称构成。冗余字段，方便显示。
	 */
	private String serialName;
	
	/**
	 * 区域类型的描述信息。
	 */
	private String description;
	
	/**
	 * 备注。用于备注其他信息。
	 */
	private String memo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentTypeId() {
		return parentTypeId;
	}

	public void setParentTypeId(Long parentTypeId) {
		this.parentTypeId = parentTypeId;
	}

	public String getParentTypeName() {
		return parentTypeName;
	}

	public void setParentTypeName(String parentTypeName) {
		this.parentTypeName = parentTypeName;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getSerialName() {
		return serialName;
	}

	public void setSerialName(String serialName) {
		this.serialName = serialName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
	
	
}
