/**
 * 
 */
package com.topaiebiz.system.district.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * Description： TODO (描述Java类的用途或使用说明。)
 * 
 * 
 * Author hxpeng
 * 
 * Date 2017年10月19日 上午11:57:05
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@TableName("t_sys_district_type")
public class DistrictTypeEntity extends BaseBizEntity<Long> {

	/**
	 * 版本序列号
	 */
	private static final long serialVersionUID = -664458548096308833L;

	/**
	 * 区域类型信息的全局唯一主键标识符。
	 */
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
